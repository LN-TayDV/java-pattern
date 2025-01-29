Trong Spring Boot, các annotation được sử dụng rộng rãi để cấu hình và quản lý các thành phần của ứng dụng. Dưới đây là cách hệ thống hóa các annotation chính trong Spring Boot, được nhóm theo tính năng hoặc module.

### 1. **Annotations liên quan đến cấu hình ứng dụng:**
- **@SpringBootApplication:** Được sử dụng để đánh dấu lớp chính của ứng dụng Spring Boot. Bao gồm các annotation như @Configuration, @EnableAutoConfiguration, và @ComponentScan.
- **@Configuration:** Đánh dấu một lớp là nguồn các định nghĩa bean để Spring IoC container quản lý.
- **@PropertySource:** Chỉ định các tệp thuộc tính để tải trong môi trường Spring.
- **@Import:** Được sử dụng để nhập cấu hình từ các lớp cấu hình khác.

### 2. **Annotations liên quan đến thành phần quản lý và phụ thuộc:**
- **@Component:** Đánh dấu một lớp là thành phần của Spring để Spring IoC container quản lý.
- **@Service:** Được sử dụng để đánh dấu một lớp là một dịch vụ trong tầng business.
- **@Repository:** Được sử dụng để đánh dấu một lớp là một repository trong tầng dữ liệu.
- **@Controller:** Được sử dụng để đánh dấu một lớp là một controller trong tầng presentation.
- **@Autowired:** Được sử dụng để tự động tiêm phụ thuộc vào các thành phần hoặc đối tượng cần thiết.
- **@Qualifier:** Được sử dụng để chỉ định bean cụ thể trong trường hợp có nhiều bean cùng loại.
- **@Lazy:** Được sử dụng để đánh dấu bean sẽ được khởi tạo lười (khi cần thiết mới khởi tạo).

### 3. **Annotations liên quan đến Web (Spring MVC):**
- **@RequestMapping:** Được sử dụng để ánh xạ các yêu cầu HTTP tới các phương thức xử lý cụ thể.
- **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping:** Các biến thể của @RequestMapping để ánh xạ các yêu cầu GET, POST, PUT, DELETE tương ứng.
- **@RestController:** Kết hợp @Controller và @ResponseBody, được sử dụng cho các API RESTful.
- **@ResponseBody:** Chỉ định rằng giá trị trả về của phương thức sẽ được ghi trực tiếp vào phần thân của phản hồi HTTP.

### 4. **Annotations liên quan đến bảo mật (Security):**
- **@EnableWebSecurity:** Được sử dụng để kích hoạt cấu hình bảo mật web trong ứng dụng Spring.
- **@Secured:** Xác định các quyền cần thiết để truy cập phương thức hoặc lớp.
- **@PreAuthorize, @PostAuthorize:** Kiểm tra quyền truy cập trước và sau khi thực thi phương thức.
- **@RolesAllowed:** Định nghĩa các vai trò được phép truy cập vào phương thức hoặc lớp.

### 5. **Annotations liên quan đến quản lý giao dịch (Transaction Management):**
- **@EnableTransactionManagement:** Kích hoạt quản lý giao dịch trong ứng dụng.
- **@Transactional:** Đánh dấu phương thức hoặc lớp yêu cầu giao dịch, có thể cấu hình phạm vi giao dịch, mức độ cô lập, và các thuộc tính khác.

### 6. **Annotations liên quan đến dữ liệu (Spring Data):**
- **@Entity:** Đánh dấu một lớp là một thực thể trong JPA.
- **@Table:** Xác định bảng cơ sở dữ liệu tương ứng với thực thể JPA.
- **@Id:** Đánh dấu trường là khóa chính.
- **@GeneratedValue:** Được sử dụng để chỉ định chiến lược tạo giá trị tự động cho khóa chính.
- **@Query:** Được sử dụng để xác định các truy vấn SQL tùy chỉnh trong Spring Data repositories.

### 7. **Annotations liên quan đến kiểm thử (Testing):**
- **@SpringBootTest:** Được sử dụng để thiết lập môi trường thử nghiệm toàn diện cho ứng dụng Spring Boot.
- **@MockBean:** Được sử dụng để tạo và tiêm một đối tượng mock vào Spring ApplicationContext.
- **@Test:** Đánh dấu một phương thức là một trường hợp thử nghiệm.

### 8. **Annotations liên quan đến AOP (Aspect-Oriented Programming):**
- **@Aspect:** Đánh dấu một lớp là một aspect trong AOP.
- **@Before, @After, @Around, @AfterReturning, @AfterThrowing:** Được sử dụng để chỉ định hành vi của aspect trước, sau, hoặc xung quanh phương thức mục tiêu.

### 9. **Annotations liên quan đến Caching (Bộ nhớ đệm):**
- **@EnableCaching:** Kích hoạt cơ chế caching trong ứng dụng Spring.
- **@Cacheable:** Đánh dấu một phương thức hoặc lớp để kích hoạt bộ nhớ đệm kết quả trả về.
- **@CachePut:** Cập nhật cache với giá trị mới mà không cần kiểm tra trước.
- **@CacheEvict:** Loại bỏ một hoặc nhiều mục từ cache.

Các annotations này có thể kết hợp với nhau để cung cấp các chức năng đa dạng trong Spring Boot, giúp ứng dụng trở nên linh hoạt và dễ quản lý.