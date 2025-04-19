
---

## 📌 **1. Access Modifiers – Quyền truy cập (Ai được gọi method?)**

| Modifier    | Ai có thể gọi?                                      | Ghi nhớ nhanh            |
|-------------|------------------------------------------------------|---------------------------|
| `private`   | Chỉ trong **cùng class**                             | 🔒 "Private room"         |
| *default*   | Cùng **package**, không cần keyword                  | 📦 "Gói nội bộ"           |
| `protected` | Cùng **package** + **subclass (dù khác package)**    | 🛡️ "Bảo vệ con cháu"       |
| `public`    | Mọi nơi đều gọi được                                | 🌍 "Public toàn cầu"      |

**⚠️ Bẫy thi**:
- `default` **không phải** là keyword hợp lệ → `default void walk()` ❌
- Sai thứ tự: `void public walk()` ❌ → đúng là `public void walk()`

---

## 📌 **2. Optional Specifiers – Từ khóa tùy chọn**

| Specifier      | Ý nghĩa                                      | Có trên OCP 8 |
|----------------|-----------------------------------------------|----------------|
| `static`       | Thuộc về class, không cần tạo object          | ✅             |
| `final`        | Không cho override trong subclass             | ✅             |
| `abstract`     | Không có thân method, dùng cho abstract class | ✅             |
| `synchronized` | Dùng cho đa luồng                             | ❌ (chỉ OCP 11)|
| `native`       | Liên kết với code C/C++                       | ❌             |
| `strictfp`     | Tính toán float/double ổn định                | ❌             |

✅ Bạn **có thể dùng nhiều specifier**, và **thứ tự không quan trọng**:
```java
public static final void walk() {}  // Hợp lệ
final public void walk() {}         // Cũng hợp lệ
```

**⚠️ Bẫy thi**:
- Sai thứ tự: `public void final walk()` ❌
- Dùng từ khóa không tồn tại: `public modifier void walk()` ❌

---

## 📌 **3. Return Type – Kiểu trả về**

| Kiểu trả về  | Ghi nhớ nhanh                                  |
|--------------|------------------------------------------------|
| `void`       | Không trả về gì                                |
| `String`, `int`, etc | Phải có `return` đúng kiểu bên trong body |

### ✅ Hợp lệ:
```java
public void walk() {}                         // Không cần return
public void walk2() { return; }               // Return rỗng cũng được
public String walk3() { return ""; }          // Phải return chuỗi
```

### ❌ Không hợp lệ:
```java
public String walk() {}                       // Thiếu return
public walk() {}                              // Thiếu kiểu trả về
public String int walk() {}                   // Quá nhiều kiểu trả về
String walk(int a) { if (a == 1) return ""; } // Thiếu return ở mọi nhánh
```

---

## 🧠 **Ghi nhớ bản chất – công thức viết method hợp lệ:**

```java
[access_modifier] [optional_specifier]* [return_type] methodName(parameters) { ... }
```

- **Access**: `public`, `protected`, *default*, `private`
- **Optional**: `static`, `final`, `abstract`, v.v.
- **Return**: `void` hoặc kiểu cụ thể (`int`, `String`...)

---

## 🎯 **Tips ghi nhớ – Cách tránh bẫy thi:**

- **Default không có keyword** → chỉ cần viết `void walk() {}` là đủ.
- **Kiểm tra thứ tự**: `modifiers → return type → method name`.
- **Abstract method** KHÔNG có thân → phải chấm phẩy `;`.
- **Return**: Nếu không `void` thì **bắt buộc phải có `return` đúng kiểu**.

---

Nếu bạn muốn mình làm một bảng tổng hợp PDF/Markdown dễ học trên tablet, mình có thể soạn ngay! Bạn muốn theo mẫu nào?