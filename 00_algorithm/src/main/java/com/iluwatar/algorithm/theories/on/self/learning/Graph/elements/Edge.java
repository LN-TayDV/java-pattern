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

    private Vertex<T, W> startVertex;
    private Vertex<T, W> endVertex;
    private W weight;
    private boolean directed;

    public Edge(Vertex<T, W> startVertex, Vertex<T, W> endVertex, W weight, boolean directed) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        this.directed = directed;
    }

    public Vertex<T, W> getStartVertex() {
        return startVertex;
    }

    public Vertex<T, W> getEndVertex() {
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
            "%s -%s-> %s (weight: %s)",
            startVertex,
            directed ? ">" : "-",
            endVertex,
            weight
        );
    }
}
