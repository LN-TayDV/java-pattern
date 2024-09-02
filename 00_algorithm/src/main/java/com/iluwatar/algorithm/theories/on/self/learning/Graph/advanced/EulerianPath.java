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
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Path;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Eulerian Path/Circuit: Tìm đường đi hoặc chu trình đi qua tất cả các cạnh của đồ thị một lần.
 */
public class EulerianPath {

    // Thuật toán để tìm đường đi hoặc chu trình Eulerian trong đồ thị
    public static <T, W extends Number & Comparable<W>> Path<T, W> algorithm(Graph<T, W> graph) {
        // Kiểm tra nếu đồ thị không có đường đi hoặc chu trình Eulerian
        // Nếu không có Eulerian Path hoặc Circuit thì trả về null
        if (!hasEulerianPath(graph) && !hasEulerianCircuit(graph)) {
            return null;
        }

        // Tìm đỉnh bắt đầu cho thuật toán
        // Bắt đầu từ đỉnh có bậc lẻ nếu tồn tại, nếu không thì chọn đỉnh bất kỳ
        Vertex<T> startVertex = findStartVertex(graph);

        // Sử dụng stack để lưu trữ các đỉnh đang được duyệt
        Deque<Vertex<T>> stack = new LinkedList<>();
        stack.push(startVertex); // Đẩy đỉnh bắt đầu vào stack

        // Biến để lưu trữ đường đi Eulerian cuối cùng
        Path<T, W> eulerianPath = null;
        Path<T, W> currentPath = null; // Biến tạm thời để lưu đường đi hiện tại

        // Bắt đầu duyệt đồ thị để tìm đường đi Eulerian
        while (!stack.isEmpty()) {
            // Lấy đỉnh hiện tại từ đỉnh trên cùng của stack
            Vertex<T> vertex = stack.peek();

            // Lấy danh sách các cạnh nối với đỉnh hiện tại
            List<Edge<T, W>> edges = graph.getEdges(vertex);

            // Kiểm tra nếu không còn cạnh nào nối với đỉnh hiện tại
            if (edges == null || edges.isEmpty()) {
                // Nếu không còn cạnh nào, lấy đỉnh này ra khỏi stack
                vertex = stack.pop();

                // Xây dựng đường đi Eulerian từ đỉnh này
                if (currentPath == null) {
                    // Nếu currentPath chưa tồn tại, tạo một đường đi mới với đỉnh hiện tại
                    currentPath = new Path<>(vertex, null, null);
                    eulerianPath = currentPath; // Đường đi Eulerian bắt đầu từ đây
                } else {
                    // Nếu đã có đường đi, kết nối đỉnh hiện tại với đường đi hiện tại
                    Path<T, W> newPath = new Path<>(vertex, null, currentPath);
                    currentPath = newPath; // Cập nhật currentPath với đỉnh mới
                }
            } else {
                // Nếu vẫn còn cạnh liên kết với đỉnh hiện tại, tiếp tục duyệt
                Edge<T, W> edge = edges.get(0); // Lấy cạnh đầu tiên trong danh sách
                stack.push(edge.getEndVertex()); // Đẩy đỉnh kết thúc của cạnh này vào stack
                graph.removeEdge(vertex, edge); // Xóa cạnh sau khi đã đi qua
                if (!graph.getDirected()) {
                    // Nếu đồ thị là vô hướng, xóa cạnh ngược lại
                    graph.removeEdge(edge.getEndVertex(), findReverseEdge(graph, edge.getEndVertex(), vertex));
                }
            }
        }

        // Trả về đường đi Eulerian cuối cùng
        return eulerianPath;
    }

    // Hàm kiểm tra đồ thị có chứa Eulerian Circuit (chu trình Eulerian) hay không
    private static <T, W extends Number & Comparable<W>> boolean hasEulerianCircuit(Graph<T, W> graph) {
        // Kiểm tra nếu tất cả các đỉnh đều có bậc chẵn và đồ thị liên thông
        return allVerticesEvenDegree(graph) && isConnected(graph);
    }

    // Hàm kiểm tra đồ thị có chứa Eulerian Path (đường đi Eulerian) hay không
    private static <T, W extends Number & Comparable<W>> boolean hasEulerianPath(Graph<T, W> graph) {
        // Kiểm tra nếu đồ thị có đúng hai đỉnh có bậc lẻ và đồ thị liên thông
        return hasTwoOddDegreeVertices(graph) && isConnected(graph);
    }

    // Hàm kiểm tra nếu tất cả các đỉnh trong đồ thị có bậc chẵn
    private static <T, W extends Number & Comparable<W>> boolean allVerticesEvenDegree(Graph<T, W> graph) {
        // Duyệt qua tất cả các đỉnh trong đồ thị
        for (Vertex<T> vertex : graph.getVertices()) {
            // Nếu có đỉnh nào có bậc lẻ, trả về false
            if (graph.getEdges(vertex).size() % 2 != 0) {
                return false;
            }
        }
        // Nếu tất cả các đỉnh đều có bậc chẵn, trả về true
        return true;
    }

    // Hàm kiểm tra đồ thị có đúng hai đỉnh có bậc lẻ hay không
    private static <T, W extends Number & Comparable<W>> boolean hasTwoOddDegreeVertices(Graph<T, W> graph) {
        int oddCount = 0; // Biến đếm số lượng đỉnh có bậc lẻ
        for (Vertex<T> vertex : graph.getVertices()) {
            // Kiểm tra nếu đỉnh có bậc lẻ, tăng biến đếm
            if (graph.getEdges(vertex).size() % 2 != 0) {
                oddCount++;
            }
        }
        // Đồ thị có đúng hai đỉnh lẻ thì trả về true, nếu không trả về false
        return oddCount == 2;
    }

    // Hàm kiểm tra nếu đồ thị là liên thông (có thể đi từ một đỉnh đến tất cả các đỉnh khác)
    private static <T, W extends Number & Comparable<W>> boolean isConnected(Graph<T, W> graph) {
        Set<Vertex<T>> visited = new HashSet<>(); // Tập hợp lưu trữ các đỉnh đã duyệt
        Deque<Vertex<T>> stack = new LinkedList<>(); // Stack để duyệt các đỉnh
        Vertex<T> startVertex = graph.getVertices().iterator().next(); // Lấy một đỉnh bất kỳ để bắt đầu

        stack.push(startVertex); // Đẩy đỉnh bắt đầu vào stack
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop(); // Lấy đỉnh trên cùng ra khỏi stack
            visited.add(vertex); // Đánh dấu đỉnh này đã được duyệt
            for (Edge<T, W> edge : graph.getEdges(vertex)) {
                // Duyệt qua tất cả các cạnh nối với đỉnh hiện tại
                if (!visited.contains(edge.getEndVertex())) {
                    stack.push(edge.getEndVertex()); // Đẩy đỉnh kết thúc của cạnh vào stack nếu chưa được duyệt
                }
            }
        }

        // Kiểm tra nếu tất cả các đỉnh đều đã được duyệt, trả về true
        for (Vertex<T> vertex : graph.getVertices()) {
            if (graph.getEdges(vertex).size() > 0 && !visited.contains(vertex)) {
                return false; // Nếu có đỉnh chưa được duyệt, trả về false
            }
        }

        return true; // Trả về true nếu tất cả các đỉnh đều liên thông
    }

    // Tìm đỉnh bắt đầu cho thuật toán, đỉnh có bậc lẻ hoặc đỉnh bất kỳ
    private static <T, W extends Number & Comparable<W>> Vertex<T> findStartVertex(Graph<T, W> graph) {
        // Duyệt qua tất cả các đỉnh, trả về đỉnh có bậc lẻ đầu tiên
        for (Vertex<T> vertex : graph.getVertices()) {
            if (graph.getEdges(vertex).size() % 2 != 0) {
                return vertex;
            }
        }
        // Nếu không có đỉnh lẻ nào, trả về một đỉnh bất kỳ
        return graph.getVertices().iterator().next();
    }

    // Tìm cạnh ngược lại trong đồ thị nếu là đồ thị vô hướng
    private static <T, W extends Number & Comparable<W>> Edge<T, W> findReverseEdge(Graph<T, W> graph, Vertex<T> u, Vertex<T> v) {
        // Duyệt qua các cạnh nối với đỉnh u, trả về cạnh nối với đỉnh v
        return graph.getEdges(u).stream()
            .filter(edge -> edge.getEndVertex().equals(v))
            .findFirst()
            .orElse(null);
    }


    public static void main(String[] args) {
        // Tạo đồ thị không có hướng (undirected)
        Graph<String, Double> graph = new Graph<>(false);

        // Thêm các đỉnh
        Vertex<String> vA = graph.addVertex("A");
        Vertex<String> vB = graph.addVertex("B");
        Vertex<String> vC = graph.addVertex("C");
        Vertex<String> vD = graph.addVertex("D");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("A", "C", 1.0);
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("B", "D", 1.0);
        graph.addEdge("C", "D", 1.0);

        // In ra đồ thị
        System.out.println(graph);

        // Chạy thuật toán Eulerian Path
        var path = EulerianPath.algorithm(graph);

        // In ra đường đi Eulerian Path
        if (path != null) {
            System.out.println("Eulerian Path: " + path);
        } else {
            System.out.println("Không tìm thấy đường đi Eulerian trong đồ thị.");
        }
    }
}
