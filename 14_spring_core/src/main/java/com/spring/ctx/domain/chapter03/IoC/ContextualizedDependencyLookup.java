package com.spring.ctx.domain.chapter03.IoC;

import java.util.HashMap;
import java.util.Map;

/**
 * Lớp chính thực thi thiết kế Dependency Pull.
 */
public class ContextualizedDependencyLookup implements Runnable {

    public static void main(String[] args) {
        // Khởi chạy ứng dụng bằng cách thực thi interface Runnable
        var ctx = new ContextualizedDependencyLookup();
        ctx.run();
    }

    @Override
    public void run() {
        this.execute();
    }

    /**
     * Phương thức thực hiện quá trình Dependency Pull
     */
    public void execute() {
        // 1. Tạo container để lưu trữ dependency
        SimpleContainer container = new SimpleContainer();
        container.registerDependency("provider", new SimpleMessageProvider());
        // Đăng ký dependency với key "provider"

        // 2. Tạo renderer và thực hiện việc lookup (tìm nạp dependency)
        StandardOutMessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.performLookup(container);
        // Tìm nạp dependency từ container và thiết lập nó trong renderer

        // 3. Gọi phương thức render() để hiển thị thông báo
        renderer.render();
    }

    /**
     * Interface Container định nghĩa cách thức quản lý các dependency
     */
    public interface Container {
        /**
         * Lấy một dependency từ container dựa trên key
         */
        Object getDependency(String key);
    }

    /**
     * Container đơn giản lưu trữ các dependency
     */
    public class SimpleContainer implements Container {
        private final Map<String, Object> dependencies = new HashMap<>();

        /**
         * Đăng ký một dependency với key và giá trị
         */
        public void registerDependency(String key, Object dependency) {
            dependencies.put(key, dependency);
        }

        @Override
        public Object getDependency(String key) {
            // Trả về dependency dựa trên key
            return dependencies.get(key);
        }
    }

    /**
     * Interface ManagedComponent mô tả một đối tượng cần phụ thuộc
     */
    public interface ManagedComponent {
        /**
         * Thiết lập dependency bằng cách tìm nạp từ container
         */
        void performLookup(Container container);
    }

    /**
     * Interface MessageRenderer kế thừa từ ManagedComponent
     * và thêm khả năng render thông báo
     */
    public interface MessageRenderer extends ManagedComponent {
        void render();
    }

    /**
     * MessageRenderer cơ bản hiển thị thông báo ra console
     */
    public class StandardOutMessageRenderer implements MessageRenderer {

        private MessageProvider messageProvider;

        @Override
        public void performLookup(Container container) {
            // Tìm nạp dependency từ container và gán vào messageProvider
            this.messageProvider = (MessageProvider) container.getDependency("provider");
        }

        @Override
        public void render() {
            if (messageProvider == null) {
                throw new IllegalStateException("MessageProvider is not set");
                // Nếu dependency chưa được thiết lập, ném ngoại lệ
            }
            System.out.println(messageProvider.getMessage());
            // Hiển thị thông báo từ MessageProvider
        }
    }

    /**
     * Interface MessageProvider định nghĩa đối tượng cung cấp thông báo
     */
    public interface MessageProvider {
        /**
         * Trả về thông báo để hiển thị
         */
        String getMessage();
    }

    /**
     * Triển khai MessageProvider với thông báo mặc định
     */
    public class SimpleMessageProvider implements MessageProvider {
        @Override
        public String getMessage() {
            return "Hello, Dependency Lookup!";
        }
    }
}

