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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.advanced;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Thuật toán tìm thành phần liên thông mạnh (Strongly Connected Components - SCC)
 *
 * Kosaraju's Algorithm: Dùng hai lần DFS để tìm tất cả các thành phần liên thông mạnh trong đồ thị có hướng.
 */
public class Kosaraju {

    // Tìm các thành phần liên thông mạnh (SCC) trong đồ thị
    public static <T, W extends Number & Comparable<W>> List<Set<Vertex<T>>> algorithm(Graph<T, W> graph) {
        List<Vertex<T>> vertices = new ArrayList<>(graph.getVertices());

        // Bước 1: DFS để tạo thứ tự kết thúc
        Map<Vertex<T>, Boolean> visited = new HashMap<>();
        Stack<Vertex<T>> finishStack = new Stack<>();

        for (Vertex<T> vertex : vertices) {
            visited.put(vertex, false);
        }

        for (Vertex<T> vertex : vertices) {
            if (!visited.get(vertex)) {
                dfsFinishOrder(graph, vertex, visited, finishStack);
            }
        }

        // Bước 2: Đảo ngược đồ thị
        Graph<T, ?> reversedGraph = reverseGraph(graph);

        // Bước 3: DFS trên đồ thị đảo ngược để tìm các SCC
        visited.clear();
        List<Set<Vertex<T>>> sccs = new ArrayList<>();

        for (Vertex<T> vertex : vertices) {
            visited.put(vertex, false);
        }

        while (!finishStack.isEmpty()) {
            Vertex<T> vertex = finishStack.pop();
            if (!visited.get(vertex)) {
                Set<Vertex<T>> scc = new HashSet<>();
                dfsCollectSCC(reversedGraph, vertex, visited, scc);
                sccs.add(scc);
            }
        }

        return sccs;
    }

    // DFS để tạo thứ tự kết thúc
    private static <T, W extends Number & Comparable<W>> void dfsFinishOrder(Graph<T, W> graph, Vertex<T> vertex, Map<Vertex<T>, Boolean> visited, Stack<Vertex<T>> finishStack) {
        visited.put(vertex, true);

        for (Edge<T, ?> edge : graph.getEdges(vertex)) {
            Vertex<T> neighbor = edge.getDestination();
            if (!visited.get(neighbor)) {
                dfsFinishOrder(graph, neighbor, visited, finishStack);
            }
        }

        finishStack.push(vertex);
    }

    // Đảo ngược đồ thị
    private static <T, W extends Number & Comparable<W>> Graph<T, ?> reverseGraph(Graph<T, W> graph) {
        Graph<T, W> reversedGraph = new Graph<>(graph.isDirected());

        for (Vertex<T> vertex : graph.getVertices()) {
            reversedGraph.addVertex(vertex.getTop());
        }

        for (Vertex<T> u : graph.getVertices()) {
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getDestination();
                reversedGraph.addEdge(v.getTop(), u.getTop(), edge.getWeight());
            }
        }

        return reversedGraph;
    }

    // DFS để thu thập các đỉnh thuộc một SCC
    private static <T, W extends Number & Comparable<W>> void dfsCollectSCC(Graph<T, W> graph, Vertex<T> vertex, Map<Vertex<T>, Boolean> visited, Set<Vertex<T>> scc) {
        visited.put(vertex, true);
        scc.add(vertex);

        for (Edge<T, W> edge : graph.getEdges(vertex)) {
            Vertex<T> neighbor = edge.getDestination();
            if (!visited.get(neighbor)) {
                dfsCollectSCC(graph, neighbor, visited, scc);
            }
        }
    }

    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(true); // true cho đồ thị có hướng

        // Thêm các đỉnh
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");

        // Thêm các đỉnh vào đồ thị
        graph.addVertex(a.getTop());
        graph.addVertex(b.getTop());
        graph.addVertex(c.getTop());
        graph.addVertex(d.getTop());
        graph.addVertex(e.getTop());

        // Thêm các cạnh
        graph.addEdge(a.getTop(), b.getTop(), 1);
        graph.addEdge(b.getTop(), c.getTop(), 1);
        graph.addEdge(c.getTop(), a.getTop(), 1);
        graph.addEdge(b.getTop(), d.getTop(), 1);
        graph.addEdge(d.getTop(), e.getTop(), 1);
        graph.addEdge(e.getTop(), d.getTop(), 1);

        // Tìm các SCC
        List<Set<Vertex<String>>> sccs = Kosaraju.algorithm(graph);

        // In kết quả ra màn hình
        int count = 1;
        for (Set<Vertex<String>> scc : sccs) {
            System.out.println("SCC " + count + ": " + scc);
            count++;
        }
    }
}
