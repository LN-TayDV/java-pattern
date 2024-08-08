Một đồ thị (graph) là một cấu trúc dữ liệu quan trọng trong khoa học máy tính, được sử dụng để biểu diễn các mối quan hệ giữa các đối tượng. Các thành phần cơ bản của một đồ thị bao gồm:

### Các thành phần cơ bản của đồ thị
1. **Đỉnh (Vertex)**: Là các đối tượng hoặc điểm trong đồ thị. Đỉnh được ký hiệu là V. Mỗi đỉnh có thể được kết nối với một hoặc nhiều đỉnh khác.

2. **Cạnh (Edge)**: Là các kết nối giữa hai đỉnh trong đồ thị. Cạnh được ký hiệu là E. Cạnh có thể là:
    - **Cạnh có hướng (Directed Edge)**: Chỉ có thể đi theo một hướng từ đỉnh này đến đỉnh kia.
    - **Cạnh không hướng (Undirected Edge)**: Cho phép đi từ đỉnh này đến đỉnh kia và ngược lại.

3. **Trọng số (Weight)**: Một số đồ thị có cạnh được gán trọng số, thường đại diện cho chi phí hoặc khoảng cách giữa hai đỉnh.

4. **Đồ thị vô hướng (Undirected Graph)**: Đồ thị mà các cạnh không có hướng.

5. **Đồ thị có hướng (Directed Graph)**: Đồ thị mà các cạnh có hướng.

6. **Đường đi (Path)**: Một dãy các đỉnh và cạnh liên tiếp từ một đỉnh đến một đỉnh khác.

7. **Chu trình (Cycle)**: Một đường đi bắt đầu và kết thúc tại cùng một đỉnh.

### Các thuật toán trên đồ thị từ dễ đến nâng cao
1. **Duyệt đồ thị (Graph Traversal)**
    - **DFS (Depth-First Search)**: Duyệt đồ thị bằng cách đi theo chiều sâu từ đỉnh xuất phát, sau đó quay lại các đỉnh đã duyệt để khám phá các nhánh khác.
    - **BFS (Breadth-First Search)**: Duyệt đồ thị theo chiều rộng, khám phá tất cả các đỉnh cùng mức trước khi chuyển sang mức tiếp theo.

2. **Thuật toán tìm đường đi ngắn nhất**
    - **Dijkstra**: Tìm đường đi ngắn nhất từ một đỉnh đến tất cả các đỉnh khác trong đồ thị có trọng số không âm.
    - **Bellman-Ford**: Tìm đường đi ngắn nhất từ một đỉnh đến tất cả các đỉnh khác, có thể xử lý các cạnh có trọng số âm.
    - **Floyd-Warshall**: Tìm đường đi ngắn nhất giữa tất cả các cặp đỉnh trong đồ thị.

3. **Thuật toán tìm cây khung nhỏ nhất (Minimum Spanning Tree)**
    - **Kruskal**: Sử dụng chiến lược tham lam để chọn cạnh có trọng số nhỏ nhất, kết hợp các đỉnh thành một cây mà không tạo thành chu trình.
    - **Prim**: Tương tự Kruskal nhưng bắt đầu từ một đỉnh và mở rộng cây khung bằng cách thêm các cạnh nhỏ nhất kết nối với cây khung hiện có.

4. **Thuật toán tìm thành phần liên thông mạnh (Strongly Connected Components - SCC)**
    - **Kosaraju's Algorithm**: Dùng hai lần DFS để tìm tất cả các thành phần liên thông mạnh trong đồ thị có hướng.
    - **Tarjan's Algorithm**: Tìm SCCs hiệu quả với một lần duyệt DFS duy nhất.

5. **Thuật toán tô màu đồ thị (Graph Coloring)**
    - Giải quyết vấn đề phân màu sao cho không có hai đỉnh kề nhau cùng màu với ít số màu nhất có thể.

6. **Thuật toán tối ưu hóa trên đồ thị**
    - **Max-Flow (Ford-Fulkerson Algorithm)**: Tìm luồng cực đại từ một đỉnh nguồn đến đỉnh đích trong đồ thị dòng chảy.
    - **Edmonds-Karp**: Phiên bản cải tiến của Ford-Fulkerson dùng BFS để tìm đường tăng.

7. **Thuật toán tìm đường đi Euler và Hamilton**
    - **Eulerian Path/Circuit**: Tìm đường đi hoặc chu trình đi qua tất cả các cạnh của đồ thị một lần.
    - **Hamiltonian Path/Circuit**: Tìm đường đi hoặc chu trình đi qua tất cả các đỉnh của đồ thị một lần.

8. **Thuật toán tìm kiếm kết hợp trong đồ thị (Graph Matching)**
    - **Hungarian Algorithm**: Tìm kiếm kết hợp tối ưu trong đồ thị lưỡng phân (bipartite graph).
    - **Hopcroft-Karp**: Tìm kết hợp cực đại trong đồ thị lưỡng phân.

Những thuật toán này rất quan trọng và thường được áp dụng trong nhiều bài toán thực tế, từ lập kế hoạch đường đi, mạng xã hội, đến tối ưu hóa logistics.