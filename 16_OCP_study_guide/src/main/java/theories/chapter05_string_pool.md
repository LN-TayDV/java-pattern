
---

```markdown
# 📚 String Pool & So Sánh String trong Java

## 🧠 1. String Pool là gì?
- **String Pool (intern pool)** là vùng nhớ đặc biệt trong JVM dùng để **tái sử dụng** các chuỗi lặp lại (string literals).
- Tất cả **string literal** (ví dụ: `"hello"`) **tự động được lưu vào pool**.
- Các chuỗi được tạo bằng **phép nối, method, constructor** → **không tự động vào pool**.
-

## 🔍 2. So sánh bằng `==` vs `equals()`
- `==`: so sánh **địa chỉ (vị trí trong bộ nhớ)**.
- `.equals()`: so sánh **nội dung (giá trị ký tự)**.

👉 Do đó:
```java
String x = "Hello";
String y = "Hello";
System.out.println(x == y); // true (vì cùng pool)

String a = new String("Hello");
System.out.println(x == a); // false (new object)
```

## 🔥 3. Các tình huống thường gặp

### ✅ Cùng literal → dùng chung pool
```java
String x = "Java";
String y = "Java";
System.out.println(x == y); // true
```

### ❌ Khác cách tạo → khác địa chỉ
```java
String x = "Java";
String y = " JaVa ".trim(); // tính tại runtime
System.out.println(x == y); // false
```

### ❌ Phép nối tại runtime → không dùng pool
```java
String x = "hello";
String y = "he" + "llo"; // compile-time → pool
String z = "he";
z += "llo";              // runtime concat
System.out.println(x == y); // true
System.out.println(x == z); // false
```

---

## 💡 4. Dùng `intern()` để ép chuỗi vào pool
```java
String a = new String("Hi").intern();
String b = "Hi";
System.out.println(a == b); // true
```
- `intern()` sẽ kiểm tra xem có chuỗi giống vậy trong pool chưa:
    - Nếu có → trả về chuỗi trong pool
    - Nếu chưa → thêm chuỗi mới vào pool

---

## 🧩 5. Bẫy thường gặp trong đề thi
```java
String first = "rat" + 1;               // compile-time → vào pool
String second = "r" + "a" + "t" + "1";  // compile-time → vào pool
String third = "r" + "a" + "t" + new String("1"); // runtime → không vào pool

System.out.println(first == second);           // true
System.out.println(first == second.intern());  // true
System.out.println(first == third);            // false
System.out.println(first == third.intern());   // true
```

---

## ❗ Ghi nhớ quan trọng
- **String là immutable** → không thay đổi nội dung → JVM mới có thể tái sử dụng.
- Chỉ dùng `==` để so sánh **literal với literal** khi biết chắc chúng cùng pool.
- Tuyệt đối **không dùng `==` để so sánh chuỗi trong thực tế** → dùng `.equals()`.

📌 `intern()` và `==` chỉ dùng để hiểu lý thuyết và làm bài thi OCP!

```
---