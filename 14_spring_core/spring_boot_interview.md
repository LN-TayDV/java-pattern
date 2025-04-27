Dưới đây là **bảng 4 cột** cho 10 câu hỏi đầu tiên bạn yêu cầu:

| **STT** | **Câu hỏi** | **Ý chính trả lời** | **Ghi chú thêm** |
|:------|:------------|:-------------------|:---------------|
| 1 | Spring Framework là gì? | Framework Java EE phổ biến, dùng Dependency Injection (DI) và AOP để xây dựng ứng dụng. | Hỗ trợ nhiều module: MVC, JDBC, Security,... |
| 2 | Tính năng và lợi thế của Spring? | Nhẹ, dễ test, giảm code boilerplate, hỗ trợ mạnh cho Java EE và công nghệ mới. | IoC Container quản lý vòng đời Bean. |
| 3 | Dependency Injection là gì? | Mẫu thiết kế giúp tách rời phụ thuộc, tăng khả năng mở rộng và bảo trì ứng dụng. | Hỗ trợ dễ dàng cho test unit. |
| 4 | Cách triển khai DI trong Spring? | Qua XML cấu hình hoặc dùng annotation (ví dụ `@Autowired`). | XML phù hợp cho dự án lớn, annotation cho dự án nhỏ nhanh gọn. |
| 5 | Tính năng mới trong Spring 5? | Java 8+, Spring WebFlux (lập trình phản ứng), hỗ trợ Kotlin, JUnit 5. | Thêm module spring-jcl thay thế Commons Logging. |
| 6 | Spring WebFlux là gì? | Mô-đun reactive, hỗ trợ async và non-blocking cho Spring Framework. | Xây dựng dự án hiệu năng cao, đáp ứng real-time. |
| 7 | Lợi ích của Spring Tool Suite (STS)? | Tích hợp đầy đủ công cụ hỗ trợ phát triển Spring, templates, server tích hợp sẵn. | Nổi bật hỗ trợ AOP pointcut visualization. |
| 8 | Một số module quan trọng của Spring? | Context, AOP, DAO, JDBC, ORM, Web, MVC. | Tùy dự án mà chọn module cần thiết để lightweight. |
| 9 | Lập trình hướng khía cạnh (AOP) là gì? | Quản lý các mối quan tâm xuyên suốt (như logging, transaction) tách biệt khỏi business logic. | Giúp giảm lặp code, tăng module hóa. |
| 10 | Aspect, Advice, Pointcut, JoinPoint, Advice Arguments? | Aspect: class chứa logic AOP; Advice: hành động tại JoinPoint; Pointcut: tiêu chí lọc JoinPoint; JoinPoint: điểm cụ thể (ví dụ thực thi method); Advice Arguments: tham số truyền vào advice. | Spring AOP dùng ngôn ngữ Pointcut của AspectJ. |
| 11 | Sự khác biệt giữa Spring AOP và AspectJ AOP là gì? | AspectJ là triển khai AOP đầy đủ, hỗ trợ tất cả joinpoint; Spring AOP đơn giản hơn, chỉ hỗ trợ phương thức, dùng proxy. | Spring AOP dùng AspectJ annotations nhưng không cần weaving phức tạp. |
| 12 | Spring IoC Container là gì? | Là cơ chế inject dependencies vào các đối tượng. Hai core package: `org.springframework.beans` và `org.springframework.context`. | ApplicationContext là triển khai phổ biến nhất. |
| 13 | Spring Bean là gì? | Là bất kỳ object Java do Spring IoC container quản lý. Bean có vòng đời riêng và được inject dependency. | Được khởi tạo từ ApplicationContext hoặc BeanFactory. |
| 14 | Tầm quan trọng của file cấu hình Spring Bean? | Xác định bean, dependency, interceptor, view resolver,... trong Spring context. | Hỗ trợ cả XML config và annotation config. |
| 15 | Các cách cấu hình lớp thành Spring Bean? | 3 cách: XML (`<bean>`), Java Config (`@Configuration`, `@Bean`), Annotation (`@Component`, `@Service`, `@Repository`, `@Controller`). | Java Config và Annotation được ưa chuộng trong Spring Boot. |
| 16 | Các phạm vi (scope) khác nhau của Spring Bean? | singleton, prototype, request, session, global-session. | Singleton là mặc định; Request/Session chỉ dùng cho ứng dụng web. |
| 17 | Vòng đời của Spring Bean? | Tạo -> Dependency Injection -> Custom Init -> Sử dụng -> Custom Destroy -> Xóa khỏi context. | Hỗ trợ thông qua interface (`InitializingBean`, `DisposableBean`) hoặc method (`init-method`, `destroy-method`). |
| 18 | Làm cách nào lấy ServletContext và ServletConfig trong Spring Bean? | Dùng interface `ServletContextAware`, `ServletConfigAware` hoặc inject trực tiếp với `@Autowired`. | Chỉ dùng trong môi trường servlet container (web app). |
| 19 | Bean wiring và chú thích @Autowired là gì? | Wiring: inject dependencies cho Bean. `@Autowired`: tự động inject theo type, vào field hoặc setter. | Phải bật annotation-config (`<context:annotation-config/>`). |
| 20 | Các loại autowiring trong Spring? | byName, byType, constructor, annotation (`@Autowired`, `@Qualifier`). | Trước Spring 3.1 còn có autodetect (deprecated sau đó). |
| 21 | Spring Bean có cung cấp an toàn cho thread không? | Mặc định, Spring Bean không an toàn cho thread do phạm vi singleton. Tuy nhiên, có thể sử dụng phạm vi `prototype`, `request`, hoặc `session` để đảm bảo an toàn cho thread. | Phạm vi singleton có thể dẫn đến dữ liệu không nhất quán khi có nhiều luồng. |
| 22 | Controller trong Spring MVC là gì? | Controller xử lý các yêu cầu của khách hàng và gửi đến tài nguyên tương ứng. Trong Spring, `DispatcherServlet` là controller trung tâm. | Thường dùng với `@RequestMapping` để ánh xạ các URI. |
| 23 | Sự khác biệt giữa các chú thích @Component, @Controller, @Repository, @Service là gì? | Các chú thích này đều dùng để tự động phát hiện lớp và cấu hình thành bean. `@Controller` cho MVC, `@Repository` cho kho dữ liệu, `@Service` cho dịch vụ, `@Component` cho các thành phần chung. | Các chú thích giúp phân biệt rõ mục đích của lớp. |
| 24 | DispatcherServlet và ContextLoaderListener là gì? | `DispatcherServlet` là controller chính của Spring MVC, `ContextLoaderListener` khởi tạo Spring context. | `DispatcherServlet` điều khiển ứng dụng, `ContextLoaderListener` quản lý vòng đời ngữ cảnh. |
| 25 | ViewResolver trong Spring là gì? | ViewResolver giúp phân giải tên view thành trang thực tế, như `.jsp`. | `InternalResourceViewResolver` là một ví dụ phổ biến. |
| 26 | MultipartResolver là gì và nó được sử dụng khi nào? | `MultipartResolver` xử lý các yêu cầu tải file. `CommonsMultipartResolver` và `StandardServletMultipartResolver` là hai triển khai chính. | Dùng khi xử lý các file upload từ form HTML. |
| 27 | Làm thế nào để xử lý ngoại lệ trong Spring MVC? | Dùng `@ExceptionHandler` trong Controller hoặc `@ControllerAdvice` để xử lý ngoại lệ toàn cục. | Cũng có thể triển khai `HandlerExceptionResolver` để xử lý ngoại lệ chung. |
| 28 | Làm thế nào để tạo ApplicationContext trong một chương trình Java? | Dùng `AnnotationConfigApplicationContext` cho ứng dụng Java có chú thích hoặc `ClassPathXmlApplicationContext` cho cấu hình XML. | Dùng để khởi tạo Spring container trong các ứng dụng độc lập. |
| 29 | Chúng ta có thể có nhiều file cấu hình Spring không? | Có thể sử dụng nhiều file cấu hình trong Spring thông qua `contextConfigLocation` hoặc `beans:import`. | Thích hợp khi cần tách riêng các cấu hình cho các phần của ứng dụng. |
| 30 | ContextLoaderListener là gì? | `ContextLoaderListener` khởi tạo Spring context gốc và quản lý vòng đời của ngữ cảnh. | Được cấu hình trong `web.xml` để kết nối với các cấu hình Spring khác. |
| 31      | Cấu hình cơ bản cho ứng dụng Spring MVC               | Cần `spring-context`, `spring-webmvc`, cấu hình `DispatcherServlet`. | Làm nền tảng cho ứng dụng web.                                     |
| 32      | Kiến trúc Spring MVC                                | Dựa trên Model-View-Controller, `DispatcherServlet` là controller chính. | Giúp phân chia rõ ràng giữa dữ liệu, hiển thị và điều khiển.       |
| 33      | Localization trong Spring                           | Dùng `messageSource`, `localeResolver`, `LocaleChangeInterceptor`. | Giúp hỗ trợ đa ngôn ngữ cho ứng dụng.                              |
| 34      | Tạo RESTful services trong Spring                   | Sử dụng `@RestController` và Jackson để trả về JSON.             | Được dùng để tạo API cho ứng dụng web.                            |
| 35      | Các annotation quan trọng trong Spring MVC          | Bao gồm `@Controller`, `@RequestMapping`, `@ResponseBody`.        | Giúp định nghĩa controller và route.                              |
| 36      | Trả về đối tượng dưới dạng JSON hoặc XML trong Spring MVC | Dùng `@ResponseBody` để trả về JSON/XML.                         | Hỗ trợ chuyển đổi đối tượng thành dữ liệu JSON hoặc XML.         |
| 37      | Tải file trong Spring MVC                           | Sử dụng `MultipartResolver` để xử lý tải file.                    | Cần cấu hình cho phép tải lên tệp.                                 |
| 38      | Kiểm tra và xác thực form trong Spring MVC          | Dùng `@Valid` hoặc tạo `Validator` tùy chỉnh.                    | Đảm bảo dữ liệu người dùng nhập vào hợp lệ.                        |
| 39      | Spring MVC Interceptors so với Filters               | Interceptors kiểm tra trước và sau khi yêu cầu được xử lý.       | Filters xử lý ở mức thấp hơn, quản lý yêu cầu HTTP.               |
| 40      | JdbcTemplate trong Spring                           | Sử dụng `JdbcTemplate` để thao tác cơ sở dữ liệu.                 | Giảm thiểu mã lặp lại khi làm việc với JDBC.                       |
| 41      | Sử dụng JNDI DataSource trong Spring                | Cấu hình trong `spring-beans.xml`, tiêm vào bean.                 | Quản lý kết nối dữ liệu qua JNDI.                                 |
| 42      | Quản lý giao dịch trong Spring                     | Sử dụng `@Transactional` để quản lý giao dịch.                   | Tự động commit hoặc rollback giao dịch.                           |
| 43      | Spring DAO                                          | Cung cấp sự trừu tượng với JDBC và Hibernate.                   | Đơn giản hóa thao tác truy cập dữ liệu.                            |
| 44      | Spring và Hibernate                                 | Dùng Spring ORM để quản lý session và giao dịch Hibernate.      | Tiết kiệm thời gian và mã nguồn khi làm việc với Hibernate.      |
| 45      | Spring Security                                     | Cung cấp xác thực và phân quyền, bảo vệ chống tấn công.          | Quản lý quyền truy cập ứng dụng hiệu quả.                          |
| 46      | Tiêm `java.util.Properties` vào Spring Bean        | Dùng `PropertySourcesPlaceholderConfigurer` để tiêm vào bean.     | Tiện lợi khi cần sử dụng các giá trị từ file properties.          |
| 47      | Các mẫu thiết kế trong Spring                      | Bao gồm Singleton, Factory, Proxy, Template Method.              | Các mẫu thiết kế giúp tái sử dụng và quản lý mã hiệu quả hơn.     |
| 48      | Các mẹo quan trọng khi làm việc với Spring         | Tránh autowiring quá mức, sử dụng rõ ràng các annotation.        | Đảm bảo mã nguồn dễ đọc và bảo trì.                               |
| 49      | Core container trong Spring                        | Dựa trên `BeanFactory` để quản lý bean trong ứng dụng.            | Quản lý vòng đời của các đối tượng trong Spring.                  |
| 50      | Hạn chế của Autowiring trong Spring                | Có thể gây lỗi nếu có nhiều bean phù hợp hoặc thiếu bean.       | Cần cẩn trọng khi sử dụng để tránh xung đột.                      |

Bảng này tóm tắt lại các câu hỏi và câu trả lời một cách dễ hiểu và nhanh nhớ.