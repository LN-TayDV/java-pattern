package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements;

public class Main {

    public static void main(String[] args) {
        Graph<String, Integer> graph = new Graph<>(false); // Đồ thị vô hướng với trọng số kiểu Integer

        Vertex<String, Integer> v1 = graph.addVertex("A");
        Vertex<String, Integer> v2 = graph.addVertex("B");
        Vertex<String, Integer> v3 = graph.addVertex("C");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 2);

        System.out.println(graph);
    }
}
