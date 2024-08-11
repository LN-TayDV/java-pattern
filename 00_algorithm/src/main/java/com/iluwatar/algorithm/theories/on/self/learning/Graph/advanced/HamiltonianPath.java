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

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Path;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.HashSet;
import java.util.Set;

/**
 * Hamiltonian Path/Circuit: Tìm đường đi hoặc chu trình đi qua tất cả các đỉnh của đồ thị một lần.
 */
public class HamiltonianPath {

    /**
     * Tìm đường đi Hamiltonian trong đồ thị
     * @param graph Đồ thị cần tìm đường đi
     * @param <T> Loại của đỉnh
     * @param <W> Loại của trọng số
     * @return Đường đi Hamiltonian nếu tồn tại, null nếu không
     */
    public static <T, W extends Number & Comparable<W>> Path<T, W> algorithm(Graph<T, W> graph) {
        // Lấy tất cả các đỉnh của đồ thị
        Set<Vertex<T>> vertices = graph.getVertices();
        // Nếu đồ thị không có đỉnh, trả về null
        if (vertices.isEmpty()) {
            return null;
        }

        // Dùng một danh sách để lưu trữ đường đi Hamiltonian
        Path<T, W> path = new Path<>();
        // Tạo một tập hợp để theo dõi các đỉnh đã thăm
        Set<Vertex<T>> visited = new HashSet<>();

        // Thử tất cả các đỉnh làm điểm bắt đầu cho đường đi Hamiltonian
        for (Vertex<T> startVertex : vertices) {
            // Nếu tìm được đường đi Hamiltonian từ đỉnh này, trả về nó
            if (findHamiltonianPath(graph, startVertex, visited, path)) {
                return path;
            }
        }

        // Nếu không tìm thấy đường đi Hamiltonian, trả về null
        return null;
    }

    /**
     * Đệ quy để tìm đường đi Hamiltonian
     * @param graph Đồ thị cần tìm đường đi
     * @param currentVertex Đỉnh hiện tại
     * @param visited Tập hợp các đỉnh đã thăm
     * @param path Đường đi Hamiltonian hiện tại
     * @param <T> Loại của đỉnh
     * @param <W> Loại của trọng số
     * @return true nếu tìm được đường đi Hamiltonian, false nếu không
     */
    private static <T, W extends Number & Comparable<W>> boolean findHamiltonianPath(
        Graph<T, W> graph, Vertex<T> currentVertex, Set<Vertex<T>> visited, Path<T, W> path) {

        // Thêm đỉnh hiện tại vào đường đi và đánh dấu là đã thăm
        visited.add(currentVertex);
        path.add(currentVertex, null); // Truyền null cho fCost vì không cần thiết cho Hamiltonian Path

        // Nếu tất cả các đỉnh đã được thăm, kiểm tra xem có phải là đường đi Hamiltonian
        if (visited.size() == graph.getVertices().size()) {
            return true;
        }

        // Lấy danh sách các cạnh của đỉnh hiện tại
        for (Edge<T, W> edge : graph.getEdges(currentVertex)) {
            Vertex<T> neighbor = edge.getEndVertex(); // Lấy đỉnh kết thúc của cạnh

            // Nếu đỉnh hàng xóm chưa được thăm
            if (!visited.contains(neighbor)) {
                // Thử tìm đường đi Hamiltonian từ đỉnh hàng xóm
                if (findHamiltonianPath(graph, neighbor, visited, path)) {
                    return true;
                }
            }
        }

        // Nếu không tìm thấy đường đi Hamiltonian từ đỉnh hiện tại, gỡ bỏ đỉnh và quay lại
        visited.remove(currentVertex);

        // Loại bỏ đỉnh cuối cùng khỏi đường đi
        path.removeLast();

        return false;
    }

    public static void main(String[] args) {
        // Tạo đồ thị có hướng với trọng số là số thực (Double)
        Graph<String, Double> graph = new Graph<>(true);

        // Thêm các đỉnh vào đồ thị
        Vertex<String> s = graph.addVertex("S");
        Vertex<String> source = graph.addVertex("A");
        Vertex<String> b = graph.addVertex("B");
        Vertex<String> c = graph.addVertex("C");
        Vertex<String> sink = graph.addVertex("T");

        // Thêm các cạnh với trọng số (dung lượng)
        graph.addEdge("S", "A", 10.0);
        graph.addEdge("S", "B", 5.0);
        graph.addEdge("A", "B", 15.0);
        graph.addEdge("A", "C", 10.0);
        graph.addEdge("B", "C", 10.0);
        graph.addEdge("B", "T", 10.0);
        graph.addEdge("C", "T", 10.0);

        System.out.println(graph);

        // Chạy thuật toán Ford-Fulkerson để tìm luồng cực đại từ đỉnh nguồn đến đỉnh đích
        var path = HamiltonianPath.algorithm(graph);

        // In ra đường đi Eulerian Path
        if (path != null) {
            System.out.println("Hamiltonian Path: " + path);
        } else {
            System.out.println("Không tìm thấy đường đi Eulerian trong đồ thị.");
        }


    }
}
