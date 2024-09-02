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
package com.iluwatar.algorithm.theories.on.self.learning.Graph.basic;

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.HashSet;
import java.util.Set;

/**
 * Kiểm tra chu trình (Cycle Detection)
 * Xác định xem có chu trình trong đồ thị hay không.
 */
public class CycleDetection {

    // Phương thức tĩnh để kiểm tra chu trình trong đồ thị
    public static <T, W extends Number & Comparable<W>> boolean algorithm(Graph<T, W> graph) {
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Vertex<T>> recursionStack = new HashSet<>();

        // Duyệt qua tất cả các đỉnh trong đồ thị
        for (Vertex<T> vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                // Nếu phát hiện chu trình, trả về true
                if (dfs(vertex, visited, recursionStack, graph, graph.getDirected())) {
                    return true;
                }
            }
        }
        // Nếu không phát hiện chu trình, trả về false
        return false;
    }

    // Phương thức đệ quy để thực hiện DFS và kiểm tra chu trình
    private static <T, W extends Number & Comparable<W>> boolean dfs(Vertex<T> current,
                                      Set<Vertex<T>> visited,
                                      Set<Vertex<T>> recursionStack,
                                      Graph<T, W> graph,
                                      boolean directed) {
        // Đánh dấu đỉnh hiện tại là đã thăm
        visited.add(current);
        // Thêm đỉnh hiện tại vào ngăn xếp đệ quy để theo dõi đường đi hiện tại
        recursionStack.add(current);

        // Duyệt qua tất cả các cạnh nối với đỉnh hiện tại
        for (Edge<T, W> edge : graph.getEdges(current)) {
            // Lấy đỉnh kề từ cạnh đang duyệt
            Vertex<T> neighbor = edge.getEndVertex();

            // Nếu đỉnh kề chưa được thăm
            if (!visited.contains(neighbor)) {
                // Thực hiện DFS đệ quy trên đỉnh kề này
                if (dfs(neighbor, visited, recursionStack, graph, directed)) {
                    // Nếu phát hiện chu trình trong nhánh đệ quy, trả về true
                    return true;
                }
            }
            // Nếu đỉnh kề đã nằm trong ngăn xếp đệ quy
            else if (recursionStack.contains(neighbor)) {
                // Nếu đồ thị có hướng và phát hiện chu trình, trả về true
                if (directed) {
                    return true;
                }
                // Nếu đồ thị vô hướng và phát hiện chu trình (với neighbor không phải là đỉnh hiện tại)
                else if (!directed && !neighbor.equals(current)) {
                    return true;
                }
            }
        }

        // Xóa đỉnh hiện tại khỏi ngăn xếp đệ quy sau khi duyệt xong tất cả các đỉnh kề
        recursionStack.remove(current);
        // Nếu không phát hiện chu trình trong nhánh đệ quy hiện tại, trả về false
        return false;
    }


    public static void main(String[] args) {
        // Tạo đồ thị
        Graph<String, Integer> graph = new Graph<>(true);

        // Thêm các đỉnh vào đồ thị
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Thêm các cạnh vào đồ thị
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "E", 7);
        graph.addEdge("D", "E", 9);

        // In ra cấu trúc đồ thị
        System.out.println(graph);

        // Thực hiện BFS từ đỉnh "A"
        System.out.println("CycleDetection : " + algorithm(graph));
    }
}
