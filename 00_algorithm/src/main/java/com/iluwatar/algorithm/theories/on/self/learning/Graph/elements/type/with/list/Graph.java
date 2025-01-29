package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.directed.Arc;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list.undirected.Edge;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public abstract class Graph<V> {

    // Danh sách kề cho các đỉnh và các cạnh liên quan
    protected final Map<Vertex<V>, Set<GraphGdge<V, ? extends Number>>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Phương thức trừu tượng để thêm một đỉnh vào đồ thị
    public abstract boolean addVertex(Vertex<V> vertex);

    // Phương thức trừu tượng để thêm một cạnh vào đồ thị
    public abstract boolean addEdge(GraphGdge<V, ? extends Number> element);

    // Tạo một tập hợp rỗng cho các cạnh
    protected Set<GraphGdge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }

    // Lấy tập hợp các đỉnh
    public Set<V> getVertices() {
        return adjacencyList.keySet().stream().map(Vertex::getTop).collect(Collectors.toSet());
    }

    // Lấy tập hợp các cạnh liên quan đến một đỉnh
    public Set<GraphGdge<V, ? extends Number>> getElements(Vertex<V> vertex) {
        return adjacencyList.getOrDefault(vertex, createEmptyCollection());
    }

    // Tính bậc cho các cạnh (áp dụng cho Edge)
    public Set<GraphGdge<V, ? extends Number>> degForIndirect(Vertex<V> u) {
        if (!adjacencyList.containsKey(u)) {
            return Collections.emptySet();
        }

        Set<GraphGdge<V, ? extends Number>> elements = getElements(u);
        Set<GraphGdge<V, ? extends Number>> edges = new HashSet<>();

        for (GraphGdge<V, ? extends Number> element : elements) {
            if (element instanceof Edge) {
                edges.add(element);
            }
        }

        return edges;
    }

    // Tính bậc cho các cung (áp dụng cho Arc)
    public DegreeReversal<DegreeValue, DegreeValue> degForDirect(Vertex<V> u) {
        int outDegreeCount = 0;
        int inDegreeCount = 0;

        Set<GraphGdge<V, ? extends Number>> outArcs = new HashSet<>();
        Set<GraphGdge<V, ? extends Number>> inArcs = new HashSet<>();

        Set<GraphGdge<V, ? extends Number>> elements = getElements(u);

        for (GraphGdge<V, ? extends Number> arc : elements) {
            if (arc instanceof Arc) {
                if (arc.u().equals(u)) {
                    outArcs.add(arc);
                    outDegreeCount++;
                }
                if (arc.v().equals(u)) {
                    inArcs.add(arc);
                    inDegreeCount++;
                }
            }
        }

        DegreeValue positiveDegree = new DegreeValue(outDegreeCount, outArcs);
        DegreeValue negativeDegree = new DegreeValue(inDegreeCount, inArcs);

        return new DegreeReversal<>(u, positiveDegree, negativeDegree);
    }

    @Data
    @AllArgsConstructor
    private class DegreeReversal<P extends DegreeValue, N extends DegreeValue> {
        private Vertex<V> u;
        private P positiveDegree;
        private N negativeDegree;

        @Override
        public String toString() {
            return String.format("deg(%s) = (%s, %s) ",
                u.getTop(),
                positiveDegree.numberOfArcs,
                negativeDegree.numberOfArcs
            );
        }
    }

    @AllArgsConstructor
    @Getter
    private class DegreeValue {
        int numberOfArcs;
        Set<GraphGdge<V, ? extends Number>> degreeArcs;
    }

    // Kiểm tra xem hai đỉnh u và v có kề nhau không
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            return false;
        }

        Set<GraphGdge<V, ? extends Number>> elementsFromU = getElements(u);

        for (GraphGdge<V, ? extends Number> element : elementsFromU) {
            if (element.u().equals(v) || element.v().equals(v)) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem có cạnh nào nối hai đỉnh u và v không
    public boolean isIncidentTo(Vertex<V> u, Vertex<V> v) {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            return false;
        }

        Set<GraphGdge<V, ? extends Number>> elementsFromU = getElements(u);

        for (GraphGdge<V, ? extends Number> element : elementsFromU) {
            if ((element.u().equals(u) && element.v().equals(v)) ||
                (element.u().equals(v) && element.v().equals(u))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices and their elements:\n");

        for (Map.Entry<Vertex<V>, Set<GraphGdge<V, ? extends Number>>> entry : adjacencyList.entrySet()) {
            Vertex<V> vertex = entry.getKey();
            Set<GraphGdge<V, ? extends Number>> elements = entry.getValue();
            sb.append(vertex).append(":\n");
            for (GraphGdge<V, ? extends Number> element : elements) {
                sb.append("  ").append(element).append("\n");
            }
        }

        return sb.toString();
    }
}
