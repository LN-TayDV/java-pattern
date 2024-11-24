package com.spring.ctx.domain.chapter03.IoC;

import com.spring.ctx.domain.chapter02.MessageProvider;
import java.util.HashMap;
import java.util.Map;

/**
 * 2. Contextualized Dependency Lookup
 * Mô tả: Đây là một biến thể của Dependency Pull, nhưng được dùng trong các tình huống
 * mà dependency phụ thuộc vào ngữ cảnh sử dụng.
 *
 * Cách hoạt động:
 * Spring cung cấp dependency dựa trên ngữ cảnh hoặc điều kiện cụ thể.
 * Thường sử dụng ObjectFactory hoặc Provider để trì hoãn việc khởi tạo dependency cho đến khi cần thiết
 *
 * Ưu điểm:
 * Phù hợp khi dependency có vòng đời ngắn hoặc thay đổi theo ngữ cảnh.
 *
 * Nhược điểm:
 * Tăng độ phức tạp khi phải quản lý ngữ cảnh thủ công.
 */

public class ContextualizedDependencyLookup implements Runnable {

    public static void main(String[] args) {
        var ctx = new ContextualizedDependencyLookup();
        ctx.run();;
    }

    public void exe() {
        // 1. Tạo container và đăng ký dependency
        SimpleContainer container = new SimpleContainer();
        container.registerDependency("provider", new SimpleMessageProvider());

        // 2. Tạo renderer và thực hiện lookup
        StandardOutMessageRenderer renderer = new ContextualizedDependencyLookup().new StandardOutMessageRenderer();
        renderer.performLookup(container);

        // 3. Gọi render để in message
        renderer.render();
    }

    @Override
    public void run() {
        this.exe();
    }

    class StandardOutMessageRenderer implements MessageRenderer {

        private MessageProvider messageProvider;

        @Override
        public void performLookup(Container container) {
            this.messageProvider = (MessageProvider) container.getDependency("provider");
        }

        @Override
        public void render() {
            if (messageProvider == null) {
                throw new IllegalStateException("MessageProvider is not set");
            }
            System.out.println(messageProvider.getMessage());
        }
    }

    public class SimpleMessageProvider implements MessageProvider {
        @Override
        public String getMessage() {
            return "Hello, Dependency Lookup!";
        }
    }

    interface MessageRenderer extends ManagedComponent {
        void render();
    }

    public interface ManagedComponent {
        void performLookup(Container container);
    }

    public interface Container {
        Object getDependency(String key);
    }

    public class SimpleContainer implements Container {
        private final Map<String, Object> dependencies = new HashMap<>();

        public void registerDependency(String key, Object dependency) {
            dependencies.put(key, dependency);
        }

        @Override
        public Object getDependency(String key) {
            return dependencies.get(key);
        }
    }
}
