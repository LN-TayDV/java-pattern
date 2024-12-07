Interface `ApplicationContextAware` trong Spring cho phép một bean biết và truy cập vào `ApplicationContext` – ngữ cảnh chứa tất cả các bean được Spring quản lý. Dưới đây là chi tiết về cách hoạt động và logic của nó:

### Cách Hoạt Động
1. **Triển khai `ApplicationContextAware`**:
    - Bean của bạn cần triển khai interface `ApplicationContextAware`.
    - Bạn phải override phương thức `setApplicationContext(ApplicationContext applicationContext)`.

2. **Spring Inject `ApplicationContext`**:
    - Khi Spring khởi tạo bean, nó sẽ tự động gọi `setApplicationContext` và truyền vào một instance của `ApplicationContext`.
    - Bean của bạn có thể lưu trữ instance này để sử dụng sau.

3. **Truy cập `ApplicationContext`**:
    - Với `ApplicationContext`, bạn có thể gọi phương thức `getBean` để lấy các bean khác theo chương trình.
    - Bạn cũng có thể truy cập các tính năng khác của `ApplicationContext`, như quản lý lifecycle của bean hoặc truy cập tài nguyên.

### Logic Hoạt Động
1. **Triển khai Interface**:
    ```java
    public class MyBean implements ApplicationContextAware {
        private ApplicationContext applicationContext;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

        public void doSomething() {
            // Sử dụng applicationContext để lấy bean khác
            OtherBean otherBean = applicationContext.getBean(OtherBean.class);
            otherBean.performAction();
        }
    }
    ```

2. **Cấu hình trong Spring**:
    ```java
    @Configuration
    public class AppConfig {
        
        @Bean
        public MyBean myBean() {
            return new MyBean();
        }

        @Bean
        public OtherBean otherBean() {
            return new OtherBean();
        }
    }
    ```

### Lợi ích và Hạn chế
- **Lợi ích**:
    - Hữu ích trong một số trường hợp đặc biệt, như cấu hình shutdown hooks tự động hoặc lấy bean theo điều kiện.
- **Hạn chế**:
    - Tăng độ phức tạp và làm cho bean của bạn phụ thuộc chặt chẽ vào Spring, điều này vi phạm nguyên tắc dependency injection (DI).

### Ví dụ sử dụng `ApplicationContextAware`
```java
public class ShutdownHookBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ((ConfigurableApplicationContext) applicationContext).close();
        }));
    }
}
```
Trong ví dụ này, `ShutdownHookBean` sẽ thêm một shutdown hook để đóng `ApplicationContext` khi JVM tắt, đảm bảo tất cả các singleton được Spring quản lý được hủy bỏ một cách thích hợp.

Như vậy, `ApplicationContextAware` là một công cụ mạnh mẽ nhưng cần sử dụng cẩn thận để tránh làm phức tạp mã và tăng sự phụ thuộc vào Spring.