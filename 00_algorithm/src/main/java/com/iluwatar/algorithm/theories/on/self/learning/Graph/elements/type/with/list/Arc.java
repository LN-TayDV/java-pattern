package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Arc<E, W extends Number> implements GraphValue<E , W> {

    @Getter
    private Vertex<E> from; // Đỉnh bắt đầu
    @Getter
    private Vertex<E> to;   // Đỉnh kết thúc
    @Getter
    private W weight;       // Trọng số của cung

    // Phương thức để kiểm tra xem cung có phải là khuyên hay không
    public boolean isSelfLoop() {
        return from.equals(to);
    }

    // Phương thức để lấy hướng của cung
    public String getDirection() {
        return from.toString() + " -> " + to.toString();
    }

    @Override
    public Vertex<E> u() {
        return this.from;
    }

    @Override
    public Vertex<E> v() {
        return this.to;
    }
}

