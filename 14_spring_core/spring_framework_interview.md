Dưới đây là tài liệu đã được format lại với các câu trả lời ngắn gọn và xuống dòng khi cần thiết để dễ đọc hơn:

```markdown
# 📘 Tài liệu Phỏng vấn Spring Boot - Câu trả lời chi tiết

Danh sách các câu hỏi phỏng vấn Spring Boot cùng với phân tích và trả lời chi tiết bằng tiếng Việt, được chia theo các cấp độ và giải thích sự khác nhau giữa các khái niệm.

---

## 🟢 Cấp độ 1 - Cơ bản

### ➡️ **1. Spring Boot là gì?**

Spring Boot là một framework được xây dựng dựa trên Spring Framework, 
giúp phát triển ứng dụng Java nhanh chóng và đơn giản hơn. 

Nó giúp giảm thiểu cấu hình thủ công, cung cấp cấu hình tự động (auto-configuration), 
tích hợp server nhúng (embedded server), và hỗ trợ các tính năng như monitoring, logging dễ dàng.

### ➡️ **2. Sự khác biệt giữa Spring và Spring Boot?**

| Tiêu chí            | Spring Framework         | Spring Boot                            |
|---------------------|--------------------------|----------------------------------------|
| Cấu hình            | Cần cấu hình thủ công    | Cấu hình tự động (auto-configuration)   |
| Triển khai          | Cần build thành WAR      | Chạy trực tiếp với JAR và sử dụng embedded server như Tomcat, Jetty |
| Quản lý dependencies| Cần khai báo từng dependency | Sử dụng các Starter POMs với cấu hình sẵn |

**So sánh**: Spring yêu cầu người dùng cấu hình nhiều thứ từ đầu, trong khi Spring Boot tối giản, 
giúp việc cấu hình và triển khai nhanh chóng hơn.

### ➡️ **3. Các ưu điểm chính của Spring Boot?**

- **Auto-configuration**: Tự động cấu hình các thư viện có sẵn.
- **Embedded Server**: Không cần phải triển khai trên một server bên ngoài như Tomcat.
- **Spring Initializr**: Giúp tạo dự án nhanh chóng, lựa chọn các dependency cần thiết.
- **Cấu hình dễ dàng**: Có thể cấu hình ứng dụng qua file `application.properties` hoặc `application.yml`.
- **Hỗ trợ nhanh chóng cho RESTful API**.

### ➡️ **4. Starter trong Spring Boot là gì?**

Starter là các module trong Spring Boot cung cấp các dependency sẵn có cho các tính năng cụ thể. 
Ví dụ: `spring-boot-starter-web` cho ứng dụng Web, `spring-boot-starter-data-jpa` cho ứng dụng sử dụng JPA.

### ➡️ **5. Spring Initializr là gì?**

Spring Initializr là công cụ trực tuyến giúp tạo nhanh một dự án Spring Boot với các cấu hình 
và dependency cần thiết chỉ trong vài bước.

Bạn có thể truy cập tại [Spring Initializr](https://start.spring.io).

### ➡️ **6. `@SpringBootApplication` chứa các annotation nào?**

`@SpringBootApplication` là sự kết hợp của ba annotation:

- **`@Configuration`**: Đánh dấu class là nguồn cấu hình cho Spring.
- **`@EnableAutoConfiguration`**: Kích hoạt khả năng tự động cấu hình của Spring Boot.
- **`@ComponentScan`**: Tự động quét các package và tìm kiếm các bean được đánh dấu 
  với `@Component`, `@Service`, `@Repository`,...

### ➡️ **7. Cấu hình tự động (Auto-Configuration) là gì?**

Cấu hình tự động trong Spring Boot giúp tự động cấu hình các thành phần của ứng dụng dựa trên
các thư viện có sẵn trong classpath. Điều này giúp lập trình viên không phải cấu hình thủ công nhiều thứ.

### ➡️ **8. Spring Boot Actuator là gì?**

Spring Boot Actuator cung cấp các tính năng sẵn có để giám sát và quản lý ứng dụng Spring Boot,
bao gồm các endpoint như `/actuator/health` (kiểm tra sức khỏe) và `/actuator/metrics` (thống kê hiệu suất).

### ➡️ **9. Spring Boot DevTools là gì?**

Spring Boot DevTools giúp tăng tốc quá trình phát triển ứng dụng bằng cách cung cấp 
tính năng tự động làm mới (auto-restart), LiveReload, và cấu hình cho việc phát triển nhanh chóng.

### ➡️ **10. Sự khác biệt giữa `application.properties` và `application.yml`?**

| Tính năng              | `application.properties`  | `application.yml`         |
|------------------------|---------------------------|---------------------------|
| Cú pháp                | Key-Value                 | Cấu trúc dữ liệu theo định dạng YAML |
| Đọc và quản lý         | Đơn giản, dễ đọc          | Cấu trúc rõ ràng hơn cho dữ liệu lồng nhau |

**Lựa chọn**: `application.properties` đơn giản và dễ sử dụng cho cấu hình đơn giản, 
trong khi `application.yml` giúp quản lý cấu hình phức tạp hơn.

### ➡️ **11. Cách đọc giá trị từ cấu hình trong Spring Boot?**

Có thể sử dụng annotation `@Value` hoặc `@ConfigurationProperties` để đọc các giá trị cấu hình trong Spring Boot.

### ➡️ **12. `@Value` và `@ConfigurationProperties` khác nhau thế nào?**

| Tiêu chí                | `@Value`                   | `@ConfigurationProperties`            |
|-------------------------|----------------------------|--------------------------------------|
| Đơn giản                | Dễ sử dụng cho giá trị đơn | Phù hợp cho nhiều giá trị cấu hình   |
| Kiểm tra và xác thực    | Không hỗ trợ               | Hỗ trợ thông qua `@Validated`        |

**Chọn `@Value`** khi chỉ cần lấy một giá trị đơn, 
còn `@ConfigurationProperties` thích hợp hơn cho việc quản lý nhiều giá trị cấu hình phức tạp.

### ➡️ **13. `@RestController` và `@Controller` khác nhau thế nào?**

- **`@Controller`**: Sử dụng trong ứng dụng MVC, trả về các view.
- **`@RestController`**: Kết hợp giữa `@Controller` và `@ResponseBody`,
  trả về dữ liệu trực tiếp dưới dạng JSON hoặc XML mà không cần view.

**So sánh**: `@RestController` thường được dùng trong các ứng dụng RESTful API.

### ➡️ **14. `@Component` vs `@Service` vs `@Repository` vs `@Bean`?**

| Annotation      | Mục đích                               | Lưu ý                                      |
|-----------------|---------------------------------------|--------------------------------------------|
| `@Component`    | Đánh dấu bean chung                    | Được sử dụng cho các lớp chung trong ứng dụng |
| `@Service`      | Đánh dấu lớp dịch vụ (service layer)    | Thường dùng cho các lớp xử lý nghiệp vụ     |
| `@Repository`   | Đánh dấu lớp DAO (Data Access Object)  | Thường dùng trong lớp truy cập dữ liệu     |
| `@Bean`         | Định nghĩa bean thủ công trong `@Configuration` | Dùng khi muốn định nghĩa bean ngoài annotations tự động |

### ➡️ **15. Spring Profiles là gì?**

Spring Profiles cho phép bạn định nghĩa các cấu hình khác nhau cho các môi trường khác nhau 
như phát triển (dev), kiểm thử (test) và sản xuất (prod). 

Ví dụ, bạn có thể kích hoạt profile `dev` bằng cách cấu hình trong `application.properties`: `spring.profiles.active=dev`.

---

## 🟡 Cấp độ 2 - Trung cấp (Mở rộng)

### ➡️ **16. Constructor Injection và Field Injection khác nhau thế nào?**

**Mở rộng câu trả lời:**

- **Constructor Injection** yêu cầu các dependency được truyền vào thông qua constructor của lớp. Điều này đảm bảo rằng đối tượng được khởi tạo luôn có đầy đủ các dependency cần thiết và không thể thay đổi sau khi khởi tạo (immutability). Nó cũng giúp phát hiện lỗi sớm trong quá trình khởi tạo nếu thiếu dependency.
    - **Ví dụ thực tế**:
      ```java
      @Service
      public class UserService {
          private final UserRepository userRepository;
  
          public UserService(UserRepository userRepository) {
              this.userRepository = userRepository;
          }
  
          public User findUser(Long id) {
              return userRepository.findById(id).orElse(null);
          }
      }
      ```
      Trong ví dụ này, `UserService` yêu cầu `UserRepository` ngay từ khi khởi tạo, giúp đảm bảo tính hợp lệ của đối tượng.

- **Field Injection** sử dụng `@Autowired` trực tiếp trên các trường (field) của lớp. Mặc dù cú pháp ngắn gọn, nhưng nó làm cho dependency không rõ ràng và khó kiểm thử vì không có cách nào để truyền dependency thủ công trong unit test.
    - **Ví dụ thực tế**:
      ```java
      @Service
      public class UserService {
          @Autowired
          private UserRepository userRepository;
  
          public User findUser(Long id) {
              return userRepository.findById(id).orElse(null);
          }
      }
      ```
      Trong trường hợp này, `UserRepository` được inject tự động, nhưng bạn sẽ gặp khó khăn khi mock `UserRepository` trong unit test mà không sử dụng các framework như Mockito.

- **So sánh chi tiết**:
  | Tiêu chí                | Constructor Injection                      | Field Injection                         |
  |-------------------------|--------------------------------------------|-----------------------------------------|
  | **Tính bất biến (Immutability)** | Hỗ trợ (có thể sử dụng `final`)            | Không hỗ trợ                            |
  | **Kiểm thử (Testability)** | Dễ dàng mock hoặc inject thủ công          | Phụ thuộc vào framework như Mockito     |
  | **Khả năng phát hiện lỗi** | Phát hiện lỗi ngay khi khởi tạo bean       | Có thể chạy mà không phát hiện lỗi      |
  | **Tính rõ ràng**         | Rõ ràng về dependency ngay trong constructor | Dependency ẩn trong code                |

- **Ứng dụng thực tế**:
    - **Constructor Injection** thường được sử dụng trong các dự án lớn, nơi tính bảo trì và khả năng kiểm thử là ưu tiên hàng đầu. Ví dụ, trong một hệ thống thương mại điện tử, bạn có thể sử dụng Constructor Injection để inject các service như `PaymentService` hoặc `OrderRepository` vào `OrderService`, đảm bảo rằng tất cả các dependency đều được cung cấp trước khi xử lý đơn hàng.
    - **Field Injection** đôi khi được dùng trong các dự án nhỏ hoặc prototype vì cú pháp đơn giản, nhưng nó không được khuyến khích trong các hệ thống sản xuất vì khó bảo trì và kiểm thử.
    - Trong thực tế, các công cụ như **Spring Boot Test** hoặc **Mockito** thường được sử dụng để kiểm thử các lớp sử dụng Constructor Injection, giúp đảm bảo tính đúng đắn của logic nghiệp vụ.

- **Mẹo**:
    - Luôn ưu tiên **Constructor Injection** trong các dự án thực tế để đảm bảo tính bất biến và dễ dàng kiểm thử.
    - Nếu bạn cần Field Injection để giảm boilerplate code, hãy cân nhắc sử dụng **Lombok** (với `@RequiredArgsConstructor`) để tự động tạo constructor mà vẫn giữ được tính gọn gàng.

---

### ➡️ **17. Dependency Injection trong Spring hoạt động như thế nào?**

**Mở rộng câu trả lời:**

- **Dependency Injection (DI)** là một mẫu thiết kế trong Spring, nơi các dependency của một đối tượng được cung cấp từ bên ngoài thay vì tự tạo bên trong đối tượng đó. Spring quản lý các dependency thông qua **Spring Container** (hay còn gọi là **ApplicationContext**), chịu trách nhiệm khởi tạo, cấu hình, và cung cấp các bean cho ứng dụng.
    - **Quy trình hoạt động**:
        1. Spring quét các lớp được đánh dấu bằng `@Component`, `@Service`, `@Repository`, hoặc các annotation tương tự.
        2. Các bean được đăng ký vào ApplicationContext.
        3. Khi một bean yêu cầu dependency (thông qua `@Autowired`, constructor, hoặc setter), Spring tìm kiếm bean phù hợp trong ApplicationContext và inject nó vào.
    - **Ví dụ thực tế**:
      ```java
      @Service
      public class OrderService {
          private final PaymentService paymentService;
  
          @Autowired
          public OrderService(PaymentService paymentService) {
              this.paymentService = paymentService;
          }
  
          public void processOrder(Order order) {
              paymentService.processPayment(order.getAmount());
          }
      }
      ```
      Trong ví dụ này, `PaymentService` được inject vào `OrderService` thông qua constructor.

- **Các loại Dependency Injection**:
    - **Constructor Injection**: Như đã giải thích ở trên.
    - **Setter Injection**: Inject thông qua phương thức setter.
      ```java
      @Service
      public class OrderService {
          private PaymentService paymentService;
  
          @Autowired
          public void setPaymentService(PaymentService paymentService) {
              this.paymentService = paymentService;
          }
      }
      ```
    - **Field Injection**: Inject trực tiếp vào trường (ít khuyến khích).

- **Ứng dụng thực tế**:
    - **Trong hệ thống thương mại điện tử**: DI được sử dụng để quản lý các service như `CartService`, `InventoryService`, và `PaymentService`. Ví dụ, `CartService` có thể cần `InventoryService` để kiểm tra số lượng hàng tồn kho trước khi thêm sản phẩm vào giỏ hàng.
    - **Trong ứng dụng tài chính**: DI giúp inject các dịch vụ như `TransactionService` hoặc `AuditService` để xử lý giao dịch và ghi log, đảm bảo tính mô-đun và dễ mở rộng.
    - **Trong microservices**: DI được sử dụng để inject các client (như `RestTemplate` hoặc `WebClient`) để giao tiếp giữa các dịch vụ.

- **Mẹo**:
    - Sử dụng `@Qualifier` khi có nhiều bean cùng loại để chỉ định rõ bean cần inject.
    - Để tối ưu hiệu suất, hãy giảm số lượng bean được quét bằng cách giới hạn phạm vi của `@ComponentScan`.
    - Trong các dự án lớn, hãy tổ chức các dependency thành các module riêng biệt để dễ quản lý.

---

### ➡️ **18. Spring Boot hỗ trợ các cơ sở dữ liệu như thế nào?**

**Mở rộng câu trả lời:**

- Spring Boot hỗ trợ kết nối và làm việc với nhiều loại cơ sở dữ liệu (SQL và NoSQL) thông qua các **starter** và cấu hình đơn giản. Một số cơ chế chính:
    - **JPA/Hibernate**: Sử dụng `spring-boot-starter-data-jpa` để làm việc với các cơ sở dữ liệu quan hệ như MySQL, PostgreSQL, Oracle.
    - **JDBC**: Sử dụng `spring-boot-starter-jdbc` để thực hiện các truy vấn SQL trực tiếp.
    - **NoSQL**: Hỗ trợ MongoDB (`spring-boot-starter-data-mongodb`), Redis (`spring-boot-starter-data-redis`), và Cassandra (`spring-boot-starter-data-cassandra`).
    - **Cấu hình**:
      ```properties
      # MySQL configuration
      spring.datasource.url=jdbc:mysql://localhost:3306/mydb
      spring.datasource.username=root
      spring.datasource.password=password
      spring.jpa.hibernate.ddl-auto=update
      ```
      hoặc YAML:
      ```yaml
      spring:
        datasource:
          url: jdbc:mysql://localhost:3306/mydb
          username: root
          password: password
        jpa:
          hibernate:
            ddl-auto: update
      ```

- **Ví dụ thực tế**:
    - **Ứng dụng quản lý nhân sự**:
      ```java
      @Entity
      public class Employee {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          private String name;
          private String department;
          // Getters and setters
      }
  
      @Repository
      public interface EmployeeRepository extends JpaRepository<Employee, Long> {
          List<Employee> findByDepartment(String department);
      }
      ```
      Trong ví dụ này, Spring Boot tự động cấu hình kết nối tới MySQL và cung cấp các phương thức CRUD thông qua `JpaRepository`.

- **Ứng dụng thực tế**:
    - **Trong thương mại điện tử**: Sử dụng JPA để lưu trữ thông tin sản phẩm, đơn hàng, và khách hàng trong MySQL hoặc PostgreSQL. Redis có thể được dùng để cache danh sách sản phẩm hot nhằm giảm tải cho cơ sở dữ liệu chính.
    - **Trong ứng dụng phân tích dữ liệu**: MongoDB được sử dụng để lưu trữ dữ liệu phi cấu trúc như log hoặc dữ liệu người dùng, trong khi JPA xử lý các bảng cấu trúc như báo cáo tài chính.
    - **Hệ thống thời gian thực**: Redis được sử dụng để lưu trữ trạng thái phiên người dùng (session) hoặc dữ liệu tạm thời để tăng tốc độ truy xuất.

- **Mẹo**:
    - Sử dụng **HikariCP** (mặc định trong Spring Boot) để quản lý connection pool, tối ưu hiệu suất kết nối cơ sở dữ liệu.
    - Đối với các ứng dụng lớn, hãy cấu hình **Flyway** hoặc **Liquibase** để quản lý schema migration.
    - Khi làm việc với NoSQL, hãy cân nhắc sử dụng các thư viện như **Spring Data MongoDB** để tận dụng các tính năng như query method.

---

### ➡️ **19. Giải thích các annotation `@Entity`, `@Id`, `@GeneratedValue` trong JPA?**

**Mở rộng câu trả lời:**

- **`@Entity`**: Đánh dấu một lớp là một thực thể JPA, ánh xạ tới một bảng trong cơ sở dữ liệu. Mỗi instance của lớp đại diện cho một hàng trong bảng.
    - **Ví dụ**:
      ```java
      @Entity
      public class Product {
          @Id
          private Long id;
          private String name;
          private Double price;
          // Getters and setters
      }
      ```
      Lớp `Product` sẽ ánh xạ tới bảng `product` trong cơ sở dữ liệu.

- **`@Id`**: Đánh dấu trường là khóa chính của thực thể. Mỗi thực thể phải có ít nhất một trường được đánh dấu `@Id`.
    - **Lưu ý**: Có thể sử dụng các kiểu dữ liệu như `Long`, `String`, hoặc `UUID` tùy thuộc vào yêu cầu.

- **`@GeneratedValue`**: Chỉ định chiến lược sinh giá trị tự động cho khóa chính. Các chiến lược phổ biến:
    - `GenerationType.AUTO`: Để JPA tự chọn chiến lược phù hợp (thường là `IDENTITY` hoặc `SEQUENCE`).
    - `GenerationType.IDENTITY`: Sử dụng cột tự tăng của cơ sở dữ liệu (phổ biến với MySQL).
    - `GenerationType.SEQUENCE`: Sử dụng sequence của cơ sở dữ liệu (phổ biến với PostgreSQL, Oracle).
    - `GenerationType.TABLE`: Sử dụng bảng riêng để lưu trữ giá trị khóa chính (ít được dùng).
    - **Ví dụ**:
      ```java
      @Entity
      public class Order {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          private String orderNumber;
          // Getters and setters
      }
      ```

- **Ứng dụng thực tế**:
    - **Trong hệ thống quản lý kho**: Sử dụng `@Entity` để ánh xạ các lớp như `InventoryItem`, `@Id` để đánh dấu mã sản phẩm duy nhất, và `@GeneratedValue` để tự động tạo ID cho mỗi mục hàng mới.
    - **Trong ứng dụng đặt vé**: Một thực thể `Ticket` có thể sử dụng `@Id` với `GenerationType.SEQUENCE` để tạo mã vé duy nhất theo thứ tự tăng dần, đảm bảo không trùng lặp.
    - **Trong hệ thống tài chính**: Sử dụng `@Entity` để ánh xạ các giao dịch (`Transaction`), với `@Id` và `@GeneratedValue` để tạo mã giao dịch tự động, giúp theo dõi dễ dàng.

- **Mẹo**:
    - Luôn sử dụng `@GeneratedValue` với chiến lược phù hợp với cơ sở dữ liệu (ví dụ: `IDENTITY` cho MySQL, `SEQUENCE` cho PostgreSQL).
    - Nếu cần khóa chính phức tạp (composite key), sử dụng `@EmbeddedId` hoặc `@IdClass`.
    - Kiểm tra schema cơ sở dữ liệu để đảm bảo rằng cột khóa chính được cấu hình đúng với chiến lược đã chọn.

---

### ➡️ **20. `CrudRepository` và `JpaRepository` khác nhau thế nào?**

**Mở rộng câu trả lời:**

- **`CrudRepository`** cung cấp các phương thức cơ bản để thực hiện các thao tác CRUD (Create, Read, Update, Delete).
    - **Phương thức chính**:
        - `save(T entity)`: Lưu hoặc cập nhật thực thể.
        - `findById(ID id)`: Tìm thực thể theo ID.
        - `findAll()`: Lấy tất cả thực thể.
        - `deleteById(ID id)`: Xóa thực thể theo ID.
    - **Ví dụ**:
      ```java
      public interface UserRepository extends CrudRepository<User, Long> {
          // Không cần định nghĩa phương thức CRUD, đã có sẵn
      }
      ```

- **`JpaRepository`** mở rộng `CrudRepository` và thêm các tính năng như phân trang (pagination), sắp xếp (sorting), và các phương thức tiện ích khác.
    - **Phương thức bổ sung**:
        - `findAll(Pageable pageable)`: Lấy danh sách thực thể với phân trang.
        - `findAll(Sort sort)`: Lấy danh sách thực thể với sắp xếp.
        - `deleteAllInBatch()`: Xóa tất cả thực thể trong một lần thực thi.
    - **Ví dụ**:
      ```java
      public interface UserRepository extends JpaRepository<User, Long> {
          Page<User> findByRole(String role, Pageable pageable);
      }
      ```

- **So sánh chi tiết**:
  | Tiêu chí                | `CrudRepository`                     | `JpaRepository`                     |
  |-------------------------|--------------------------------------|-------------------------------------|
  | **Phạm vi tính năng**   | Chỉ hỗ trợ CRUD cơ bản               | Hỗ trợ CRUD + phân trang, sắp xếp   |
  | **Hiệu suất**           | Nhẹ hơn, ít phương thức hơn          | Nặng hơn do có thêm nhiều tính năng |
  | **Tính linh hoạt**      | Ít linh hoạt hơn                     | Linh hoạt hơn với các ứng dụng lớn  |

- **Ứng dụng thực tế**:
    - **Trong ứng dụng quản lý blog**: Sử dụng `CrudRepository` cho các thao tác đơn giản như thêm, sửa, xóa bài viết. Nếu cần hiển thị danh sách bài viết phân trang trên giao diện người dùng, hãy sử dụng `JpaRepository` với `findAll(Pageable pageable)`.
    - **Trong hệ thống thương mại điện tử**: `JpaRepository` được sử dụng để lấy danh sách sản phẩm với phân trang và sắp xếp theo giá hoặc lượt xem, giúp tối ưu trải nghiệm người dùng.
    - **Trong ứng dụng báo cáo**: Sử dụng `JpaRepository` để lấy dữ liệu thống kê với các tiêu chí sắp xếp và phân trang, ví dụ: danh sách giao dịch theo ngày hoặc trạng thái.

- **Mẹo**:
    - Sử dụng `CrudRepository` trong các dự án nhỏ hoặc khi chỉ cần các thao tác cơ bản để giảm chi phí tài nguyên.
    - Với `JpaRepository`, hãy cẩn thận khi sử dụng các phương thức như `findAll()` mà không có phân trang vì có thể gây tải nặng nếu bảng dữ liệu lớn.
    - Kết hợp với **Spring Data JPA Query Methods** để định nghĩa các truy vấn tùy chỉnh mà không cần viết SQL.

---

## 🔴 Cấp độ 3 - Nâng cao (Mở rộng)

### ➡️ **21. Các cách quản lý transaction trong Spring Boot?**

**Mở rộng câu trả lời:**

- **Transaction** trong Spring Boot đảm bảo tính toàn vẹn dữ liệu khi thực hiện các thao tác với cơ sở dữ liệu. Spring cung cấp hai cách chính để quản lý transaction:
    - **Annotation-based (@Transactional)**:
        - Sử dụng annotation `@Transactional` trên phương thức hoặc lớp để khai báo rằng các thao tác trong phạm vi đó sẽ được thực thi trong một transaction.
        - Các thuộc tính quan trọng:
            - `propagation`: Quy định cách transaction được lan truyền (ví dụ: `REQUIRED`, `REQUIRES_NEW`).
            - `isolation`: Quy định mức độ cách ly của transaction (ví dụ: `READ_COMMITTED`, `SERIALIZABLE`).
            - `rollbackOn`: Chỉ định các ngoại lệ gây rollback (mặc định là `RuntimeException`).
        - **Ví dụ**:
          ```java
          @Service
          public class OrderService {
              @Transactional
              public void processOrder(Order order) {
                  orderRepository.save(order);
                  paymentService.processPayment(order.getAmount());
              }
          }
          ```
          Trong ví dụ này, nếu `processPayment` thất bại, toàn bộ transaction sẽ rollback, đảm bảo `order` không được lưu.

    - **Programmatic Transaction Management**:
        - Sử dụng `TransactionTemplate` hoặc `PlatformTransactionManager` để kiểm soát transaction một cách thủ công.
        - **Ví dụ**:
          ```java
          @Service
          public class OrderService {
              private final TransactionTemplate transactionTemplate;
    
              public OrderService(PlatformTransactionManager transactionManager) {
                  this.transactionTemplate = new TransactionTemplate(transactionManager);
              }
    
              public void processOrder(Order order) {
                  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                      @Override
                      protected void doInTransactionWithoutResult(TransactionStatus status) {
                          orderRepository.save(order);
                          paymentService.processPayment(order.getAmount());
                      }
                  });
              }
          }
          ```

- **Ứng dụng thực tế**:
    - **Trong hệ thống ngân hàng**: Sử dụng `@Transactional` để đảm bảo rằng khi chuyển tiền, cả hai thao tác trừ tiền từ tài khoản nguồn và cộng tiền vào tài khoản đích đều thành công, hoặc không thao tác nào được thực hiện nếu có lỗi.
    - **Trong thương mại điện tử**: Khi xử lý đơn hàng, `@Transactional` đảm bảo rằng việc lưu đơn hàng, trừ hàng tồn kho, và ghi log giao dịch được thực hiện đồng bộ. Nếu bất kỳ bước nào thất bại (ví dụ: hết hàng), toàn bộ transaction sẽ rollback.
    - **Trong hệ thống đặt lịch**: Sử dụng transaction để đảm bảo rằng khi đặt một lịch hẹn, cả trạng thái lịch và thông tin người dùng đều được cập nhật đồng thời.

- **Mẹo**:
    - Chỉ sử dụng `@Transactional` trên các phương thức public, vì Spring sử dụng proxy để quản lý transaction.
    - Cẩn thận với các transaction dài (long-running transactions) vì chúng có thể làm giảm hiệu suất cơ sở dữ liệu.
    - Sử dụng `REQUIRES_NEW` khi cần tách biệt transaction con để tránh ảnh hưởng đến transaction chính.

---

### ➡️ **22. Spring Boot hỗ trợ Asynchronous như thế nào?**

**Mở rộng câu trả lời:**

- Spring Boot hỗ trợ xử lý bất đồng bộ (asynchronous) thông qua annotation `@Async`, cho phép các phương thức chạy trong một thread riêng biệt, không chặn thread chính của ứng dụng.
    - **Cấu hình**:
        - Kích hoạt hỗ trợ async bằng annotation `@EnableAsync` trên lớp cấu hình hoặc lớp chính của ứng dụng.
        - Đánh dấu phương thức với `@Async` để chạy bất đồng bộ.
    - **Ví dụ**:
      ```java
      @SpringBootApplication
      @EnableAsync
      public class Application {
          public static void main(String[] args) {
              SpringApplication.run(Application.class, args);
          }
      }
  
      @Service
      public class NotificationService {
          @Async
          public CompletableFuture<String> sendEmail(String recipient, String message) {
              // Giả lập gửi email
              Thread.sleep(2000);
              return CompletableFuture.completedFuture("Email sent to " + recipient);
          }
      }
      ```

- **Thread Pool Configuration**:
    - Theo mặc định, Spring sử dụng `SimpleAsyncTaskExecutor`, nhưng bạn có thể tùy chỉnh thread pool:
      ```java
      @Configuration
      public class AsyncConfig implements AsyncConfigurer {
          @Override
          public Executor getAsyncExecutor() {
              ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
              executor.setCorePoolSize(5);
              executor.setMaxPoolSize(10);
              executor.setQueueCapacity(25);
              executor.initialize();
              return executor;
          }
      }
      ```

- **Ứng dụng thực tế**:
    - **Trong hệ thống thương mại điện tử**: Sử dụng `@Async` để gửi email xác nhận đơn hàng hoặc thông báo khuyến mãi mà không làm chậm quá trình đặt hàng của người dùng.
    - **Trong ứng dụng phân tích dữ liệu**: Xử lý các tác vụ nặng như tính toán thống kê hoặc xử lý log trong background để không ảnh hưởng đến giao diện người dùng.
    - **Trong hệ thống chat**: Gửi thông báo đẩy (push notification) bất đồng bộ để đảm bảo phản hồi nhanh chóng cho người dùng.

- **Mẹo**:
    - Luôn trả về `CompletableFuture` hoặc `Future` trong các phương thức `@Async` để xử lý kết quả bất đồng bộ.
    - Tránh gọi phương thức `@Async` trực tiếp trong cùng một lớp (vì Spring sử dụng proxy), hãy tách thành các service riêng.
    - Theo dõi hiệu suất của thread pool để tránh tình trạng quá tải khi có quá nhiều tác vụ bất đồng bộ.

---

### ➡️ **23. Giải thích về Bean Scopes trong Spring Boot?**

**Mở rộng câu trả lời:**

- **Bean Scopes** xác định vòng đời và phạm vi của một bean trong Spring Container. Spring Boot hỗ trợ các scope sau:
    - **Singleton** (mặc định): Chỉ một instance duy nhất của bean được tạo cho toàn bộ ứng dụng.
        - **Ví dụ**:
          ```java
          @Service
          public class SingletonService {
              // Chỉ một instance được tạo
          }
          ```
    - **Prototype**: Mỗi lần request bean, Spring tạo một instance mới.
        - **Ví dụ**:
          ```java
          @Service
          @Scope("prototype")
          public class PrototypeService {
              // Mỗi lần inject, một instance mới được tạo
          }
          ```
    - **Request**: Một instance được tạo cho mỗi HTTP request (chỉ áp dụng trong ứng dụng web).
    - **Session**: Một instance được tạo cho mỗi HTTP session.
    - **Application**: Một instance được tạo cho toàn bộ vòng đời của ứng dụng web.
    - **Websocket**: Một instance được tạo cho mỗi kết nối WebSocket.

- **Ứng dụng thực tế**:
    - **Singleton**: Phù hợp với các service hoặc repository được sử dụng chung, như `UserService` hoặc `OrderRepository` trong một hệ thống thương mại điện tử.
    - **Prototype**: Sử dụng khi cần các instance riêng biệt cho mỗi yêu cầu, ví dụ: một lớp xử lý dữ liệu tạm thời trong quá trình nhập liệu.
    - **Request/Session**: Trong ứng dụng web, sử dụng để lưu trữ thông tin người dùng trong một phiên (session) hoặc trạng thái của một yêu cầu cụ thể (request), ví dụ: giỏ hàng của người dùng.
    - **Application**: Sử dụng cho các cấu hình hoặc tài nguyên toàn cục, như cấu hình bảo mật hoặc thông tin ứng dụng.

- **Mẹo**:
    - Hạn chế sử dụng **Prototype** trong các ứng dụng lớn vì có thể gây rò rỉ bộ nhớ nếu không quản lý đúng vòng đời của bean.
    - Khi sử dụng **Request** hoặc **Session** scope, hãy đảm bảo ứng dụng chạy trong môi trường web (Spring Web hoặc Spring Boot Web).
    - Theo dõi số lượng bean trong container để tối ưu hiệu suất.

---

### ➡️ **24. Spring Boot hỗ trợ Security như thế nào?**

**Mở rộng câu trả lời:**

- Spring Boot tích hợp bảo mật thông qua **Spring Security** với starter `spring-boot-starter-security`. Nó cung cấp các tính năng như xác thực (authentication), phân quyền (authorization), bảo vệ CSRF, và hỗ trợ OAuth2/JWT.
    - **Cấu hình cơ bản**:
      ```java
      @Configuration
      @EnableWebSecurity
      public class SecurityConfig {
  
          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              http
                  .authorizeHttpRequests(auth -> auth
                      .requestMatchers("/public/**").permitAll()
                      .anyRequest().authenticated()
                  )
                  .formLogin(form -> form
                      .loginPage("/login")
                      .permitAll()
                  )
                  .logout(logout -> logout.permitAll());
              return http.build();
          }
  
          @Bean
          public UserDetailsService userDetailsService() {
              UserDetails user = User.withDefaultPasswordEncoder()
                  .username("user")
                  .password("password")
                  .roles("USER")
                  .build();
              return new InMemoryUserDetailsManager(user);
          }
      }
      ```

- **Các tính năng chính**:
    - **Xác thực**: Hỗ trợ nhiều cơ chế như form login, OAuth2, JWT, LDAP.
    - **Phân quyền**: Quy định quyền truy cập dựa trên vai trò (role) hoặc quyền (authority).
    - **Bảo mật API**: Sử dụng JWT hoặc OAuth2 để bảo vệ các RESTful API.
    - **Bảo vệ CSRF/XSS**: Spring Security tự động kích hoạt CSRF protection cho các form.

- **Ứng dụng thực tế**:
    - **Trong ứng dụng quản lý nhân sự**: Sử dụng Spring Security để phân quyền, đảm bảo chỉ admin mới có thể chỉnh sửa thông tin nhân viên, trong khi nhân viên chỉ có thể xem thông tin cá nhân.
    - **Trong hệ thống thương mại điện tử**: Kích hoạt OAuth2 để cho phép người dùng đăng nhập bằng Google hoặc Facebook, đồng thời sử dụng JWT để bảo vệ các API như `/api/orders`.
    - **Trong ứng dụng tài chính**: Sử dụng Spring Security để mã hóa mật khẩu, kiểm tra quyền truy cập vào các giao dịch nhạy cảm, và ghi log các hành động của người dùng.

- **Mẹo**:
    - Sử dụng **BCryptPasswordEncoder** để mã hóa mật khẩu thay vì các phương pháp lỗi thời như MD5 hoặc SHA.
    - Khi tích hợp OAuth2, hãy cấu hình **refresh token** để tăng cường bảo mật.
    - Kiểm tra các endpoint `/actuator` để đảm bảo chúng được bảo vệ đúng cách trong môi trường sản xuất.

---

### ➡️ **25. Giải thích về CORS trong Spring Boot?**

**Mở rộng câu trả lời:**

- **CORS (Cross-Origin Resource Sharing)** là cơ chế cho phép hoặc hạn chế các yêu cầu HTTP từ các domain khác nhau. Spring Boot hỗ trợ cấu hình CORS để đảm bảo ứng dụng web an toàn và linh hoạt khi giao tiếp với các client từ domain khác.
    - **Cấu hình với `@CrossOrigin`**:
      ```java
      @RestController
      @CrossOrigin(origins = "http://frontend.com")
      public class ProductController {
          @GetMapping("/products")
          public List<Product> getProducts() {
              return productService.findAll();
          }
      }
      ```
    - **Cấu hình toàn cục**:
      ```java
      @Configuration
      public class WebConfig implements WebMvcConfigurer {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/api/**")
                  .allowedOrigins("http://frontend.com")
                  .allowedMethods("GET", "POST")
                  .allowedHeaders("*")
                  .allowCredentials(true);
          }
      }
      ```

- **Ứng dụng thực tế**:
    - **Trong ứng dụng web**: Khi frontend chạy trên `http://localhost:3000` (React/Vue) và backend chạy trên `http://localhost:8080`, CORS được cấu hình để cho phép frontend gửi yêu cầu tới backend.
    - **Trong hệ thống microservices**: Các dịch vụ chạy trên các domain khác nhau (ví dụ: `auth-service.com` và `product-service.com`) cần CORS để giao tiếp.
    - **Trong ứng dụng đa nền tảng**: Một ứng dụng mobile gọi API từ backend Spring Boot cần cấu hình CORS để tránh lỗi "Access-Control-Allow-Origin".

- **Mẹo**:
    - Chỉ định rõ `allowedOrigins` thay vì sử dụng `*` để tăng cường bảo mật.
    - Nếu sử dụng Spring Security, hãy đảm bảo cấu hình CORS tương thích với các quy tắc bảo mật.
    - Theo dõi các lỗi CORS trong console trình duyệt để điều chỉnh cấu hình phù hợp.

---