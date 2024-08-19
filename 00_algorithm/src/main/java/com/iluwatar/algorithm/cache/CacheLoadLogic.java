package com.iluwatar.algorithm.cache;

import java.util.List;
import java.util.Optional;
import lombok.Value;

public interface CacheLoadLogic<K, T> {
    // Phương thức load dữ liệu dựa trên employeeId và key
    //Optional<Result<K, T>> load(String targetEmployee, K key);

    Optional<Result<K, T>> load(String targetEmployee, K key);

    // Lớp Result chứa kết quả sau khi load, bao gồm context và danh sách domain (dữ liệu)
    @Value
    class Result<K, T> {
        LoadProcessContext<K> loadedContext;  // Context đã load
        List<T> domains;  // Danh sách các đối tượng đã load
    }
}
