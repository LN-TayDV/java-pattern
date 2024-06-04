package com.iluwatar.algorithm.Graph.search;


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
