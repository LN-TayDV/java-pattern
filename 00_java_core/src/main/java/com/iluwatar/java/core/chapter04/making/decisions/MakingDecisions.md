# Cây kiến thức về câu lệnh điều khiển và vòng lặp trong Java

## 1. Câu lệnh điều khiển (Control Statements)
- **if, if/else, switch**
    - **if**: Dùng để kiểm tra một điều kiện và thực thi một khối lệnh nếu điều kiện đúng.
        - Ví dụ:
          ```java
          int x = 10;
          if (x > 5) {
              System.out.println("x is greater than 5");
          }
          ```
    - **if/else**: Dùng để kiểm tra một điều kiện và thực thi một khối lệnh nếu điều kiện đúng và một khối lệnh khác nếu điều kiện sai.
        - Ví dụ:
          ```java
          int x = 3;
          if (x % 2 == 0) {
              System.out.println("x is even");
          } else {
              System.out.println("x is odd");
          }
          ```
    - **switch**: Dùng để lựa chọn giữa các lựa chọn dựa trên giá trị của biến hoặc biểu thức.
        - Ví dụ:
          ```java
          int day = 3;
          switch (day) {
              case 1:
                  System.out.println("Monday");
                  break;
              case 2:
                  System.out.println("Tuesday");
                  break;
              default:
                  System.out.println("Other day");
          }
          ```

## 2. Vòng lặp (Loops)
- **for**: Dùng để lặp qua một tập hợp các lệnh một số lần xác định trước.
    - Ví dụ:
      ```java
      for (int i = 0; i < 5; i++) {
          System.out.println("Iteration " + i);
      }
      ```
- **while**: Dùng để lặp lại một khối lệnh miễn là điều kiện là đúng.
    - Ví dụ:
      ```java
      int i = 0;
      while (i < 5) {
          System.out.println("Iteration " + i);
          i++;
      }
      ```
- **do/while**: Dùng để thực hiện một khối lệnh ít nhất một lần và lặp lại nó miễn là điều kiện là đúng.
    - Ví dụ:
      ```java
      int i = 0;
      do {
          System.out.println("Iteration " + i);
          i++;
      } while (i < 5);
      ```
- **for each**: Dùng để lặp qua các phần tử của một mảng hoặc một collection.
    - Ví dụ:
      ```java
      int[] numbers = {1, 2, 3, 4, 5};
      for (int number : numbers) {
          System.out.println(number);
      }
      ```

## 3. Lệnh break và continue
- **break**: Dùng để kết thúc một vòng lặp hoặc một câu lệnh switch.
    - Ví dụ:
      ```java
      for (int i = 0; i < 10; i++) {
          if (i == 5) {
              break;
          }
          System.out.println(i);
      }
      ```
- **continue**: Dùng để bỏ qua phần còn lại của vòng lặp và chuyển đến lần lặp tiếp theo.
    - Ví dụ:
      ```java
      for (int i = 0; i < 10; i++) {
          if (i % 2 == 0) {
              continue;
          }
          System.out.println(i);
      }
      ```

Trên đây là cấu trúc cây của các kiến thức về câu lệnh điều khiển và vòng lặp trong Java. Mỗi phần được giải thích chi tiết và cung cấp ví dụ minh họa để bạn dễ dàng hiểu và áp dụng vào thực tế khi lập trình bằng Java. Cấu trúc này giúp bạn hình dung toàn bộ phạm vi của các chủ đề này và cách chúng hoạt động một cách logic và có tổ chức.
