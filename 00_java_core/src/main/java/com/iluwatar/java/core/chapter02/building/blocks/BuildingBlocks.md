# Biến Java

- Loại biến
    - Biến cục bộ
        - Khối lệnh
        - Phương thức
    - Biến toàn cục
    - Tham số phương thức
    - Biến thành viên lớp

- Phạm vi của biến
    - Biến cục bộ
        - Khối lệnh
        - Phương thức
    - Biến toàn cục
    - Tham số phương thức

- Quản lý và hủy biến
    - Phạm vi và vòng đời biến cục bộ
        - Khai báo và khởi tạo
        - Sử dụng biến trong phạm vi
        - Kết thúc phạm vi và thu gom rác (garbage collection)
    - Quản lý bộ nhớ

Tất nhiên! Để giải thích sơ đồ cây về biến trong Java một cách chi tiết hơn, chúng ta sẽ đi vào từng phần của sơ đồ:

### Biến Java

**Loại biến**:
- **Biến cục bộ (Local Variables)**: Được khai báo và chỉ có thể sử dụng trong một phương thức, một khối lệnh hoặc một constructor. Ví dụ:

  ```java
  public void exampleMethod() {
      int localVar = 10; // Biến cục bộ trong phương thức
      // localVar chỉ có thể sử dụng trong phương thức này
  }
  ```

- **Biến toàn cục (Global Variables)**: Là biến được khai báo ngoài các phương thức và được sử dụng trong toàn bộ lớp. Chúng có thể được truy cập từ bất kỳ đâu trong lớp đó. Ví dụ:

  ```java
  public class ExampleClass {
      static int globalVar = 20; // Biến toàn cục
      public void exampleMethod() {
          System.out.println(globalVar); // Có thể truy cập biến toàn cục trong phương thức
      }
  }
  ```

- **Tham số phương thức (Method Parameters)**: Là các biến được khai báo trong danh sách tham số của phương thức và được truyền vào khi gọi phương thức. Ví dụ:

  ```java
  public void exampleMethod(int param1, String param2) {
      // param1 và param2 là các tham số phương thức
      System.out.println(param1 + " " + param2);
  }
  ```

- **Biến thành viên lớp (Class Member Variables)**: Là các biến được khai báo trong lớp nhưng ngoài các phương thức, thường được sử dụng để lưu trữ trạng thái của đối tượng. Chúng có thể được truy cập từ bất kỳ phương thức nào trong lớp đó. Ví dụ:

  ```java
  public class ExampleClass {
      int instanceVar; // Biến thành viên lớp
      public void setInstanceVar(int value) {
          instanceVar = value; // Gán giá trị cho biến thành viên lớp
      }
  }
  ```

**Phạm vi của biến**:
- **Biến cục bộ**: Có thể có phạm vi là một khối lệnh (block scope) hoặc một phương thức (method scope). Biến cục bộ được khai báo trong một khối lệnh chỉ có thể sử dụng trong khối lệnh đó và biến được khai báo trong phương thức chỉ có thể sử dụng trong phương thức đó.

- **Biến toàn cục**: Có thể được truy cập từ bất kỳ đâu trong lớp nơi chúng được khai báo.

- **Tham số phương thức**: Được sử dụng để truyền giá trị vào phương thức khi gọi nó và có thể được sử dụng bên trong phương thức đó.

**Quản lý và hủy biến**:
- **Phạm vi và vòng đời của biến cục bộ**:
    - Biến cục bộ được khai báo và khởi tạo trong một phạm vi nhất định và tồn tại cho đến khi phạm vi đó kết thúc. Ví dụ, khi một phương thức kết thúc, các biến cục bộ của nó sẽ không còn tồn tại và sẽ được thu gom bởi bộ thu gom rác.

- **Quản lý bộ nhớ**:
    - Trong Java, bộ nhớ được quản lý tự động thông qua bộ thu gom rác (garbage collector). Bộ thu gom rác tự động thu gom các đối tượng không còn được sử dụng nữa để giải phóng bộ nhớ, giúp ứng dụng Java không bị rò rỉ bộ nhớ và hoạt động hiệu quả hơn.

Sơ đồ cây này giúp bạn hiểu rõ cách các biến hoạt động và được quản lý trong Java, từ khai báo đến phạm vi sử dụng và quản lý bộ nhớ. Điều này làm nền tảng cho việc lập trình Java hiệu quả và tránh các vấn đề liên quan đến quản lý bộ nhớ và phạm vi biến.