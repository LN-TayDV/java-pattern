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
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Đồ Thị Tạo Nhánh (Spanning Tree)
 *
 * Thuật toán Chuẩn Cải Tiến (Chu’s Improved Algorithm)
 * Cải tiến các thuật toán cây khung nhỏ nhất để làm việc hiệu quả hơn với các đồ thị lớn.
 */
public class ChusImproved {

    /**
     * Phương thức chính của thuật toán Chu's Improved để tìm cây khung nhỏ nhất.
     *
     * @param graph Đồ thị đầu vào với các đỉnh và cạnh có trọng số.
     * @param <T> Kiểu dữ liệu của đỉnh.
     * @param <W> Kiểu dữ liệu của trọng số cạnh, mở rộng từ Number và Comparable.
     * @return Cây khung nhỏ nhất dưới dạng một đồ thị mới.
     */
    public static <T, W extends Number & Comparable<W>> Graph<T, W> algorithm(Graph<T, W> graph) {
        // Khởi tạo cây khung nhỏ nhất rỗng
        Graph<T, W> mst = new Graph<>(graph.getDirected());

        // Tập hợp các đỉnh đã được xử lý
        Set<Vertex<T>> processedVertices = new HashSet<>();

        // Tập hợp các cạnh có trọng số nhỏ nhất, bắt đầu bằng một đỉnh ngẫu nhiên
        PriorityQueue<Edge<T, W>> minHeap = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));

        // Chọn một đỉnh bắt đầu ngẫu nhiên từ đồ thị
        Vertex<T> startVertex = graph.getVertices().iterator().next();
        processedVertices.add(startVertex);

        // Thêm tất cả các cạnh nối với đỉnh bắt đầu vào minHeap
        minHeap.addAll(graph.getEdges(startVertex));

        // Duyệt qua tất cả các đỉnh trong đồ thị
        while (!minHeap.isEmpty() && processedVertices.size() < graph.getVertices().size()) {
            // Lấy cạnh có trọng số nhỏ nhất từ minHeap
            Edge<T, W> minEdge = minHeap.poll();

            // Lấy đỉnh đầu và đỉnh cuối của cạnh
            Vertex<T> u = minEdge.getStartVertex();
            Vertex<T> v = minEdge.getEndVertex();

            // Kiểm tra xem đỉnh v đã được xử lý hay chưa
            if (processedVertices.contains(v)) {
                // Nếu đỉnh v đã được xử lý, bỏ qua cạnh này để tránh chu trình
                continue;
            }

            // Nếu đỉnh v chưa được xử lý, thêm nó vào tập hợp các đỉnh đã xử lý
            processedVertices.add(v);

            // Thêm cạnh này vào cây khung nhỏ nhất
            mst.addVertex(u.getTop());
            mst.addVertex(v.getTop());
            mst.addEdge(u.getTop(), v.getTop(), minEdge.getWeight());

            // Thêm tất cả các cạnh nối với đỉnh v mà chưa được xử lý vào minHeap
            for (Edge<T, W> edge : graph.getEdges(v)) {
                if (!processedVertices.contains(edge.getEndVertex())) {
                    minHeap.add(edge);
                }
            }
        }

        // Trả về cây khung nhỏ nhất
        return mst;
    }

    public static void main(String[] args) {
        // Khởi tạo đồ thị có hướng hoặc vô hướng
        boolean directed = false;  // Change this to `true` for a directed graph
        Graph<String, Integer> graph = new Graph<>(directed);

        // Thêm các đỉnh vào đồ thị
        Vertex<String> A = graph.addVertex("A");
        Vertex<String> B = graph.addVertex("B");
        Vertex<String> C = graph.addVertex("C");
        Vertex<String> D = graph.addVertex("D");
        Vertex<String> E = graph.addVertex("E");

        // Thêm các cạnh và trọng số vào đồ thị
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 3);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 4);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "E", 1);

        // In ra đồ thị ban đầu
        System.out.println("Đồ thị ban đầu:");
        System.out.println(graph);

        // Chạy thuật toán Chu's Improved để tìm cây khung nhỏ nhất
        Graph<String, Integer> mst = ChusImproved.algorithm(graph);

        // In ra cây khung nhỏ nhất
        System.out.println("Cây khung nhỏ nhất (MST):");
        System.out.println(mst);
    }
}
