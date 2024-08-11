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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Tìm Đường Đi Ngắn Nhất Trong Đồ Thị Có Trọng Số Âm
 *
 * Thuật toán Johnson
 * Tìm đường đi ngắn nhất giữa tất cả các cặp đỉnh trong đồ thị có trọng số âm.
 */
public class Johnson {

    // Tìm đường đi ngắn nhất giữa tất cả các cặp đỉnh
    public static <T, W extends Number & Comparable<W>> Map<Vertex<T>, Map<Vertex<T>, W>> algorithm(Graph<T, W> graph, T defaultVertex) {

        List<Vertex<T>> vertices = new ArrayList<>(graph.getVertices());

        Vertex<T> virtualVertex = new Vertex<>(defaultVertex);

        // Thêm đỉnh ảo vào đồ thị
        Graph<T, W> modifiedGraph = new Graph<>(graph.isDirected());
        for (Vertex<T> vertex : vertices) {
            modifiedGraph.addVertex(vertex.getTop());
        }
        modifiedGraph.addVertex(virtualVertex.getTop());

        // Thêm các cạnh từ đỉnh ảo đến tất cả các đỉnh còn lại
        for (Vertex<T> vertex : vertices) {
            modifiedGraph.addEdge(virtualVertex.getTop(), vertex.getTop(), AlgorithmUtils.defaultValue(graph));
        }

        // Thêm tất cả các cạnh của đồ thị gốc vào đồ thị mới
        for (Vertex<T> u : vertices) {
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getDestination();
                modifiedGraph.addEdge(u.getTop(), v.getTop(), edge.getWeight());
            }
        }

        // Sử dụng Bellman-Ford để tính toán khoảng cách từ đỉnh ảo
        Map<Vertex<T>, W> h = bellmanFord(modifiedGraph, virtualVertex);

        // Điều chỉnh trọng số của đồ thị
        Graph<T, W> adjustedGraph = adjustGraphWeights(graph, h);

        // Tìm đường đi ngắn nhất từ mỗi đỉnh
        Map<Vertex<T>, Map<Vertex<T>, W>> allPairsShortestPaths = new HashMap<>();
        for (Vertex<T> vertex : vertices) {
            Map<Vertex<T>, W> distances = dijkstra(adjustedGraph, vertex);
            allPairsShortestPaths.put(vertex, distances);
        }

        return allPairsShortestPaths;
    }

    // Thuật toán Bellman-Ford để tính khoảng cách từ đỉnh ảo
    private static  <T, W extends Number & Comparable<W>> Map<Vertex<T>, W> bellmanFord(Graph<T, W> graph, Vertex<T> source) {
        Map<Vertex<T>, W> distances = new HashMap<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            distances.put(vertex, AlgorithmUtils.max(graph));
        }

        distances.put(source, AlgorithmUtils.defaultValue(graph, 0));

        for (int i = 0; i < graph.getVertices().size() - 1; i++) {

            for (Vertex<T> u : graph.getVertices()) {

                for (Edge<T, W> edge : graph.getEdges(u)) {

                    Vertex<T> v = edge.getDestination();

                    W newDist = AlgorithmUtils.sum(distances.get(u), edge.getWeight());

                    if (distances.get(v).compareTo(newDist) > 0) {
                        distances.put(v, newDist);
                    }
                }
            }
        }

        // Kiểm tra chu trình âm
        for (Vertex<T> u : graph.getVertices()) {

            for (Edge<T, W> edge : graph.getEdges(u)) {

                Vertex<T> v = edge.getDestination();

                W newDist = AlgorithmUtils.sum(distances.get(u), edge.getWeight());

                if (distances.get(v).compareTo(newDist) > 0) {
                    throw new RuntimeException("Graph contains a negative weight cycle");
                }

            }
        }

        return distances;
    }

    // Điều chỉnh trọng số của đồ thị
    private static <T, W extends Number & Comparable<W>> Graph<T, W> adjustGraphWeights(Graph<T, W> graph, Map<Vertex<T>, W> h) {
        Graph<T, W> adjustedGraph = new Graph<>(graph.isDirected());

        for (Vertex<T> u : graph.getVertices()) {
            adjustedGraph.addVertex(u.getTop());
        }

        for (Vertex<T> u : graph.getVertices()) {

            for (Edge<T, W> edge : graph.getEdges(u)) {

                Vertex<T> v = edge.getDestination();

                W adjustedWeight = AlgorithmUtils.sum(
                    edge.getWeight(),
                    AlgorithmUtils.subtract(
                        h.get(u) == null ? AlgorithmUtils.defaultValue(graph) : h.get(u),
                        h.get(v) == null ? AlgorithmUtils.defaultValue(graph) : h.get(v)
                    )
                );

                adjustedGraph.addEdge(u.getTop(), v.getTop(), adjustedWeight);
            }
        }

        return adjustedGraph;
    }

    // Thuật toán Dijkstra để tìm đường đi ngắn nhất từ một đỉnh
    private static  <T, W extends Number & Comparable<W>> Map<Vertex<T>, W> dijkstra(Graph<T, W> graph, Vertex<T> source) {
        Map<Vertex<T>, W> distances = new HashMap<>();

        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        for (Vertex<T> vertex : graph.getVertices()) {
            distances.put(vertex, AlgorithmUtils.max(graph));
            queue.add(vertex);
        }

        distances.put(source, AlgorithmUtils.defaultValue(graph));
        queue.add(source);

        while (!queue.isEmpty()) {

            Vertex<T> u = queue.poll();

            for (Edge<T, W> edge : graph.getEdges(u)) {

                Vertex<T> v = edge.getDestination();

                W newDist = AlgorithmUtils.sum(distances.get(u), edge.getWeight());

                if (newDist.compareTo(distances.get(v)) < 0) {
                    distances.put(v, newDist);
                    queue.add(v);
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(true); // true cho đồ thị có hướng

        // Thêm các đỉnh
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        // Thêm các đỉnh vào đồ thị
        graph.addVertex(a.getTop());
        graph.addVertex(b.getTop());
        graph.addVertex(c.getTop());
        graph.addVertex(d.getTop());
        graph.addVertex(e.getTop());

        // Thêm các cạnh
        graph.addEdge(a.getTop(), b.getTop(), 1);
        graph.addEdge(b.getTop(), c.getTop(), 1);
        graph.addEdge(c.getTop(), a.getTop(), 1);
        graph.addEdge(b.getTop(), d.getTop(), 1);
        graph.addEdge(d.getTop(), e.getTop(), 1);
        graph.addEdge(e.getTop(), d.getTop(), 1);

        // Tìm các SCC
        var map =  Johnson.algorithm(graph, "A");

        // In kết quả ra màn hình
        int count = 1;
        map.forEach((k1, v1) -> {
            var s= String.format("%s --> [ %s ]",
                k1.getTop(),
                v1.entrySet().stream().map(en -> String.format("%s(%s) ", en.getKey().getTop(), en.getValue())).collect(Collectors.joining(", "))
            );

            System.out.println(s);
        });
    }
}
