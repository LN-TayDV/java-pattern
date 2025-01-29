package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.directed;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(of = {
    "from", "to", "weight"
})
public class Arc<E, W extends Number> implements GraphGdge<E , W> {

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

    @Override
    public W w() {
        return this.weight;
    }

    @Override
    public GraphGdge<E, W> reverse() {
        throw new RuntimeException("Đây là vector có hướng , không thể đảo chiều");
    }

    @Override
    public String toString() {
        return String.format(
            "%s -> %s (w : %s)",
            from.toString(),
            to.toString(),
            weight
        );
    }

}

