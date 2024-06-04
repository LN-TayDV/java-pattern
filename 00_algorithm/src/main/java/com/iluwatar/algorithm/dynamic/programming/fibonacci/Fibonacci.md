### README: Fibonacci Algorithm Using Dynamic Programming

## 1. Ý nghĩa của Thuật toán

Thuật toán Fibonacci sử dụng phương pháp lập trình động (dynamic programming) để tính số Fibonacci thứ `n`. Các số Fibonacci là một dãy số trong đó mỗi số là tổng của hai số trước đó, bắt đầu từ 0 và 1. Cụ thể:
- F(0) = 0
- F(1) = 1
- F(n) = F(n-1) + F(n-2) (với n ≥ 2)

### Bài toán Fibonacci cụ thể:
- **Đầu vào**: Một số nguyên `n`.
- **Đầu ra**: Số Fibonacci thứ `n`.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Khởi tạo mảng `fib`**:
    - Tạo một mảng `fib` có kích thước `n + 1` để lưu trữ các giá trị Fibonacci đã tính toán.
    - Đặt giá trị ban đầu: `fib[0] = 0` và `fib[1] = 1`.

2. **Tính toán các số Fibonacci**:
    - Sử dụng vòng lặp để tính toán các số Fibonacci từ `2` đến `n`.
    - Mỗi giá trị `fib[i]` được tính bằng tổng của hai giá trị trước đó: `fib[i] = fib[i - 1] + fib[i - 2]`.

3. **Trả về kết quả**:
    - Kết quả cuối cùng là giá trị `fib[n]`, đại diện cho số Fibonacci thứ `n`.

### Mã nguồn:
```java
package com.iluwatar.algorithm.dynamic.programming;

public class Fibonacci {
    // Phương thức để tính số Fibonacci thứ n
    public static int fibonacci(int n) {
        // Tạo một mảng để lưu trữ các giá trị Fibonacci đã tính
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        // Tính toán và lưu trữ các số Fibonacci từ 2 đến n
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        // Trả về số Fibonacci thứ n
        return fib[n];
    }

    public static void main(String[] args) {
        int n = 10; // Số Fibonacci cần tìm
        System.out.println("Số Fibonacci thứ " + n + " là: " + fibonacci(n));
    }
}
```

### Cách Ghi Nhớ Logic Thuật Toán:
1. **Khởi tạo mảng**:
    - Tạo mảng `fib` với kích thước `n+1`.
    - Đặt giá trị ban đầu: `fib[0] = 0` và `fib[1] = 1`.

2. **Duyệt qua các số từ 2 đến n**:
    - Sử dụng vòng lặp để tính `fib[i] = fib[i-1] + fib[i-2]` cho mọi `i` từ `2` đến `n`.

3. **Trả về kết quả**:
    - Trả về `fib[n]`.

### Ghi Nhớ Thuật Toán
- **Bước 1**: Khởi tạo mảng với kích thước `n+1`.
- **Bước 2**: Đặt giá trị ban đầu cho `fib[0]` và `fib[1]`.
- **Bước 3**: Tính các số Fibonacci từ `2` đến `n` bằng cách sử dụng công thức `fib[i] = fib[i-1] + fib[i-2]`.
- **Bước 4**: Trả về kết quả `fib[n]`.

## 3. Ứng Dụng trong Thực Tế

Thuật toán Fibonacci có nhiều ứng dụng trong thực tế:

1. **Khoa học máy tính**:
    - Tối ưu hóa các thuật toán và cấu trúc dữ liệu.
    - Phân tích độ phức tạp thời gian của các thuật toán đệ quy.

2. **Sinh học**:
    - Nghiên cứu các mô hình sinh trưởng của quần thể, như sự phát triển của thỏ trong các mô hình toán học.

3. **Tài chính**:
    - Dự đoán xu hướng thị trường tài chính thông qua phân tích kỹ thuật.

4. **Mật mã học**:
    - Ứng dụng trong việc tạo ra các số giả ngẫu nhiên và các thuật toán mật mã.

5. **Kỹ thuật**:
    - Thiết kế các hệ thống điều khiển tối ưu và các thuật toán điều khiển.

### Tóm Lại

Thuật toán Fibonacci sử dụng phương pháp lập trình động để tính toán các số Fibonacci một cách hiệu quả. Việc hiểu và nắm vững logic của thuật toán giúp bạn áp dụng nó vào nhiều bài toán và ứng dụng thực tế khác nhau, từ khoa học máy tính đến tài chính và kỹ thuật.