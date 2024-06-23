### Thiết kế Lớp Nâng Cao (Advanced Class Design) trong Java

- **Tái Sử Dụng Triển Khai Qua Kế Thừa**
    - Tạo và mở rộng các lớp trừu tượng
        - Ví dụ: Định nghĩa lớp trừu tượng `Shape` và các lớp con như `Circle` và `Rectangle`.
          ```java
          // Lớp trừu tượng
          public abstract class Shape {
              // Phương thức trừu tượng tính diện tích
              public abstract double calculateArea();
          }
          
          // Lớp con Circle kế thừa từ Shape
          public class Circle extends Shape {
              private double radius;
              
              // Constructor
              public Circle(double radius) {
                  this.radius = radius;
              }
              
              // Override phương thức calculateArea
              @Override
              public double calculateArea() {
                  return Math.PI * radius * radius;
              }
          }
          
          // Lớp con Rectangle kế thừa từ Shape
          public class Rectangle extends Shape {
              private double width;
              private double height;
              
              // Constructor
              public Rectangle(double width, double height) {
                  this.width = width;
                  this.height = height;
              }
              
              // Override phương thức calculateArea
              @Override
              public double calculateArea() {
                  return width * height;
              }
          }
          ```

- **Lập Trình Trừu Tượng Qua Giao Diện**
    - Tạo và triển khai các giao diện
        - Ví dụ: Định nghĩa giao diện `Drawable` và các lớp như `Circle` và `Rectangle` triển khai giao diện này.
          ```java
          // Giao diện Drawable
          public interface Drawable {
              void draw();
          }
          
          // Lớp Circle triển khai giao diện Drawable
          public class Circle implements Drawable {
              private double radius;
              
              // Constructor
              public Circle(double radius) {
                  this.radius = radius;
              }
              
              // Override phương thức draw từ giao diện Drawable
              @Override
              public void draw() {
                  System.out.println("Drawing Circle");
              }
          }
          
          // Lớp Rectangle triển khai giao diện Drawable
          public class Rectangle implements Drawable {
              private double width;
              private double height;
              
              // Constructor
              public Rectangle(double width, double height) {
                  this.width = width;
                  this.height = height;
              }
              
              // Override phương thức draw từ giao diện Drawable
              @Override
              public void draw() {
                  System.out.println("Drawing Rectangle");
              }
          }
          ```
| Đặc điểm           | Kế thừa Lớp                                      | Kế thừa Giao Diện                             |
|--------------------|---------------------------------------------------|------------------------------------------------|
| **Định nghĩa**     | Sử dụng từ khóa `extends` để kế thừa từ lớp khác | Sử dụng từ khóa `implements` để triển khai giao diện |
| **Đối tượng**      | Cho phép kế thừa các tính năng cụ thể của lớp cha | Yêu cầu triển khai lại tất cả phương thức của giao diện |
| **Đa kế thừa**     | Chỉ hỗ trợ kế thừa từ một lớp cha duy nhất         | Có thể triển khai nhiều giao diện cùng một lúc   |
| **Xây dựng lại**   | Các phương thức có thể được ghi đè (override)      | Các phương thức phải được triển khai một cách rõ ràng |
| **Cơ chế**         | Thừa hưởng tất cả các phương thức và thuộc tính không phải `private` của lớp cha | Phải triển khai lại toàn bộ các phương thức của giao diện, không kế thừa thuộc tính |
| **Mô hình sử dụng** | Thích hợp khi cần tái sử dụng code và chia sẻ tính năng giữa các lớp con | Thích hợp khi cần đảm bảo các lớp có cùng hành vi nhưng khác nhau về tính chất |
