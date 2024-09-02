package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SelfLoopGraph<V> extends Graph<V> {

    public SelfLoopGraph() {
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
    public boolean addEdge(GraphGdge<V, ? extends Number> element) {
        V fromVertex = element.u().getTop();
        V toVertex = element.v().getTop();
        Number weight = element.w();

        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(fromVertex) || !adjacencyList.containsKey(toVertex)) {
            throw new IllegalArgumentException("Both vertices must be part of the graph.");
        }

        // Nếu cạnh là khuyên, trọng số mặc định là 0
        if (fromVertex.equals(toVertex) && weight.intValue() != 0) {
            throw new IllegalArgumentException("Self-loops must have a weight of 0.");
        }

        // Thêm cạnh vào tập hợp cạnh của cả hai đỉnh
        boolean added = false;
        added |= adjacencyList.get(fromVertex).add(element);
        added |= adjacencyList.get(toVertex).add(new Edge<>(element.v(), element.u(), element.w())); // Đối xứng cho đồ thị vô hướng

        return added;

    }

    @Override
    protected Set<GraphGdge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices and their edges:\n");

        for (Map.Entry<V, Set<GraphGdge<V, ? extends Number>>> entry : adjacencyList.entrySet()) {
            V vertex = entry.getKey();
            Set<GraphGdge<V, ? extends Number>> edges = entry.getValue();
            sb.append(vertex).append(":\n");
            for (GraphGdge<V, ? extends Number> edge : edges) {
                sb.append("  ").append(edge).append("\n");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Graph<String> graph = new SelfLoopGraph<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");

        graph.addVertex(v1.getTop());
        graph.addVertex(v2.getTop());
        graph.addVertex(v3.getTop());

        graph.addEdge(new Edge<>(v1, v1, 0)); // Self-loop with weight 0
        graph.addEdge(new Edge<>(v1, v2, 1)); // Edge between v1 and v2
        graph.addEdge(new Edge<>(v2, v2, 0)); // Self-loop with weight 0
        graph.addEdge(new Edge<>(v3, v1, 2)); // Edge between v3 and v1

        System.out.println(graph);
    }
}
