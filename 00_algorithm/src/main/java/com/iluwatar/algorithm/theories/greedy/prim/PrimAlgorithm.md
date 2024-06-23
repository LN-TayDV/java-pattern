### README: Prim's Algorithm

## 1. Ý nghĩa của Thuật toán

Thuật toán Prim được sử dụng để tìm cây bao trùm nhỏ nhất trong một đồ thị vô hướng có trọng số. Cây bao trùm nhỏ nhất là một cây con của đồ thị ban đầu, bao gồm tất cả các đỉnh và một số cạnh, sao cho tổng trọng số của các cạnh đó là nhỏ nhất có thể.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Khởi tạo**: Khởi tạo một tập hợp con rỗng và một mảng `key[]` chứa trọng số của các đỉnh, với giá trị ban đầu là vô cùng.

2. **Chọn đỉnh xuất phát**: Chọn một đỉnh bất kỳ làm đỉnh xuất phát.

3. **Duyệt qua tất cả các đỉnh**: Duyệt qua tất cả các đỉnh của đồ thị.

4. **Cập nhật key và parent**: Cập nhật `key[]` và `parent[]` cho các đỉnh kề của đỉnh hiện tại, nếu cần thiết.

5. **Chọn đỉnh tiếp theo**: Chọn đỉnh kề có `key` nhỏ nhất từ tập hợp con chưa thêm vào cây bao trùm nhỏ nhất.

6. **Lặp lại bước 4 và 5 cho đến khi tất cả các đỉnh được thêm vào cây**.

### Ghi Nhớ Thuật Toán
- **Khởi tạo key và parent**: Khởi tạo `key[]` với giá trị vô cùng và `parent[]` là -1.
- **Chọn đỉnh xuất phát**: Bắt đầu từ một đỉnh bất kỳ.
- **Cập nhật key và parent**: Cập nhật `key[]` và `parent[]` cho các đỉnh kề của đỉnh hiện tại nếu cần thiết.
- **Chọn đỉnh tiếp theo**: Chọn đỉnh kề có `key` nhỏ nhất từ tập hợp con chưa thêm vào cây.
- **Lặp lại quá trình**: Lặp lại quá trình cho đến khi tất cả các đỉnh được thêm vào cây.

## 3. Ứng Dụng trong Thực Tế

Thuật toán Prim được sử dụng rộng rãi trong các lĩnh vực như:

1. **Mạng Lưới Giao Thông**: Xây dựng mạng lưới đường giao thông với chi phí xây dựng thấp nhất.
2. **Mạng Lưới Máy Tính**: Xây dựng mạng lưới máy tính với chi phí thiết lập kết nối thấp nhất.
3. **Thư Viện Đồ Thị**: Được sử dụng trong thư viện đồ thị để tìm cây bao trùm nhỏ nhất.

### Tóm Lược

Thuật toán Prim là một công cụ quan trọng để tìm kiếm cây bao trùm nhỏ nhất trong các hệ thống đồ thị có trọng số. Hiểu và ghi nhớ logic của thuật toán giúp áp dụng nó một cách hiệu quả vào nhiều vấn đề thực tế khác nhau.