Trong Spring, các annotation như `@Configuration`, `@Component`, `@Service`, `@Repository`, `@Controller`, và `@Autowired` có các vai trò khác nhau và giúp tổ chức các thành phần trong ứng dụng một cách rõ ràng và hiệu quả. Dưới đây là mô tả chi tiết về từng annotation:

### `@Configuration`

- **Mục đích**: Đánh dấu một lớp chứa các định nghĩa bean và là nguồn định nghĩa bean cho ngữ cảnh ứng dụng. Nó được sử dụng để định nghĩa cấu hình cho các bean trong ứng dụng theo kiểu cấu hình Java.
- **Sử dụng**: Thường được dùng để định nghĩa các bean và cấu hình phụ thuộc của chúng. Các phương thức được chú thích bằng `@Bean` trong một lớp `@Configuration` sẽ định nghĩa các bean riêng lẻ.

**Ví dụ**:
```java
@Configuration
public class AppConfig {
    
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

### `@Component`

- **Mục đích**: Là một annotation chung cho các thành phần quản lý bởi Spring. Đây là annotation cơ bản cho các annotation cụ thể hơn như `@Service`, `@Repository`, và `@Controller`.
- **Sử dụng**: Khi một lớp được chú thích bằng `@Component`, nó sẽ được tự động phát hiện trong quá trình quét lớp và đăng ký như một bean của Spring.

**Ví dụ**:
```java
@Component
public class MyComponent {
    // Nội dung của lớp
}
```

### `@Service`, `@Repository`, và `@Controller`

- **Mục đích**: Là các dạng chuyên biệt của `@Component`, mỗi loại phục vụ một vai trò cụ thể:
    - `@Service`: Chỉ ra rằng lớp thực hiện vai trò của lớp dịch vụ trong ứng dụng. Đây là một loại đặc biệt của `@Component`.
    - `@Repository`: Đánh dấu lớp là lớp truy cập dữ liệu, thường được sử dụng để quản lý các lớp truy cập cơ sở dữ liệu. Nó cũng là một dạng của `@Component` nhưng cung cấp một số tính năng bổ sung liên quan đến quản lý dữ liệu.
    - `@Controller`: Chỉ ra rằng lớp là một controller trong ứng dụng web, chịu trách nhiệm xử lý các yêu cầu HTTP và trả về kết quả.

**Ví dụ**:
```java
@Service
public class MyService {
    // Nội dung của lớp dịch vụ
}

@Repository
public class MyRepository {
    // Nội dung của lớp repository
}

@Controller
public class MyController {
    // Nội dung của lớp controller
}
```

### `@Autowired`

- **Mục đích**: Được sử dụng để tự động tiêm (inject) các phụ thuộc vào bean. Spring sẽ tự động cung cấp các bean cần thiết vào các thuộc tính, phương thức, hoặc constructor của lớp được chú thích bằng `@Autowired`.
- **Sử dụng**: Có thể được áp dụng cho các thuộc tính, phương thức setter, hoặc constructor để tự động tiêm các phụ thuộc.

**Ví dụ**:
```java
@Component
public class MyComponent {

    @Autowired
    private MyService myService;

    // Hoặc sử dụng @Autowired cho constructor
    @Autowired
    public MyComponent(MyService myService) {
        this.myService = myService;
    }
}
```

### Tóm lại

- **`@Configuration`**: Định nghĩa cấu hình bean.
- **`@Component`**: Annotation cơ bản cho các thành phần của Spring.
- **`@Service`, `@Repository`, `@Controller`**: Các dạng chuyên biệt của `@Component` cho các vai trò khác nhau.
- **`@Autowired`**: Tự động tiêm phụ thuộc vào các bean.

Các annotation này giúp phân loại và tổ chức các lớp trong ứng dụng, làm cho mã nguồn dễ bảo trì và quản lý hơn.