Đúng vậy, Spring hỗ trợ năm chế độ autowiring để tự động inject các bean vào các bean phụ thuộc vào chúng. Dưới đây là mô tả chi tiết về từng chế độ autowiring:

1. **byName**:
    - Khi sử dụng autowiring byName, Spring cố gắng wire mỗi thuộc tính (property) của bean mục tiêu với một bean có cùng tên.
    - Ví dụ: Nếu bean mục tiêu có một thuộc tính tên là `foo` và một bean `foo` được định nghĩa trong `ApplicationContext`, thì bean `foo` sẽ được gán vào thuộc tính `foo` của bean mục tiêu.
    - Cách này yêu cầu tên của thuộc tính và tên của bean phải khớp nhau.

2. **byType**:
    - Khi sử dụng autowiring byType, Spring cố gắng wire mỗi thuộc tính của bean mục tiêu với một bean có cùng kiểu trong `ApplicationContext`.
    - Ví dụ: Nếu bean mục tiêu có một thuộc tính kiểu `FooService` và một bean `FooService` được định nghĩa trong `ApplicationContext`, thì bean `FooService` sẽ được gán vào thuộc tính `FooService` của bean mục tiêu.
    - Cách này yêu cầu kiểu của thuộc tính và kiểu của bean phải khớp nhau.

3. **constructor**:
    - Chế độ autowiring này hoạt động giống như byType, nhưng sử dụng các constructor thay vì các setter method để thực hiện injection.
    - Spring cố gắng khớp số lượng tham số nhiều nhất có thể trong constructor.
    - Ví dụ: Nếu bean của bạn có hai constructor, một nhận `String` và một nhận `String` và `Integer`, và bạn có cả bean `String` và `Integer` trong `ApplicationContext`, Spring sẽ sử dụng constructor có hai tham số.

4. **default**:
    - Spring sẽ chọn tự động giữa các chế độ constructor và byType.
    - Nếu bean của bạn có một constructor mặc định (không có tham số), Spring sẽ sử dụng byType; nếu không, nó sẽ sử dụng constructor.

5. **no**:
    - Không autowiring; đây là chế độ mặc định.
    - Trong chế độ này, bạn phải tự tay cấu hình các dependencies.

Dưới đây là ví dụ về cách sử dụng các chế độ autowiring trong cấu hình Spring XML:

```xml
<bean id="myBean" class="com.example.MyBean" autowire="byName" />
<bean id="foo" class="com.example.Foo" />
```

Với cấu hình này, nếu `MyBean` có một thuộc tính tên là `foo`, Spring sẽ tự động wire bean `foo` vào thuộc tính `foo` của `MyBean` bằng cách sử dụng chế độ `byName`.

Trong cấu hình Java, bạn có thể sử dụng `@Autowired` và các annotation liên quan để điều khiển chế độ autowiring:

```java
@Component
public class MyBean {

    @Autowired
    private Foo foo; // byType autowiring

    @Autowired
    public MyBean(Foo foo) { // constructor autowiring
        this.foo = foo;
    }
}
```

Trong ví dụ này, Spring sẽ tự động wire bean `Foo` vào thuộc tính `foo` của `MyBean` bằng cách sử dụng chế độ `byType` hoặc `constructor` tùy theo cách bạn định nghĩa `@Autowired`.