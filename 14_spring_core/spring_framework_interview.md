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

## 🟡 Cấp độ 2 - Trung cấp

### ➡️ **16. Constructor Injection và Field Injection khác nhau thế nào?**

| Tiêu chí                | Constructor Injection        | Field Injection           |
|-------------------------|------------------------------|---------------------------|
| Kiểm tra và kiểm thử    | Dễ dàng kiểm thử             | Khó kiểm thử              |
| Immutability            | Khuyến khích sử dụng         | Không khuyến khích        |

**Constructor Injection là lựa chọn tốt hơn** do nó dễ kiểm thử và dễ duy trì.

### ➡️ **17. Dependency Injection trong Spring hoạt động như thế nào?**

Spring sử dụng các annotation như `@Autowired` hoặc cách tiêm phụ thuộc qua constructor
để inject các dependency vào các bean được quản lý bởi Spring Container (ApplicationContext).

### ➡️ **18. Spring Boot hỗ trợ các cơ sở dữ liệu như thế nào?**

Spring Boot hỗ trợ các cơ sở dữ liệu thông qua các starter như `spring-boot-starter-data-jpa` cho JPA và Hibernate.
Cấu hình kết nối được thực hiện qua file `application.properties` hoặc `application.yml`.

### ➡️ **19. Giải thích các annotation `@Entity`, `@Id`, `@GeneratedValue` trong JPA?**

- **`@Entity`**: Đánh dấu lớp là thực thể JPA.
- **`@Id`**: Đánh dấu trường là khóa chính.
- **`@GeneratedValue`**: Đánh dấu chiến lược tự động sinh giá trị khóa chính.

### ➡️ **20. `CrudRepository` và `JpaRepository` khác nhau thế nào?**

| Interface         | Tính năng                            |
|-------------------|--------------------------------------|
| `CrudRepository`  | Các thao tác CRUD cơ bản             |
| `JpaRepository`   | Mở rộng `CrudRepository` + Paging/Sorting |

---

## 🔴 Cấp độ 3 - Nâng cao

### ➡️ **21. Các cách quản lý transaction trong Spring Boot?**

Spring Boot cung cấp một số cách để quản lý transaction bao gồm sử dụng annotation `@Transactional`, 
hỗ trợ cho các thao tác commit và rollback tự động trong môi trường JPA.

### ➡️ **22. Spring Boot hỗ trợ Asynchronous như thế nào?**

Spring Boot hỗ trợ các tác vụ bất đồng bộ thông qua annotation `@Async`. 
Các method được đánh dấu với `@Async` sẽ được thực thi trong một thread riêng biệt,
giúp xử lý các công việc nặng mà không làm ảnh hưởng đến hiệu suất của ứng dụng chính.

### ➡️ **23. Giải thích về Bean Scopes trong Spring Boot?**

Spring Boot hỗ trợ nhiều loại scope cho bean, như:
- **Singleton**: Mỗi ứng dụng Spring chỉ có một instance của bean.
- **Prototype**: Mỗi lần request bean, Spring sẽ tạo một instance mới.
- **Request**, **Session**, **Application**: Liên quan đến phạm vi HTTP request, session, hoặc toàn bộ ứng dụng.

### ➡️ **24. Spring Boot hỗ trợ Security như thế nào?**

Spring Boot hỗ trợ bảo mật qua `spring-boot-starter-security`, với các tính năng như 
xác thực, phân quyền, mã hóa và hỗ trợ OAuth2. 
Cấu hình bảo mật có thể thực hiện trong class `WebSecurityConfigurerAdapter`.

### ➡️ **25. Giải thích về CORS trong Spring Boot?**

CORS (Cross-Origin Resource Sharing) cho phép các ứng dụng chạy trên các domain khác nhau giao tiếp với nhau. 
Spring Boot cung cấp cấu hình CORS thông qua annotation `@CrossOrigin` hoặc trong class cấu hình bảo mật.

---

## 🟢 Cấp độ 4 - Chuyên sâu

### ➡️ **26. Thế nào là Spring Boot Microservices?**

Spring Boot Microservices là một kiến trúc mà ứng dụng được chia thành nhiều dịch vụ nhỏ, độc lập.
Mỗi microservice có thể triển khai riêng biệt và giao tiếp qua HTTP hoặc các cơ chế khác.

### ➡️ **27. Giải thích về Eureka trong Spring Cloud?**

Eureka là một service discovery tool trong Spring Cloud, giúp các microservice tìm kiếm nhau 
và giao tiếp mà không cần biết IP và port.

### ➡️ **28. Giải thích về Hystrix trong Spring Cloud?**

Hystrix là một thư viện trong Spring Cloud, giúp bảo vệ các microservice 
khỏi tình trạng bị lỗi khi một service bị quá tải hoặc không khả dụng, 
thông qua các cơ chế như fallback và circuit breaker.

### ➡️ **29. Spring Boot và Spring Cloud có thể kết hợp như thế nào?**

Spring Boot cung cấp nền tảng phát triển ứng dụng đơn giản, 
trong khi Spring Cloud hỗ trợ các tính năng phức tạp như 
service discovery, configuration management, và load balancing cho các ứng dụng microservices.

### ➡️ **30. SPRING BOOT HỖ TRỢ TESTING NHƯ THẾ NÀO?**

Spring Boot cung cấp các annotation và công cụ như `@SpringBootTest`, `@DataJpaTest`, `@WebMvcTest` 
để kiểm thử ứng dụng ở các cấp độ khác nhau từ kiểm thử tích hợp đến kiểm thử đơn vị.

## 🟢 Cấp độ 4 - Chuyên sâu (tiếp theo)

### 31. **GIẢI THÍCH VỀ SPRING BATCH?**

Spring Batch là một framework mạnh mẽ để xử lý các công việc batch (xử lý theo lô). 
Nó cung cấp các công cụ như phân trang, xử lý lỗi, quản lý trạng thái công việc, và hỗ trợ nhiều loại dữ liệu đầu vào và đầu ra, giúp triển khai các công việc xử lý dữ liệu lớn hiệu quả.

### 32. **SPRING BOOT VÀ SPRING MVC CÓ MỐI QUAN HỆ NHƯ THẾ NÀO?**

Spring Boot và Spring MVC có mối quan hệ mật thiết, 
vì Spring Boot giúp cấu hình và triển khai Spring MVC một cách đơn giản và nhanh chóng. 
Spring Boot hỗ trợ tất cả các tính năng của Spring MVC như xử lý các request HTTP, cung cấp các ViewResolver và Controller.

### 33. **CÁC KỸ THUẬT TỐI ƯU HÓA HIỆU SUẤT TRONG SPRING BOOT?**

Một số kỹ thuật tối ưu hóa trong Spring Boot bao gồm:
- **Caching**: Sử dụng `@Cacheable` để cache kết quả của các phương thức.
- **Connection Pooling**: Sử dụng connection pooling cho cơ sở dữ liệu để tối ưu số lượng kết nối.
- **Tối ưu bộ nhớ**: Cấu hình và giảm thiểu kích thước ứng dụng.

### 34. **GIẢI THÍCH VỀ SERVER CONFIGURATION TRONG SPRING BOOT?**

Spring Boot cung cấp các cấu hình dễ dàng cho các server nhúng (embedded server). Bạn có thể tùy chỉnh các thuộc tính như cổng, thời gian chờ, bộ đệm HTTP qua file cấu hình `application.properties`.

### 35. **CÁC CÔNG CỤ DEVOPS THƯỜNG DÙNG CÙNG SPRING BOOT?**

Các công cụ DevOps như Jenkins, Docker, Kubernetes, và CI/CD pipeline thường được sử dụng trong các dự án Spring Boot để tự động hóa quy trình phát triển, kiểm thử, và triển khai.
```
