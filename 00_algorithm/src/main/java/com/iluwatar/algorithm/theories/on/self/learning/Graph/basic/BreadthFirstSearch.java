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
import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Duyệt theo chiều rộng (Breadth-First Search - BFS)
 * Duyệt tất cả các đỉnh của đồ thị bắt đầu từ một đỉnh và đi qua các đỉnh kề theo từng lớp.
 */
@Slf4j
public class BreadthFirstSearch<T, W> {

    // Phương thức thực hiện BFS từ đỉnh bắt đầu `startTop`
    public static <T, W> void algorithm(Graph<T, W> graph, T startTop) {

        LOGGER.info("Lấy đỉnh bắt đầu từ đồ thị");
        Vertex<T> startVertex = graph.getVertex(startTop);

        if (startVertex == null) {
            throw new IllegalArgumentException("Start vertex not found in graph");
        }

        LOGGER.info("Hàng đợi để duyệt các đỉnh");
        BlockingQueue<Vertex<T>> queue = new LinkedBlockingQueue<>();

        LOGGER.info("Tập hợp để đánh dấu các đỉnh đã được thăm");
        Set<Vertex<T>> visited = new HashSet<>();

        LOGGER.info("Đưa đỉnh bắt đầu vào hàng đợi và đánh dấu đã thăm");
        queue.add(startVertex);
        visited.add(startVertex);

        LOGGER.info("Bắt đầu duyệt đồ thị theo chiều rộng");

        LOGGER.info("--------------------------------------------------------------------------");
        while (!queue.isEmpty()) {

            LOGGER.info("Lấy đỉnh từ đầu hàng đợi");
            Vertex<T> currentVertex = queue.poll();
            LOGGER.info("---------- Visited vertex: {} ----------------" , currentVertex.getTop());

            // Duyệt qua các đỉnh kề
            LOGGER.info("Duyệt qua các đỉnh kề");
            for (Edge<T, W> edge : graph.getEdges(currentVertex)) {

                // Vì BFS duyệt từ startVertex tới endVertex
                Vertex<T> neighbor = edge.getEndVertex();

                LOGGER.info("Nếu đỉnh kề chưa được thăm, thêm nó vào hàng đợi");
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
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
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "E", 7);
        graph.addEdge("D", "E", 9);

        // In ra cấu trúc đồ thị
        System.out.println(graph);

        // Thực hiện BFS từ đỉnh "A"
        algorithm(graph, "C");
    }
}
