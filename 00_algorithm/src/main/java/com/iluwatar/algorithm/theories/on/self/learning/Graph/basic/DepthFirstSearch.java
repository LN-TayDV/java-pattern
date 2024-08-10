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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.basic;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * Duyệt theo chiều sâu (Depth-First Search - DFS)
 * Duyệt tất cả các đỉnh của đồ thị bắt đầu từ một đỉnh và đi sâu vào các đỉnh kề chưa được thăm.
 */
@Slf4j
public class DepthFirstSearch<T, W> {

    // Phương thức thực hiện DFS từ đỉnh bắt đầu `startTop`
    public static <T, W> void algorithm(Graph<T, W> graph, T startTop) {
        LOGGER.info("Lấy đỉnh bắt đầu từ đồ thị");
        Vertex<T> startVertex = graph.getVertex(startTop);

        if (startVertex == null) {
            throw new IllegalArgumentException("Start vertex not found in graph");
        }

        LOGGER.info("Tập hợp để đánh dấu các đỉnh đã được thăm");
        Set<Vertex<T>> visited = new HashSet<>();

        LOGGER.info("---------------------------------------------------------------------------");

        LOGGER.info("Bắt đầu duyệt đồ thị theo chiều sâu");
        dfsRecursive(graph, startVertex, visited);
    }

    // Phương thức đệ quy cho DFS
    private static <T, W> void dfsRecursive(Graph<T, W> graph, Vertex<T> vertex, Set<Vertex<T>> visited) {
        LOGGER.info("Thăm đỉnh: " + vertex.getTop());
        visited.add(vertex);
        LOGGER.info("---------- Visited vertex: {} ----------------" , vertex.getTop());

        // Duyệt qua các đỉnh kề
        LOGGER.info("Duyệt qua các đỉnh kề của đỉnh " + vertex.getTop());
        for (Edge<T, W> edge : graph.getEdges(vertex)) {
            Vertex<T> neighbor = edge.getEndVertex(); // Duyệt đến endVertex của cạnh

            // Nếu đỉnh kề chưa được thăm, thực hiện DFS đệ quy
            if (!visited.contains(neighbor)) {
                LOGGER.info("Đỉnh " + neighbor.getTop() + " chưa được thăm, thực hiện DFS đệ quy thăm đỉnh " + neighbor.getTop());
                dfsRecursive(graph, neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(false);

        // Thêm các đỉnh vào đồ thị
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Thêm các cạnh vào đồ thị
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("A", "D", 8);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "E", 4);
        graph.addEdge("D", "E", 5);

        // In ra cấu trúc đồ thị
        System.out.println(graph);

        // Thực hiện DFS từ đỉnh "A"
        algorithm(graph, "A");
    }
}

