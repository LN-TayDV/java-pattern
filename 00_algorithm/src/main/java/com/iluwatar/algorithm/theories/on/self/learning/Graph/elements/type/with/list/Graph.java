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

public abstract class Graph<V> {

    // Adjacency list for vertices and their related edges
    protected final Map<Vertex<V>, Set<GraphGdge<V, ? extends Number>>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Abstract method to add a vertex to the graph
    public abstract boolean addVertex(Vertex<V> vertex);

    // Abstract method to add an edge to the graph
    public abstract boolean addEdge(GraphGdge<V, ? extends Number> element);

    // Create an empty collection for edges
    protected Set<GraphGdge<V, ? extends Number>> createEmptyCollection() {
        return new HashSet<>();
    }

    // Get the set of vertices
    public Set<V> getVertices() {
        return adjacencyList.keySet().stream().map(Vertex::getTop).collect(Collectors.toSet());
    }

    // Get the set of edges related to a vertex
    public Set<GraphGdge<V, ? extends Number>> getElements(V vertex) {
        return adjacencyList.getOrDefault(vertex, createEmptyCollection());
    }

    // Degree calculation for edges (applicable to Edge)
    public Set<GraphGdge<V, ? extends Number>> degForIndirect(Vertex<V> u) {
        if (!adjacencyList.containsKey(u.getTop())) {
            return Collections.emptySet();
        }

        Set<GraphGdge<V, ? extends Number>> elements = getElements(u.getTop());
        Set<GraphGdge<V, ? extends Number>> edges = new HashSet<>();

        for (GraphGdge<V, ? extends Number> element : elements) {
            if (element instanceof Edge) {
                edges.add(element);
            }
        }

        return edges;
    }

    // Degree calculation for arcs (applicable to Arc)
    public DegreeReversal<DegreeValue, DegreeValue> degForDirect(Vertex<V> u) {
        int outDegreeCount = 0;
        int inDegreeCount = 0;

        Set<GraphGdge<V, ? extends Number>> outArcs = new HashSet<>();
        Set<GraphGdge<V, ? extends Number>> inArcs = new HashSet<>();

        Set<GraphGdge<V, ? extends Number>> elements = getElements(u.getTop());

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
    private class DegreeValue {
        int numberOfArcs;
        Set<GraphGdge<V, ? extends Number>> degreeArcs;
    }

    // Check if vertices u and v are adjacent
    public boolean areAdjacent(V u, V v) {
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

    // Check if there is an edge incident to both vertices u and v
    public boolean isIncidentTo(V u, V v) {
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
