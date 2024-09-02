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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.elementary;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Cây Khung Nhỏ Nhất:

 * Thuật toán Kruskal
 * Tìm cây khung nhỏ nhất trong đồ thị có trọng số không âm bằng cách chọn
 * các cạnh có trọng số nhỏ nhất mà không tạo ra chu trình.
 */
public class Kruskal {

    /**
     * Phương thức tĩnh để thực hiện thuật toán Kruskal.
     *
     * @param graph   Đồ thị cần tìm cây khung nhỏ nhất
     * @return        Một tập hợp các cạnh cấu thành cây khung nhỏ nhất
     */
    public static <T, W extends Number & Comparable<W>> Set<Edge<T, W>> algorithm(Graph<T, W> graph) {
        // Tập hợp để lưu trữ các cạnh của cây khung nhỏ nhất
        Set<Edge<T, W>> mstEdges = new HashSet<>();

        // Danh sách tất cả các cạnh của đồ thị sắp xếp theo trọng số
        List<Edge<T, W>> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparing(Edge::getWeight));

        // Danh sách các đỉnh trong đồ thị
        List<Vertex<T>> vertices = new ArrayList<>(graph.getVertices());

        // Tạo danh sách để theo dõi các nhóm đỉnh (tập hợp)
        Map<Vertex<T>, Set<Vertex<T>>> disjointSets = new HashMap<>();
        for (Vertex<T> vertex : vertices) {
            Set<Vertex<T>> set = new HashSet<>();
            set.add(vertex);
            disjointSets.put(vertex, set);
        }

        // Lặp qua tất cả các cạnh sắp xếp theo trọng số
        for (Edge<T, W> edge : edges) {
            Vertex<T> u = edge.getStartVertex();
            Vertex<T> v = edge.getEndVertex();

            // Tìm tập hợp của đỉnh u và v
            Set<Vertex<T>> setU = disjointSets.get(u);
            Set<Vertex<T>> setV = disjointSets.get(v);

            // Nếu đỉnh u và v thuộc các tập hợp khác nhau, hợp nhất các tập hợp này
            if (setU != setV) {
                // Thêm cạnh vào cây khung nhỏ nhất
                mstEdges.add(edge);

                // Hợp nhất các tập hợp
                setU.addAll(setV);
                for (Vertex<T> vertex : setV) {
                    disjointSets.put(vertex, setU);
                }
            }
        }

        // Trả về tập hợp các cạnh cấu thành cây khung nhỏ nhất
        return mstEdges;
    }

    public static void main(String[] args) {
        // Tạo đồ thị không có hướng
        Graph<String, Double> graph = new Graph<>(false);

        // Thêm các đỉnh
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 3.0);

        // In ra đồ thị
        System.out.println(graph);

        // Chạy thuật toán Kruskal để tìm cây khung nhỏ nhất
        Set<Edge<String, Double>> mstEdges = Kruskal.algorithm(graph);

        // In ra các cạnh của cây khung nhỏ nhất
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge<String, Double> edge : mstEdges) {
            System.out.println(edge.getStartVertex().getTop() + " -> " + edge.getEndVertex().getTop() + " : " + edge.getWeight());
        }
    }
}
