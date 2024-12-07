Đúng vậy, Spring hỗ trợ nhiều scope khác nhau cho bean, giúp kiểm soát vòng đời và phạm vi của các bean trong ứng dụng. Dưới đây là các scope mà Spring hỗ trợ từ phiên bản 6:

1. **singleton**:
    - Đây là scope mặc định. Chỉ có một đối tượng duy nhất được tạo ra cho mỗi Spring IoC container.
    - Ví dụ:
      ```java
      @Bean
      @Scope("singleton")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

2. **prototype**:
    - Một instance mới sẽ được tạo ra mỗi khi bean được yêu cầu bởi ứng dụng.
    - Ví dụ:
      ```java
      @Bean
      @Scope("prototype")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

3. **request**:
    - Sử dụng cho ứng dụng web. Với Spring MVC, các bean có scope request sẽ được tạo ra cho mỗi HTTP request và bị hủy khi request kết thúc.
    - Ví dụ:
      ```java
      @Bean
      @Scope("request")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

4. **session**:
    - Sử dụng cho ứng dụng web. Với Spring MVC, các bean có scope session sẽ được tạo ra cho mỗi HTTP session và bị hủy khi session kết thúc.
    - Ví dụ:
      ```java
      @Bean
      @Scope("session")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

5. **application**:
    - Scope này liên kết một bean duy nhất với vòng đời của một `ServletContext`. Scope application chỉ hợp lệ trong ứng dụng web Spring.
    - Ví dụ:
      ```java
      @Bean
      @Scope("application")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

6. **thread**:
    - Một instance mới của bean sẽ được tạo ra mỗi khi được yêu cầu bởi một thread mới, và cùng một instance sẽ được trả về cho cùng một thread. Lưu ý rằng scope này không được đăng ký mặc định.
    - Ví dụ:
      ```java
      @Bean
      @Scope("thread")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

7. **custom**:
    - Bạn có thể tạo scope tùy chỉnh bằng cách implement interface `org.springframework.beans.factory.config.Scope` và đăng ký scope tùy chỉnh này trong cấu hình Spring (đối với XML, sử dụng class `org.springframework.beans.factory.config.CustomScopeConfigurer`).
    - Ví dụ:
      ```java
      public class MyCustomScope implements Scope {
          // Implement các phương thức cần thiết
      }
 
      @Configuration
      public class AppConfig {
          @Bean
          public CustomScopeConfigurer customScopeConfigurer() {
              CustomScopeConfigurer configurer = new CustomScopeConfigurer();
              Map<String, Object> scopes = new HashMap<>();
              scopes.put("myCustomScope", new MyCustomScope());
              configurer.setScopes(scopes);
              return configurer;
          }
      }
      ```

8. **websocket**:
    - Scope này liên kết một bean duy nhất với vòng đời của một WebSocket. Scope websocket chỉ hợp lệ trong ứng dụng web Spring.
    - Ví dụ:
      ```java
      @Bean
      @Scope("websocket")
      public MyBean myBean() {
          return new MyBean();
      }
      ```

Mỗi scope trên giúp bạn kiểm soát cách các bean được tạo ra và quản lý trong ứng dụng Spring của mình, phù hợp với các yêu cầu và ngữ cảnh cụ thể.