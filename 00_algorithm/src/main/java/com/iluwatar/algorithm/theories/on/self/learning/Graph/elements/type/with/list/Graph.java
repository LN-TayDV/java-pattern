package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public abstract class Graph<V, E extends Collection<? extends GraphValue<V, ? extends Number>>> {

    protected final Map<V, E> adjacencyList; // Tập hợp các đỉnh và các cạnh liên quan

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Phương thức trừu tượng thêm đỉnh vào đồ thị
    public abstract boolean addVertex(V vertex);

    // Phương thức trừu tượng thêm cạnh vào đồ thị
    public abstract boolean addEdge(GraphValue<V, ? extends Number> element);

    // Lấy tập hợp các đỉnh
    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    // Lấy tập hợp các phần tử liên quan đến một đỉnh
    public E getElements(V vertex) {
        return adjacencyList.getOrDefault(vertex, createEmptyCollection());
    }

    // Tạo một tập hợp rỗng phù hợp với kiểu E
    protected abstract E createEmptyCollection();

    /**
     * Kiểm tra xem đỉnh u và đỉnh v có kề nhau không.
     * Đỉnh u và v được coi là kề nhau nếu có một cạnh nối giữa chúng trong đồ thị.
     */
    public boolean areAdjacent(V u, V v) {
        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            return false;
        }

        // Lấy tập hợp các phần tử liên quan đến đỉnh u
        E elementsFromU = getElements(u);

        // Kiểm tra xem đỉnh v có xuất hiện trong tập hợp các phần tử liên quan đến đỉnh u không
        for (GraphValue<V, ? extends Number> element : elementsFromU) {
            if (element instanceof Edge) {
                Edge<V, ? extends Number> edge = (Edge<V, ? extends Number>) element;
                if (edge.u().equals(v) || edge.v().equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Kiểm tra xem có cạnh nào liên thuộc với hai đỉnh u và v.
     * Cạnh liên thuộc với hai đỉnh u và v là một cạnh nối giữa chúng.
     */
    public boolean isIncidentTo(V u, V v) {
        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            return false;
        }

        // Lấy tập hợp các phần tử liên quan đến đỉnh u
        E elementsFromU = getElements(u);

        // Kiểm tra xem có cạnh nào liên thuộc với đỉnh u và v không
        for (GraphValue<V, ? extends Number> element : elementsFromU) {
            if (element instanceof Edge) {
                Edge<V, ? extends Number> edge = (Edge<V, ? extends Number>) element;
                if ((edge.u().equals(u) && edge.v().equals(v)) ||
                    (edge.u().equals(v) && edge.v().equals(u))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices and their elements:\n");

        for (Map.Entry<V, E> entry : adjacencyList.entrySet()) {
            V vertex = entry.getKey();
            E elements = entry.getValue();
            sb.append(vertex).append(":\n");
            for (GraphValue<V, ? extends Number> element : elements) {
                sb.append("  ").append(element).append("\n");
            }
        }

        return sb.toString();
    }
}