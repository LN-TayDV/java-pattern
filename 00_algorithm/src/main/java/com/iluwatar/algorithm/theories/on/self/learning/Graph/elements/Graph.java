package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Graph<T, W> {

    /** Lưu trữ các đỉnh và các cạnh liên kết với từng đỉnh */
    private final Map<Vertex<T>, List<Edge<T, W>>> adjacentVertices;

    /** Vô hướng : false / Có hướng : true */
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjacentVertices = new HashMap<>();
    }

    public Vertex<T> addVertex(T top) {
        Vertex<T> vertex = new Vertex<>(top);
        adjacentVertices.putIfAbsent(vertex, new ArrayList<>()); // Thêm vertex vào adjacentVertices nếu chưa có
        return vertex;
    }

    public Optional<String> typeWeight () {
        return adjacentVertices.values().stream().flatMap(List::stream)
            .findFirst()
            .map(edge -> edge.getWeight().getClass().getTypeName());
    }

    public Vertex<T> getVertex(T top) {
        // Tìm và trả về vertex có id tương ứng
        return adjacentVertices
            .keySet()
            .stream()
            .filter(v -> v.getTop().equals(top))
            .findFirst()
            .orElse(null);
    }

    public void addEdge(T startTop, T endTop, W weight) {
        Vertex<T> startVertex = getVertex(startTop);
        Vertex<T> endVertex = getVertex(endTop);

        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Invalid vertex id");
        }

        // Thêm cạnh vào danh sách của startVertex
        Edge<T, W> edge = new Edge<>(startVertex, endVertex, weight, directed);
        adjacentVertices.get(startVertex).add(edge);

        // Nếu đồ thị là vô hướng, thêm cạnh ngược từ endVertex về startVertex
        if (!directed) {
            Edge<T, W> reverseEdge = new Edge<>(endVertex, startVertex, weight, directed);
            adjacentVertices.get(endVertex).add(reverseEdge);
        }
    }

    public List<Edge<T, W>> getEdges(Vertex<T> vertex) {
        return adjacentVertices.get(vertex);
    }

    public Set<Vertex<T>> getVertices() {
        return adjacentVertices.keySet();
    }

    public boolean getDirected () {
        return this.directed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        adjacentVertices.forEach((key, value) -> {
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
