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
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Tìm Cây Khung Nhỏ Nhất Với Điều Kiện Đặc Biệt
 *
 * Thuật toán Edmonds-Karp
 * Giải quyết bài toán dòng chảy tối đa (Maximum Flow) trong đồ thị.
 */
public class EdmondsKarp {

    /**
     * Thuật toán Edmonds-Karp để tìm dòng chảy tối đa trong một đồ thị.
     *
     * @param graph - Đồ thị đầu vào với các đỉnh và cạnh.
     * @param source - Đỉnh nguồn (s).
     * @param sink - Đỉnh đích (t).
     * @param zero - Giá trị zero của loại W, dùng cho khởi tạo.
     * @param infinity - Giá trị vô cực của loại W, dùng để biểu thị một dòng chảy rất lớn.
     * @return Giá trị dòng chảy tối đa dạng W.
     */
    public static <T, W extends Number & Comparable<W>> W algorithm(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink, W zero, W infinity) {
        W maxFlow = zero;

        while (true) {
            // Tìm đường tăng cường trong đồ thị
            Map<Vertex<T>, Vertex<T>> parentMap = bfsFindAugmentingPath(graph, source, sink, zero);
            if (parentMap == null) {
                // Nếu không còn đường tăng cường, kết thúc thuật toán
                break;
            }

            // Tính dòng chảy tối đa có thể cho đường tăng cường
            W pathFlow = calculatePathFlow(graph, source, sink, parentMap, infinity, zero);

            // Cập nhật dòng chảy trong đồ thị
            maxFlow = AlgorithmUtils.sum(maxFlow, pathFlow);
        }

        return maxFlow;
    }

    /**
     * Hàm BFS để tìm đường tăng cường từ nguồn đến đích.
     */
    private static <T, W extends Number & Comparable<W>> Map<Vertex<T>, Vertex<T>> bfsFindAugmentingPath(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink, W zero) {
        Map<Vertex<T>, Vertex<T>> parentMap = new HashMap<>();
        Map<Vertex<T>, W> pathFlow = new HashMap<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        queue.offer(source);
        parentMap.put(source, null);
        pathFlow.put(source, zero);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();

            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getEndVertex();
                W residualCapacity = edge.getWeight();

                if (!parentMap.containsKey(v) && residualCapacity.compareTo(zero) > 0) {
                    parentMap.put(v, u);
                    pathFlow.put(v, AlgorithmUtils.min(pathFlow.get(u), residualCapacity));
                    if (v.equals(sink)) {
                        return parentMap;
                    }
                    queue.offer(v);
                }
            }
        }

        return null; // Không tìm thấy đường tăng cường
    }

    /**
     * Tính toán dòng chảy có thể cho đường tăng cường.
     */
    private static <T, W extends Number & Comparable<W>> W calculatePathFlow(
        Graph<T, W> graph, Vertex<T> source, Vertex<T> sink,
        Map<Vertex<T>, Vertex<T>> parentMap, W infinity, W zero) {

        // Tính toán dòng chảy tối đa có thể cho đường tăng cường
        W pathFlow = AlgorithmUtils.min(AlgorithmUtils.subtract(infinity, zero), infinity);
        Vertex<T> current = sink;

        // Lần theo đường tăng cường để tìm dòng chảy nhỏ nhất
        while (current != null && !current.equals(source)) {
            Vertex<T> parent = parentMap.get(current);
            if (parent == null) {
                throw new IllegalStateException("Parent map does not contain a parent for vertex " + current);
            }
            Edge<T, W> edge = findEdge(graph, parent, current);
            pathFlow = AlgorithmUtils.min(pathFlow, edge.getWeight());
            current = parent;
        }

        // Cập nhật dòng chảy cho các cạnh trong đường tăng cường
        current = sink;
        while (current != null && !current.equals(source)) {
            Vertex<T> parent = parentMap.get(current);
            if (parent == null) {
                throw new IllegalStateException("Parent map does not contain a parent for vertex " + current);
            }
            Edge<T, W> edge = findEdge(graph, parent, current);
            edge.setWeight(AlgorithmUtils.subtract(edge.getWeight(), pathFlow));

            // Cập nhật hoặc thêm cạnh ngược
            Edge<T, W> reverseEdge = findReverseEdge(graph, current, parent);
            if (reverseEdge == null) {
                graph.addEdge(current, parent, zero, pathFlow);
            }

            current = parent;
        }

        return pathFlow;
    }

    /**
     * Tìm cạnh trong đồ thị từ đỉnh u đến đỉnh v.
     */
    private static <T, W extends Number & Comparable<W>> Edge<T, W> findEdge(Graph<T, W> graph, Vertex<T> u, Vertex<T> v) {
        return graph.getEdges(u).stream()
            .filter(edge -> edge.getEndVertex().equals(v))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Edge not found"));
    }

    /**
     * Tìm cạnh ngược trong đồ thị từ đỉnh u đến đỉnh v.
     */
    private static <T, W extends Number & Comparable<W>> Edge<T, W> findReverseEdge(Graph<T, W> graph, Vertex<T> u, Vertex<T> v) {
        return graph.getEdges(u).stream()
            .filter(edge -> edge.getEndVertex().equals(v))
            .findFirst()
            .orElse(null);
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> s = graph.addVertex("A");
        Vertex<String> t = graph.addVertex("F");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");
        Vertex<String> vE = graph.addVertex("E");
        Vertex<String> vG = graph.addVertex("G");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "E", 3);
        graph.addEdge("E", "F", 2);
        graph.addEdge("F", "G", 1);
        graph.addEdge("C", "G", 7);

        // Đặt giá trị zero và vô cực cho loại W
        Integer zero = 0;
        Integer infinity = Integer.MAX_VALUE;

        // Gọi thuật toán Edmonds-Karp để tìm dòng chảy tối đa
        Integer maxFlow = EdmondsKarp.algorithm(graph, s, t, zero, infinity);

        // In kết quả
        System.out.println("Dòng chảy tối đa: " + maxFlow);
    }
}
