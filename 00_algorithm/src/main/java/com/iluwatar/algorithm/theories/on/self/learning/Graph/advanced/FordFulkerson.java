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
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Thuật toán tối ưu hóa trên đồ thị
 *
 * Max-Flow (Ford-Fulkerson Algorithm): Tìm luồng cực đại từ một đỉnh nguồn đến đỉnh đích trong đồ thị dòng chảy.
 */
public class FordFulkerson {

    /**
     * Phương thức tìm luồng cực đại từ đỉnh nguồn `source` đến đỉnh đích `sink` trong đồ thị `graph`.
     * @param graph Đồ thị chứa các đỉnh và cạnh với trọng số (sức chứa).
     * @param source Đỉnh nguồn.
     * @param sink Đỉnh đích.
     * @return Luồng cực đại từ đỉnh nguồn đến đỉnh đích dưới dạng kiểu dữ liệu W.
     */
    public static <T, W extends Number & Comparable<W>> W algorithm(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink) {
        // Khởi tạo luồng cực đại với giá trị ban đầu là 0
        W maxFlow = AlgorithmUtils.defaultValue(graph);

        // Tạo một bản sao của đồ thị để lưu trữ dung lượng dư (residual capacities) của các cạnh
        Graph<T, W> residualGraph = createResidualGraph(graph);

        // Tạo một bản đồ để lưu thông tin về cha của các đỉnh khi tìm đường đi tăng cường
        Map<Vertex<T>, Edge<T, W>> parentMap = new HashMap<>();

        // Lặp lại cho đến khi không còn đường đi tăng cường nào
        while (bfs(residualGraph, source, sink, parentMap)) {
            // Tìm dung lượng nhỏ nhất trên đường đi tăng cường
            W pathFlow = AlgorithmUtils.defaultValue(graph); // Khởi tạo với giá trị lớn nhất

            // Tìm dung lượng nhỏ nhất của các cạnh trên đường đi từ đỉnh đích quay về đỉnh nguồn
            Vertex<T> v = sink;
            while (!v.equals(source)) {
                Edge<T, W> edge = parentMap.get(v);
                pathFlow = AlgorithmUtils.min(pathFlow, edge.getWeight());
                v = edge.getFromVertex();
            }

            // Cập nhật dung lượng dư của các cạnh và cạnh ngược lại
            v = sink;
            while (!v.equals(source)) {
                Edge<T, W> edge = parentMap.get(v);

                // Cập nhật dung lượng của cạnh
                W newWeight = AlgorithmUtils.subtract(edge.getWeight(), pathFlow);
                edge.setWeight(newWeight);

                // Cập nhật dung lượng của cạnh ngược
                Vertex<T> u = edge.getFromVertex();
                Edge<T, W> reverseEdge = residualGraph.getEdge(v, u);

                if (reverseEdge == null) {
                    // Nếu cạnh ngược không tồn tại, thêm nó vào đồ thị với dung lượng bằng `pathFlow`
                    residualGraph.addEdge(v.getTop(), u.getTop(), pathFlow);
                } else {
                    // Cập nhật dung lượng của cạnh ngược
                    reverseEdge.setWeight(AlgorithmUtils.sum(reverseEdge.getWeight(), pathFlow));
                }

                v = u;
            }

            // Cộng dung lượng của đường đi vào luồng cực đại
            maxFlow = AlgorithmUtils.sum(maxFlow, pathFlow);
        }

        // Trả về luồng cực đại tìm được
        return maxFlow;
    }

    /**
     * Tạo đồ thị dung lượng dư từ đồ thị ban đầu.
     * @param graph Đồ thị ban đầu.
     * @return Đồ thị dung lượng dư.
     */
    private static <T, W extends Number & Comparable<W>> Graph<T, W> createResidualGraph(Graph<T, W> graph) {
        Graph<T, W> residualGraph = new Graph<>(true); // Đồ thị có hướng

        for (Vertex<T> vertex : graph.getVertices()) {
            for (Edge<T, W> edge : graph.getEdges(vertex)) {
                residualGraph.addVertex(vertex.getTop());
                residualGraph.addVertex(edge.getToVertex().getTop());
                residualGraph.addEdge(vertex.getTop(), edge.getToVertex().getTop(), edge.getWeight());
            }
        }

        return residualGraph;
    }

    /**
     * Thực hiện tìm kiếm theo chiều rộng (BFS) để tìm đường đi từ nguồn đến đích trong đồ thị dung lượng dư.
     * @param graph Đồ thị dung lượng dư.
     * @param source Đỉnh nguồn.
     * @param sink Đỉnh đích.
     * @param parentMap Bản đồ lưu trữ thông tin cha của các đỉnh trên đường đi.
     * @return true nếu tìm thấy đường đi từ nguồn đến đích, ngược lại false.
     */
    private static <T, W extends Number & Comparable<W>> boolean bfs(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink, Map<Vertex<T>, Edge<T, W>> parentMap) {
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        // Bắt đầu từ đỉnh nguồn
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();

            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getToVertex();

                // Nếu chưa thăm đỉnh v và dung lượng của cạnh u -> v > 0
                if (!visited.contains(v) && edge.getWeight().compareTo(AlgorithmUtils.defaultValue(graph)) > 0) {
                    parentMap.put(v, edge);
                    visited.add(v);
                    queue.add(v);

                    // Nếu đỉnh đích được tìm thấy, kết thúc BFS
                    if (v.equals(sink)) {
                        return true;
                    }
                }
            }
        }

        // Không tìm thấy đường đi từ nguồn đến đích
        return false;
    }

    public static void main(String[] args) {
        // Tạo đồ thị có hướng với trọng số là số thực (Double)
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh vào đồ thị
        Vertex<String> s = graph.addVertex("S");
        Vertex<String> source = graph.addVertex("A");
        Vertex<String> b = graph.addVertex("B");
        Vertex<String> c = graph.addVertex("C");
        Vertex<String> sink = graph.addVertex("T");

        // Thêm các cạnh với trọng số (dung lượng)
        graph.addEdge("S", "A", 10.0);
        graph.addEdge("S", "B", 5.0);
        graph.addEdge("A", "B", 15.0);
        graph.addEdge("A", "C", 10.0);
        graph.addEdge("B", "C", 10.0);
        graph.addEdge("B", "T", 10.0);
        graph.addEdge("C", "T", 10.0);

        System.out.println(graph);

        // Chạy thuật toán Ford-Fulkerson để tìm luồng cực đại từ đỉnh nguồn đến đỉnh đích
        Double maxFlow = FordFulkerson.algorithm(graph, source, sink);

        // In ra kết quả
        System.out.println("Luồng cực đại từ đỉnh nguồn '" + source.getTop() + "' đến đỉnh đích '" + sink.getTop() + "' là: " + maxFlow);
    }
}
