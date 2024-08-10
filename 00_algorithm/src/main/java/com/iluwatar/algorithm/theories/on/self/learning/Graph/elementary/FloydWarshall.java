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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class implementing the Floyd-Warshall algorithm for finding the shortest paths in a graph.
 */
public class FloydWarshall {

    /**
     * Phương thức tĩnh để thực hiện thuật toán Floyd-Warshall.
     *
     * @param graph   Đồ thị cần tìm đường đi ngắn nhất
     * @return        Một bản đồ chứa khoảng cách ngắn nhất giữa tất cả các cặp đỉnh
     * @throws IllegalArgumentException Nếu đồ thị chứa chu trình trọng số âm
     */
    public static <T, W extends Number & Comparable<W>> Map<Vertex<T>, Map<Vertex<T>, W>> algorithm(Graph<T, W> graph) {
        // Bản đồ lưu trữ khoảng cách ngắn nhất giữa tất cả các cặp đỉnh
        Map<Vertex<T>, Map<Vertex<T>, W>> distance = new HashMap<>();

        // Bản đồ lưu trữ đỉnh tiếp theo để tạo thành đường đi ngắn nhất
        Map<Vertex<T>, Map<Vertex<T>, Vertex<T>>> nextVertex = new HashMap<>();

        // Khởi tạo bản đồ khoảng cách và đường đi cho từng cặp đỉnh
        for (Vertex<T> u : graph.getVertices()) {
            Map<Vertex<T>, W> distMap = new HashMap<>();
            Map<Vertex<T>, Vertex<T>> nextMap = new HashMap<>();

            for (Vertex<T> v : graph.getVertices()) {
                if (u.equals(v)) {
                    // Khoảng cách từ đỉnh đến chính nó là 0
                    distMap.put(v, AlgorithmUtils.defaultValue(graph));
                    // Không cần đỉnh tiếp theo khi từ đỉnh đến chính nó
                    nextMap.put(v, null);
                } else {
                    // Khoảng cách từ đỉnh u đến v là vô cực (null)
                    distMap.put(v, null);
                    // Không có đường đi từ u đến v
                    nextMap.put(v, null);
                }
            }

            // Lưu trữ khoảng cách và đỉnh tiếp theo cho đỉnh u
            distance.put(u, distMap);
            nextVertex.put(u, nextMap);
        }

        // Cập nhật khoảng cách dựa trên các cạnh của đồ thị
        for (Vertex<T> u : graph.getVertices()) {
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getEndVertex();
                W weight = edge.getWeight();
                // Cập nhật khoảng cách từ u đến v là trọng số của cạnh
                distance.get(u).put(v, weight);
                // Đỉnh tiếp theo từ u đến v là v
                nextVertex.get(u).put(v, v);
            }
        }

        // Thực hiện thuật toán Floyd-Warshall
        int V = graph.getVertices().size(); // Số lượng đỉnh trong đồ thị
        List<Vertex<T>> verticesList = new ArrayList<>(graph.getVertices()); // Danh sách các đỉnh

        // Lặp qua tất cả các đỉnh k để cập nhật khoảng cách giữa các đỉnh i và j
        for (int k = 0; k < V; k++) {
            Vertex<T> kVertex = verticesList.get(k);

            for (int i = 0; i < V; i++) {
                Vertex<T> iVertex = verticesList.get(i);

                for (int j = 0; j < V; j++) {
                    Vertex<T> jVertex = verticesList.get(j);

                    // Nếu có đường đi từ i đến j thông qua k và nó ngắn hơn đường đi hiện tại từ i đến j
                    if (distance.get(iVertex).get(kVertex) != null && distance.get(kVertex).get(jVertex) != null) {

                        // Tính toán khoảng cách mới từ i đến j thông qua k
                        W newDist = AlgorithmUtils.distance(
                            distance.get(iVertex).get(kVertex),
                            distance.get(kVertex).get(jVertex)
                        );

                        // Nếu khoảng cách mới ngắn hơn khoảng cách hiện tại, cập nhật khoảng cách và đỉnh tiếp theo
                        if (distance.get(iVertex).get(jVertex) == null || newDist.compareTo(distance.get(iVertex).get(jVertex)) < 0) {
                            distance.get(iVertex).put(jVertex, newDist);
                            nextVertex.get(iVertex).put(jVertex, nextVertex.get(kVertex).get(jVertex));
                        }
                    }
                }
            }
        }

        // Kiểm tra chu trình trọng số âm
        for (Vertex<T> u : graph.getVertices()) {
            // Nếu khoảng cách từ u đến chính nó không phải là null (vô cực)
            if (distance.get(u).get(u) != null) {
                // So sánh khoảng cách từ u đến chính nó với giá trị mặc định (0)
                W distanceFromUToZero = AlgorithmUtils.distance(distance.get(u).get(u), AlgorithmUtils.defaultValue(graph));

                // Nếu khoảng cách từ u đến chính nó nhỏ hơn giá trị mặc định, có nghĩa là đồ thị chứa chu trình trọng số âm
                if (distanceFromUToZero.compareTo(distance.get(u).get(u)) < 0) {
                    throw new IllegalArgumentException("Graph contains a negative-weight cycle");
                }
            }
        }

        // Tạo bản đồ kết quả chứa danh sách các cặp đỉnh và khoảng cách ngắn nhất từ mỗi đỉnh
        Map<Vertex<T>, Map<Vertex<T>, W>> result = new HashMap<>();
        for (Vertex<T> u : graph.getVertices()) {
            Map<Vertex<T>, W> distMap = distance.get(u);
            Map<Vertex<T>, W> resultMap = new HashMap<>();
            for (Vertex<T> v : graph.getVertices()) {
                W dist = distMap.get(v);
                if (dist != null) {
                    resultMap.put(v, dist);
                }
            }
            result.put(u, resultMap);
        }

        // Trả về kết quả
        return result;
    }

    public static void main(String[] args) {
        // Tạo đồ thị có hướng
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> vA = graph.addVertex("A");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 3.0);
        graph.addEdge("A", "C", 7.0);
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("B", "D", 2.0);
        graph.addEdge("C", "D", 1.0);

        // In ra đồ thị
        System.out.println(graph);

        // Chạy thuật toán Floyd-Warshall
        try {
            Map<Vertex<String>, Map<Vertex<String>, Double>> shortestPaths = FloydWarshall.algorithm(graph);

            // In ra khoảng cách ngắn nhất giữa tất cả các cặp đỉnh
            shortestPaths.forEach((key, value) -> {
                Vertex<String> from = key;
                System.out.println("Distances from " + from.getTop() + ":");

                StringBuilder sb = new StringBuilder();
                sb.append("{\n ");
                sb.append(
                    value.entrySet()
                        .stream()
                        .map(e -> String.format("%s -> %s : (%s)", from.getTop(),  e.getKey().getTop(), e.getValue()))
                        .collect(Collectors.joining(" , \n "))

                );
                sb.append("\n}");

                System.out.println(sb.toString());
            });


        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
