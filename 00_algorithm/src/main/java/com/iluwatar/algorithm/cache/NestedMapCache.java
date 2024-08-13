package com.iluwatar.algorithm.cache;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NestedMapCache<K1, K2, E> {

    // Function để lấy hoặc tải bản đồ con (Map<K2, E>) tương ứng với key1
    private final Function<K1, Map<K2, E>> getChildCache;

    // Bản đồ chính lưu trữ dữ liệu theo cặp key1 -> Map<K2, E>
    private final Map<K1, Map<K2, E>> nestedMap;

    // Constructor này tạo NestedMapCache với một bản đồ đã có sẵn
    private NestedMapCache(Map<K1, Map<K2, E>> nestedMap) {
        this.nestedMap = nestedMap;
        // Gán getChildCache để lấy bản đồ con hoặc trả về một Map rỗng nếu không có key1
        this.getChildCache = key1 -> nestedMap.getOrDefault(key1, Collections.emptyMap());
    }

    // Constructor này tạo NestedMapCache với một Function để tải bản đồ con khi cần thiết
    private NestedMapCache(Function<K1, Map<K2, E>> loadChildCache) {
        this.nestedMap = new ConcurrentHashMap<>(); // Sử dụng ConcurrentHashMap để hỗ trợ đa luồng
        // Gán getChildCache để tải và thêm bản đồ con nếu không có key1
        this.getChildCache = key1 -> nestedMap.computeIfAbsent(key1, loadChildCache);
    }

    /**
     * Tạo một NestedMapCache không có tự động tải, cần phải preload dữ liệu rõ ràng.
     *
     * @return Một instance của NestedMapCache với chế độ tải dữ liệu thủ công
     */
    public static <K1, K2, E> NestedMapCache<K1, K2, E> manualLoading() {
        return new NestedMapCache<>(
            key1 -> new ConcurrentHashMap<>()); // Tạo cache con trống khi cần thiết
    }

    /**
     * Tạo một NestedMapCache tự động tải dữ liệu khi cần thiết.
     *
     * @param loadChildCache Function để tải bản đồ con khi key1 không có trong cache
     * @return Một instance của NestedMapCache với chế độ tải dữ liệu tự động
     */
    public static <K1, K2, E> NestedMapCache<K1, K2, E> incremental(
        Function<K1, Map<K2, E>> loadChildCache) {

        return new NestedMapCache<>(loadChildCache); // Gọi constructor với chức năng tự động tải
    }

    /**
     * Tạo một NestedMapCache tự động tải dữ liệu khi cần thiết, nhưng sử dụng Stream<E>.
     *
     * @param loadChildCache Function để tải một Stream dữ liệu từ key1
     * @param childKeyMapper Function để ánh xạ mỗi phần tử E trong Stream thành key2
     * @return Một instance của NestedMapCache với chế độ tải dữ liệu tự động từ Stream
     */
    public static <K1, K2, E> NestedMapCache<K1, K2, E> incremental(
        Function<K1, Stream<E>> loadChildCache,
        Function<E, K2> childKeyMapper) {

        // Chuyển Stream<E> thành Map<K2, E> và gọi constructor
        return new NestedMapCache<>(key1 -> loadChildCache.apply(key1)
            .collect(Collectors.toMap(childKeyMapper, e -> e)));
    }

    /**
     * Tạo một NestedMapCache đã được tải sẵn toàn bộ dữ liệu.
     *
     * @param preloadedAllData Bản đồ dữ liệu đã được tải sẵn
     * @return Một instance của NestedMapCache với dữ liệu được tải sẵn
     */
    public static <K1, K2, E> NestedMapCache<K1, K2, E> preloadedAll(
        Map<K1, Map<K2, E>> preloadedAllData) {

        return new NestedMapCache<>(preloadedAllData); // Gọi constructor với dữ liệu đã có sẵn
    }

    /**
     * Tạo một NestedMapCache đã được tải sẵn toàn bộ dữ liệu từ một Stream.
     *
     * @param source     Stream của dữ liệu E
     * @param key1Mapper Function để ánh xạ mỗi phần tử E thành key1
     * @param key2Mapper Function để ánh xạ mỗi phần tử E thành key2
     * @return Một instance của NestedMapCache với dữ liệu được tải sẵn từ Stream
     */
    public static <K1, K2, E> NestedMapCache<K1, K2, E> preloadedAll(
        Stream<E> source,
        Function<E, K1> key1Mapper,
        Function<E, K2> key2Mapper) {

        // Chuyển đổi Stream thành bản đồ lồng nhau và gọi constructor
        return new NestedMapCache<>(toNestedMap(source, key1Mapper, key2Mapper));
    }

    // Hàm trợ giúp để chuyển đổi Stream thành bản đồ lồng nhau
    private static <K1, K2, E> Map<K1, Map<K2, E>> toNestedMap(
        Stream<E> source,
        Function<E, K1> key1Mapper,
        Function<E, K2> key2Mappter) {

        return source.collect(Collectors.groupingBy(
            key1Mapper, // Nhóm theo key1
            Collectors.toMap(key2Mappter, e -> e) // Sau đó tạo Map<K2, E> cho mỗi key1
        ));
    }

    // Phương thức preload dữ liệu vào cache với Map<K2, E> đã có sẵn
    public void preload(K1 key1, Map<K2, E> preloadedData) {
        nestedMap.put(key1, Collections.unmodifiableMap(
            preloadedData)); // Đảm bảo dữ liệu là bất biến sau khi preload
    }

    // Phương thức preload dữ liệu vào cache từ một Stream
    public void preload(K1 key1, Stream<E> preloadedDataStream, Function<E, K2> keyMap) {
        preload(key1, preloadedDataStream.collect(
            Collectors.toMap(keyMap, e -> e))); // Chuyển Stream thành Map và preload
    }

    // Phương thức preload dữ liệu vào cache với một phần tử duy nhất
    public void preload(K1 key1, K2 key2, E preloadedData) {
        nestedMap.computeIfAbsent(key1, k1 -> new ConcurrentHashMap<>())
            .put(key2, preloadedData); // Nếu chưa có Map con thì tạo mới, sau đó thêm dữ liệu
    }

    // Phương thức lấy dữ liệu từ cache, trả về Optional để xử lý trường hợp không tìm thấy
    public Optional<E> get(K1 key1, K2 key2) {
        return Optional.ofNullable(
            getChildCache.apply(key1).get(key2)); // Lấy Map con và tìm kiếm theo key2
    }

}

