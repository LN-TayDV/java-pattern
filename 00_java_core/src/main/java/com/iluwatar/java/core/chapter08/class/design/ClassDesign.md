### Thiết kế Lớp trong Java

- **Tạo và Sử Dụng Phương Thức**
    - Tạo phương thức với tham số và giá trị trả về
        - Ví dụ: Định nghĩa phương thức `add(int a, int b)` và `factorial(int n)` trong lớp `Calculator` để thực hiện phép cộng và tính giai thừa tương ứng.
          ```java
          public class Calculator {
              // Phương thức cộng hai số nguyên
              public int add(int a, int b) {
                  return a + b;
              }
              
              // Phương thức tính giai thừa của một số
              public int factorial(int n) {
                  if (n == 0 || n == 1) {
                      return 1;
                  } else {
                      return n * factorial(n - 1);
                  }
              }
          }
          ```

- **Tái Sử Dụng Triển Khai Qua Kế Thừa**
    - Lớp con và lớp cha
        - Ví dụ: `Dog` mở rộng từ `Animal`, lớp `Animal` có phương thức `makeSound()` bị ghi đè trong lớp `Dog`.
          ```java
          // Lớp cha
          public class Animal {
              // Phương thức phát ra tiếng kêu
              public void makeSound() {
                  System.out.println("Động vật kêu");
              }
          }
          
          // Lớp con mở rộng từ Animal
          public class Dog extends Animal {
              // Ghi đè phương thức makeSound để cho thú cưng sủa
              @Override
              public void makeSound() {
                  System.out.println("Chó sủa");
              }
          }
          ```
    - Đa hình
        - Ép kiểu và gọi phương thức
            - Ví dụ: `Animal myDog = new Dog();`, `((Dog) myDog).makeSound();`
            - Giải thích: Ở đây, `myDog` có kiểu dữ liệu `Animal` nhưng tham chiếu đến một đối tượng của lớp `Dog`. Bằng cách ép kiểu `(Dog) myDog`, chúng ta có thể gọi `makeSound()` dành riêng cho lớp `Dog`, thể hiện tính đa hình.

- **Phân Biệt Nạp Chồng, Ghi Đè và Che Dấu**
    - Nạp chồng
        - Định nghĩa nhiều phương thức cùng tên nhưng khác tham số (`add(int, int)` vs `add(double, double)`).
          ```java
          public class MathUtils {
              // Phương thức cộng hai số nguyên
              public int add(int a, int b) {
                  return a + b;
              }
          
              // Phương thức cộng hai số thực
              public double add(double a, double b) {
                  return a + b;
              }
          }
          ```
    - Ghi đè
        - Ví dụ: Ghi đè phương thức `makeSound()` trong lớp `Dog` để cung cấp hành vi cụ thể.
          ```java
          // Lớp cha
          public class Animal {
              // Phương thức phát ra tiếng kêu
              public void makeSound() {
                  System.out.println("Động vật kêu");
              }
          }
          
          // Lớp con mở rộng từ Animal
          public class Dog extends Animal {
              // Ghi đè phương thức makeSound để cho chó sủa
              @Override
              public void makeSound() {
                  System.out.println("Chó sủa");
              }
          }
          ```
    - Che Dấu
        - Ví dụ: Che dấu các phương thức tĩnh (`eat()`) trong các lớp con (`Dog`) bằng cách định nghĩa lại chúng.
          ```java
          // Lớp cha
          public class Animal {
              // Phương thức tĩnh ăn
              public static void eat() {
                  System.out.println("Động vật ăn");
              }
          }
          
          // Lớp con che dấu phương thức tĩnh eat() trong Animal
          public class Dog extends Animal {
              // Phương thức tĩnh che dấu eat() trong Animal
              public static void eat() {
                  System.out.println("Chó ăn xương");
              }
          }
          ```

Cây phân cấp này giúp bạn hiểu rõ các khái niệm cơ bản trong thiết kế lớp của Java, từ tạo và sử dụng phương thức đến kế thừa và đa hình, kèm theo các ví dụ minh họa và giải thích chi tiết.
