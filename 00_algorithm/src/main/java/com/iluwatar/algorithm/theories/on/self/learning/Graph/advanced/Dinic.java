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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        NodeVT<Vertex<T>> maxFlowVertices = null; // Danh sách các đỉnh tạo nên maxFlow

        while (bfsLevelGraph(graph, source, sink, zero)) {
            Map<Vertex<T>, Iterator<Edge<T, W>>> edgeIteratorMap = new HashMap<>();
            for (Vertex<T> vertex : graph.getVertices()) {
                edgeIteratorMap.put(vertex, graph.getEdges(vertex).iterator());
            }

            W flow;
            while (!(flow = dfsBlockingFlow(graph, source, sink, edgeIteratorMap, infinity, zero, new ArrayList<>())).equals(zero)) {
                maxFlow = AlgorithmUtils.sum(maxFlow, flow);
                // Update maxFlowVertices
                NodeVT<Vertex<T>> currentNode = new NodeVT<>(source, maxFlowVertices);
                maxFlowVertices = currentNode;
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
     * @param path - Đường đi hiện tại được theo dõi
     * @return Giá trị dòng chảy chặn
     */
    private static <T, W extends Number & Comparable<W>> W dfsBlockingFlow(Graph<T, W> graph, Vertex<T> u, Vertex<T> sink, Map<Vertex<T>, Iterator<Edge<T, W>>> edgeIteratorMap, W flow, W zero, List<Vertex<T>> path) {
        path.add(u);

        if (u.equals(sink)) {
            // Nếu đến đích, trả về dòng chảy hiện tại
            return flow;
        }

        Iterator<Edge<T, W>> iterator = edgeIteratorMap.get(u);

        while (iterator.hasNext()) {
            Edge<T, W> edge = iterator.next();
            Vertex<T> v = edge.getEndVertex();

            if (edge.getWeight().compareTo(zero) > 0) {
                W currentFlow = AlgorithmUtils.min(flow, edge.getWeight());

                W tempFlow = dfsBlockingFlow(graph, v, sink, edgeIteratorMap, currentFlow, zero, path);

                if (tempFlow.compareTo(zero) > 0) {
                    edge.setWeight(AlgorithmUtils.subtract(edge.getWeight(), tempFlow));

                    graph.getEdges(v).stream()
                        .filter(e -> e.getEndVertex().equals(u))
                        .findFirst()
                        .ifPresent(reverseEdge -> reverseEdge.setWeight(AlgorithmUtils.sum(reverseEdge.getWeight(), tempFlow)));

                    path.remove(path.size() - 1);
                    return tempFlow;
                }
            }
        }

        path.remove(path.size() - 1);
        return zero;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class MaxFlowPath<T, W> implements Iterable<Vertex<T>> {
        private W maxFlow;
        private NodeVT<Vertex<T>> maxFlowVertex; // Danh sách các đỉnh tạo nên maxFlow

        @Override
        public Iterator<Vertex<T>> iterator() {
            return new VertexIterator(maxFlowVertex);
        }

        @Override
        public String toString () {
            StringBuilder sb = new StringBuilder();

            Iterator<Vertex<T>> iterator = new VertexIterator(maxFlowVertex);

            while (iterator.hasNext()) {
                Vertex<T> vertex = iterator.next();
                sb.append(vertex.getTop());
                if (iterator.hasNext()) {
                    sb.append(" --> ");
                }
            }

            return String.format("{ maxFlow : %s , maxFlowVertex : %s }", maxFlow, sb.toString());
        }


        // Lớp Iterator để duyệt qua các đỉnh trong danh sách liên kết
        private class VertexIterator implements Iterator<Vertex<T>> {
            private NodeVT<Vertex<T>> current;

            public VertexIterator(NodeVT<Vertex<T>> start) {
                this.current = start;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Vertex<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the list.");
                }
                Vertex<T> vertex = current.getFirstVertex();
                current = current.getNextVertex();
                return vertex;
            }
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
        Graph<String, Integer> graph = new Graph<>(true);

        // Thêm các đỉnh
        Vertex<String> s = graph.addVertex("A");
        Vertex<String> goal = graph.addVertex("G");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");
        Vertex<String> vE = graph.addVertex("E");
        Vertex<String> t = graph.addVertex("F");

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

        // Gọi thuật toán Dinic để tìm dòng chảy tối đa
        MaxFlowPath<String, Integer> result = Dinic.algorithm(graph, s, t, zero, infinity);

        // In kết quả
        System.out.println(result);
    }
}