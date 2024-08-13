package com.iluwatar.algorithm.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class EmployeeCache<K, T> {

    // BulkLoaderContainer dùng để quản lý và lưu trữ bộ tải dữ liệu liên quan đến một employee ID và danh sách keys cụ thể.
    private final BulkLoaderContainer<K, T> bulkLoaderContainer = new BulkLoaderContainer<>();

    // NestedMapCache là một bộ nhớ cache sử dụng cấu trúc map lồng nhau, trong đó mỗi phần tử được lưu trữ theo employee ID và danh sách keys.
    // Nó được khởi tạo dưới dạng "manual loading" (việc tải dữ liệu vào cache không được thực hiện tự động mà cần phải được điều khiển thủ công).
    private final NestedMapCache<String, K, List<T>> cached = NestedMapCache.manualLoading();

    // Function dùng để lấy employee ID từ đối tượng T.
    private final Function<T, String> getEmployeeId;

    // Function dùng để lấy key từ đối tượng T.
    private final Function<T, K> getKey;

    // CachingDataLoader là một bộ tải dữ liệu sử dụng cache, được khởi tạo bằng cách truyền vào một function để tải dữ liệu thủ công.
    private final CachingDataLoader<K, T> manualLoader;

    // Constructor khởi tạo class với các function để lấy employee ID, key và bộ tải dữ liệu thủ công.
    protected EmployeeCache(
        Function<T, String> getEmployeeId,
        Function<T, K> getKey,
        BiFunction<String, K, List<T>> manualLoad
    ) {
        this.getEmployeeId = getEmployeeId;
        this.getKey = getKey;
        this.manualLoader = CachingDataLoader.manualLoader(manualLoad);
    }


    // Phương thức này trả về một danh sách các đối tượng T dựa trên employee ID và danh sách keys, nếu không tìm thấy sẽ trả về một danh sách rỗng.
    public List<T> getList(String employeeId, K key) {
        return this.get(employeeId, key).orElse(Collections.emptyList());
    }

    // Phương thức này trả về đối tượng đầu tiên trong danh sách kết quả, nếu có.
    public Optional<T> getFirst(String employeeId, K key) {
        return this.getList(employeeId, key).stream().findFirst();
    }

    // Phương thức này trả về một Optional chứa danh sách các đối tượng T dựa trên employee ID và danh sách keys.
    private Optional<List<T>> get(String employeeId, K key) {
        // Lấy danh sách các bộ tải dữ liệu từ BulkLoaderContainer cho employee ID và keys cụ thể.
        List<CachingDataLoader<K, T>> bulkLoaders = this.bulkLoaderContainer.getLoader(employeeId, key);

        // Nếu không có bộ tải dữ liệu nào, thì sử dụng bộ tải dữ liệu thủ công để nạp dữ liệu vào cache.
        if (bulkLoaders.isEmpty()) {
            this.caching(employeeId, key, manualLoader);
        }

        // Nếu có bộ tải dữ liệu, thực hiện caching dữ liệu từ từng loader.
        bulkLoaders.forEach(loader -> this.caching(employeeId, key, loader));

        // Trả về kết quả đã được cache.
        return cached.get(employeeId, key);
    }

    // Phương thức này xóa cache liên quan đến employee ID cụ thể.
    public void clearCache(String employeeId) {
        this.cached.preload(employeeId, Collections.emptyMap());
    }

    // Phương thức này nạp dữ liệu vào cache từ CachingDataLoader cho một employee ID và danh sách keys cụ thể.
    private void caching(String employeeId, K key, CachingDataLoader<K, T> loader) {
        loader.load(employeeId, key, getEmployeeId, getKey).forEach((empId, keyMap) -> {
            keyMap.forEach((k, domains) -> this.cached.preload(empId, k, domains));
        });
    }

    // New method to handle loading data for a list of lists of keys
    public Map<K, List<T>> getListForKeys(String employeeId, List<K> keysList) {
        Map<K, List<T>> resultMap = new ConcurrentHashMap<>();
        List<K> keysToLoad = new ArrayList<>();

        // Check if the keys are already in the cache
        for (K key : keysList) {
            Optional<List<T>> cachedData = this.get(employeeId, key);
            if (cachedData.isPresent()) {
                resultMap.put(key, cachedData.get());
            } else {
                keysToLoad.add(key);
            }
        }

        // If there are keys not in the cache, load them
        if (!keysToLoad.isEmpty()) {
            this.loadAndCacheData(employeeId, keysToLoad, resultMap);
        }

        return resultMap;
    }

    // New private method to load and cache data for a list of lists of keys
    private void loadAndCacheData(String employeeId, List<K> keysToLoad, Map<K, List<T>> resultMap) {
        for (K key : keysToLoad) {
            this.caching(employeeId, key, manualLoader);
            resultMap.put(key, this.getList(employeeId, key));
        }
    }
}
