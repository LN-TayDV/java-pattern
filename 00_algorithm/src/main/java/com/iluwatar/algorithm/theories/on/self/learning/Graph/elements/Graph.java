package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Graph<T, W> {
    // Lưu trữ các đỉnh và các cạnh liên kết với từng đỉnh
    private Map<Vertex<T, W>, List<Edge<T, W>>> adjacencyList;

    //Vô hướng : false / Có hướng : true
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjacencyList = new HashMap<>();
    }

    public Vertex<T, W> addVertex(T top) {
        Vertex<T, W> vertex = new Vertex<>(top, null);
        adjacencyList.putIfAbsent(vertex, new ArrayList<>()); // Thêm vertex vào adjacencyList nếu chưa có
        return vertex;
    }

    public Vertex<T, W> getVertex(T top) {
        // Tìm và trả về vertex có id tương ứng
        return adjacencyList.keySet().stream()
            .filter(v -> v.getTop().equals(top))
            .findFirst()
            .orElse(null);
    }

    public void addEdge(T startTop, T endTop, W weight) {
        Vertex<T, W> startVertex = getVertex(startTop);
        Vertex<T, W> endVertex = getVertex(endTop);

        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Invalid vertex id");
        }

        // Thêm cạnh vào danh sách của startVertex
        Edge<T, W> edge = new Edge<>(startVertex, endVertex, weight, directed);
        adjacencyList.get(startVertex).add(edge);

        // Nếu đồ thị là vô hướng, thêm cạnh ngược từ endVertex về startVertex
        if (!directed) {
            Edge<T, W> reverseEdge = new Edge<>(endVertex, startVertex, weight, false);
            adjacencyList.get(endVertex).add(reverseEdge);
        }
    }

    public List<Edge<T, W>> getEdges(Vertex<T, W> vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<Vertex<T, W>> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        adjacencyList.forEach((key, value) -> {
            sb.append(key.getTop()).append(" -> ");
            sb.append("[ ");
            sb.append(value.stream()
                .map(Edge::toString)
                .collect(Collectors.joining(", ")));
            sb.append(" ]\n");
        });
        return sb.toString();
    }
}
