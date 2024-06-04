### README: Depth-First Search (DFS) Algorithm

## 1. Ý nghĩa của Thuật toán

Thuật toán Depth-First Search (DFS) là một trong những thuật toán tìm kiếm đồ thị cơ bản nhất. Nó được sử dụng để duyệt và tìm kiếm trong một đồ thị, bắt đầu từ một đỉnh nguồn và đi sâu vào một nhánh của đồ thị trước khi quay lại và khám phá các nhánh khác.

## 2. Cách Ghi Nhớ Logic Thuật Toán

### Quy trình của thuật toán:

1. **Khởi tạo**:
    - Tạo một mảng boolean để đánh dấu các đỉnh đã được thăm.
    - Gọi hàm hỗ trợ DFS từ đỉnh nguồn.

2. **DFS Hỗ Trợ**:
    - Đánh dấu đỉnh hiện tại là đã thăm và in ra nó.
    - Duyệt qua tất cả các đỉnh kề của đỉnh hiện tại chưa được thăm và gọi đệ quy DFS trên chúng.

3. **Kết Thúc**:
    - Khi tất cả các đỉnh kề của đỉnh hiện tại đã được thăm, quay lại và tiếp tục duyệt đồ thị.

### Ghi Nhớ Thuật Toán
- **Bước 1**: Khởi tạo mảng đánh dấu và gọi DFS từ đỉnh nguồn.
- **Bước 2**: Đánh dấu đỉnh hiện tại và duyệt qua các đỉnh kề chưa được thăm.
- **Bước 3**: Lặp lại quá trình đệ quy cho tất cả các đỉnh kề.

## 3. Ứng Dụng trong Thực Tế

Thuật toán DFS có nhiều ứng dụng trong thực tế:

1. **Tìm kiếm và duyệt đồ thị**:
    - Sử dụng để tìm kiếm đường đi hoặc kiểm tra kết nối trong mạng lưới, dữ liệu biểu đồ, hoặc mô hình phân cấp.

2. **Tìm kiếm trong cơ sở dữ liệu**:
    - Áp dụng để truy vấn dữ liệu trong các cơ sở dữ liệu phân tán hoặc đám mây.

3. **Trò chơi và trí tuệ nhân tạo**:
    - Sử dụng trong việc tìm kiếm đường đi trong các trò chơi điện tử hoặc trong các thuật toán trí tuệ nhân tạo.

4. **Xử lý ngôn ngữ tự nhiên**:
    - Áp dụng để phân tích cấu trúc ngữ pháp hoặc tạo ra cây cú pháp cho ngôn ngữ tự nhiên.

### Tóm Lại

Thuật toán DFS là một công cụ quan trọng để duyệt và tìm kiếm đồ thị. Nó được sử dụng rộng rãi trong nhiều ứng dụng thực tế như tìm kiếm đường đi, kiểm tra kết nối mạng, xử lý dữ liệu và trò chơi điện tử. Hiểu và ghi nhớ logic của thuật toán DFS giúp bạn áp dụng nó hiệu quả trong nhiều tình huống và bài toán thực tế.