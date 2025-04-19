### Tóm Tắt Ngắn Gọn về `ArrayList` trong Java

**1. Giới thiệu:**
- **ArrayList** là một danh sách có thể thay đổi kích thước động, lưu trữ các phần tử theo thứ tự và cho phép trùng lặp.
- **Import:** `import java.util.ArrayList;` hoặc `import java.util.*;`

**2. Cách Tạo `ArrayList`:**
- **Cơ bản:** `ArrayList<String> list = new ArrayList<>();`
- **Khi không chỉ định kiểu:** `ArrayList list = new ArrayList();` (Chứa mọi loại đối tượng).
- **Sao chép từ một ArrayList khác:** `ArrayList<String> copyList = new ArrayList<>(list);`

**3. Các Phương Thức Chính:**
- **add(E element):** Thêm phần tử vào cuối.
  ```java
  ArrayList<String> list = new ArrayList<>();
  list.add("apple");
  ```
- **remove(Object object):** Xóa phần tử đầu tiên tìm thấy.
  ```java
  list.remove("apple"); // Xóa "apple"
  ```
- **set(int index, E element):** Thay thế phần tử tại chỉ số.
  ```java
  list.set(0, "orange"); // Thay thế phần tử tại index 0 thành "orange"
  ```
- **size() và isEmpty():** Kiểm tra số lượng phần tử và nếu danh sách rỗng.
  ```java
  System.out.println(list.size()); // Số lượng phần tử
  System.out.println(list.isEmpty()); // true nếu rỗng
  ```

**4. Cạm Bẫy Thường Gặp:**

- **Sử dụng `var` với Diamond Operator mà không chỉ định kiểu:**
  ```java
  var list = new ArrayList<>(); // Kiểu của list là ArrayList<Object>
  list.add("string");
  for (String s : list) { }  // Lỗi: Không thể duyệt qua ArrayList<Object> với kiểu String
  ```
  **Lý do:** Java sẽ sử dụng `Object` làm kiểu mặc định, dẫn đến lỗi khi cố gắng lặp qua kiểu cụ thể.

- **Sử dụng `remove()` với chỉ số ngoài phạm vi:**
  ```java
  list.remove(100); // Lỗi: IndexOutOfBoundsException
  ```
  **Lý do:** Chỉ số 100 không tồn tại trong danh sách.

- **So sánh ArrayList với `equals()`:**
  ```java
  ArrayList<String> list1 = new ArrayList<>();
  ArrayList<String> list2 = new ArrayList<>();
  list1.add("a");
  list2.add("a");
  System.out.println(list1.equals(list2)); // true

  list1.add("b");
  list2.add(0, "b");
  System.out.println(list1.equals(list2)); // false
  ```
  **Lý do:** Hai ArrayList có cùng phần tử nhưng không cùng thứ tự sẽ không được coi là bằng nhau.

### Kết luận:
- **ArrayList** là công cụ mạnh mẽ để làm việc với danh sách động trong Java, nhưng cần chú ý tới việc sử dụng generics, chỉ số hợp lệ, và so sánh chính xác ArrayList.
  Dưới đây là **tóm tắt lý thuyết và các cạm bẫy** liên quan đến **Autoboxing, Unboxing**, **chuyển đổi giữa mảng và List**, và **sắp xếp List**, có **ví dụ minh họa** dễ hiểu:

---

## 🧠 **1. Autoboxing & Unboxing**
- **Autoboxing**: Tự động chuyển **primitive → wrapper** (int → Integer).
- **Unboxing**: Tự động chuyển **wrapper → primitive** (Integer → int).

### 🧪 Ví dụ:
```java
List<Integer> weights = new ArrayList<>();
Integer w = 50;         // autoboxing: int → Integer
weights.add(w);
weights.remove(50);     // autoboxing: int → Integer
double first = weights.get(0);  // unboxing + widening: Integer → int → double
```

### ⚠️ **Cạm bẫy:**
1. **Unboxing null gây lỗi:**
```java
List<Integer> heights = new ArrayList<>();
heights.add(null);
int h = heights.get(0); // ❌ NullPointerException
```

2. **remove(index) hay remove(object)?**
```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.remove(1); // ✅ Xóa phần tử ở vị trí index = 1, không phải giá trị 1!
System.out.println(numbers); // [1]

numbers.remove(new Integer(1)); // ✅ Xóa giá trị 1
```

---

## 🔄 **2. Chuyển đổi giữa Array và List**

### ➡️ **List → Array**
```java
List<String> list = new ArrayList<>();
list.add("hawk");
list.add("robin");
Object[] objectArray = list.toArray();               // [Object]
String[] stringArray = list.toArray(new String[0]);  // [String]
```

- ⚠️ Dùng `new String[0]` để Java tự tạo mảng đúng kích thước.
- Gọi `list.clear()` **không ảnh hưởng** đến mảng đã tạo (mảng là bản sao).

---

### ⬅️ **Array → List**

#### ✅ `Arrays.asList(array)` – backed List (liên kết với array, không thay đổi kích thước được)
```java
String[] array = {"hawk", "robin"};
List<String> list = Arrays.asList(array);
list.set(1, "test");   // OK, cập nhật cả list và array
array[0] = "new";      // OK, cập nhật cả array và list
list.remove(1);        // ❌ UnsupportedOperationException
```

#### ✅ `List.of(array)` – immutable List (không thay đổi nội dung & kích thước)
```java
String[] array = {"hawk", "robin"};
List<String> list = List.of(array);
array[0] = "new";      // OK, array thay đổi
list.set(1, "test");   // ❌ UnsupportedOperationException
```

#### ➕ Nếu muốn **thay đổi được**, cần sao chép:
```java
List<String> fixed = Arrays.asList("a", "b");
List<String> editable = new ArrayList<>(fixed);
```

---

## 🔢 **3. Varargs để tạo List nhanh**
```java
List<String> list1 = Arrays.asList("a", "b");
List<String> list2 = List.of("a", "b");
```
- ❗ Cả hai đều tạo **List cố định**, không thêm/xóa được.

---

## 📊 **4. Sắp xếp `ArrayList`**
```java
List<Integer> numbers = new ArrayList<>();
numbers.add(99);
numbers.add(5);
numbers.add(81);
Collections.sort(numbers); // [5, 81, 99]
```
- Sắp xếp tự nhiên theo thứ tự tăng dần (nếu là String thì theo bảng chữ cái).

---

## ✅ **Tóm tắt bảng chuyển đổi Array ↔ List:**

| Phương thức        | Thay đổi kích thước | Thay đổi giá trị | Ảnh hưởng qua lại |
|--------------------|---------------------|------------------|-------------------|
| `toArray()`        | ❌ Không             | ✅ Có            | ❌ Không          |
| `Arrays.asList()`  | ❌ Không             | ✅ Có            | ✅ Có             |
| `List.of()`        | ❌ Không             | ❌ Không         | ❌ Không          |

---

Muốn mình tạo thêm bảng tổng hợp nhanh (cheatsheet) không?