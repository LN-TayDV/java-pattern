Để **ghi nhớ cách khai báo kiểu dữ liệu trong Java** và giải tốt các dạng bài tập trắc nghiệm kiểu này (như trong kỳ thi OCP Java SE 8), bạn có thể học theo 3 bước: **(1) bản chất logic, (2) mẹo ghi nhớ, (3) cách luyện đề**.

---

### 🎯 **1. Bản chất logic về khai báo kiểu dữ liệu trong Java**

#### 🔹 Kiểu nguyên thủy (primitive types):
| Loại      | Tên kiểu      | Ví dụ |
|-----------|----------------|-------|
| Số thực   | `float`        | `float f = 4.5f;` *(bắt buộc phải có `f` hoặc `F`)* |
|           | `double`       | `double d = 4.5;` *(mặc định số thực là `double`)* |
| Số nguyên | `byte`         | `byte b = 10;` |
|           | `short`        | `short s = 100;` |
|           | `int`          | `int i = 1000;` *(mặc định số nguyên là `int`)* |
|           | `long`         | `long l = 10000L;` *(nên có `L` để rõ ràng)* |
| Ký tự     | `char`         | `char c = 'A';` |
| Logic     | `boolean`      | `boolean b = true;` |

#### 🔸 Wrapper class (object version):
| Primitive | Wrapper class |
|-----------|----------------|
| `int`     | `Integer`      |
| `float`   | `Float`        |
| `double`  | `Double`       |
| `char`    | `Character`    |
| `boolean` | `Boolean`      |

> ⚠️ Lưu ý: `Object q = 4.0f;` **không hợp lệ trực tiếp** nếu không có ép kiểu hoặc autoboxing.

---

### 🧠 **2. Mẹo ghi nhớ nhanh**

#### ✅ **Mẹo 1: Nhớ hậu tố**
- `f` → float
- `L` → long
- Không hậu tố → `double` (cho số thực), `int` (cho số nguyên)

#### ✅ **Mẹo 2: Nhớ bảng tương đương với `var`**
| Câu lệnh `var` | Tương đương rõ kiểu |
|----------------|----------------------|
| `var x = 10;`  | `int x = 10;`        |
| `var x = 10L;` | `long x = 10L;`      |
| `var x = 4.0f;`| `float x = 4.0f;`    |
| `var x = 4.0;` | `double x = 4.0;`    |

#### ✅ **Mẹo 3: `var` suy luận kiểu tại compile-time**
- `var q = 4.0f;` ⇒ trình biên dịch tự hiểu `q` là kiểu `float`.

---

### 📝 **3. Cách luyện đề dạng này**

- Bước 1: Khi thấy `var x = <giá trị>;`, hãy xác định kiểu dữ liệu từ giá trị.
- Bước 2: Đối chiếu các lựa chọn xem cái nào **tương đương về kiểu** (không chỉ giống tên biến).
- Bước 3: Chú ý đến lỗi thường gặp: thiếu hậu tố `f`, dùng `Float` thay vì `float`, khai báo không tương thích.

---

### 🎯 Áp dụng lại vào câu hỏi bạn hỏi lúc đầu:
**Câu:**  
Which is equivalent to `var q = 4.0f;`?

#### ✅ Phân tích:
- `4.0f` → kiểu `float`
- Vậy câu tương đương là: `float q = 4.0f;`

#### 🅐 **Đáp án đúng:**
**A. float q = 4.0f;**

---
## 💡 Mẹo ghi nhớ cho dạng “does not compile”:

| Tình huống thường gặp | Mẹo nhận diện nhanh | Ví dụ lỗi |
|----------------------|---------------------|-----------|
| Khai báo nhiều biến cùng lúc với **kiểu khác nhau** | Tất cả biến phải cùng kiểu | `int a, double b;` ❌ |
| Sử dụng biến chưa khởi tạo trong code | Biến local phải được gán trước khi dùng | `int x; System.out.println(x);` ❌ |
| Sử dụng `var` không rõ ràng hoặc trộn kiểu | Không được trộn `var` và kiểu tường minh | `(var x, String y) -> {}` ❌ |
| Gọi method không đúng kiểu đối tượng | Kiểm tra kỹ loại đối tượng và method | `"hello".append("x")` ❌ |
| Truy cập biến/method không `static` trong method `static` | Không dùng trực tiếp biến/method non-static trong static | `main` method gọi biến instance ❌ |

---
Rất tuyệt! Đây là một trong những chủ đề quan trọng và hay bị nhầm trong Java: **`++a`, `a++`, `--a`, `a--`**. Mình sẽ giải thích một cách **cực kỳ dễ nhớ và rõ ràng** nhé 👇

---

## 🧠 Tổng quan: Tiền tố vs Hậu tố

| Biểu thức | Tên gọi       | Ý nghĩa                                          |
|-----------|----------------|--------------------------------------------------|
| `++a`     | Tiền tăng (`pre-increment`)  | Tăng `a` trước, rồi dùng giá trị mới của `a` |
| `a++`     | Hậu tăng (`post-increment`)  | Dùng giá trị cũ của `a`, rồi mới tăng `a`     |
| `--a`     | Tiền giảm (`pre-decrement`)  | Giảm `a` trước, rồi dùng giá trị mới          |
| `a--`     | Hậu giảm (`post-decrement`)  | Dùng giá trị cũ của `a`, rồi mới giảm `a`     |

---

## ✅ Ví dụ dễ hiểu:

```java
int a = 5;

System.out.println(++a); // 👉 6 (tăng trước, rồi in ra)
System.out.println(a);   // 👉 6 (a đã tăng)

a = 5;
System.out.println(a++); // 👉 5 (in ra trước, rồi mới tăng)
System.out.println(a);   // 👉 6 (sau đó mới tăng)
```

```java
int b = 5;

System.out.println(--b); // 👉 4 (giảm trước, rồi in)
System.out.println(b);   // 👉 4

b = 5;
System.out.println(b--); // 👉 5 (in ra rồi mới giảm)
System.out.println(b);   // 👉 4
```

---

## 🎯 Khi nào dùng cái nào?

| Trường hợp | Gợi ý |
|------------|-------|
| Bạn chỉ muốn tăng/giảm `a` và **không dùng ngay giá trị** | Dùng `a++` hoặc `a--` đều được |
| Bạn cần **dùng ngay giá trị đã thay đổi** | Dùng `++a` hoặc `--a` |

---

## ⚠️ Lưu ý khi dùng trong biểu thức

```java
int x = 5;
int y = ++x + 2;  // x tăng trước: x=6, y = 6 + 2 = 8

x = 5;
int z = x++ + 2;  // x tăng sau:  z = 5 + 2 = 7, x = 6
```

---

## ✅ Mẹo nhớ cực nhanh:

- `++a`: "**Tăng trước** → dùng sau" → giống kiểu bạn chuẩn bị kỹ trước khi làm
- `a++`: "**Dùng trước** → tăng sau" → giống kiểu vội vàng dùng xong mới tăng

---

Nếu bạn muốn có bài tập nhỏ để luyện hoặc minh họa bằng code `for`, `while`, thì mình có thể làm sẵn giúp luôn! 😄