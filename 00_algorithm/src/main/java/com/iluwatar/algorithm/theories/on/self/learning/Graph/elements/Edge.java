package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import lombok.EqualsAndHashCode;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
@EqualsAndHashCode(of = {
    "startVertex", "endVertex", "weight"
})
public class Edge<T, W> {

    private final Vertex<T> startVertex;
    private final Vertex<T> endVertex;
    private final W weight;
    private final boolean directed;

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
