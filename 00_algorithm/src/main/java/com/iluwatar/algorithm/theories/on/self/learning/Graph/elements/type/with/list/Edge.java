package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Edge<E, W extends Number> implements GraphValue<E, W> {

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
        return vertexU.toString() + " -- " + vertexV.toString() + " (weight: " + weight + ")";
    }

    // Phương thức để kiểm tra xem cạnh có phải là khuyên không
    public boolean isSelfLoop() {
        return vertexU.equals(vertexV);
    }

    /**
     * Đỉnh kề : Hai đình u, v được gọi là 2 đình kề nếu tồn tại cạnh e = (u, v) là cạnh của đồ thị.
     * Ví dụ AB = (A, B), AF = (A, F)
     *
     * Cạnh liên thuộc : Nếu cạnh e = (u, v) là cạnh của đồ thị thì cạnh e
     * được gọi là cạnh liên thuộc với 2 đình u, v. Ví dụ AF là cạnh liên thuộc với 2 đình A, F
     */
}
