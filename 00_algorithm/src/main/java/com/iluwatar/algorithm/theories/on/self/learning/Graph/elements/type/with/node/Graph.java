package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Graph <V>{

    private final List<Vertex<V>> adjacencyList;

    public Graph() {
        this.adjacencyList = new ArrayList<>();
    }

    // Thêm một đỉnh vào đồ thị
    public void addVertex(Vertex<V> vertex) {
        adjacencyList.add(vertex);
    }

    // Thêm một cạnh giữa hai đỉnh và lưu trọng số
    public void addEdge(Vertex<V> fromVertex, Vertex<V> toVertex, BigDecimal weight) {
        // Loại bỏ cạnh khuyên (self-loop)
        if (fromVertex.equals(toVertex)) {
            System.out.println("Cạnh khuyên được phát hiện và loại bỏ: " + fromVertex.getTop());
            return;
        }

        // Nếu fromVertex chưa có trong đồ thị, thêm nó
        if (!adjacencyList.contains(fromVertex)) {
            addVertex(fromVertex);
        }

        // Nếu toVertex chưa có trong đồ thị, thêm nó
        if (!adjacencyList.contains(toVertex)) {
            addVertex(toVertex);
        }

        // Thêm toVertex vào danh sách kề của fromVertex với trọng số
        fromVertex.addAdjacentVertex(toVertex, weight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices and their elements:\n");

        adjacencyList.forEach(sb::append);

        return sb.toString();
    }

    public static void main(String[] args) {
        // Tạo các đỉnh
        Vertex<String> vertexA = new Vertex<>("A");
        Vertex<String> vertexB = new Vertex<>("B");
        Vertex<String> vertexC = new Vertex<>("C");

        // Tạo đồ thị
        Graph<String> graph = new Graph<>();

        // Thêm các đỉnh
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);

        // Thêm các cạnh (liên kết giữa các đỉnh với trọng số)
        graph.addEdge(vertexA, vertexB, BigDecimal.valueOf(1));
        graph.addEdge(vertexA, vertexC, BigDecimal.valueOf(2));
        graph.addEdge(vertexC, vertexB, BigDecimal.valueOf(3));

        // In đồ thị
        System.out.println(graph);
    }
}
