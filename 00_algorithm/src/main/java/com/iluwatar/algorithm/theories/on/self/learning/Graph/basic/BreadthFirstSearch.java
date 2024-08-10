package com.iluwatar.algorithm.theories.on.self.learning.Graph.basic;

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

    // Phương thức thực hiện BFS từ đỉnh bắt đầu `startVertex`
    public static <T, W> void algorithm(Graph<T, W> graph, T startTop) {

        LOGGER.info("Lấy đỉnh bắt đầu từ đồ thị");
        Vertex<T, W> startVertex = graph.getVertex(startTop);

        if (startVertex == null) {
            throw new IllegalArgumentException("Start vertex not found in graph");
        }

        LOGGER.info("Hàng đợi để duyệt các đỉnh");
        BlockingQueue<Vertex<T, W>> queue = new LinkedBlockingQueue<>();

        LOGGER.info("Tập hợp để đánh dấu các đỉnh đã được thăm");
        Set<Vertex<T, W>> visited = new HashSet<>();

        LOGGER.info("Đưa đỉnh bắt đầu vào hàng đợi và đánh dấu đã thăm");
        queue.add(startVertex);
        visited.add(startVertex);

        LOGGER.info("Bắt đầu duyệt đồ thị theo chiều rộng");

        LOGGER.info("--------------------------------------------------------------------------");
        while (!queue.isEmpty()) {

            // Lấy đỉnh từ đầu hàng đợi
            LOGGER.info("Lấy đỉnh từ đầu hàng đợi");
            Vertex<T, W> currentVertex = queue.poll();
            System.out.println("Visited vertex: " + currentVertex.getTop());

            // Duyệt qua các đỉnh kề
            LOGGER.info("Duyệt qua các đỉnh kề");
            for (Vertex<T, W> neighbor : currentVertex.getAdjacentVertices()) {
                // Nếu đỉnh kề chưa được thăm, thêm nó vào hàng đợi
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
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "E", 1);

        // In ra cấu trúc đồ thị
        System.out.println(graph);

        // Thực hiện BFS từ đỉnh "A"
        algorithm(graph, "B");
    }
}
