package com.iluwatar.algorithm.theories.on.self.learning.Graph.basic;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *Sắp xếp topo (Topological Sorting)
 * Sắp xếp các đỉnh của đồ thị có hướng sao cho đối với mọi cạnh từ đỉnh u đến đỉnh v, u xuất hiện trước v.
 */
@Slf4j
public class TopologicalSorting {

    /**
     * Thực hiện sắp xếp topo trên đồ thị có hướng.
     * @param graph Đồ thị cần sắp xếp.
     * @param <T> Kiểu dữ liệu của đỉnh.
     * @param <W> Kiểu dữ liệu của trọng số cạnh.
     * @return Danh sách các đỉnh được sắp xếp theo thứ tự topo.
     */
    public static <T, W> List<Vertex<T>> algorithm(Graph<T, W> graph) {
        List<Vertex<T>> sortedList = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        Deque<Vertex<T>> stack = new ArrayDeque<>();

        for (Vertex<T> vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack, graph);
            }
        }

        while (!stack.isEmpty()) {
            sortedList.add(stack.pop());
        }

        return sortedList;
    }

    /**
     * Hàm đệ quy để thực hiện DFS và sắp xếp topo.
     * @param vertex Đỉnh hiện tại đang được duyệt.
     * @param visited Tập hợp các đỉnh đã được thăm.
     * @param stack Ngăn xếp để lưu trữ thứ tự topo.
     * @param graph Đồ thị cần sắp xếp.
     * @param <T> Kiểu dữ liệu của đỉnh.
     * @param <W> Kiểu dữ liệu của trọng số cạnh.
     */
    private static <T, W> void topologicalSortUtil(Vertex<T> vertex,
                                                   Set<Vertex<T>> visited,
                                                   Deque<Vertex<T>> stack,
                                                   Graph<T, W> graph) {
        visited.add(vertex);

        for (Edge<T, W> edge : graph.getEdges(vertex)) {
            Vertex<T> neighbor = edge.getEndVertex();
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack, graph);
            }
        }

        stack.push(vertex);
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(true);

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
        System.out.println(algorithm(graph));
    }
}
