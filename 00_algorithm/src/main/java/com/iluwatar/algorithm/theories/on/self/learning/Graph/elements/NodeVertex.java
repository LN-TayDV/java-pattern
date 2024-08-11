/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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