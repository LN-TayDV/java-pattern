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
package com.iluwatar.algorithm.theories.on.internet.search.breadth.first;

import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstSearch {
    private int V; // Số lượng đỉnh
    private LinkedList<Integer> adj[]; // Danh sách kề

    // Constructor
    BreadthFirstSearch(int v) {
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

    // Hàm thực hiện BFS từ một đỉnh nguồn s
    void BFS(int s) {
        // Đánh dấu tất cả các đỉnh là chưa được thăm
        boolean visited[] = new boolean[V];

        // Hàng đợi cho BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Đánh dấu đỉnh hiện tại đã được thăm và đưa nó vào hàng đợi
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Lấy ra một đỉnh từ hàng đợi và in ra
            s = queue.poll();
            System.out.print(s + " ");

            // Lấy tất cả các đỉnh kề của đỉnh s
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        BreadthFirstSearch g = new BreadthFirstSearch(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Duyệt đồ thị theo chiều rộng " + "(bắt đầu từ đỉnh 2)");

        g.BFS(2);
    }
}
