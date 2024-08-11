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

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Cây Khung Nhỏ Nhất :
 *
 * Thuật toán Prim
 * Tìm cây khung nhỏ nhất trong đồ thị có trọng số không âm bằng cách mở rộng cây khung từ một đỉnh gốc.
 */
public class Prim {

    /**
     * Phương thức tĩnh để thực hiện thuật toán Prim.
     *
     * @param graph   Đồ thị cần tìm cây khung nhỏ nhất
     * @return        Một tập hợp các cạnh cấu thành cây khung nhỏ nhất
     */
    public static <T, W extends Number & Comparable<W>> Set<Edge<T, W>> algorithm(Graph<T, W> graph) {
        // Tập hợp để lưu trữ các cạnh của cây khung nhỏ nhất
        Set<Edge<T, W>> mstEdges = new HashSet<>();

        // PriorityQueue để lưu trữ các cạnh theo trọng số (nhỏ nhất có độ ưu tiên cao nhất)
        PriorityQueue<Edge<T, W>> priorityQueue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));

        // Danh sách các đỉnh đã được thêm vào cây khung
        Set<Vertex<T>> inMST = new HashSet<>();

        // Bắt đầu từ đỉnh đầu tiên (có thể chọn bất kỳ đỉnh nào)
        Vertex<T> startVertex = graph.getVertices().iterator().next();
        inMST.add(startVertex);

        // Thêm các cạnh của đỉnh bắt đầu vào PriorityQueue
        priorityQueue.addAll(graph.getEdges(startVertex));

        while (!priorityQueue.isEmpty()) {
            // Chọn cạnh có trọng số nhỏ nhất từ PriorityQueue
            Edge<T, W> edge = priorityQueue.poll();
            Vertex<T> u = edge.getStartVertex();
            Vertex<T> v = edge.getEndVertex();

            // Nếu đỉnh v chưa được thêm vào cây khung, thêm nó vào cây khung
            if (!inMST.contains(v)) {
                // Thêm cạnh vào cây khung nhỏ nhất
                mstEdges.add(edge);
                inMST.add(v);

                // Thêm các cạnh của đỉnh v vào PriorityQueue nếu đỉnh này chưa được thêm vào cây khung
                for (Edge<T, W> nextEdge : graph.getEdges(v)) {
                    if (!inMST.contains(nextEdge.getEndVertex())) {
                        priorityQueue.add(nextEdge);
                    }
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
        Vertex<String> vA = graph.addVertex("A");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("B", "D", 5.0);
        graph.addEdge("C", "D", 3.0);

        // In ra đồ thị
        System.out.println(graph);

        // Chạy thuật toán Prim để tìm cây khung nhỏ nhất
        Set<Edge<String, Double>> mstEdges = Prim.algorithm(graph);

        // In ra các cạnh của cây khung nhỏ nhất
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge<String, Double> edge : mstEdges) {
            System.out.println(edge.getStartVertex().getTop() + " - " + edge.getEndVertex().getTop() + ": " + edge.getWeight());
        }
    }
}
