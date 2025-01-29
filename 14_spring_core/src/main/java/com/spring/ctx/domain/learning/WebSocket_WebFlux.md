**WebSocket** và **WebFlux** là hai công nghệ quan trọng trong Spring Framework, đặc biệt hữu ích khi bạn cần xây dựng các ứng dụng web có yêu cầu về hiệu suất cao và khả năng phản hồi tốt.

### WebSocket

**WebSocket** là một giao thức mạng cho phép mở một kết nối liên tục giữa client và server, cho phép trao đổi dữ liệu hai chiều trong thời gian thực. Khác với HTTP, WebSocket giữ kết nối mở sau khi handshake ban đầu, giúp truyền dữ liệu liên tục mà không cần phải thiết lập kết nối mới cho mỗi lần gửi dữ liệu.

#### Đặc điểm chính của WebSocket:

- **Kết nối hai chiều:** Cho phép gửi và nhận dữ liệu từ cả hai phía (client và server) mà không cần phải gửi yêu cầu mới.
- **Hiệu suất cao:** Giảm độ trễ và tăng hiệu suất truyền dữ liệu nhờ việc duy trì kết nối mở.
- **Sử dụng trong các ứng dụng thời gian thực:** Phù hợp cho các ứng dụng chat, trò chơi trực tuyến, và các ứng dụng cần phản hồi thời gian thực.

#### Cấu hình WebSocket trong Spring:

Để cấu hình WebSocket trong Spring, bạn cần tạo một lớp cấu hình và một endpoint WebSocket. Dưới đây là ví dụ đơn giản về cách cấu hình WebSocket trong Spring:

**1. Tạo lớp cấu hình WebSocket:**

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/ws").setAllowedOrigins("*");
    }
}
```

**2. Tạo WebSocket Handler:**

```java
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("Hello, " + message.getPayload()));
    }
}
```

### WebFlux

**WebFlux** là một phần của Spring Framework cung cấp hỗ trợ lập trình phản ứng (reactive programming) cho các ứng dụng web. WebFlux hỗ trợ việc xây dựng các ứng dụng web không đồng bộ và có khả năng mở rộng cao.

#### Đặc điểm chính của WebFlux:

- **Lập trình phản ứng:** WebFlux hỗ trợ lập trình phản ứng bằng cách sử dụng `Mono` và `Flux` từ Project Reactor, giúp bạn xử lý các dữ liệu không đồng bộ và luồng dữ liệu.
- **Phi đồng bộ:** WebFlux giúp xử lý các yêu cầu mà không cần chặn luồng, làm giảm tải trên server.
- **Hiệu suất cao và khả năng mở rộng:** Phù hợp với các ứng dụng cần xử lý nhiều yêu cầu đồng thời mà không tiêu tốn quá nhiều tài nguyên.

#### Cấu hình WebFlux trong Spring:

Để cấu hình WebFlux trong Spring, bạn có thể sử dụng các `Reactive` controllers và cấu hình Spring Boot để hỗ trợ WebFlux.

**1. Tạo Reactive Controller:**

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ReactiveController {

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return Mono.just("Hello, WebFlux!");
    }
}
```

**2. Cấu hình Spring Boot để sử dụng WebFlux:**

Nếu bạn sử dụng Spring Boot, chỉ cần thêm dependency `spring-boot-starter-webflux` vào `pom.xml` hoặc `build.gradle` của bạn.

**pom.xml:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

**build.gradle:**

```gradle
implementation 'org.springframework.boot:spring-boot-starter-webflux'
```

Khi cấu hình WebFlux, Spring Boot sẽ tự động cấu hình ứng dụng của bạn để sử dụng WebFlux thay vì Spring MVC (cả hai không thể hoạt động đồng thời trong cùng một ứng dụng).

### Tóm Tắt

- **WebSocket:** Giao thức cho phép giao tiếp hai chiều liên tục giữa client và server. Phù hợp với các ứng dụng cần truyền dữ liệu thời gian thực.
- **WebFlux:** Một phần của Spring Framework hỗ trợ lập trình phản ứng, giúp xây dựng các ứng dụng web không đồng bộ và có khả năng mở rộng cao.

Hy vọng điều này giúp bạn hiểu rõ hơn về WebSocket và WebFlux trong Spring!