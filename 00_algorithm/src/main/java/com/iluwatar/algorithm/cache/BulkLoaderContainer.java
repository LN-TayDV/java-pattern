package com.iluwatar.algorithm.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Value;

public class BulkLoaderContainer<K, T> {
    // Danh sách các Initializer để khởi tạo dữ liệu dựa trên employeeId
    private final List<Initializer<K, T>> initializers = new ArrayList<>();

    // Danh sách các Setting đã được tải và lưu trong cache
    private final List<Setting<K, T>> loaderCaches = new ArrayList<>();

    // Thêm một Initializer vào danh sách
    public void add(Initializer<K, T> initializer) {
        this.initializers.add(initializer);
    }

    // Lấy danh sách các CachingDataLoader dựa trên employeeId và key
    public List<CachingDataLoader<K, T>> getLoader(String employeeId, K key) {
        // Lọc những Setting đã có trong cache theo employeeId và key
        List<Setting<K, T>> target = loaderCaches.stream()
            .filter(c -> c.getContext().contains(employeeId, key))
            .toList();

        // Nếu đã có trong cache, trả về danh sách CachingDataLoader
        if (!target.isEmpty()) {
            return target.stream().map(Setting::getLoader).collect(Collectors.toList());
        }

        // Nếu chưa có, khởi tạo và thêm vào cache
        initializers.stream()
            .flatMap(init -> init.initialize(employeeId).stream())
            .forEach(this.loaderCaches::add);

        // Trả về danh sách CachingDataLoader mới sau khi đã khởi tạo
        return loaderCaches
            .stream()
            .filter(c -> c.getContext().contains(employeeId, key))
            .map(Setting::getLoader)
            .collect(Collectors.toList());
    }

    // Giao diện Initializer giúp khởi tạo Setting dựa trên employeeId
    public interface Initializer<K, T> {
        Optional<Setting<K, T>> initialize(String employeeId);
    }

    // Lớp Setting chứa context và CachingDataLoader tương ứng
    @Value
    public static class Setting<K, T> {
        LoadProcessContext<K> context;  // Bối cảnh chứa thông tin employeeId và key
        CachingDataLoader<K, T> loader;  // Loader chứa logic tải dữ liệu
    }
}

