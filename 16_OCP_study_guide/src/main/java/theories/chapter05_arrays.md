Dưới đây là bản **tóm tắt toàn diện về Java Array**, bao gồm **kiến thức cốt lõi**, **cạm bẫy thường gặp**, và **mẹo ghi nhớ nhanh**, giúp bạn ôn thi hiệu quả cho chứng chỉ **OCP Java SE 8**:

---

## ✅ 1. Khái niệm và tạo mảng

- **Mảng** là một vùng nhớ trên heap, chứa **các phần tử cùng kiểu**, **có thứ tự** (indexed), và **có thể trùng lặp**.
- **Cách khai báo và khởi tạo**:
  ```java
  int[] a1 = new int[3];                 // mảng có 3 phần tử, mặc định = 0
  int[] a2 = new int[] {1, 2, 3};        // khai báo với giá trị
  int[] a3 = {1, 2, 3};                  // anonymous array
  ```
- **Vị trí dấu `[]` không quan trọng**:
  ```java
  int[] a; int a[]; int []a; // tất cả đều hợp lệ
  ```

---

## ✅ 2. Mảng của primitive và object

- **Primitive**: `int[]`, `char[]`, `double[]`...
- **Object**: `String[]`, `Object[]`, `YourClass[]`...
- Các phần tử của mảng **object** mặc định là `null`.

---

## ✅ 3. Truy cập và sử dụng

- **Chỉ số bắt đầu từ 0**, kết thúc ở `length - 1`.
- `arr.length` là **thuộc tính**, không phải phương thức (`()`).
- **Ví dụ**:
  ```java
  String[] birds = new String[6];
  System.out.println(birds.length); // 6
  ```

---

## ⚠️ 4. Các *cạm bẫy thường gặp*

### ❗ ArrayIndexOutOfBoundsException

```java
int[] numbers = new int[10];
numbers[10] = 5;                // Lỗi! Chỉ số 10 là invalid (chỉ có 0-9)
numbers[numbers.length] = 5;   // Lỗi tương tự
```

### ❗ So sánh mảng bằng `==` và `equals()`

```java
String[] bugs = { "cricket", "beetle" };
String[] alias = bugs;
System.out.println(bugs == alias);         // true (same reference)
System.out.println(bugs.equals(alias));    // true (vẫn là reference)
System.out.println(Arrays.toString(bugs)); // [cricket, beetle]
```

> ✅ **Ghi nhớ**: So sánh nội dung mảng = `Arrays.equals()`, in đẹp = `Arrays.toString()`.

---

## ❗ Gán mảng kiểu con vào kiểu cha – nguy hiểm

```java
String[] strings = {"a"};
Object[] objects = strings;
objects[0] = new StringBuilder(); // Runtime Error! ArrayStoreException
```

> ✅ **Ghi nhớ**: Biến có kiểu `Object[]` nhưng thực tế là `String[]` thì chỉ gán `String` được.

---

## ✅ 5. Ghi nhớ siêu nhanh (Mnemonics)

| Ghi nhớ | Ý nghĩa |
|--------|---------|
| **0-based** | Mảng bắt đầu từ chỉ số 0 |
| **length** | Không có dấu `()` vì là field |
| **init = 0/null** | Primitive → 0, Object → null |
| **OOBE** | Lỗi truy cập ngoài phạm vi → `ArrayIndexOutOfBoundsException` |
| **Alias trap** | `a = b;` là cùng tham chiếu |
| **Array != equals()** | `==` và `equals()` kiểm tra tham chiếu, không phải nội dung |

---

## ✅ 6. **Mảng đa chiều (Multidimensional Arrays)**

### Tạo mảng đa chiều:
Mảng đa chiều thực chất là một **mảng của mảng**, ví dụ mảng 2 chiều là mảng chứa các mảng khác.

#### Cách khai báo và khởi tạo:
```java
int[][] matrix = new int[3][4];  // Mảng 2 chiều: 3 hàng, 4 cột
```

- **Truy cập phần tử**: Sử dụng 2 chỉ số, ví dụ `matrix[2][3]` để truy cập phần tử ở hàng 2, cột 3.

#### Khởi tạo mảng đa chiều với giá trị:
```java
int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
```

- **Truy cập**:
  ```java
  System.out.println(matrix[1][2]); // 6 (hàng 1, cột 2)
  ```

### Cạm bẫy:
- **Mảng không đều** (ragged arrays): Mỗi hàng có thể có số cột khác nhau.
  ```java
  int[][] raggedArray = new int[2][];
  raggedArray[0] = new int[3];  // Hàng 0 có 3 phần tử
  raggedArray[1] = new int[2];  // Hàng 1 có 2 phần tử
  ```

---

## ✅ 7. **Sắp xếp (Sorting) Mảng**

- **Sắp xếp mảng** dùng `Arrays.sort()`:
  ```java
  int[] numbers = {5, 3, 8, 1};
  Arrays.sort(numbers);  // Sắp xếp theo thứ tự tăng dần
  System.out.println(Arrays.toString(numbers));  // [1, 3, 5, 8]
  ```

- **Sắp xếp mảng đối tượng**:
  ```java
  String[] words = {"banana", "apple", "cherry"};
  Arrays.sort(words);  // Sắp xếp theo thứ tự chữ cái
  ```

- **Sắp xếp ngược (Descending Order)**:
  Sử dụng `Comparator` để sắp xếp ngược:
  ```java
  Integer[] numbers = {5, 3, 8, 1};
  Arrays.sort(numbers, Collections.reverseOrder());  // Sắp xếp giảm dần
  ```

### Cạm bẫy:
- Mảng đã được sắp xếp theo mặc định là **tăng dần**. Nếu muốn sắp xếp theo thứ tự ngược lại, cần phải dùng **Comparator**.

---

## ✅ 8. **Tìm kiếm (Searching) Mảng**

### Tìm kiếm tuyến tính:
Sử dụng **tìm kiếm tuyến tính** với vòng lặp (Linear Search):
```java
int[] numbers = {10, 20, 30, 40};
for (int i = 0; i < numbers.length; i++) {
    if (numbers[i] == 30) {
        System.out.println("Found at index " + i);  // In ra chỉ số
    }
}
```

### Tìm kiếm nhị phân (Binary Search):
**Lưu ý**: Mảng phải được **sắp xếp** trước khi sử dụng tìm kiếm nhị phân.
```java
int[] numbers = {1, 3, 5, 7, 9};
int index = Arrays.binarySearch(numbers, 5);  // Tìm kiếm 5
System.out.println(index);  // In ra chỉ số của phần tử
```

### Cạm bẫy:
- **Tìm kiếm nhị phân** chỉ hiệu quả trên **mảng đã sắp xếp**. Nếu mảng không sắp xếp, bạn sẽ cần phải sử dụng **tìm kiếm tuyến tính**.

---

## ✅ 9. **So sánh Mảng**

### So sánh bằng `==` và `equals()`:
- **`==`** kiểm tra **tham chiếu** (kiểm tra xem hai mảng có trỏ đến cùng một đối tượng trong bộ nhớ hay không).
- **`equals()`** trong lớp **Object** không so sánh các phần tử của mảng, mà chỉ so sánh **tham chiếu**.

#### Ví dụ:
```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
System.out.println(a == b);         // false (tham chiếu khác nhau)
System.out.println(Arrays.equals(a, b)); // true (so sánh phần tử)
```

### So sánh với `Arrays.equals()`:
Để so sánh nội dung mảng (chứ không phải tham chiếu), dùng `Arrays.equals()`.

#### Ví dụ:
```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
System.out.println(Arrays.equals(a, b)); // true (so sánh phần tử)
```

### So sánh mảng đối tượng:
Mảng đối tượng là **tham chiếu**. Nếu bạn so sánh mảng đối tượng bằng `==`, bạn sẽ so sánh **tham chiếu**:
```java
String[] a = {"apple", "banana"};
String[] b = {"apple", "banana"};
System.out.println(a == b);        // false (khác tham chiếu)
System.out.println(Arrays.equals(a, b)); // true (so sánh nội dung)
```

---

## ⚠️ **Cạm bẫy khi so sánh mảng**:

- Khi so sánh mảng đối tượng, nếu sử dụng `==`, bạn sẽ so sánh **tham chiếu** thay vì **nội dung**.
- Để so sánh nội dung, luôn luôn sử dụng **`Arrays.equals()`**.

---

### 🧠 **Ghi nhớ nhanh**:
- **`==`**: So sánh **tham chiếu** (cùng đối tượng).
- **`Arrays.equals()`**: So sánh **nội dung** mảng (so sánh phần tử).
- **Sắp xếp**: `Arrays.sort()` cho mảng đã sắp xếp theo thứ tự tăng dần.
- **Tìm kiếm**: `Arrays.binarySearch()` (mảng phải đã sắp xếp).
- **Mảng đa chiều**: Cách khai báo và truy cập như mảng bình thường, nhưng sử dụng chỉ số nhiều hơn.

---

Nếu bạn cần thêm bất kỳ giải thích nào về các chủ đề trên hoặc có câu hỏi về **OCP Java SE 8**, đừng ngần ngại yêu cầu!