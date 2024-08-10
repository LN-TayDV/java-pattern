package com.iluwatar.algorithm.theories.on.self.learning.Graph.basic;

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

    // Phương thức thực hiện DFS từ đỉnh bắt đầu `startVertex`
    public static <T, W> void algorithm(Graph<T, W> graph, T startTop) {
        LOGGER.info("Lấy đỉnh bắt đầu từ đồ thị");
        Vertex<T, W> startVertex = graph.getVertex(startTop);

        if (startVertex == null) {
            throw new IllegalArgumentException("Start vertex not found in graph");
        }

        LOGGER.info("Tập hợp để đánh dấu các đỉnh đã được thăm");
        Set<Vertex<T, W>> visited = new HashSet<>();

        LOGGER.info("---------------------------------------------------------------------------");

        LOGGER.info("Bắt đầu duyệt đồ thị theo chiều sâu");
        dfsRecursive(startVertex, visited);
    }

    // Phương thức đệ quy cho DFS
    private static <T, W> void dfsRecursive(Vertex<T, W> vertex, Set<Vertex<T, W>> visited) {

        LOGGER.info("Thăm đỉnh: " + vertex.getTop());
        visited.add(vertex);
        System.out.println("Visited vertex: " + vertex.getTop());

        // Duyệt qua các đỉnh kề
        LOGGER.info("Duyệt qua các đỉnh kề của đỉnh " + vertex.getTop());
        for (Vertex<T, W> neighbor : vertex.getAdjacentVertices()) {
            // Nếu đỉnh kề chưa được thăm, thực hiện DFS đệ quy
            if (!visited.contains(neighbor)) {
                LOGGER.info("Đỉnh " + neighbor.getTop() + " chưa được thăm, thực hiện DFS đệ quy");
                dfsRecursive(neighbor, visited);
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

        // Thực hiện DFS từ đỉnh "A"
        algorithm(graph, "A");
    }
}

