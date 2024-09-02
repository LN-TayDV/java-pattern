package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.list;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("unchecked")
public abstract class Graph<V , E extends Collection<? extends GraphGdge<V, ? extends Number>>> {

    protected final Map<V, E> adjacencyList; // Tập hợp các đỉnh và các cạnh liên quan

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Phương thức trừu tượng thêm đỉnh vào đồ thị
    public abstract boolean addVertex(V vertex);

    // Phương thức trừu tượng thêm cạnh vào đồ thị
    public abstract boolean addEdge(GraphGdge<V, ? extends Number> element);

    // Lấy tập hợp các đỉnh
    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    // Lấy tập hợp các phần tử liên quan đến một đỉnh
    public E getElements(V vertex) {
        return adjacencyList.getOrDefault(vertex, createEmptyCollection());
    }

    // Phương thức deg chỉ áp dụng cho Edge
    public Set<Edge<V, ? extends Number>> degForIndirect(Vertex<V> u) {

        // Kiểm tra sự tồn tại của các đỉnh trong đồ thị
        if (!adjacencyList.containsKey(u.getTop())) {
            return Collections.emptySet();
        }

        // Lấy tập hợp các phần tử liên quan đến đỉnh u
        E elements = getElements(u.getTop());

        // Tạo một tập hợp chứa các Edge liên quan
        Set<Edge<V, ? extends Number>> edges = new HashSet<>();

        for (GraphGdge<V, ? extends Number> element : elements) {
            if (element instanceof Edge) {
                edges.add((Edge<V, ? extends Number>) element);
            }
        }

        return edges;
    }

    // Phương thức deg chỉ áp dụng cho Arc
    public DegreeReversal<DegreeValue, DegreeValue> degForDirect(Vertex<V> u) {
        // Khởi tạo biến để lưu trữ thông tin bán bậc ra và bán bậc vào
        int outDegreeCount = 0;
        int inDegreeCount = 0;

        // Tập hợp các cung đi ra từ đỉnh u (deg+(u))
        Set<Arc<V, ? extends Number>> outArcs = new HashSet<>();

        // Tập hợp các cung đi vào đỉnh u (deg-(u))
        Set<Arc<V, ? extends Number>> inArcs = new HashSet<>();

        // Giả định có một phương thức getElements để lấy tất cả các cung liên quan đến đỉnh u
        Set<Arc<V, ? extends Number>> elements = (Set<Arc<V, ? extends Number>>) getElements(u.getTop());

        // Duyệt qua tất cả các cung liên quan đến đỉnh u
        for (Arc<V, ? extends Number> arc : elements) {
            if (arc.getFrom().equals(u)) {
                // Nếu đỉnh nguồn là u, thì đây là một cung đi ra (deg+(u))
                outArcs.add(arc);
                outDegreeCount++;
            }
            if (arc.getTo().equals(u)) {
                // Nếu đỉnh đích là u, thì đây là một cung đi vào (deg-(u))
                inArcs.add(arc);
                inDegreeCount++;
            }
        }

        // Tạo các đối tượng DegreeValue cho bán bậc ra và bán bậc vào
        DegreeValue positiveDegree = new DegreeValue(outDegreeCount, outArcs);
        DegreeValue negativeDegree = new DegreeValue(inDegreeCount, inArcs);

        // Trả về một đối tượng DegreeReversal chứa bán bậc ra và bán bậc vào của đỉnh u
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
    private class DegreeValue {
        int numberOfArcs;
        Set<Arc<V, ? extends Number>> degreeArcs;
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
        for (GraphGdge<V, ? extends Number> element : elementsFromU) {
            if (element.u().equals(v) || element.v().equals(v)) {
                return true;
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

        for (Map.Entry<V, E> entry : adjacencyList.entrySet()) {
            V vertex = entry.getKey();
            E elements = entry.getValue();
            sb.append(vertex).append(":\n");
            for (GraphGdge<V, ? extends Number> element : elements) {
                sb.append("  ").append(element).append("\n");
            }
        }

        return sb.toString();
    }
}