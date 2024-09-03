package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

public  interface GraphGdge<E , W> {

    Vertex<E> u() ;

    Vertex<E> v() ;

    W w();

    GraphGdge<E , W> reverse ();

    // Phương thức mặc định để cung cấp chuỗi mô tả về cạnh
    default String describe() {
        return String.format("%s -- %s", u().getTop(), v().getTop());
    }
}
