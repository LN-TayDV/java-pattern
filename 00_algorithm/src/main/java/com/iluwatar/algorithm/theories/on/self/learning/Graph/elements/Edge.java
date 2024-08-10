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

    public Edge(Vertex<T> startVertex, Vertex<T> endVertex, W weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
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


    public String toString(boolean directed) {
        return String.format(
            "%s %s %s (%s)",
            startVertex,
            directed ? "->>" : "-->",
            endVertex,
            weight
        );
    }
}
