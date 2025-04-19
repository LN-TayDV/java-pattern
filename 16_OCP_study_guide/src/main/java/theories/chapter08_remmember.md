Chủ đề **Class Design** trong OCP exam bao gồm một số mục tiêu quan trọng như tạo và sử dụng phương thức, kế thừa, và đa hình. Để giúp bạn ghi nhớ và hiểu rõ về chủ đề này, dưới đây là cách tiếp cận logic và các dạng bài tập thường gặp.

---

### 🔑 **Cách tiếp cận logic cho chủ đề Class Design**

1. **Tạo và Sử dụng Phương thức**:
    - **Constructor và Phương thức**:
        - Constructor được sử dụng để khởi tạo đối tượng của lớp. Một lớp có thể có nhiều constructor với các đối số khác nhau (overloading).
        - Phương thức được sử dụng để thực hiện hành động, có thể có hoặc không có giá trị trả về và có thể nhận đối số.

2. **Kế thừa và Sử dụng lại Cài đặt qua Inheritance**:
    - **Superclasses và Subclasses**:
        - Lớp con (subclass) kế thừa thuộc tính và phương thức từ lớp cha (superclass). Lớp con có thể sử dụng các phương thức và trường của lớp cha, đồng thời có thể ghi đè (override) các phương thức của lớp cha để thay đổi hành vi.

    - **Polymorphism (Đa hình)**:
        - **Method Overriding**: Lớp con có thể ghi đè phương thức của lớp cha để thực hiện hành động đặc biệt.
        - **Casting và gọi phương thức**: Polymorphism cho phép bạn làm việc với các đối tượng theo cách tổng quát hơn, nơi tham chiếu của đối tượng có thể là kiểu lớp cha, nhưng đối tượng thực tế là kiểu lớp con.

3. **Phân biệt Overloading, Overriding và Hiding**:
    - **Overloading**: Là việc tạo ra nhiều phương thức có cùng tên nhưng khác nhau về số lượng hoặc kiểu tham số trong cùng một lớp.
    - **Overriding**: Là việc lớp con ghi đè phương thức của lớp cha, phương thức phải có cùng tên và kiểu trả về.
    - **Hiding**: Xảy ra khi một trường (field) trong lớp con có cùng tên với trường của lớp cha, nhưng không có sự kế thừa về dữ liệu (chỉ có sự che giấu tên biến).

---

### 🔍 **Các dạng bài tập liên quan đến Class Design**

#### 1. **Tạo và Sử dụng Phương thức và Constructor**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào chỗ trống để tạo constructor và phương thức trong lớp `Rectangle`:
      ```java
      public class Rectangle {
          private int width;
          private int height;

          // Constructor
          public ____ Rectangle(int width, int height) {
              this.width = width;
              this.height = height;
          }

          // Phương thức tính diện tích
          public ____ area() {
              return width * height;
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Constructor trong Java có thể có gì?
            - A. Tham số
            - B. Giá trị trả về
            - C. Tên giống lớp
            - D. Cả A và C đúng
    - **Giải thích**: Constructor trong Java không có giá trị trả về và tên giống với tên lớp.

#### 2. **Reusing Implementations Through Inheritance**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào chỗ trống để tạo lớp `Dog` kế thừa từ lớp `Animal`:
      ```java
      public class Animal {
          public void speak() {
              System.out.println("Animal speaks");
          }
      }

      public class Dog ____ Animal {
          @Override
          public void speak() {
              System.out.println("Dog barks");
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Inheritance trong Java cho phép lớp con làm gì?
            - A. Sử dụng phương thức của lớp cha
            - B. Ghi đè phương thức của lớp cha
            - C. Tạo một lớp hoàn toàn mới
            - D. Cả A và B đều đúng
    - **Giải thích**: Kế thừa cho phép lớp con sử dụng và ghi đè phương thức của lớp cha.

#### 3. **Polymorphism (Đa hình)**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào chỗ trống để sử dụng polymorphism và gọi phương thức `speak` từ đối tượng `Dog` thông qua biến kiểu `Animal`:
      ```java
      public class Animal {
          public void speak() {
              System.out.println("Animal speaks");
          }
      }

      public class Dog extends Animal {
          @Override
          public void speak() {
              System.out.println("Dog barks");
          }
      }

      public class TestPolymorphism {
          public static void main(String[] args) {
              Animal animal = new Dog();
              animal.speak();  // Gọi phương thức speak() của Dog
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Polymorphism cho phép chúng ta làm gì?
            - A. Chạy một phương thức trong nhiều lớp khác nhau
            - B. Chạy phương thức của lớp con thông qua tham chiếu lớp cha
            - C. Khởi tạo đối tượng mà không cần phương thức khởi tạo
            - D. Cả A và B đều đúng
    - **Giải thích**: Polymorphism cho phép gọi phương thức của lớp con thông qua tham chiếu lớp cha.

#### 4. **Overloading, Overriding và Hiding**
- **Câu hỏi mẫu**:
    - **Điền vào chỗ trống**: Điền vào chỗ trống để phân biệt việc **overloading** và **overriding**:
      ```java
      class Vehicle {
          public void move() {
              System.out.println("Vehicle moves");
          }

          // Overloading: Tạo phương thức với tham số khác
          public void move(int speed) {
              System.out.println("Vehicle moves at speed " + speed);
          }
      }

      class Car extends Vehicle {
          @Override
          public void move() {
              System.out.println("Car moves");
          }
      }
      ```
    - **Câu hỏi trắc nghiệm**:
        - Phân biệt giữa **overloading**, **overriding**, và **hiding**?
            - A. **Overloading**: Nhiều phương thức cùng tên nhưng khác tham số
            - B. **Overriding**: Lớp con ghi đè phương thức của lớp cha
            - C. **Hiding**: Lớp con che giấu trường của lớp cha
            - D. Tất cả A, B, C đều đúng
    - **Giải thích**: Overloading là nạp chồng phương thức trong cùng một lớp; Overriding là ghi đè phương thức của lớp cha trong lớp con; Hiding xảy ra khi một trường trong lớp con che giấu trường trong lớp cha.

---

### 🧠 **Cách ghi nhớ chủ đề Class Design**:

1. **Tạo và sử dụng phương thức và constructor**:
    - Constructor được sử dụng để khởi tạo đối tượng, và phương thức có thể nhận đối số hoặc trả về giá trị.
    - **Overloading** giúp tạo ra nhiều phương thức với tên giống nhau nhưng khác tham số.

2. **Kế thừa và sử dụng lại cài đặt**:
    - Lớp con kế thừa phương thức và trường của lớp cha.
    - **Polymorphism** cho phép bạn gọi phương thức của lớp con thông qua tham chiếu của lớp cha, giúp tăng tính linh hoạt và giảm sự phụ thuộc vào kiểu cụ thể của đối tượng.

3. **Overloading, Overriding và Hiding**:
    - **Overloading** xảy ra trong cùng một lớp khi phương thức có tên giống nhau nhưng khác tham số.
    - **Overriding** là khi lớp con ghi đè phương thức của lớp cha.
    - **Hiding** là khi trường của lớp con che giấu trường của lớp cha.

---

### 📌 **Mã nguồn mẫu**:

#### **Overloading và Overriding**:
```java
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }

    public void speak(String sound) {
        System.out.println("Animal makes a sound: " + sound);
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Dog barks");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.speak();  // Overriding
        animal.speak("Growl");  // Overloading
    }
}
```

---