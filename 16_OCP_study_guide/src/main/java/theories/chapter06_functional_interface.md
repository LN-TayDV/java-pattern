Chủ đề **Lambda Expressions và Functional Interfaces** trong OCP exam bao gồm các mục tiêu sau:

1. **Programming Abstractly Through Interfaces**
    - Khai báo và sử dụng các instance của `List` và `ArrayList`
    - Hiểu và sử dụng **Lambda Expressions**

---

### 🔍 **Các dạng đề chủ yếu về Lambda và Functional Interfaces**:

#### 1. **Lambda Expressions**
- **Câu hỏi mẫu**:
    - **Chọn kết quả đúng** khi bạn sử dụng Lambda expression để xử lý danh sách.
      ```java
      List<String> names = Arrays.asList("John", "Jane", "Alex");
      names.forEach(name -> System.out.println(name));
      ```
    - **Điền từ**: Điền vào chỗ trống để hoàn thành đoạn mã sử dụng Lambda expression cho một `List`:
      ```java
      List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
      numbers.forEach(____);
      ```
    - **Giải thích**: Lambda expression có thể được sử dụng để thay thế cho các anonymous class khi triển khai các interface, đặc biệt là khi xử lý dữ liệu trong `List`, `Stream`, hoặc `Map`.

#### 2. **Functional Interfaces**
- **Câu hỏi mẫu**:
    - **Chọn kết quả đúng** về việc khai báo một **Functional Interface**:
      ```java
      @FunctionalInterface
      public interface MyFunction {
          int apply(int a, int b);
      }
      ```
    - **Điền từ**: Điền vào chỗ trống để tạo ra một Functional Interface hợp lệ.
      ```java
      @FunctionalInterface
      public interface ____ {
          void execute();
      }
      ```
    - **Giải thích**: Functional Interface là một interface chỉ có một phương thức trừu tượng, thường được sử dụng trong kết hợp với Lambda expressions.

#### 3. **Sử dụng Lambda với Functional Interface**
- **Câu hỏi mẫu**:
    - **Chọn kết quả đúng** về việc sử dụng Lambda expression để triển khai một Functional Interface:
      ```java
      MyFunction add = (a, b) -> a + b;
      System.out.println(add.apply(5, 3));
      ```
    - **Giải thích**: Lambda expression có thể dễ dàng sử dụng để triển khai các phương thức trong **Functional Interface**, giúp mã ngắn gọn và dễ đọc.

---

### 🧠 **Cách ghi nhớ bản chất và giải quyết các vấn đề về Lambda và Functional Interfaces**:

#### 1. **Lambda Expressions**:
- **Cách ghi nhớ**:
    - Lambda expression là một cách rút gọn để triển khai các phương thức của interface.
    - Cấu trúc cơ bản của một Lambda là: `(parameters) -> expression` hoặc `(parameters) -> {statements}`.
    - **Ví dụ**:
        - `(a, b) -> a + b` (đây là Lambda expression cho phép cộng hai số).

- **Lưu ý**:
    - Lambda expressions chỉ có thể được sử dụng khi interface có **một phương thức trừu tượng** (Functional Interface).
    - Lambda làm cho mã nguồn gọn gàng hơn, dễ đọc hơn và không cần tạo thêm lớp vô danh.

#### 2. **Functional Interfaces**:
- **Cách ghi nhớ**:
    - **Functional Interface** là interface chỉ có **một phương thức trừu tượng**.
    - Bạn có thể xác định Functional Interface bằng cách sử dụng annotation `@FunctionalInterface` (tuy nhiên, annotation này không bắt buộc, nhưng giúp kiểm tra tính hợp lệ).

- **Lưu ý**:
    - Các interface như `Runnable`, `Comparator`, `Callable`, v.v. là các **Functional Interfaces** phổ biến trong Java.
    - Lambda expression có thể triển khai một **Functional Interface** trong vòng một dòng.

---

### 💡 **Các mẹo ghi nhớ**:
- **Lambda**: Hãy tưởng tượng Lambda như một cách viết ngắn gọn cho các anonymous classes. Mỗi Lambda expression có 3 phần chính: tham số (nếu có), dấu mũi tên `->`, và nội dung của phương thức.
- **Functional Interface**: Hãy nhớ rằng bất kỳ interface nào có **một phương thức trừu tượng** có thể được coi là một **Functional Interface**. Bạn có thể thêm các phương thức mặc định (`default methods`) hoặc phương thức tĩnh (`static methods`) nhưng chỉ có **một phương thức trừu tượng**.

---

### 📌 **Mã nguồn mẫu**:

#### **Lambda Expression với List**:
```java
import java.util.*;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Alex");
        names.forEach(name -> System.out.println(name));  // In ra danh sách tên
    }
}
```

#### **Functional Interface và Lambda**:
```java
@FunctionalInterface
public interface MyFunction {
    int apply(int a, int b);  // Phương thức trừu tượng duy nhất
}

public class LambdaFunctionalInterface {
    public static void main(String[] args) {
        MyFunction add = (a, b) -> a + b;  // Lambda expression triển khai phương thức apply
        System.out.println(add.apply(5, 3));  // Output: 8
    }
}
```

---

### 📝 **Lưu ý quan trọng khi giải bài tập**:

1. **Lambda không thể được sử dụng với interface có nhiều phương thức trừu tượng**. Đây là điều quan trọng để phân biệt với các interface không phải **Functional Interface**.
2. **Functional Interface** không nhất thiết phải có annotation `@FunctionalInterface`, nhưng việc sử dụng annotation này giúp đảm bảo rằng interface chỉ có một phương thức trừu tượng.
3. **Lambda expression** có thể được sử dụng thay thế cho việc triển khai phương thức trong các **Functional Interfaces**. Đảm bảo hiểu rõ cách sử dụng các phương thức trong `forEach`, `map`, và các API khác của `Stream`.

---

Với những hướng dẫn trên, bạn sẽ dễ dàng làm quen và chuẩn bị cho các câu hỏi về **Lambda expressions** và **Functional interfaces** trong kỳ thi OCP.