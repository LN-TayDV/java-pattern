Tôi sẽ giải thích chi tiết về các annotation trong Spring mà bạn đang bối rối. Các annotation này giúp bạn cấu hình và quản lý các bean trong ứng dụng Spring một cách dễ dàng và linh hoạt. Dưới đây là mô tả chi tiết về từng annotation và ngữ cảnh sử dụng:

### 1. `@Component`

`@Component` là một annotation tổng quát được sử dụng để đánh dấu một lớp là một bean trong Spring. Lớp này sẽ được Spring container quản lý.

**Ví dụ:**
```java
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

### 2. `@Configuration`

`@Configuration` được sử dụng để đánh dấu một lớp là nguồn của các định nghĩa bean. Lớp này chứa các phương thức được chú thích với `@Bean` để khởi tạo và cấu hình các bean.

**Ví dụ:**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public MyComponent myComponent() {
        return new MyComponent();
    }
}
```

### 3. `@Service`

`@Service` là một dạng đặc biệt của `@Component`, được sử dụng để đánh dấu một lớp là một bean thuộc tầng dịch vụ (service layer). Nó giúp bạn phân biệt rõ ràng giữa các bean thuộc các tầng khác nhau của ứng dụng.

**Ví dụ:**
```java
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public void performService() {
        System.out.println("Performing service...");
    }
}
```

### 4. `@Controller`

`@Controller` là một dạng đặc biệt của `@Component`, được sử dụng để đánh dấu một lớp là một controller trong Spring MVC. Controller xử lý các request từ client và trả về view hoặc dữ liệu.

**Ví dụ:**
```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello, World!";
    }
}
```

### 5. `@Bean`

`@Bean` được sử dụng trong các lớp cấu hình (`@Configuration`) để định nghĩa một bean. Phương thức được chú thích với `@Bean` sẽ trả về một instance của bean đó.

**Ví dụ:**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public MyComponent myComponent() {
        return new MyComponent();
    }
}
```

### 6. `@Autowired`

`@Autowired` được sử dụng để tự động tiêm (inject) các bean vào các thuộc tính, constructor hoặc phương thức setter. Nó giúp Spring tự động tìm và gán bean thích hợp vào đối tượng.

**Ví dụ:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    
    private MyService myService;

    @Autowired
    public MyComponent(MyService myService) {
        this.myService = myService;
    }

    public void doSomething() {
        myService.performService();
    }
}
```

### 7. `@Qualifier`

Khi có nhiều hơn một bean cùng loại, bạn có thể sử dụng `@Qualifier` cùng với `@Autowired` để chỉ định rõ bean nào cần được tiêm.

**Ví dụ:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    private MyService myService;

    @Autowired
    public MyComponent(@Qualifier("mySpecificService") MyService myService) {
        this.myService = myService;
    }

    public void doSomething() {
        myService.performService();
    }
}
```

### Tổng kết

- `@Component`: Đánh dấu một lớp là một bean quản lý bởi Spring.
- `@Configuration`: Đánh dấu một lớp chứa các định nghĩa bean.
- `@Service`: Đánh dấu một lớp là một bean thuộc tầng dịch vụ.
- `@Controller`: Đánh dấu một lớp là một controller trong Spring MVC.
- `@Bean`: Định nghĩa một bean trong lớp cấu hình.
- `@Autowired`: Tự động tiêm bean vào các thuộc tính, constructor, hoặc setter.
- `@Qualifier`: Chỉ định rõ bean nào cần được tiêm khi có nhiều bean cùng loại.

Bằng cách sử dụng các annotation này, bạn có thể cấu hình và quản lý các bean trong ứng dụng Spring một cách dễ dàng và hiệu quả.