# Cây kiến thức về Core Java APIs

## 1. Working with Java Primitive Data Types and String APIs
- **Create and manipulate Strings**
    - Tạo và thao tác với chuỗi trong Java.
        - Ví dụ:
          ```java
          String str1 = "Hello";
          String str2 = "World";
          String result = str1 + " " + str2; // Concatenation
          System.out.println(result); // Output: Hello World
          ```
- **Manipulate data using the StringBuilder class and its methods**
    - Sử dụng lớp StringBuilder để thay đổi nội dung chuỗi một cách hiệu quả.
        - Ví dụ:
          ```java
          StringBuilder sb = new StringBuilder("Java");
          sb.append(" is").append(" awesome"); // Thêm nội dung vào StringBuilder
          String result = sb.toString(); // Chuyển đổi StringBuilder thành String
          System.out.println(result); // Output: Java is awesome
          ```

## 2. Working with Java Arrays
- **Declare, instantiate, initialize and use a one-dimensional array**
    - Khai báo, khởi tạo và sử dụng mảng một chiều trong Java.
        - Ví dụ:
          ```java
          int[] numbers = new int[5]; // Khởi tạo mảng có 5 phần tử
          numbers[0] = 1; // Gán giá trị cho phần tử đầu tiên
          System.out.println(numbers[0]); // Output: 1
          ```
- **Declare, instantiate, initialize and use a two-dimensional array**
    - Khai báo, khởi tạo và sử dụng mảng hai chiều trong Java.
        - Ví dụ:
          ```java
          int[][] matrix = new int[3][3]; // Khởi tạo mảng hai chiều 3x3
          matrix[0][0] = 1; // Gán giá trị cho phần tử đầu tiên của mảng
          System.out.println(matrix[0][0]); // Output: 1
          ```

## 3. Programming Abstractly Through Interfaces
- **Declare and use List and ArrayList instances**
    - Khai báo và sử dụng các thực thể List và ArrayList trong Java.
        - Ví dụ:
          ```java
          List<String> list = new ArrayList<>(); // Khởi tạo ArrayList
          list.add("Apple"); // Thêm phần tử vào ArrayList
          list.add("Banana");
          System.out.println(list.get(0)); // Output: Apple
          ```

Trên đây là cấu trúc cây của các kiến thức về Core Java APIs, mỗi phần được giải thích ngắn gọn và cung cấp ví dụ minh họa để bạn dễ dàng hiểu và áp dụng vào thực hành khi lập trình bằng Java. Các ví dụ này giúp bạn thấy rõ cách các khái niệm và API hoạt động trong ngữ cảnh thực tế.
