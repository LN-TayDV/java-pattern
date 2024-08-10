package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Vertex<T, W> {
    private T top;
    private W weight;
    private Set<Vertex<T, W>> adjacentVertices;

    public Vertex(T id, W weight) {
        this.top = id;
        this.weight = weight;
        this.adjacentVertices = new HashSet<>();
    }

    public T getTop() {
        return top;
    }

    public Vertex<T, W> setWeight (W weight) {
        this.weight = weight;
        return this;
    }

    public void addNeighbor(Vertex<T, W> vertex) {
        adjacentVertices.add(vertex);
    }

    public Set<Vertex<T, W>> getAdjacentVertices() {
        return adjacentVertices;
    }

    public String getAdjacentVerticesString() {
        return "[ " + this.adjacentVertices
            .stream()
            .map(e -> String.format("%s = %s ", e.top, e.weight))
            .collect(Collectors.joining(","))
        + "]";
    }

    @Override
    public String toString() {
        return top.toString();
    }
}
