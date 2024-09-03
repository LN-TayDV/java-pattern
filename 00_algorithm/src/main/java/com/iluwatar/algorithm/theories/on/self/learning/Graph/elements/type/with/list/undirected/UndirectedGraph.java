package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.GraphGdge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.Vertex;

/**
 * UndirectedGraph  :
 * 1/ Đa đồ thị vô hướng
 * 2/ Đơn đồ thị vô hướng
 * 3/ Đồ thị có cạnh khuyên (đỉnh đầu và đỉnh cuối trùng nhau)
 * @param <V>
 */
public class UndirectedGraph<V> extends Graph<V> {

    public UndirectedGraph() {
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
        added |= adjacencyList.get(fromVertex).add(edge);
        // Đối xứng cho đồ thị vô hướng
        added |= adjacencyList.get(toVertex).add(edge.reverse());

        return added;
    }

    public static void main(String[] args) {
        Graph<String> graph = new UndirectedGraph<>();

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

