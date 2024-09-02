package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;

public class MultipleUndirectedGraph<V> extends Graph<V> {

    public MultipleUndirectedGraph() {
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
    public boolean addEdge(GraphGdge<V, ? extends Number> element) {
        if (!(element instanceof Edge)) {
            throw new IllegalArgumentException("Element must be an instance of Edge.");
        }

        Edge<V, ? extends Number> edge = (Edge<V, ? extends Number>) element;
        Vertex<V> fromVertex = edge.u();
        Vertex<V> toVertex = edge.v();

        // Check the existence of vertices in the graph
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Add the edge to the set of edges for both vertices
        boolean added = false;
        added |= adjacencyList.get(fromVertex).add(edge);
        added |= adjacencyList.get(toVertex).add(new Edge<>(edge.v(), edge.u(), edge.w())); // Symmetric for undirected graph

        return added;
    }

    public static void main(String[] args) {
        Graph<String> graph = new MultipleUndirectedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("3")));
        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("5")));
        graph.addEdge(new Edge<>(v2, v3, Double.valueOf("5")));
        graph.addEdge(new Edge<>(v3, v1, Double.valueOf("7")));

        System.out.println(graph);
    }
}

