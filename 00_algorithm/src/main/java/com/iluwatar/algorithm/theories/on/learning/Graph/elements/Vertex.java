package com.iluwatar.algorithm.theories.on.learning.Graph.elements;

import java.util.HashMap;
import java.util.Map;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Vertex<T, W> {

    private T top;
    private Map<Vertex<T, W>, W> adjacentVertices;

    public Vertex(T id) {
        this.top = id;
        this.adjacentVertices = new HashMap<>();
    }

    public T getTop() {
        return top;
    }

    public void addNeighbor(Vertex<T, W> vertex, W weight) {
        adjacentVertices.put(vertex, weight);
    }

    public Map<Vertex<T, W>, W> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public String toString() {
        return top.toString();
    }
}
