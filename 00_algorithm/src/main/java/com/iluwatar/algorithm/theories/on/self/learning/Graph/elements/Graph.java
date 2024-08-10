package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import java.util.HashMap;
import java.util.Map;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Graph<T, W> {
    private Map<T, Vertex<T, W>> vertices;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public Vertex<T, W> addVertex(T top) {
        Vertex<T, W> vertex = new Vertex<>(top, null);
        vertices.put(top, vertex);
        return vertex;
    }

    public Vertex<T, W> getVertex(T id) {
        return vertices.get(id);
    }

    public void addEdge(T startTop, T endTop, W weight) {
        // Lấy đỉnh tương ứng với ID `startTop` từ bản đồ `vertices`
        Vertex<T, W> startVertex = vertices.get(startTop);

        // Lấy đỉnh tương ứng với ID `endTop` từ bản đồ `vertices`
        Vertex<T, W> endVertex = vertices.get(endTop);

        // Kiểm tra nếu một trong hai đỉnh không tồn tại (null), ném ra ngoại lệ
        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Invalid vertex id");
        }

        // Thêm `endVertex` như là hàng xóm của `startVertex` với trọng số `weight`
        startVertex.addNeighbor(endVertex.setWeight(weight));

        // Nếu đồ thị là vô hướng, thêm `startVertex` như là hàng xóm của `endVertex`
        if (!directed) {
            endVertex.addNeighbor(startVertex.setWeight(weight));
        }
    }


    public Map<T, Vertex<T, W>> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T, W> vertex : vertices.values()) {
            sb.append(vertex.getTop()).append(" -> ")
                .append(vertex.getAdjacentVerticesString())
                .append("\n");
        }
        return sb.toString();
    }
}