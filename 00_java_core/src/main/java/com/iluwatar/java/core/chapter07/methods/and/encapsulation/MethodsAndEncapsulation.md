# Cây về Methods và Encapsulation trong Java

## 1. Creating and Using Methods
- **Create methods and constructors with arguments and return values**
    - Tạo các phương thức và constructors với tham số và giá trị trả về.
        - Ví dụ:
          ```java
          public class Calculator {
              // Phương thức tính tổng hai số nguyên
              public int add(int a, int b) {
                  return a + b;
              }
 
              // Constructor có tham số
              public Calculator(int initialValue) {
                  // Khởi tạo đối tượng với giá trị ban đầu
              }
          }
          ```

- **Create and invoke overloaded methods**
    - Tạo và gọi các phương thức nạp chồng (overloaded methods).
        - Ví dụ:
          ```java
          public class MathOperations {
              // Phương thức tính tổng hai số nguyên
              public int add(int a, int b) {
                  return a + b;
              }
 
              // Phương thức tính tổng ba số nguyên
              public int add(int a, int b, int c) {
                  return a + b + c;
              }
          }
          ```

- **Apply the static keyword to methods and fields**
    - Áp dụng từ khóa static cho các phương thức và trường (fields).
        - Ví dụ:
          ```java
          public class Utility {
              // Phương thức static tính giai thừa
              public static int factorial(int n) {
                  if (n == 0 || n == 1) {
                      return 1;
                  }
                  return n * factorial(n - 1);
              }
 
              // Trường static pi
              public static final double PI = 3.14159;
          }
          ```

## 2. Applying Encapsulation
- **Apply access modifiers**
    - Áp dụng các bộ điều khiển truy cập (access modifiers) như `private`, `protected`, `public` và `default`.
        - Ví dụ:
          ```java
          public class Person {
              private String name; // private trường name
 
              // Phương thức public getName để lấy giá trị của name
              public String getName() {
                  return name;
              }
 
              // Phương thức public setName để thiết lập giá trị của name
              public void setName(String newName) {
                  this.name = newName;
              }
          }
          ```

- **Apply encapsulation principles to a class**
    - Áp dụng các nguyên lý bao đóng (encapsulation) vào một lớp.
        - Ví dụ:
          ```java
          public class BankAccount {
              private String accountNumber;
              private double balance;
 
              // Constructor có tham số để khởi tạo tài khoản ngân hàng
              public BankAccount(String accountNumber, double balance) {
                  this.accountNumber = accountNumber;
                  this.balance = balance;
              }
 
              // Phương thức để rút tiền từ tài khoản
              public void withdraw(double amount) {
                  if (amount > 0 && amount <= balance) {
                      balance -= amount;
                      System.out.println("Withdrawal successful");
                  } else {
                      System.out.println("Withdrawal failed. Insufficient balance.");
                  }
              }
 
              // Phương thức để nạp tiền vào tài khoản
              public void deposit(double amount) {
                  if (amount > 0) {
                      balance += amount;
                      System.out.println("Deposit successful");
                  } else {
                      System.out.println("Deposit failed. Invalid amount.");
                  }
              }
 
              // Phương thức để lấy số dư hiện tại
              public double getBalance() {
                  return balance;
              }
          }
          ```

| Loại       | Private                              | Protected                            | Default (Package-private)             | Public                                |
|------------|--------------------------------------|--------------------------------------|--------------------------------------|---------------------------------------|
| **Biến**   | Chỉ có thể truy cập từ bên trong lớp | Chỉ có thể truy cập từ cùng gói và các lớp con | Chỉ có thể truy cập từ cùng gói      | Có thể truy cập từ mọi nơi            |
| **Phương thức** | Chỉ có thể gọi từ bên trong lớp    | Chỉ có thể gọi từ cùng gói và các lớp con | Chỉ có thể gọi từ cùng gói           | Có thể gọi từ mọi nơi                 |
| **Thuộc tính** | Chỉ có thể truy cập từ bên trong lớp | Chỉ có thể truy cập từ cùng gói và các lớp con | Chỉ có thể truy cập từ cùng gói      | Có thể truy cập từ mọi nơi            |
| **Lớp (Class)** | Chỉ có thể khai báo trong cùng gói  | Chỉ có thể truy cập từ cùng gói và các lớp con | Chỉ có thể truy cập từ cùng gói      | Có thể truy cập từ mọi nơi            |
| **Package** | N/A (Không áp dụng cho package)      | N/A (Không áp dụng cho package)      | N/A (Không áp dụng cho package)      | N/A (Không áp dụng cho package)       |

| Loại       | Permits                                        | Sealed                                         | Pattern                                          | Static                                         | Final                                          | Synchronized                                  | Volatile                                      |
|------------|------------------------------------------------|------------------------------------------------|--------------------------------------------------|------------------------------------------------|------------------------------------------------|------------------------------------------------|------------------------------------------------|
| **Biến**   | Không                         |                         | Không                          | Biến static là biến của lớp, không phải của đối tượng. | Biến final là hằng số không thay đổi giá trị sau khi gán. | Không                         | Biến volatile là biến được đồng bộ hóa theo cấp độ Thread. |
| **Phương thức** | Không .                 | Phương thức sealed không thể bị ghi đè.       | Không                | Phương thức static thuộc về lớp, không phụ thuộc vào đối tượng. | Phương thức final không thể bị ghi đè.        | Phương thức synchronized đảm bảo đồng bộ hóa giữa các Thread khi gọi đến phương thức. | Không                  |
| **Thuộc tính** | Không                 | Không                 | Không                   | Thuộc tính static là thuộc tính của lớp, không phải của đối tượng. | Thuộc tính final là biến hằng số không thay đổi giá trị. | Không                 | Không                 |
| **Lớp (Class)** | Không                | Lớp sealed chỉ có thể được kế thừa bởi các lớp được liệt kê. | Lớp pattern là một lớp trừu tượng để định nghĩa mẫu của một nhóm lớp. | Không thể đánh dấu lớp là static trong Java.   | Lớp final không thể được kế thừa.             | Không               | Không                |
| **Package** | Package sealed chỉ cho phép các lớp cụ thể được liệt kê. | Không                   | Không                     | Không                    | Không                     | Không                    | Không                     |

### Giải thích:

- **Permits**: Được sử dụng trong context của sealed classes/interfaces để chỉ định các lớp cụ thể được phép kế thừa hoặc triển khai.
- **Sealed**: Đánh dấu lớp hoặc interface để chỉ cho phép một số lớp cụ thể được kế thừa hoặc triển khai.
- **Pattern**: Không có từ khóa `pattern` cụ thể trong Java, có thể bạn đang nhắc đến các design pattern như Singleton, Factory, Observer, etc.
- **Static**: Đánh dấu biến, phương thức, hoặc khối mã thuộc về lớp, không phải của đối tượng riêng lẻ.
- **Final**: Đánh dấu biến, phương thức, thuộc tính, hoặc lớp không thể thay đổi sau khi đã khởi tạo (biến hằng số, phương thức không thể ghi đè, lớp không thể kế thừa).
- **Synchronized**: Đánh dấu phương thức để đảm bảo đồng bộ hóa giữa các Thread khi gọi đến phương thức.
- **Volatile**: Đánh dấu biến để đảm bảo rằng giá trị của nó luôn được đọc từ và ghi vào bộ nhớ chính, không phải từ bộ nhớ cache của từng Thread riêng biệt.

Các từ khóa này giúp kiểm soát và quản lý tính linh hoạt, hiệu suất, và bảo mật trong lập trình Java, đặc biệt là trong môi trường đa luồng và thiết kế ứng dụng lớn.       