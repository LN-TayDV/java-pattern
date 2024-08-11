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
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Thuật toán tìm thành phần liên thông mạnh (Strongly Connected Components - SCC)

 * Tarjan's Algorithm: Tìm SCCs hiệu quả với một lần duyệt DFS duy nhất.
 */
public class Tarjan {

    /**
     * Tìm các thành phần liên thông mạnh (SCC) trong đồ thị có hướng.
     *
     * @param graph Đồ thị đầu vào có hướng.
     * @param <T>   Loại dữ liệu của đỉnh.
     * @param <W>   Loại dữ liệu của trọng số cạnh, phải kế thừa từ Number và Comparable.
     * @return Danh sách các thành phần liên thông mạnh, mỗi thành phần là một tập hợp các đỉnh.
     */
    public static <T, W extends Number & Comparable<W>> List<Set<Vertex<T>>> algorithm(Graph<T, W> graph) {

        // Lấy danh sách tất cả các đỉnh trong đồ thị
        List<Vertex<T>> vertices = new ArrayList<>(graph.getVertices());

        // Maps để lưu chỉ số DFS và low-link của các đỉnh
        Map<Vertex<T>, Integer> index = new HashMap<>();
        Map<Vertex<T>, Integer> lowLink = new HashMap<>();

        // Ngăn xếp lưu trữ các đỉnh trong quá trình DFS
        Stack<Vertex<T>> stack = new Stack<>();

        // Tập hợp các đỉnh hiện đang trong ngăn xếp
        Set<Vertex<T>> onStack = new HashSet<>();

        // Danh sách các SCC được tìm thấy
        List<Set<Vertex<T>>> sccs = new ArrayList<>();

        // Biến chỉ số DFS hiện tại, được bọc trong mảng để có thể thay đổi trong lambda
        int[] currentIndex = {0};

        // Duyệt qua tất cả các đỉnh trong đồ thị
        for (Vertex<T> vertex : vertices) {

            if (!index.containsKey(vertex)) {
                // Nếu đỉnh chưa được duyệt, gọi hàm DFS để duyệt đỉnh và các đỉnh liên quan
                tarjanDFS(graph, vertex, index, lowLink, stack, onStack, sccs, currentIndex);
            }

        }

        return sccs;
    }

    /**
     * Hàm DFS thực hiện việc duyệt đồ thị để tìm các thành phần liên thông mạnh.
     *
     * @param graph        Đồ thị đầu vào.
     * @param vertex       Đỉnh hiện tại đang duyệt.
     * @param index        Map lưu chỉ số DFS của các đỉnh.
     * @param lowLink      Map lưu chỉ số thấp nhất của các đỉnh.
     * @param stack        Ngăn xếp lưu trữ các đỉnh trong quá trình duyệt.
     * @param onStack      Tập hợp các đỉnh hiện đang trong ngăn xếp.
     * @param sccs         Danh sách các SCC được tìm thấy.
     * @param currentIndex Mảng chứa chỉ số DFS hiện tại, được bọc để có thể thay đổi trong lambda.
     */
    private static <T, W extends Number & Comparable<W>> void tarjanDFS(
                                                                        Graph<T, W> graph,
                                                                        Vertex<T> vertex,
                                                                        Map<Vertex<T>, Integer> index,
                                                                        Map<Vertex<T>, Integer> lowLink,
                                                                        Stack<Vertex<T>> stack,
                                                                        Set<Vertex<T>> onStack,
                                                                        List<Set<Vertex<T>>> sccs,
                                                                        int[] currentIndex) {

        // Đặt chỉ số DFS cho đỉnh hiện tại và chỉ số thấp nhất là chỉ số DFS của nó
        index.put(vertex, currentIndex[0]);
        lowLink.put(vertex, currentIndex[0]);
        currentIndex[0]++;  // Tăng chỉ số DFS cho lần duyệt tiếp theo

        // Đẩy đỉnh hiện tại vào ngăn xếp và đánh dấu nó là đang có mặt trong ngăn xếp
        stack.push(vertex);
        onStack.add(vertex);

        // Duyệt qua tất cả các cạnh xuất phát từ đỉnh hiện tại
        for (Edge<T, W> edge : graph.getEdges(vertex)) {
            Vertex<T> neighbor = edge.getDestination();

            if (!index.containsKey(neighbor)) {
                // Nếu đỉnh hàng xóm chưa được duyệt, gọi DFS đệ quy trên đỉnh hàng xóm
                tarjanDFS(graph, neighbor, index, lowLink, stack, onStack, sccs, currentIndex);

                // Cập nhật chỉ số thấp nhất của đỉnh hiện tại dựa trên chỉ số thấp nhất của đỉnh hàng xóm
                lowLink.put(vertex, Math.min(lowLink.get(vertex), lowLink.get(neighbor)));

            } else if (onStack.contains(neighbor)) {

                // Nếu đỉnh hàng xóm đang trong ngăn xếp, cập nhật chỉ số thấp nhất của đỉnh hiện tại
                lowLink.put(vertex, Math.min(lowLink.get(vertex), index.get(neighbor)));
            }
        }

        // Nếu chỉ số thấp nhất của đỉnh hiện tại bằng chỉ số DFS của nó, đây là điểm bắt đầu của một SCC
        if (lowLink.get(vertex).equals(index.get(vertex))) {

            Set<Vertex<T>> scc = new HashSet<>();
            Vertex<T> w;

            // Lấy tất cả các đỉnh từ ngăn xếp cho đến khi gặp đỉnh hiện tại
            do {
                w = stack.pop();
                onStack.remove(w);
                scc.add(w);
            } while (!w.equals(vertex));

            // Thêm SCC tìm được vào danh sách các SCC
            sccs.add(scc);
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
        List<Set<Vertex<String>>> sccs = Tarjan.algorithm(graph);

        // In kết quả ra màn hình
        int count = 1;
        for (Set<Vertex<String>> scc : sccs) {
            System.out.println("SCC " + count + ": " + scc);
            count++;
        }
    }
}
