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
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tối Ưu Hóa Dòng Chảy
 *
 * Thuật toán Dinic
 * Giải quyết bài toán dòng chảy tối đa (Maximum Flow) với hiệu suất tốt hơn trong các trường hợp cụ thể.
 */
public class Dinic {

    /**
     * Dinic's Algorithm để tìm dòng chảy tối đa trong một đồ thị.
     *
     * @param graph - Đồ thị đầu vào với các đỉnh và cạnh.
     * @param source - Đỉnh nguồn (s).
     * @param sink - Đỉnh đích (t).
     * @param zero - Giá trị zero của loại W, dùng cho khởi tạo.
     * @param infinity - Giá trị vô cực của loại W, dùng để biểu thị một dòng chảy rất lớn.
     * @return Giá trị dòng chảy tối đa dạng W.
     */
    public static <T, W extends Number & Comparable<W>> MaxFlowPath<T, W> algorithm(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink, W zero, W infinity) {
        W maxFlow = zero;
        List<Vertex<T>> maxFlowVertices = new ArrayList<>();

        while (bfsLevelGraph(graph, source, sink, zero)) {
            Map<Vertex<T>, Iterator<Edge<T, W>>> edgeIteratorMap = new HashMap<>();
            for (Vertex<T> vertex : graph.getVertices()) {
                edgeIteratorMap.put(vertex, graph.getEdges(vertex).iterator());
            }

            W flow;
            while (!(flow = dfsBlockingFlow(graph, source, sink, edgeIteratorMap, infinity, zero)).equals(zero)) {
                maxFlow = AlgorithmUtils.sum(maxFlow, flow);
                // Update maxFlowVertices
                List<Vertex<T>> path = reconstructPath(graph, source, sink);
                maxFlowVertices.addAll(path);
            }
        }

        return new MaxFlowPath<>(maxFlow, maxFlowVertices); // Trả về giá trị dòng chảy tối đa tìm được cùng danh sách các đỉnh
    }

    /**
     * Hàm BFS để xây dựng đồ thị mức (level graph).
     */
    private static <T, W extends Number & Comparable<W>> boolean bfsLevelGraph(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink, W zero) {
        Map<Vertex<T>, Integer> levels = new HashMap<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            levels.put(vertex, -1);
        }

        levels.put(source, 0);
        queue.offer(source);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();

            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getEndVertex();

                if (levels.get(v) < 0 && edge.getWeight().compareTo(zero) > 0) {
                    levels.put(v, levels.get(u) + 1);
                    queue.offer(v);
                }
            }
        }

        return levels.get(sink) >= 0;
    }

    /**
     * Hàm DFS để tìm dòng chảy chặn trong đồ thị mức.
     *
     * @param graph - Đồ thị đầu vào
     * @param u - Đỉnh hiện tại
     * @param sink - Đỉnh đích
     * @param edgeIteratorMap - Bản đồ chứa các iterator cạnh cho từng đỉnh
     * @param flow - Dòng chảy hiện tại
     * @param zero - Giá trị zero của loại W
     * @return Giá trị dòng chảy chặn
     */
    private static <T, W extends Number & Comparable<W>> W dfsBlockingFlow(Graph<T, W> graph, Vertex<T> u, Vertex<T> sink, Map<Vertex<T>, Iterator<Edge<T, W>>> edgeIteratorMap, W flow, W zero) {
        if (u.equals(sink)) {
            return flow;
        }

        Iterator<Edge<T, W>> iterator = edgeIteratorMap.get(u);

        while (iterator.hasNext()) {
            Edge<T, W> edge = iterator.next();
            Vertex<T> v = edge.getEndVertex();

            if (edge.getWeight().compareTo(zero) > 0) {
                W currentFlow = AlgorithmUtils.min(flow, edge.getWeight());

                W tempFlow = dfsBlockingFlow(graph, v, sink, edgeIteratorMap, currentFlow, zero);

                if (tempFlow.compareTo(zero) > 0) {
                    edge.setWeight(AlgorithmUtils.subtract(edge.getWeight(), tempFlow));

                    graph.getEdges(v).stream()
                        .filter(e -> e.getEndVertex().equals(u))
                        .findFirst()
                        .ifPresent(reverseEdge -> reverseEdge.setWeight(AlgorithmUtils.sum(reverseEdge.getWeight(), tempFlow)));

                    return tempFlow;
                }
            }
        }

        return zero;
    }

    /**
     * Khôi phục đường đi từ đỉnh nguồn đến đỉnh đích.
     */
    private static <T, W extends Number & Comparable<W>> List<Vertex<T>> reconstructPath(Graph<T, W> graph, Vertex<T> source, Vertex<T> sink) {
        List<Vertex<T>> path = new ArrayList<>();
        Vertex<T> current = sink;

        // Thực hiện tìm đường đi từ đích đến nguồn
        while (current != null && !current.equals(source)) {
            path.add(current);
            current = findPreviousVertexInPath(graph, current);
        }

        if (current != null) {
            path.add(source);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Tìm đỉnh trước đó trong đường đi từ đỉnh hiện tại.
     */
    private static <T, W extends Number & Comparable<W>> Vertex<T> findPreviousVertexInPath(Graph<T, W> graph, Vertex<T> current) {
        // Cần cài đặt logic để tìm đỉnh trước đó từ đỉnh hiện tại trong đồ thị.
        return null; // Placeholder return, cần thay thế với logic thực sự.
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class MaxFlowPath<T, W> implements Iterable<Vertex<T>> {
        private W maxFlow;
        private List<Vertex<T>> maxFlowVertices; // Danh sách các đỉnh tạo nên maxFlow

        @Override
        public Iterator<Vertex<T>> iterator() {
            return maxFlowVertices.iterator();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator<Vertex<T>> iterator = maxFlowVertices.iterator();

            while (iterator.hasNext()) {
                Vertex<T> vertex = iterator.next();
                sb.append(vertex.getTop());
                if (iterator.hasNext()) {
                    sb.append(" --> ");
                }
            }

            return String.format("{ maxFlow : %s , maxFlowVertices : %s }", maxFlow, sb.toString());
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class NodeVT<T> {
        private T firstVertex;
        private NodeVT<T> nextVertex;
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> s = graph.addVertex("A");
        Vertex<String> vG = graph.addVertex("G");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");
        Vertex<String> t = graph.addVertex("E");
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

        System.out.println(graph);

        // Đặt giá trị zero và vô cực cho loại W
        Double zero = 0.0;
        Double infinity = Double.valueOf(String.valueOf(Integer.MAX_VALUE));

        // Gọi thuật toán Dinic để tìm dòng chảy tối đa
        MaxFlowPath<String, Double> result = Dinic.algorithm(graph, s, t, zero, infinity);

        // In kết quả
        System.out.println(result);
    }
}