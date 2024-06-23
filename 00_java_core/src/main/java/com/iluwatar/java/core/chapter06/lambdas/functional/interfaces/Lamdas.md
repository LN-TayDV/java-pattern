# Cây lý thuyết về Lambdas và Functional Interfaces

## 1. Functional Interfaces
- **Understanding Functional Interfaces**
    - Định nghĩa và ý nghĩa của Functional Interface trong Java.
        - **Functional Interface**: Interface có duy nhất một phương thức trừu tượng (abstract method) được gọi là phương thức chức năng (functional method).
            - Ví dụ:
              ```java
              @FunctionalInterface
              public interface Calculator {
                  int calculate(int a, int b);
              }
              ```
            - Giải thích: Interface `Calculator` là một Functional Interface với phương thức `calculate` là phương thức chức năng.

## 2. Lambda Expression
- **Lambda Expression for Calculator**
    - Biểu thức Lambda được sử dụng để triển khai phương thức `calculate` của Functional Interface `Calculator`.
      ```java
      Calculator add = (a, b) -> a + b;
      ```
    - Ví dụ:
      ```java
      public class Main {
          public static void main(String[] args) {
              // Định nghĩa Lambda expression cho Calculator
              Calculator add = (a, b) -> a + b;

              // Gọi hàm calculate với tham số
              int result = add.calculate(10, 20);
              System.out.println("Result: " + result); // Output: Result: 30
          }
      }
      ```
      
## 3. Programming Abstractly Through Interfaces
- **Declare and use List and ArrayList instances**
    - Khai báo và sử dụng các thực thể List và ArrayList trong Java.
        - Ví dụ:
          ```java
          List<String> list = new ArrayList<>();
          list.add("Apple");
          list.add("Banana");
          System.out.println(list.get(0)); // Output: Apple
          ```

Trên đây là cấu trúc cây lý thuyết cho các kiến thức về Lambdas và Functional Interfaces trong Java. Mỗi phần được giải thích rõ ràng và cung cấp ví dụ minh họa để bạn dễ dàng hiểu và áp dụng vào thực hành khi lập trình. Các ví dụ này giúp bạn thấy rõ cách Lambdas và Functional Interfaces hoạt động và cách chúng được sử dụng để lập trình một cách trừu tượng và hiệu quả.
