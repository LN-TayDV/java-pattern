package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Arc<E, W extends Number> implements GraphValue<E , W> {

    private Vertex<E> from; // Đỉnh bắt đầu
    private Vertex<E> to;   // Đỉnh kết thúc
    private W weight;       // Trọng số của cung

    // Phương thức để kiểm tra xem cung có phải là khuyên hay không
    public boolean isSelfLoop() {
        return from.equals(to);
    }

    // Phương thức để lấy hướng của cung
    public String getDirection() {
        return from.toString() + " -> " + to.toString();
    }
}

