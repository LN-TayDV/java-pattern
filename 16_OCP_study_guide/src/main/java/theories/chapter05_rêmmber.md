Chủ đề **Core Java APIs** bao gồm các mục tiêu sau:

1. **Working with Java Primitive Data Types and String APIs**
    - Làm việc với các kiểu dữ liệu nguyên thủy và API của String
    - Tạo và thao tác với String
    - Manipulate dữ liệu với StringBuilder và các phương thức của nó

2. **Working with Java Arrays**
    - Khai báo, khởi tạo và sử dụng mảng một chiều
    - Khai báo, khởi tạo và sử dụng mảng hai chiều

3. **Programming Abstractly Through Interfaces**
    - Khai báo và sử dụng List và ArrayList

---

### 🔍 Các dạng đề chủ yếu trong **Core Java APIs**:

#### 1. **Làm việc với kiểu dữ liệu nguyên thủy và String**
- **Câu hỏi mẫu**:
    - Chọn kết quả đúng khi bạn thực hiện các phép toán trên các kiểu dữ liệu nguyên thủy.
    - Đoạn mã sau sẽ in ra kết quả gì?
      ```java
      String s = "OCP";
      s.concat(" Java");
      System.out.println(s);
      ```
    - Giải thích về sự khác biệt giữa `String` và `StringBuilder`.

- **Giải pháp**:
    - **String và StringBuilder**: `String` là immutable (không thay đổi), vì vậy phương thức `concat` không thay đổi nội dung của `s` mà tạo ra một đối tượng String mới. Ngược lại, `StringBuilder` là mutable, thay đổi trực tiếp giá trị trong đối tượng mà không tạo ra đối tượng mới.

#### 2. **Làm việc với mảng (Arrays)**
- **Câu hỏi mẫu**:
    - Chọn đoạn mã đúng để khai báo và khởi tạo mảng một chiều trong Java.
      ```java
      int[] arr = {1, 2, 3, 4};
      ```
    - Kết quả của đoạn mã sau là gì?
      ```java
      int[][] arr = new int[3][3];
      arr[0][0] = 1;
      arr[1][1] = 2;
      System.out.println(arr[1][1]);
      ```
    - Tìm lỗi trong đoạn mã sau khi sử dụng mảng hai chiều.

- **Giải pháp**:
    - Cấu trúc của mảng một chiều và hai chiều là rất quan trọng. Đảm bảo khi khai báo mảng hai chiều, bạn nhớ rằng mỗi hàng của mảng có thể có số lượng phần tử khác nhau (mảng không nhất thiết phải là hình vuông).

#### 3. **Làm việc với Interfaces và Collections (List, ArrayList)**
- **Câu hỏi mẫu**:
    - Chọn đoạn mã đúng để khai báo và sử dụng ArrayList trong Java.
      ```java
      ArrayList<Integer> list = new ArrayList<>();
      list.add(10);
      list.add(20);
      ```
    - Kết quả của đoạn mã sau là gì?
      ```java
      List<String> list = Arrays.asList("OCP", "Java", "Exam");
      list.add("Programming");
      System.out.println(list);
      ```
    - **Lỗi**: List được tạo từ `Arrays.asList()` là một List có kích thước cố định. Không thể thêm phần tử mới vào.

- **Giải pháp**:
    - **ArrayList** có thể thay đổi kích thước (dynamic array), nhưng nếu sử dụng `Arrays.asList()`, nó trả về một `List` cố định và không thể thay đổi kích thước. Bạn cần phải sử dụng một `ArrayList` nếu muốn thực hiện thao tác thêm hoặc xóa phần tử.

---

### 🧠 **Cách ghi nhớ bản chất và giải quyết các vấn đề**:

#### 1. **Làm việc với String và StringBuilder**:
- **Cách ghi nhớ**:
    - `String` là immutable - bạn không thể thay đổi đối tượng đã tạo, thay vào đó, các phương thức tạo ra đối tượng mới.
    - `StringBuilder` là mutable - có thể thay đổi đối tượng tại chỗ mà không tạo ra đối tượng mới.

- **Lưu ý**: Đọc kỹ các câu hỏi yêu cầu bạn nhận biết sự khác biệt giữa việc thao tác với String (immutable) và StringBuilder (mutable).

#### 2. **Làm việc với Mảng**:
- **Cách ghi nhớ**:
    - Mảng một chiều: `int[] arr = new int[]{1, 2, 3};`
    - Mảng hai chiều: `int[][] arr = new int[3][3];` (có thể là một mảng vuông hoặc không)
- **Lưu ý**: Chú ý về cú pháp khai báo mảng và cách thức truy cập phần tử trong mảng hai chiều.

#### 3. **Làm việc với List và ArrayList**:
- **Cách ghi nhớ**:
    - **ArrayList**: Có thể thêm, xóa phần tử và thay đổi kích thước.
    - **List**: Là một interface, có thể sử dụng ArrayList hoặc các triển khai khác của List.

- **Lưu ý**: Chú ý các câu hỏi liên quan đến cách sử dụng phương thức của List như `add()`, `remove()`, `get()`, v.v. Khi dùng `Arrays.asList()`, chú ý rằng bạn không thể thay đổi kích thước của list.

---

### 💻 **Mã nguồn mẫu**:

#### **Ví dụ với StringBuilder**:
```java
public class StringBuilderExample {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("OCP");
        sb.append(" Java");
        System.out.println(sb);  // Output: OCP Java
    }
}
```

#### **Ví dụ với mảng hai chiều**:
```java
public class ArrayExample {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];
        arr[0][0] = 1;
        arr[1][1] = 2;
        System.out.println(arr[1][1]);  // Output: 2
    }
}
```

#### **Ví dụ với ArrayList**:
```java
import java.util.*;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list);  // Output: [10, 20, 30]
    }
}
```

---

Đây là các ví dụ và phương pháp giải quyết các câu hỏi chủ yếu về **Core Java APIs**. Bạn có thể luyện tập và làm quen với những bài tập này để chuẩn bị cho kỳ thi OCP.