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

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary.AlgorithmUtils;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Path;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Hopcroft-Karp: Tìm kết hợp cực đại trong đồ thị lưỡng phân.
 */
public class HopcroftKarp<T, W extends Number & Comparable<W>> {

    private final Graph<T, W> graph;
    private final List<Vertex<T>> leftVertices;
    private final List<Vertex<T>> rightVertices;
    private final W infinity;
    private final Map<Vertex<T>, Vertex<T>> pairU; // Sự ghép nối từ U
    private final Map<Vertex<T>, Vertex<T>> pairV; // Sự ghép nối từ V
    private final Map<Vertex<T>, W> dist;          // Khoảng cách trong BFS

    // Constructor để khởi tạo thuật toán
    public HopcroftKarp(Graph<T, W> graph, List<Vertex<T>> leftVertices, List<Vertex<T>> rightVertices, W infinity) {
        this.graph = graph;
        this.leftVertices = leftVertices;
        this.rightVertices = rightVertices;
        this.infinity = infinity;
        this.pairU = new HashMap<>();
        this.pairV = new HashMap<>();
        this.dist = new HashMap<>();

        // Khởi tạo ghép nối và khoảng cách
        for (Vertex<T> u : leftVertices) {
            pairU.put(u, null);
        }
        for (Vertex<T> v : rightVertices) {
            pairV.put(v, null);
        }
    }

    // Tìm ghép nối cực đại sử dụng thuật toán Hopcroft-Karp
    public int maximumMatching(Graph<T, W> graph) {
        int matchingSize = 0;
        while (bfs(graph)) {
            for (Vertex<T> u : leftVertices) {
                if (pairU.get(u) == null && dfs(graph, u)) {
                    matchingSize++;
                }
            }
        }
        return matchingSize;
    }

    // Tìm khoảng cách từ đỉnh trong BFS
    private boolean bfs(Graph<T, W> graph) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        for (Vertex<T> u : leftVertices) {
            if (pairU.get(u) == null) {
                dist.put(u, zero(graph)); // Zero là giá trị số không tương ứng với kiểu W
                queue.add(u);
            } else {
                dist.put(u, infinity);
            }
        }
        dist.put(null, infinity);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            if (dist.get(u).compareTo(dist.get(null)) < 0) {
                for (Edge<T, W> edge : graph.getEdges(u)) {
                    Vertex<T> v = edge.getDestination();
                    if (dist.get(pairV.get(v)) == infinity) {
                        dist.put(pairV.get(v), add(dist.get(u), one(graph))); // one() là giá trị số một tương ứng với kiểu W
                        queue.add(pairV.get(v));
                    }
                }
            }
        }
        return dist.get(null).compareTo(infinity) < 0;
    }

    // Tìm ghép nối tối ưu sử dụng DFS
    private boolean dfs(Graph<T, W> graph, Vertex<T> u) {
        if (u != null) {
            for (Edge<T, W> edge : graph.getEdges(u)) {
                Vertex<T> v = edge.getDestination();
                if (dist.get(pairV.get(v)).compareTo(add(dist.get(u), one(graph))) == 0) {
                    if (dfs(graph, pairV.get(v))) {
                        pairV.put(v, u);
                        pairU.put(u, v);
                        return true;
                    }
                }
            }
            dist.put(u, infinity);
            return false;
        }
        return true;
    }

    // Phương thức để tạo giá trị 0 cho kiểu W
    private W zero(Graph<T, W> graph) {
        // Implement this method based on how you create a zero value for W
        return AlgorithmUtils.defaultValue(graph, 0); // Placeholder, replace with correct implementation
    }

    // Phương thức để tạo giá trị 1 cho kiểu W
    private W one(Graph<T, W> graph) {
        // Implement this method based on how you create a one value for W
        return  AlgorithmUtils.defaultValue(graph, 1); // Placeholder, replace with correct implementation
    }

    // Phương thức để cộng hai giá trị W
    private W add(W a, W b) {
        // Implement this method to perform addition with W
        // This is a placeholder implementation
        return AlgorithmUtils.sum(a,b); // Replace with correct implementation
    }

    // Phương thức static để khởi tạo và chạy thuật toán Hopcroft-Karp
    public static <T, W extends Number & Comparable<W>> Path<T, W> algorithm(Graph<T, W> graph, List<Vertex<T>> leftVertices, List<Vertex<T>> rightVertices, W infinity) {
        // Khởi tạo thuật toán Hopcroft-Karp
        HopcroftKarp<T, W> hopcroftKarp = new HopcroftKarp<>(graph, leftVertices, rightVertices, infinity);

        // Tìm kết hợp cực đại
        int matchingSize = hopcroftKarp.maximumMatching(graph);

        // Tạo đối tượng Path để lưu kết quả
        Path<T, W> path = new Path<>();

        // Xử lý và thêm kết quả vào Path
        for (Vertex<T> u : leftVertices) {
            Vertex<T> v = hopcroftKarp.pairU.get(u);
            if (v != null) {
                path.add(u, hopcroftKarp.one(graph)); // Giả định fCost là 1 cho mỗi cạnh
            }
        }

        // Trả về kết quả Path
        return path;
    }

    public static void main(String[] args) {
        // Tạo đồ thị lưỡng phân với trọng số là số thực (Double)
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh vào đồ thị
        Vertex<String> s = graph.addVertex("S");
        Vertex<String> a = graph.addVertex("A");
        Vertex<String> b = graph.addVertex("B");
        Vertex<String> c = graph.addVertex("C");
        Vertex<String> t = graph.addVertex("T");

        // Thêm các cạnh với trọng số
        graph.addEdge("S", "A", 10.0);
        graph.addEdge("S", "B", 5.0);
        graph.addEdge("A", "B", 15.0);
        graph.addEdge("A", "C", 10.0);
        graph.addEdge("B", "C", 10.0);
        graph.addEdge("B", "T", 10.0);
        graph.addEdge("C", "T", 10.0);

        System.out.println(graph);

        // Xác định các đỉnh thuộc tập bên trái và bên phải của đồ thị lưỡng phân
        List<Vertex<String>> leftVertices = Arrays.asList(s, a, b);
        List<Vertex<String>> rightVertices = Arrays.asList(c, t);

        // Chạy thuật toán Hopcroft-Karp để tìm kết hợp cực đại
        Path<String, Double> path = HopcroftKarp.algorithm(graph, leftVertices, rightVertices, Double.MAX_VALUE);

        // In ra đường đi
        if (path != null) {
            System.out.println("Hopcroft-Karp Path: " + path);
        } else {
            System.out.println("Không tìm thấy đường đi trong đồ thị.");
        }
    }


}
