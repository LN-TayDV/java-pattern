package com.iluwatar.algorithm.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor  // Tạo constructor với các trường final
@Getter
public class CachingDataLoader<K, T> {
    // Logic tải dữ liệu cụ thể
    private final CacheLoadLogic<K, T> loadLogic;

    // Danh sách context đã cache
    private final List<LoadProcessContext<K>> cacheds = new ArrayList<>();

    // Phương thức tạo CachingDataLoader với logic load cụ thể
    public static <K, T> CachingDataLoader<K, T> manualLoader(BiFunction<String, K, List<T>> loadDomain) {
        // Tạo một logic load cụ thể cho một employeeId và key
        CacheLoadLogic<K, T> loadOneDomain = (employeeId, key) -> {
            CacheLoadLogic.Result<K, T> result = new CacheLoadLogic.Result<>(
                (loadEmpId, loadKey) -> employeeId.equals(loadEmpId) && loadKey.equals(key),
                loadDomain.apply(employeeId, key)
            );

            return Optional.of(result);
        };

        return new CachingDataLoader<>(loadOneDomain);
    }


    // Load dữ liệu và cache kết quả nếu chưa có
    public Map<String, Map<K, List<T>>> load(String employeeId, K key, Function<T, String> getEmployeeId, Function<T, K> getKey) {
        // Kiểm tra nếu đã cache trước đó
        if (this.cacheds.stream().anyMatch(cached -> cached.contains(employeeId, key))) {
            return Collections.emptyMap();
        }

        // Load dữ liệu, cache kết quả và trả về
        return loadLogic.load(employeeId, key).map(result -> {
            this.cacheds.add(result.getLoadedContext());
            return grouping(result.getDomains(), getEmployeeId, getKey);
        }).orElse(Collections.emptyMap());
    }


    // Gom nhóm dữ liệu theo employeeId và key
    private Map<String, Map<K, List<T>>> grouping(List<T> source, Function<T, String> getEmployeeId, Function<T, K> getKey) {
        return source.stream().collect(Collectors.groupingBy(getEmployeeId)).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                return entry.getValue().stream().collect(Collectors.groupingBy(getKey));
            }));
    }

    // Giao diện Initializer chứa logic khởi tạo một CachingDataLoader với kết quả cụ thể
    public interface Initializer<K, T> {
        Result<K, T> initialize(String employeeId);

        // Lớp Result chứa context và loader
        @Value
        class Result<K, T> {
            LoadProcessContext<K> context;
            CachingDataLoader<K, T> loader;
        }
    }
}
