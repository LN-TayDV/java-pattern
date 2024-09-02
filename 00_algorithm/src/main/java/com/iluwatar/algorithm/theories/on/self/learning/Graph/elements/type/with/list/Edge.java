package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@SuppressWarnings("unchecked")
@EqualsAndHashCode(of = {
    "vertexU", "vertexV", "weight"
})
public class Edge<E, W extends Number> implements GraphGdge<E, W> {

    private Vertex<E> vertexU; // Đỉnh đầu tiên
    private Vertex<E> vertexV; // Đỉnh thứ hai
    @Getter
    private W weight;    // Trọng số của cạnh

    // Constructor với trọng số mặc định là 0
    public Edge(Vertex<E> u, Vertex<E> v) {
        this(u, v, (W) Integer.valueOf(0)); // Trọng số mặc định là 0
    }

    public Vertex<E> u() {
        return this.vertexU;
    }

    public Vertex<E> v() {
        return this.vertexV;
    }



    @Override
    public String toString() {
        return String.format(
            "%s -- %s (w : %s)",
            vertexU.toString(),
            vertexV.toString(),
            weight
        );
    }

    // Phương thức để kiểm tra xem cạnh có phải là khuyên không
    public boolean isSelfLoop() {
        return vertexU.equals(vertexV);
    }

}
