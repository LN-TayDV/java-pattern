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