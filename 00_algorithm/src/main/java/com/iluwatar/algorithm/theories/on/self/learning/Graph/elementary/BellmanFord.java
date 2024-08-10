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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.HashMap;
import java.util.Map;

/**
 * Tìm Đường Đi Ngắn Nhất
 *
 * Thuật toán Bellman-Ford
 * Tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh khác trong đồ thị có trọng số âm.
 */
public class BellmanFord {

    /**
     * Phương thức tĩnh để thực hiện thuật toán Bellman-Ford
     *
     * @param graph   Đồ thị cần tìm đường đi ngắn nhất
     * @param source  Đỉnh nguồn từ đó bắt đầu tìm kiếm
     * @return        Một bản đồ chứa khoảng cách ngắn nhất từ đỉnh nguồn đến từng đỉnh
     * @throws IllegalArgumentException Nếu đồ thị chứa chu trình trọng số âm
     */
    public static <T, W extends Number & Comparable<W>> Map<Vertex<T>, W> algorithm(Graph<T, W> graph, Vertex<T> source) {

        // Bản đồ để lưu trữ khoảng cách ngắn nhất từ đỉnh nguồn đến mỗi đỉnh
        Map<Vertex<T>, W> distance = new HashMap<>();

        // Khởi tạo khoảng cách từ đỉnh nguồn đến tất cả các đỉnh khác là vô cùng
        for (Vertex<T> vertex : graph.getVertices()) {
            distance.put(vertex, null);// null biểu thị cho vô cực
        }

        // Khoảng cách từ đỉnh nguồn đến chính nó là 0
        distance.put(source, AlgorithmUtils.defaultValue(graph)); // Giả định W là số nguyên

        // Số lượng đỉnh trong đồ thị
        int V = graph.getVertices().size();

        // Thực hiện thư giãn các cạnh V-1 lần
        for (int i = 1; i < V; i++) {
            for (Vertex<T> u : graph.getVertices()) {
                for (Edge<T, W> edge : graph.getEdges(u)) {
                    Vertex<T> v = edge.getEndVertex();
                    W weight = edge.getWeight();

                    // Nếu khoảng cách từ u đến v thông qua cạnh này ngắn hơn, cập nhật khoảng cách
                    if (distance.get(u) != null &&
                        (distance.get(v) == null || AlgorithmUtils.distance(distance.get(u), weight).compareTo(distance.get(v)) < 0)
                    ) {
                        distance.put(v, AlgorithmUtils.distance(distance.get(u), weight));
                    }
                }
            }
        }

        // Kiểm tra chu trình trọng số âm
        for (Vertex<T> u : graph.getVertices()) {
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getEndVertex();
                W weight = edge.getWeight();

                // Nếu phát hiện chu trình trọng số âm, ném ra ngoại lệ
                if (distance.get(u) != null && AlgorithmUtils.distance(distance.get(u), weight).compareTo(distance.get(v)) < 0) {
                    throw new IllegalArgumentException("Graph contains a negative-weight cycle");
                }
            }
        }

        // Trả về khoảng cách ngắn nhất từ đỉnh nguồn đến tất cả các đỉnh khác
        return distance;
    }


    public static void main(String[] args) {
        // Tạo đồ thị có hướng
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> vA = graph.addVertex("A");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");
        Vertex<String> vE = graph.addVertex("E");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 4.0);
        graph.addEdge("A", "C", 2.0);
        graph.addEdge("B", "C", 3.0);
        graph.addEdge("B", "D", 2.0);
        graph.addEdge("C", "B", 1.0);
        graph.addEdge("C", "D", 4.0);
        graph.addEdge("D", "E", 2.0);
        // Đổi trọng số của cạnh từ E đến D thành giá trị dương để tránh chu trình âm
        graph.addEdge("E", "D", 1.0);

        System.out.println(graph);

        // Chạy thuật toán Bellman-Ford từ đỉnh A
        try {
            Map<Vertex<String>, Double> shortestPaths = BellmanFord.algorithm(graph, vE);

            // In ra khoảng cách ngắn nhất từ đỉnh A đến tất cả các đỉnh khác
            for (Map.Entry<Vertex<String>, Double> entry : shortestPaths.entrySet()) {
                System.out.println("Shortest distance from E to " + entry.getKey().getTop() + " is " + entry.getValue());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
