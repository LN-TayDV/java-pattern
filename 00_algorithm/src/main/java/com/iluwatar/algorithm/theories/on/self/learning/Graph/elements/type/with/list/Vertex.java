package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

/**
 * T : Top
 * @param <T>
 */
public class Vertex<T> {

    private T top;

    public Vertex(T top) {
        this.top = top;
    }

    public T getTop() {
        return top;
    }

    public Vertex<T> setTop (T top) {
        this.top = top;
        return this;
    }

    @Override
    public String toString() {
        return top.toString();
    }
}