Chủ đề **Methods and Encapsulation** trong OCP exam bao gồm các mục tiêu sau:

1. **Creating and Using Methods**:
    - Tạo các phương thức và constructor với đối số và giá trị trả về.
    - Tạo và gọi các phương thức bị overload.
    - Áp dụng từ khóa **static** vào phương thức và trường (field).

2. **Applying Encapsulation**:
    - Áp dụng các **access modifiers** (public, private, protected, default).
    - Áp dụng nguyên lý **encapsulation** cho một lớp (class).

---

### 🔍 **Các dạng câu hỏi về Methods and Encapsulation**:

#### 1. **Tạo và Sử dụng Phương thức và Constructor**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào đoạn mã để tạo một phương thức có đối số và giá trị trả về.
      ```java
      public class Calculator {
          public ____ add(int a, int b) {
              return a + b;
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Khi nào bạn cần sử dụng constructor trong Java?
        - A. Để tạo ra đối tượng của lớp
        - B. Để khởi tạo các giá trị mặc định cho đối tượng
        - C. Để khai báo các phương thức trong lớp
        - D. Cả A và B đúng
    - **Giải thích**: Constructor được sử dụng để khởi tạo các đối tượng, có thể có hoặc không có đối số.

#### 2. **Phương thức Overloading (Nạp chồng phương thức)**
- **Câu hỏi mẫu**:
    - **Điền từ**: Điền vào chỗ trống để phương thức `multiply` có thể nhận và xử lý cả hai loại đối số `int` và `double`.
      ```java
      public class MathOperations {
          public int multiply(int a, int b) {
              return a * b;
          }
          public ____ multiply(double a, double b) {
              return a * b;
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Phương thức nạp chồng (method overloading) là gì?
            - A. Phương thức có cùng tên nhưng khác tham số
            - B. Phương thức có cùng tên và tham số
            - C. Phương thức có tên khác nhau nhưng cùng tham số
            - D. Cả A và C đúng
    - **Giải thích**: Phương thức nạp chồng là khi hai phương thức có cùng tên nhưng khác về số lượng hoặc kiểu dữ liệu của tham số.

#### 3. **Static Methods and Fields**
- **Câu hỏi mẫu**:
    - **Chọn kết quả đúng** về cách khai báo và sử dụng phương thức static:
      ```java
      public class Utility {
          public static int square(int num) {
              return num * num;
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Phương thức static có thể được gọi như thế nào?
            - A. Thông qua đối tượng của lớp
            - B. Thông qua tên lớp
            - C. Phương thức static không thể gọi trực tiếp
            - D. Cả A và B đều đúng
    - **Giải thích**: Phương thức static có thể được gọi qua tên lớp hoặc đối tượng, nhưng tốt nhất là gọi qua tên lớp.

#### 4. **Access Modifiers (Các Modifier quyền truy cập)**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào chỗ trống để phương thức `getBalance` chỉ có thể truy cập trong cùng lớp hoặc các lớp con.
      ```java
      public class BankAccount {
          ____ double balance;
          
          public double getBalance() {
              return balance;
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Trong Java, các mức độ truy cập (access modifiers) có sẵn là gì?
            - A. `public`, `private`, `protected`, `default`
            - B. `public`, `private`, `package-private`, `static`
            - C. `private`, `public`, `static`, `final`
            - D. `public`, `private`, `protected`, `final`
    - **Giải thích**: Java có 4 mức độ truy cập: `public`, `private`, `protected`, và **default** (package-private).

#### 5. **Encapsulation (Đóng gói)**
- **Câu hỏi mẫu**:
    - **Chọn kết quả đúng** về encapsulation:
        - **Lý thuyết**: Encapsulation có nghĩa là ẩn thông tin trong đối tượng để tránh việc truy cập trực tiếp từ bên ngoài lớp.
        - **Ví dụ mã**:
          ```java
          public class Account {
              private double balance;  // Thực hiện encapsulation với biến private
              
              public double getBalance() {
                  return balance;  // Truy xuất qua getter method
              }
              
              public void setBalance(double balance) {
                  if (balance >= 0) {
                      this.balance = balance;  // Thực hiện cập nhật qua setter method
                  }
              }
          }
          ```
    - **Câu hỏi trắc nghiệm**:
        - Mục đích của việc sử dụng **encapsulation** là gì?
            - A. Để bảo vệ dữ liệu khỏi việc bị truy cập từ bên ngoài trực tiếp
            - B. Để giảm độ phức tạp của mã
            - C. Để tăng hiệu suất của chương trình
            - D. Để làm cho các phương thức của lớp trở nên dễ tiếp cận hơn
    - **Giải thích**: **Encapsulation** nhằm bảo vệ các thành viên của lớp khỏi việc bị truy cập hoặc thay đổi trực tiếp từ bên ngoài. Thay vào đó, dữ liệu chỉ có thể được truy xuất hoặc thay đổi thông qua các phương thức **getter** và **setter**.

---

### 🧠 **Cách ghi nhớ và cách giải các bài tập liên quan đến Methods và Encapsulation**:

#### 1. **Phương thức và Constructor**:
- **Cách ghi nhớ**:
    - Constructor được sử dụng để khởi tạo đối tượng của lớp.
    - Phương thức có thể có hoặc không có giá trị trả về, và có thể có các tham số đầu vào.

#### 2. **Phương thức Overloading**:
- **Cách ghi nhớ**:
    - **Overloading** chỉ xảy ra khi một lớp có nhiều phương thức cùng tên nhưng khác về tham số (số lượng hoặc kiểu tham số).
    - Điều này cho phép bạn sử dụng một tên phương thức duy nhất để xử lý nhiều tình huống khác nhau.

#### 3. **Static Methods and Fields**:
- **Cách ghi nhớ**:
    - Các phương thức và trường **static** không phụ thuộc vào đối tượng mà phụ thuộc vào lớp.
    - Có thể gọi phương thức static thông qua tên lớp, nhưng bạn cũng có thể gọi thông qua đối tượng (tuy nhiên không phải cách làm tốt nhất).

#### 4. **Access Modifiers và Encapsulation**:
- **Cách ghi nhớ**:
    - **Encapsulation** giúp ẩn thông tin và chỉ cho phép truy cập thông qua các phương thức getter/setter.
    - Dùng **private** để ẩn thông tin, **public** để cho phép truy cập từ bên ngoài.
    - **protected** và **default** (package-private) là các cấp độ truy cập tùy chỉnh cho phép truy cập trong phạm vi lớp con và gói.

---

### 📌 **Mã nguồn mẫu**:

#### **Phương thức Constructor và Getter/Setter**:
```java
public class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class TestEmployee {
    public static void main(String[] args) {
        Employee e = new Employee("John", 30);
        System.out.println(e.getName());  // Output: John
        System.out.println(e.getAge());   // Output: 30
    }
}
```

--
