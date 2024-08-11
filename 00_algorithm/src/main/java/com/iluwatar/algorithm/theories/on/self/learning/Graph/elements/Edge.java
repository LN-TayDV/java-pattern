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

import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
@EqualsAndHashCode(of = {
    "startVertex", "endVertex", "weight"
})
public class Edge<T, W extends Number & Comparable<W>> {

    private final Vertex<T> startVertex;
    private final Vertex<T> endVertex;
    @Setter
    private W weight;
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
            "%s %s %s (weight : %s)",
            startVertex,
            directed ? "->>" : "-->",
            endVertex,
            weight
        );
    }

    public Vertex<T> getFromVertex() {
        return this.startVertex;
    }

    public Vertex<T> getToVertex() {
        return this.endVertex;
    }
}
