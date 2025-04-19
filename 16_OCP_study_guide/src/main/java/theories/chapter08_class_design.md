Đây là **một chương cực kỳ quan trọng** trong Java về *kế thừa (inheritance)*. Dưới đây là bản **tóm tắt logic**, **cạm bẫy thường gặp**, và **mẹo nhớ nhanh** giúp bạn nắm chắc kiến thức chương này khi ôn luyện OCP Java SE 8.

---

## 🔑 **TÓM TẮT LOGIC CHƯƠNG: INHERITANCE**

### 1. **Định nghĩa Kế Thừa**
- Một class có thể **kế thừa** từ một class khác bằng từ khóa `extends`.
- Class kế thừa gọi là **subclass/child class**.
- Class bị kế thừa gọi là **superclass/parent class**.

```java
public class Animal { }
public class Dog extends Animal { } // Dog kế thừa Animal
```

### 2. **Phạm vi truy cập khi kế thừa**
| Access Modifier   | Có kế thừa được không? | Ghi chú |
|------------------|------------------------|--------|
| `public`         | ✅ Có thể truy cập ở mọi nơi |
| `protected`      | ✅ Truy cập trong package hoặc qua kế thừa |
| *(package-private)* | ⚠️ Chỉ truy cập trong cùng package |
| `private`        | ❌ Không thể truy cập trong subclass |

- 🧠 **Mẹo nhớ**: `public > protected > package-private > private` về mức độ truy cập.

---

### 3. **Kế thừa là chuyển tiếp (transitive)**
- Nếu `C extends B` và `B extends A` thì `C` cũng là con cháu của `A`.

```java
class A {}
class B extends A {}
class C extends B {} // C là hậu duệ của A và B
```

---

### 4. **Single vs Multiple Inheritance**
- Java chỉ hỗ trợ **single inheritance** (một cha trực tiếp duy nhất).
- Java **không hỗ trợ multiple inheritance giữa class** để tránh xung đột.
- Nhưng Java cho phép một class **implement nhiều interfaces**.

---

### 5. **Mặc định kế thừa từ `Object`**
- Nếu không khai báo `extends`, Java tự động thêm `extends Object`.
- Mọi class (trừ primitive) đều kế thừa từ `java.lang.Object`.

```java
public class MyClass { } 
// Tương đương với: public class MyClass extends Object { }
```

---

### 6. **Sử dụng `final` để chặn kế thừa**
- `final class` không thể bị kế thừa.

```java
final class Animal {}
class Dog extends Animal {} // ❌ Không hợp lệ
```

---

### 7. **Thực hành kế thừa**
```java
public class Animal {
  private int age;
  protected String name;

  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }
}

public class Lion extends Animal {
  public void setProperties(int age, String n) {
    setAge(age);     // OK: dùng phương thức public
    name = n;        // OK: dùng biến protected
    // age = age;    // ❌ Lỗi: age là private
  }
}
```

---

## ⚠️ **CẠM BẪY THƯỜNG GẶP**

| Cạm bẫy | Lý do | Ghi nhớ |
|--------|-------|----------|
| Truy cập trực tiếp biến `private` từ subclass | `private` không được kế thừa trực tiếp | Luôn dùng getter/setter |
| Nhầm giữa `protected` và `package-private` | `protected` = trong package + subclass khác package | Cẩn thận khi khác package |
| Quên `extends Object` là mặc định | Làm hiểu sai về quan hệ kế thừa | Java luôn tự động thêm |
| Tưởng `final` chỉ dùng cho biến | `final class` = không được kế thừa | `final` có 3 vai trò: biến, phương thức, class |
| Dùng được nhiều `extends` | Java chỉ cho `1 class`, nhiều interface | `extends` chỉ 1 class thôi |

---

## 🧠 **MẸO GHI NHỚ NHANH**

1. **Cha truyền con nối**: `public` và `protected` truyền được, `private` không truyền, `package-private` chỉ truyền nếu cùng package.
2. **Java một vợ một chồng**: Chỉ 1 `extends`, không đa thê (multiple inheritance class).
3. **Object là tổ tiên**: Mọi class đều từ `Object` mà ra.
4. **Getter/setter là cầu nối**: Nếu biến là `private`, phải dùng phương thức để truy cập.
5. **Kế thừa = nhận tài sản**: Con được tài sản (biến và phương thức) từ cha nếu được phép (public/protected).

---

Nếu bạn muốn, mình có thể giúp bạn tạo một sơ đồ mindmap hoặc flashcard để ôn luyện phần này hiệu quả hơn!