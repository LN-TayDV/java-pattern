package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

import java.util.Iterator;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NodeVertex<T> implements Iterable<NodeVertex<T>> {

    private Vertex<T> firstVertex;
    private NodeVertex<T> nextVertex;

    @Override
    public Iterator<NodeVertex<T>> iterator() {
        return new NodeVertexIterator(this);
    }

    // Lớp Iterator để duyệt qua các NodeVertex trong danh sách liên kết
    private class NodeVertexIterator implements Iterator<NodeVertex<T>> {
        private NodeVertex<T> current;

        public NodeVertexIterator(NodeVertex<T> start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public NodeVertex<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            NodeVertex<T> nodeVertex = current;
            current = current.getNextVertex();
            return nodeVertex;
        }
    }
}