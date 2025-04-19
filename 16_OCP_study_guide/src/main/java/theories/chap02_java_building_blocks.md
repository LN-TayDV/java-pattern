# Tổng Hợp Kiến Thức: Tạo Đối Tượng Trong Java (Creating Objects)

## 1. Khởi tạo đối tượng (Calling Constructors)

- Muốn tạo đối tượng, dùng: `new ClassName()`
  ```java
  Park p = new Park();
  ```
- **Constructor**: Giống như một method, nhưng:
    - Tên trùng tên class
    - KHÔNG có kiểu trả về

### Ví dụ:
```java
public class Chick {
    public Chick() {
        System.out.println("in constructor");
    }
}
```

### Lỗi hay gặp:
```java
public void Chick() { } // KHÔNG phải constructor, chỉ là method
```

- Constructor dùng để khởi tạo field
- Có thể khởi tạo trực tiếp trên dòng khai báo:
```java
public class Chicken {
    int numEggs = 12; // khởi tạo ngay
    String name;

    public Chicken() {
        name = "Duke"; // khởi tạo trong constructor
    }
}
```

> Mặc định nếu bạn KHÔNG tạo constructor, compiler sẽ tự sinh một constructor rỗng.

---

## 2. Đọc/Ghi field của đối tượng (Reading/Writing Fields)

```java
public class Swan {
    int numberEggs; // instance variable

    public static void main(String[] args) {
        Swan mother = new Swan();
        mother.numberEggs = 1; // ghi
        System.out.println(mother.numberEggs); // đọc
    }
}
```

> Ghi field = set, đọc field = get

- Có thể đọc field trong dòng khởi tạo field mới:
```java
public class Name {
    String first = "Theodore";
    String last = "Moose";
    String full = first + last; // đọc 2 field đã có
}
```

---

## 3. Instance Initializer Block (Khối khởi tạo cục bộ)

- Code block nằm NGOÀI method, dùng {}:
```java
public class Bird {
    { System.out.println("Snowy"); } // instance initializer

    public static void main(String[] args) {
        { System.out.println("Feathers"); } // block bên trong method
    }
}
```
- Khối initializer KHÔNG được đặt trong method.

---

## 4. Thứ tự khởi tạo (Order of Initialization)

Khi Java tạo đối tượng:
1. Field được khởi tạo theo thứ tự trong file.
2. Instance initializer block được chạy.
3. Sau đó constructor mới được gọi.

### Ví dụ:
```java
public class Chick {
    private String name = "Fluffy";
    { System.out.println("setting field"); }
    public Chick() {
        name = "Tiny";
        System.out.println("setting constructor");
    }
    public static void main(String[] args) {
        Chick chick = new Chick();
        System.out.println(chick.name);
    }
}
```

**Output:**
```
setting field
setting constructor
Tiny
```

### Ví dụ khác:
```java
public class Egg {
    public Egg() { number = 5; }
    private int number = 3;
    { number = 4; }
    public static void main(String[] args) {
        Egg egg = new Egg();
        System.out.println(egg.number);
    }
}
```
**Output:** `5`

> Thứ tự: 3 (field) → 4 (initializer block) → 5 (constructor)

---

## Ghi nhớ nhanh:
- `new ClassName()` = gọi constructor
- Constructor KHÔNG có kiểu trả về
- Field và instance initializer chạy trước constructor
- Instance initializer block KHÔNG nằm trong method
- Đọc = get, Ghi = set
- Field có thể được khởi tạo ngay trên dòng khai báo hoặc trong constructor


# Java Data Types (Tóm tắt kiến thức OCP)

## 1. Kiểu dữ liệu trong Java
Java chia làm 2 loại kiểu dữ liệu:
- **Primitive types**: Kiểu nguyên thủy
- **Reference types**: Kiểu tham chiếu

---

## 2. Primitive Types (Kiểu nguyên thủy)
Java hỗ trợ 8 kiểu nguyên thủy:
- `byte`: 8-bit, từ -128 đến 127
- `short`: 16-bit
- `int`: 32-bit (giá trị mặc định khi viết số nguyên)
- `long`: 64-bit (cần thêm hậu tố `L`)
- `float`: 32-bit số thực, cần thêm hậu tố `f`
- `double`: 64-bit số thực
- `char`: ký tự Unicode
- `boolean`: `true` hoặc `false`

### Đặc điểm:
- Tất cả kiểu numeric đều là signed (có dấu).
- Kiểu nhỏ hơn sử dụng ít bit hơn, mỗi kiểu gấp đôi kiểu trước.

---

## 3. Viết Literal (Giá trị hằng)
- Mặc định literal nguyên là `int`
- Dùng `L` hoặc `l` cho `long`, nên dùng `L`
- Dùng các hệ đếm:
    - **Decimal** (hệ 10): mặc định
    - **Octal**: bắt đầu bằng `0`, vd: `017`
    - **Hexadecimal**: `0x` hoặc `0X`, vd: `0xFF`
    - **Binary**: `0b` hoặc `0B`, vd: `0b10`

### Dấu gạch dồi (`_`):
- Được dùng trong literal để dễ đọc
- KHÔNG được đặt:
    - Đầu số
    - Cuối số
    - Trước hoặc sau dấu `.` trong số thực

---

## 4. Reference Types (Kiểu tham chiếu)
- Là đối tượng (object) hoặc class
- Biến tham chiếu chỉ lưu địa chỉ bộ nhớ của object, không phải giá trị trực tiếp.
- Ví dụ:
```java
java.util.Date today = new java.util.Date();
String greeting = new String("How are you?");
```

---

## 5. Khác nhau giữa Primitive và Reference:
| Tiêu chí              | Primitive                 | Reference                  |
|------------------------|---------------------------|----------------------------|
| Lưu trực tiếp       | Đúcng                 | Sai (chỉ lưu tham chiếu) |
| Cho gán null           | KHÔNG được             | Được                    |
| Gọi phương thức      | KHÔNG được             | Được                    |
| Tên kiểu             | chữ thường              | chữ hoa                    |

---

## 6. Biến (Variables)
- Biến là tên gọi cho một vùng bộ nhớ.
- Cách khai báo:
```java
String zooName;
int numberAnimals;
```
- Cách khởi tạo:
```java
zooName = "The Best Zoo";
numberAnimals = 100;
```
- Cách khai báo và khởi tạo trong cùng lúc:
```java
String zooName = "The Best Zoo";
int numberAnimals = 100;
```

---

## Ghi nhớ:
- Wrapper classes như `Integer` dùng khi cần cho phép null hoặc sử dụng các phương thức.
- String KHÔNG phải kiểu nguyên thủy.



