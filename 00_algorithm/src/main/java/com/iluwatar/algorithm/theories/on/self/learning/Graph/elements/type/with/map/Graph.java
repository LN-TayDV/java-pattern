/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * T : Top
 * W : Weight
 * @param <T>
 * @param <W>
 */
public class Graph<T, W extends Number & Comparable<W>> {

    /** Lưu trữ các đỉnh và các cạnh liên kết với từng đỉnh */
    private final Map<Vertex<T>, List<Edge<T, W>>> adjacentVertices;

    /** Vô hướng : false / Có hướng : true */
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjacentVertices = new HashMap<>();
    }

    public Vertex<T> addVertex(T top) {
        Vertex<T> vertex = new Vertex<>(top);
        adjacentVertices.putIfAbsent(vertex, new ArrayList<>()); // Thêm vertex vào adjacentVertices nếu chưa có
        return vertex;
    }

    public void addVT(T top) {
        Vertex<T> vertex = new Vertex<>(top);
        adjacentVertices.putIfAbsent(vertex, new ArrayList<>()); // Thêm vertex vào adjacentVertices nếu chưa có
    }

    public Optional<String> typeWeight () {
        return adjacentVertices.values().stream().flatMap(List::stream)
            .findFirst()
            .map(edge -> edge.getWeight().getClass().getTypeName());
    }

    public Vertex<T> getVertex(T top) {
        // Tìm và trả về vertex có id tương ứng
        return adjacentVertices
            .keySet()
            .stream()
            .filter(v -> v.getTop().equals(top))
            .findFirst()
            .orElse(null);
    }

    public void addEdge(T startTop, T endTop, W weight) {
        Vertex<T> startVertex = getVertex(startTop);
        Vertex<T> endVertex = getVertex(endTop);

        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Invalid vertex id");
        }

        // Thêm cạnh vào danh sách của startVertex
        Edge<T, W> edge = new Edge<>(startVertex, endVertex, weight, directed);
        adjacentVertices.get(startVertex).add(edge);

        // Nếu đồ thị là vô hướng, thêm cạnh ngược từ endVertex về startVertex
        if (!directed) {
            Edge<T, W> reverseEdge = new Edge<>(endVertex, startVertex, weight, directed);
            adjacentVertices.get(endVertex).add(reverseEdge);
        }
    }

    public List<Edge<T, W>> getEdges(Vertex<T> vertex) {
        return adjacentVertices.getOrDefault(vertex, Collections.emptyList());
    }

    public Edge<T, W> getEdge(Vertex<T> u, Vertex<T> v) {
        return adjacentVertices.get(u).stream()
            .filter(edge -> edge.getEndVertex().equals(v))
            .findFirst()
            .orElse(null);
    }

    public List<Edge<T, W>> getEdges() {
        return adjacentVertices.values().stream().flatMap(List::stream).toList();
    }

    public Set<Vertex<T>> getVertices() {
        return adjacentVertices.keySet();
    }

    public boolean getDirected () {
        return this.directed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        adjacentVertices.forEach((key, value) -> {
            sb.append(key.getTop()).append(" -> ");
            sb.append("[ ");
            sb.append(value.stream()
                .map(Edge::toString)
                .collect(Collectors.joining(", ")));
            sb.append(" ]\n");
        });
        
        return sb.toString();
    }

    public boolean hasEdge(T startTop, T endTop) {
        Vertex<T> startVertex = getVertex(startTop);
        Vertex<T> endVertex = getVertex(endTop);

        if (startVertex == null || endVertex == null) {
            return false; // Một trong hai đỉnh không tồn tại
        }

        // Lấy danh sách các cạnh của đỉnh bắt đầu
        List<Edge<T, W>> edges = adjacentVertices.get(startVertex);

        // Kiểm tra xem có cạnh nào nối đến đỉnh kết thúc không
        return edges.stream().anyMatch(edge -> edge.getEndVertex().equals(endVertex));
    }

    public void removeEdge(Vertex<T> vertex, Edge<T, W> edge) {

        // Lấy danh sách các cạnh kết nối với đỉnh fromVertex
        List<Edge<T, W>> edges = adjacentVertices.get(vertex);

        // Nếu danh sách cạnh không rỗng, xóa cạnh chỉ định từ danh sách
        if (edges != null) {
            edges.remove(edge);

            // Nếu không còn cạnh nào liên kết với đỉnh, xóa đỉnh khỏi bản đồ
            if (edges.isEmpty()) {
                adjacentVertices.remove(vertex);
            }
        }
    }

    public boolean isDirected() {
        return this.getDirected();
    }
}
