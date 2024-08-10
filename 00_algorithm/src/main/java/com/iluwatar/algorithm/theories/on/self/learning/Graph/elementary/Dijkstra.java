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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Tìm Đường Đi Ngắn Nhất:
 *
 * Thuật toán Dijkstra
 * Tìm đường đi ngắn nhất từ một đỉnh nguồn đến tất cả các đỉnh khác trong đồ thị có trọng số không âm.
 */
public class Dijkstra {

    /**
     * Phương thức tĩnh để thực hiện thuật toán Dijkstra
     *
     * @param graph   Đồ thị cần tìm đường đi ngắn nhất
     * @param source  Đỉnh nguồn từ đó bắt đầu tìm kiếm
     * @return        Một bản đồ chứa khoảng cách ngắn nhất từ đỉnh nguồn đến từng đỉnh
     */
    public static <T, W extends Number & Comparable<W>> Map<Vertex<T>, W> algorithm(Graph<T, W> graph, Vertex<T> source) {

        // Bản đồ để lưu trữ khoảng cách ngắn nhất từ đỉnh nguồn đến mỗi đỉnh
        Map<Vertex<T>, W> distance = new HashMap<>();

        // Bản đồ để lưu trữ các đỉnh trước đó trên đường đi ngắn nhất
        Map<Vertex<T>, Vertex<T>> previous = new HashMap<>();

        // Hàng đợi ưu tiên để chọn đỉnh có khoảng cách ngắn nhất chưa được xử lý
        PriorityQueue<Vertex<T>> priorityQueue = new PriorityQueue<>(
            Comparator.comparing(
                distance::get,
                Comparator.nullsFirst(Comparator.naturalOrder())
            )
        );

        // Khởi tạo khoảng cách từ đỉnh nguồn đến tất cả các đỉnh khác là vô cùng
        for (Vertex<T> vertex : graph.getVertices()) {
            distance.put(vertex, null); // null biểu thị cho vô cực
            previous.put(vertex, null);
        }

        // Khoảng cách từ đỉnh nguồn đến chính nó là 0
        distance.put(source, AlgorithmUtils.defaultValue(graph)); // Giả định W là số nguyên
        priorityQueue.add(source);

        // Thực hiện lặp qua các đỉnh trong hàng đợi ưu tiên
        while (!priorityQueue.isEmpty()) {
            // Lấy đỉnh có khoảng cách ngắn nhất chưa được xử lý
            Vertex<T> u = priorityQueue.poll();

            // Duyệt qua tất cả các cạnh của đỉnh u
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getEndVertex();
                W weight = edge.getWeight();

                // Tính toán khoảng cách mới từ đỉnh nguồn đến v thông qua u
                W newDist = AlgorithmUtils.distance(distance.get(u), weight);

                // Nếu khoảng cách mới này ngắn hơn, cập nhật khoảng cách và đỉnh trước đó
                if (distance.get(v) == null || newDist.compareTo(distance.get(v)) < 0) {
                    distance.put(v, newDist);
                    previous.put(v, u);

                    // Thêm hoặc cập nhật đỉnh v vào hàng đợi ưu tiên
                    priorityQueue.add(v);
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
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 8.0);
        graph.addEdge("C", "E", 10.0);
        graph.addEdge("D", "E", 2.0);

        System.out.println(graph);

        // Chạy thuật toán Dijkstra từ đỉnh A
        Map<Vertex<String>, Double> shortestPaths = Dijkstra.algorithm(graph, vA);

        // In ra khoảng cách ngắn nhất từ đỉnh A đến tất cả các đỉnh khác
        for (Map.Entry<Vertex<String>, Double> entry : shortestPaths.entrySet()) {
            System.out.println("Shortest distance from A to " + entry.getKey().getTop() + " is " + entry.getValue());
        }
    }
}
