package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.directed;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;
import java.util.HashSet;
import java.util.Set;

public class MultipleDirectedGraph <V> extends Graph<V> {

    public MultipleDirectedGraph() {
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
    public boolean addEdge(GraphGdge<V, ? extends Number> edge) {
        if (!(edge instanceof Arc)) {
            throw new IllegalArgumentException("Edge must be an instance of Arc.");
        }

        Arc<V, ? extends Number> arc = (Arc<V, ? extends Number>) edge;
        V fromVertex = arc.getFrom().getTop();
        V toVertex = arc.getTo().getTop();

        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Thêm cung vào tập hợp cung của đỉnh bắt đầu
        return adjacencyList.get(fromVertex).add(arc);
    }

    @Override
    protected Set<GraphGdge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }


    public static void main(String[] args) {
        MultipleDirectedGraph<String> graph = new MultipleDirectedGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1.getTop());
        graph.addVertex(v2.getTop());
        graph.addVertex(v3.getTop());

        graph.addEdge(new Arc<>(v1, v2, Double.valueOf("3")));
        graph.addEdge(new Arc<>(v2, v1, Double.valueOf("5")));
        graph.addEdge(new Arc<>(v2, v3, Double.valueOf("5")));
        graph.addEdge(new Arc<>(v3, v1, Double.valueOf("7")));

        System.out.println(graph);
    }
}