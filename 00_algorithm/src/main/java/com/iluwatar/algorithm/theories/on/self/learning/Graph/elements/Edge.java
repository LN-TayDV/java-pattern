package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import lombok.EqualsAndHashCode;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
@EqualsAndHashCode(of = {
    "startVertex", "endVertex", "weight", "directed"
})
public class Edge<T, W> {

    private Vertex<T> startVertex;
    private Vertex<T> endVertex;
    private W weight;
    private boolean directed;

    public Edge(Vertex<T> startVertex, Vertex<T> endVertex, W weight, boolean directed) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        this.directed = directed;
    }

    public Vertex<T> getStartVertex() {
        return startVertex;
    }

    public Vertex<T> getEndVertex() {
        return endVertex;
    }

    public W getWeight() {
        return weight;
    }

    public boolean isDirected() {
        return directed;
    }

    @Override
    public String toString() {
        return String.format(
            "%s %s %s (%s)",
            startVertex,
            directed ? "->>" : "-->",
            endVertex,
            weight
        );
    }
}
