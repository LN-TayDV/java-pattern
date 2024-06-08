### README: Dijkstra's Algorithm

## 1. Ý nghĩa của Thuật toán

Thuật toán Dijkstra được sử dụng để tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh còn lại trong đồ thị có trọng số dương. Thuật toán này là một trong những phương pháp phổ biến và hiệu quả nhất để giải quyết vấn đề đường đi ngắn nhất.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Khởi tạo**: Khởi tạo mảng `dist` để lưu trữ khoảng cách ngắn nhất từ đỉnh nguồn đến tất cả các đỉnh. Cũng khởi tạo mảng `mstSet` để đánh dấu các đỉnh đã được xác định khoảng cách ngắn nhất.

2. **Khởi tạo đỉnh nguồn**: Đặt khoảng cách từ đỉnh nguồn đến chính nó bằng 0.

3. **Tìm đường đi ngắn nhất cho tất cả các đỉnh**: Lặp qua tất cả các đỉnh và cập nhật khoảng cách từ đỉnh nguồn đến các đỉnh kề của đỉnh vừa thăm. Lựa chọn đỉnh có khoảng cách ngắn nhất từ tập các đỉnh chưa được thăm.

4. **Cập nhật và in kết quả**: Cập nhật mảng `dist` và in khoảng cách ngắn nhất từ đỉnh nguồn đến tất cả các đỉnh.

### Ghi Nhớ Thuật Toán
- **Khởi tạo**: Khởi tạo mảng `dist` và `mstSet` để lưu trữ và đánh dấu thông tin.
- **Tìm kiếm đỉnh có khoảng cách ngắn nhất**: Sử dụng hàm `minKey` để chọn đỉnh có khoảng cách ngắn nhất từ tập các đỉnh chưa được thăm.
- **Cập nhật khoảng cách**: Cập nhật khoảng cách từ đỉnh nguồn đến các đỉnh kề của đỉnh vừa thăm.
- **In kết quả**: In khoảng cách ngắn nhất từ đỉnh nguồn đến tất cả các đỉnh.

## 3. Ứng Dụng trong Thực Tế

Thuật toán Dijkstra có rất nhiều ứng dụng trong thực tế, bao gồm:

1. **Mạng Lưới Giao Thông**: Tìm đường đi ngắn nhất giữa các điểm giao thông như đường, giao lộ.
2. **Mạng Lưới Máy Tính**: Xác định đường đi ngắn nhất giữa các nút mạng trong hệ thống mạng lưới máy tính.
3. **Lập Kế Hoạch Giao Hàng**: Tính toán tuyến đường giao hàng ngắn nhất giữa các điểm đến.
4. **Thuật toán Định tuyến trong Mạng Lưới**: Xác định đường đi ngắn nhất giữa các nút trong mạng lưới để chuyển gói tin.

### Tóm Lược

Dijkstra's Algorithm là một công cụ mạnh mẽ để tìm kiếm đường đi ngắn nhất trong các hệ thống đồ thị có trọng số dương. Hiểu và ghi nhớ logic của thuật toán giúp áp dụng nó một cách hiệu quả vào nhiều vấn đề thực tế khác nhau.
------------------
Chắc chắn rồi! Hãy cùng xem xét một ví dụ cụ thể để hiểu rõ hơn về thuật toán Dijkstra. Giả sử chúng ta có một đồ thị với các đỉnh và trọng số của các cạnh như sau:

```
      2       1
  A ----- B ----- D
   \      |      /
    3     4     2
     \    |    /
      C ------
      |        3
      5
      |
      E
```

Các đỉnh là A, B, C, D, và E. Trọng số của các cạnh được biểu diễn như sau:
- A - B: 2
- A - C: 3
- B - C: 4
- B - D: 1
- C - D: 2
- C - E: 5
- D - E: 3

Chúng ta sẽ tìm đường đi ngắn nhất từ đỉnh A đến tất cả các đỉnh còn lại.

### Bước 1: Khởi tạo

- Đặt khoảng cách từ A đến A là 0, và khoảng cách từ A đến tất cả các đỉnh khác là vô hạn:
    - dist[A] = 0
    - dist[B] = ∞
    - dist[C] = ∞
    - dist[D] = ∞
    - dist[E] = ∞

- Tập hợp các đỉnh chưa được xử lý: {A, B, C, D, E}

### Bước 2: Chọn đỉnh gần nhất chưa được xử lý

- Đỉnh A có khoảng cách nhỏ nhất (0), chúng ta chọn đỉnh A.

### Bước 3: Cập nhật khoảng cách

- Xét các cạnh kề của A:
    - A -> B: khoảng cách từ A đến B qua A là 0 + 2 = 2 (cập nhật dist[B] từ ∞ xuống 2)
    - A -> C: khoảng cách từ A đến C qua A là 0 + 3 = 3 (cập nhật dist[C] từ ∞ xuống 3)

- Cập nhật các khoảng cách:
    - dist[A] = 0
    - dist[B] = 2
    - dist[C] = 3
    - dist[D] = ∞
    - dist[E] = ∞

- Tập hợp các đỉnh chưa được xử lý: {B, C, D, E}

### Bước 4: Lặp lại quá trình

1. Chọn đỉnh có khoảng cách nhỏ nhất trong tập hợp chưa được xử lý, ở đây là đỉnh B (2).
2. Xét các cạnh kề của B:
    - B -> D: khoảng cách từ A đến D qua B là 2 + 1 = 3 (cập nhật dist[D] từ ∞ xuống 3)
    - B -> C: khoảng cách từ A đến C qua B là 2 + 4 = 6 (không cập nhật vì dist[C] hiện tại là 3, nhỏ hơn 6)

- Cập nhật các khoảng cách:
    - dist[A] = 0
    - dist[B] = 2
    - dist[C] = 3
    - dist[D] = 3
    - dist[E] = ∞

- Tập hợp các đỉnh chưa được xử lý: {C, D, E}

3. Chọn đỉnh có khoảng cách nhỏ nhất trong tập hợp chưa được xử lý, ở đây là đỉnh C (3).
4. Xét các cạnh kề của C:
    - C -> D: khoảng cách từ A đến D qua C là 3 + 2 = 5 (không cập nhật vì dist[D] hiện tại là 3, nhỏ hơn 5)
    - C -> E: khoảng cách từ A đến E qua C là 3 + 5 = 8 (cập nhật dist[E] từ ∞ xuống 8)

- Cập nhật các khoảng cách:
    - dist[A] = 0
    - dist[B] = 2
    - dist[C] = 3
    - dist[D] = 3
    - dist[E] = 8

- Tập hợp các đỉnh chưa được xử lý: {D, E}

5. Chọn đỉnh có khoảng cách nhỏ nhất trong tập hợp chưa được xử lý, ở đây là đỉnh D (3).
6. Xét các cạnh kề của D:
    - D -> E: khoảng cách từ A đến E qua D là 3 + 3 = 6 (cập nhật dist[E] từ 8 xuống 6)

- Cập nhật các khoảng cách:
    - dist[A] = 0
    - dist[B] = 2
    - dist[C] = 3
    - dist[D] = 3
    - dist[E] = 6

- Tập hợp các đỉnh chưa được xử lý: {E}

7. Chọn đỉnh có khoảng cách nhỏ nhất trong tập hợp chưa được xử lý, ở đây là đỉnh E (6).

- Không có cạnh kề nào để cập nhật.

### Kết quả cuối cùng:
- Khoảng cách ngắn nhất từ đỉnh A đến các đỉnh khác:
    - dist[A] = 0
    - dist[B] = 2
    - dist[C] = 3
    - dist[D] = 3
    - dist[E] = 6

Vậy là chúng ta đã tìm được đường đi ngắn nhất từ đỉnh A đến tất cả các đỉnh còn lại trong đồ thị sử dụng thuật toán Dijkstra.
