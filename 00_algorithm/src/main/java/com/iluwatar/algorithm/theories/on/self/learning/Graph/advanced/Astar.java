/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.algorithm.theories.on.self.learning.Graph.advanced;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary.AlgorithmUtils;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Path;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 *Tìm Đường Đi Tối Ưu Trong Đồ Thị
 *
 * Thuật toán A (A-star)*
 * Tìm đường đi tối ưu trong đồ thị dựa trên một hàm đánh giá (heuristic) và trọng số.
 */
public class Astar {

    // Phương thức chính của thuật toán A* tìm đường đi tối ưu
    public static <T, W extends Number & Comparable<W>> Path<T, W> algorithm(
        Graph<T, W> graph,
        Vertex<T> start,
        Vertex<T> goal,
        Heuristic<T, W> heuristic) {

        // Tập hợp mở (open set) chứa các đỉnh cần được khám phá
        Set<Vertex<T>> openSet = new HashSet<>();
        openSet.add(start);

        // Bản đồ lưu trữ g-cost (chi phí thực tế từ đỉnh bắt đầu đến mỗi đỉnh)
        Map<Vertex<T>, W> gCost = new HashMap<>();
        gCost.put(start, AlgorithmUtils.defaultValue(graph)); // Chi phí từ đỉnh bắt đầu đến chính nó là 0

        // Bản đồ lưu trữ f-cost (g-cost + h-cost) cho mỗi đỉnh
        Map<Vertex<T>, W> fCost = new HashMap<>();
        fCost.put(start, heuristic.estimate(start, goal)); // h-cost ước lượng từ đỉnh bắt đầu đến đích

        // Bản đồ để theo dõi các đỉnh cha để có thể xây dựng lại đường đi tối ưu
        Map<Vertex<T>, Vertex<T>> cameFrom = new HashMap<>();

        // PriorityQueue ưu tiên các đỉnh có f-cost thấp nhất
        PriorityQueue<Vertex<T>> openQueue = new PriorityQueue<>(Comparator.comparing(fCost::get));
        openQueue.add(start);

        // Bắt đầu vòng lặp tìm kiếm
        while (!openQueue.isEmpty()) {
            // Lấy đỉnh có f-cost thấp nhất
            Vertex<T> current = openQueue.poll();

            // Nếu đã đến đỉnh đích, xây dựng và trả về đường đi tối ưu
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current, gCost.get(goal), fCost);
            }

            // Loại bỏ đỉnh này khỏi tập hợp mở
            openSet.remove(current);

            // Duyệt qua các cạnh kề
            for (Edge<T, W> edge : graph.getEdges(current)) {
                Vertex<T> neighbor = edge.getEndVertex();

                //Chi phí tạm thời để đi từ đỉnh bắt đầu đến đỉnh neighbor qua đỉnh current.
                W tentativeGCost = AlgorithmUtils.sum(gCost.get(current), edge.getWeight());

                //So sánh và cập nhật: So sánh tentativeGCost với gCost hiện tại của neighbor.
                //Nếu tentativeGCost thấp hơn, cập nhật gCost và fCost cho neighbor.
                if (!gCost.containsKey(neighbor) || tentativeGCost.compareTo(gCost.get(neighbor)) < 0) {
                    // Cập nhật thông tin đường đi nếu chi phí mới thấp hơn
                    cameFrom.put(neighbor, current);
                    gCost.put(neighbor, tentativeGCost);
                    fCost.put(neighbor, AlgorithmUtils.sum(tentativeGCost, heuristic.estimate(neighbor, goal)));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                        // Thêm đỉnh vào hàng đợi ưu tiên để tiếp tục khám phá
                        openQueue.add(neighbor);
                    }
                }
            }
        }

        // Nếu không tìm thấy đường đi nào, trả về null
        return null;
    }


    /**
     * Phương thức xây dựng lại đường đi tối ưu từ đỉnh đích trở về đỉnh bắt đầu.
     *
     * @param cameFrom    Bản đồ chứa thông tin đỉnh cha của mỗi đỉnh
     * @param current     Đỉnh hiện tại (đích)
     * @param totalCost   Tổng chi phí của đường đi
     * @return            Đối tượng Path thể hiện đường đi tối ưu
     */
    private static <T, W extends Number & Comparable<W>> Path<T, W> reconstructPath(
        Map<Vertex<T>, Vertex<T>> cameFrom,
        Vertex<T> current,
        W totalCost,
        Map<Vertex<T>, W> fCost) {

        // Khởi tạo đường đi từ đỉnh đích
        Path<T, W> path = new Path<>(current, totalCost, null);

        while (cameFrom.containsKey(current)) {
            // Di chuyển ngược về đỉnh cha
            current = cameFrom.get(current);

            // Lấy giá trị fCost của đỉnh hiện tại
            W currentFCost = fCost.get(current);

            // Thêm đỉnh hiện tại vào đường đi
            path = new Path<>(current, currentFCost, path);
        }

        // Trả về đường đi tối ưu
        return path;
    }


    /**
     * Giao diện cho hàm đánh giá heuristic được sử dụng trong thuật toán A*.
     */
    @FunctionalInterface
    public interface Heuristic<T, W extends Number & Comparable<W>> {
        W estimate(Vertex<T> from, Vertex<T> to);
    }

    // Đoạn code main để kiểm tra thuật toán
    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> start = graph.addVertex("A");
        Vertex<String> goal = graph.addVertex("G");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");
        Vertex<String> vE = graph.addVertex("E");
        Vertex<String> vF = graph.addVertex("F");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "D", 2.0);
        graph.addEdge("C", "D", 1.0);
        graph.addEdge("D", "E", 3.0);
        graph.addEdge("E", "F", 2.0);
        graph.addEdge("F", "G", 1.0);
        graph.addEdge("C", "G", 7.0);

        // Định nghĩa hàm heuristic (ước lượng chi phí từ một đỉnh đến đích)
        Heuristic<String, Double> heuristic = (from, to) -> {
            // Đây chỉ là một ví dụ đơn giản
            // Bạn có thể thay đổi hàm này để phù hợp với ứng dụng thực tế
            if (from.getTop().equals("A") && to.getTop().equals("G")) return 7.0;
            if (from.getTop().equals("B") && to.getTop().equals("G")) return 6.0;
            if (from.getTop().equals("C") && to.getTop().equals("G")) return 3.0;
            if (from.getTop().equals("D") && to.getTop().equals("G")) return 4.0;
            if (from.getTop().equals("E") && to.getTop().equals("G")) return 2.0;
            if (from.getTop().equals("F") && to.getTop().equals("G")) return 1.0;
            return 0.0;
        };

        // Chạy thuật toán A*
        var path = Astar.algorithm(graph, start, goal, heuristic);

        System.out.println(graph);

        // In ra đường đi tối ưu
        if (path != null) {
            System.out.println(path.toString());
        } else {
            System.out.println("Không tìm thấy đường đi nào từ " + start.getTop() + " đến " + goal.getTop());
        }
    }

}
