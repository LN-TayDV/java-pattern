package com.iluwatar.algorithm.theories.on.learning.Graph.elements;

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

    public Vertex<T, W> addVertex(T id) {
        Vertex<T, W> vertex = new Vertex<>(id);
        vertices.put(id, vertex);
        return vertex;
    }

    public Vertex<T, W> getVertex(T id) {
        return vertices.get(id);
    }

    public void addEdge(T startId, T endId, W weight) {
        Vertex<T, W> startVertex = vertices.get(startId);
        Vertex<T, W> endVertex = vertices.get(endId);
        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Invalid vertex id");
        }
        startVertex.addNeighbor(endVertex, weight);
        if (!directed) {
            endVertex.addNeighbor(startVertex, weight);
        }
    }

    public Map<T, Vertex<T, W>> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T, W> vertex : vertices.values()) {
            sb.append(vertex.getTop()).append(" -> ").append(vertex.getAdjacentVertices()).append("\n");
        }
        return sb.toString();
    }
}