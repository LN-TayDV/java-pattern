package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.directed;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;

/**
 * Đa đồ thị có hướng :
 * @param <V>
 */
public class DirectedGraph<V> extends Graph<V> {

    public DirectedGraph() {
        super();
    }

    @Override
    public boolean addVertex(Vertex<V> vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, createEmptyCollection());
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphGdge<V, ? extends Number> arc) {
        if (!(arc instanceof Arc)) {
            throw new IllegalArgumentException("Edge must be an instance of Arc.");
        }

        Vertex<V> fromVertex = arc.u();
        Vertex<V> toVertex = arc.v();

        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Thêm cung vào tập hợp cung của đỉnh bắt đầu
        return adjacencyList.get(fromVertex).add(arc);
    }

    public static void main(String[] args) {
        DirectedGraph<String> graph = new DirectedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(new Arc<>(v1, v2, Double.valueOf("3")));
        graph.addEdge(new Arc<>(v2, v1, Double.valueOf("5")));
        graph.addEdge(new Arc<>(v2, v3, Double.valueOf("5")));
        graph.addEdge(new Arc<>(v3, v1, Double.valueOf("7")));

        System.out.println(graph);
    }
}