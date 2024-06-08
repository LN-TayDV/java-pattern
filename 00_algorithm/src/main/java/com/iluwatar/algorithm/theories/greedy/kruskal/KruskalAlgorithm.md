### README: Kruskal's Algorithm

## 1. Ý nghĩa của Thuật toán

Thuật toán Kruskal được sử dụng để tìm cây bao trùm nhỏ nhất trong một đồ thị có trọng số. Cây bao trùm nhỏ nhất là một tập hợp các cạnh không chứa chu trình, và có trọng số tổng nhỏ nhất so với tất cả các cây khác.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Sắp xếp các cạnh theo trọng số tăng dần**: Sắp xếp tất cả các cạnh của đồ thị theo thứ tự tăng dần của trọng số.

2. **Khởi tạo tập hợp con**: Khởi tạo mỗi đỉnh của đồ thị thành một tập hợp con riêng biệt.

3. **Chọn cạnh nhỏ nhất**: Chọn cạnh nhỏ nhất từ danh sách cạnh đã sắp xếp, kiểm tra xem việc thêm cạnh này vào cây có tạo ra chu trình không.

4. **Hợp nhất tập hợp con**: Hợp nhất hai tập hợp con chứa các đỉnh của hai đỉnh được nối bởi cạnh vừa chọn.

### Ghi Nhớ Thuật Toán
- **Sắp xếp cạnh**: Sắp xếp các cạnh theo trọng số tăng dần.
- **Khởi tạo tập hợp con**: Khởi tạo mỗi đỉnh là một tập hợp con.
- **Chọn cạnh nhỏ nhất và kiểm tra chu trình**: Chọn cạnh nhỏ nhất từ danh sách cạnh đã sắp xếp và kiểm tra xem việc thêm cạnh này có tạo ra chu trình không.
- **Hợp nhất tập hợp con**: Hợp nhất hai tập hợp con chứa các đỉnh của hai đỉnh được nối bởi cạnh vừa chọn.

## 3. Ứng Dụng trong Thực Tế

Thuật toán Kruskal có rất nhiều ứng dụng trong thực tế, bao gồm:

1. **Mạng Lưới Điện**: Xây dựng hệ thống mạng lưới điện với chi phí thấp nhất.
2. **Mạng Lưới Giao Thông**: Xây dựng hệ thống đường giao thông với chi phí xây dựng thấp nhất.
3. **Truyền Thông**: Xây dựng mạng truyền thông với băng thông cao nhất với chi phí thấp nhất.

### Tóm Lược

Thuật toán Kruskal là một công cụ mạnh mẽ để tìm kiếm cây bao trùm nhỏ nhất trong các hệ thống đồ thị có trọng số. Hiểu và ghi nhớ logic của thuật toán giúp áp dụng nó một cách hiệu quả vào nhiều vấn đề thực tế khác nhau.