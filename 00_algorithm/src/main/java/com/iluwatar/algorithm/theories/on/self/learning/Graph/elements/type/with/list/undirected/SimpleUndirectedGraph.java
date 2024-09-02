package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;
import java.util.HashSet;
import java.util.Set;

public class SimpleUndirectedGraph<V> extends Graph<V, Set<Edge<V, ? extends Number>>> {

    public SimpleUndirectedGraph() {
        super();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, createEmptyCollection());
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addEdge(GraphGdge<V, ? extends Number> element) {
        if (!(element instanceof Edge)) {
            throw new IllegalArgumentException("Element must be an instance of Edge.");
        }

        Edge<V, ? extends Number> edge = (Edge<V, ? extends Number>) element;
        V fromVertex = edge.u().getTop();
        V toVertex = edge.v().getTop();

        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Thêm cạnh vào tập hợp cạnh của cả hai đỉnh
        boolean added = false;
        added |= adjacencyList.get(fromVertex).add(edge);
        added |= adjacencyList.get(toVertex).add(new Edge<>(edge.v(), edge.u(), edge.getWeight())); // Đối xứng cho đồ thị vô hướng

        return added;
    }

    @Override
    public Set<Edge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }

    public static void main(String[] args) {

        SimpleUndirectedGraph<String> graph = new SimpleUndirectedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1.getTop());
        graph.addVertex(v2.getTop());
        graph.addVertex(v3.getTop());

        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("3")));
        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("5")));
        graph.addEdge(new Edge<>(v2, v3, Double.valueOf("5")));
        graph.addEdge(new Edge<>(v3, v1, Double.valueOf("7")));

        System.out.println(graph);
    }
}

