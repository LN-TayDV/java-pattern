package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.node;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * T : Top
 * @param <T>
 */
public class Vertex<T> {

    private final T top;
    // Lưu các đỉnh kề và trọng số tương ứng
    private final Map<Vertex<T>, BigDecimal> adjacentVertices;

    public Vertex(T top) {
        this.top = top;
        this.adjacentVertices = new HashMap<>();
    }

    public T getTop() {
        return top;
    }

    public Map<Vertex<T>, BigDecimal> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex<T> vertex, BigDecimal weight) {
        adjacentVertices.put(vertex, weight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(top).append(":\n");
        for (Map.Entry<Vertex<T>, BigDecimal> entry : adjacentVertices.entrySet()) {
            sb.append("  ").append(entry.getKey().getTop()).append(" : ").append(entry.getValue().intValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) obj;
        return top.equals(vertex.top);
    }

    @Override
    public int hashCode() {
        return top.hashCode();
    }
}
