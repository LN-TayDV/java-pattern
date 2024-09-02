package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;
import java.util.HashSet;
import java.util.Set;

public class SimpleUndirectedGraph<V> extends Graph<V> {

    public SimpleUndirectedGraph() {
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
    public boolean addEdge(GraphGdge<V, ? extends Number> element) {
        if (!(element instanceof Edge)) {
            throw new IllegalArgumentException("Element must be an instance of Edge.");
        }

        Vertex<V> fromVertex = element.u();
        Vertex<V> toVertex = element.v();

        // Check if both vertices are present in the graph
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Add edge to the adjacency list of both vertices
        boolean added = false;
        added |= adjacencyList.get(fromVertex).add(element);
        added |= adjacencyList.get(toVertex).add(new Edge<>(element.v(), element.u(), element.w())); // Add reverse edge for undirected graph

        return added;
    }

    @Override
    protected Set<GraphGdge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }

    public static void main(String[] args) {
        Graph<String> graph = new SimpleUndirectedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("3")));
        graph.addEdge(new Edge<>(v1, v2, Double.valueOf("5"))); // Adding multiple edges between the same vertices
        graph.addEdge(new Edge<>(v2, v3, Double.valueOf("5")));
        graph.addEdge(new Edge<>(v3, v1, Double.valueOf("7")));

        System.out.println(graph);
    }
}
