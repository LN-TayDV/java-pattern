package com.iluwatar.algorithm.Graph.search.breadth.first;

import java.util.*;

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
