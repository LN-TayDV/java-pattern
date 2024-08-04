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
package com.iluwatar.algorithm.theories.on.internet.search.depth.first;


import java.util.*;

public class DepthFirstSearch {
    private int V; // Số lượng đỉnh (vertices)
    private LinkedList<Integer> adj[]; // Danh sách kề (adjacency list)

    // Constructor
    DepthFirstSearch(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    // Thêm cạnh vào đồ thị
    void addEdge(int v, int w) {
        adj[v].add(w); // Thêm w vào danh sách kề của v
    }

    // Hàm hỗ trợ cho DFS
    void DFSUtil(int v, boolean visited[]) {
        // Đánh dấu đỉnh hiện tại đã được thăm
        visited[v] = true;
        System.out.print(v + " ");

        // Duyệt tất cả các đỉnh kề của đỉnh hiện tại
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // Hàm chính thực hiện DFS
    void DFS(int v) {
        // Đánh dấu tất cả các đỉnh là chưa được thăm
        boolean visited[] = new boolean[V];

        // Gọi hàm hỗ trợ để in DFS từ đỉnh v
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        DepthFirstSearch g = new DepthFirstSearch(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Duyệt đồ thị theo chiều sâu " + "(bắt đầu từ đỉnh 2)");

        g.DFS(2);
    }
}
