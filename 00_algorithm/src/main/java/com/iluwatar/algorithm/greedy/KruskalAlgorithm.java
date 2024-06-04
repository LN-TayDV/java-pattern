package com.iluwatar.algorithm.greedy;

import java.util.Arrays;

public class KruskalAlgorithm {

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Constructor
        Edge() {
            src = dest = weight = 0;
        }

        // Comparator để so sánh hai cạnh
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    };

    // Lớp kết nối các đỉnh với nhau
    class subset {
        int parent, rank;
    };

    int V, E;
    Edge edge[];

    // Constructor
    KruskalAlgorithm(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // Tìm tập hợp con chứa đỉnh i
    int find(subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // Hợp nhất hai tập hợp con chứa u và v
    void Union(subset subsets[], int u, int v) {
        int rootU = find(subsets, u);
        int rootV = find(subsets, v);
        if (subsets[rootU].rank < subsets[rootV].rank)
            subsets[rootU].parent = rootV;
        else if (subsets[rootU].rank > subsets[rootV].rank)
            subsets[rootV].parent = rootU;
        else {
            subsets[rootV].parent = rootU;
            subsets[rootU].rank++;
        }
    }

    // Thực hiện thuật toán Kruskal và in cây bao trùm nhỏ nhất
    void KruskalMST() {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);

        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }

        System.out.println("Cây bao trùm nhỏ nhất sử dụng Kruskal's Algorithm:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }

    // Hàm main để kiểm tra thuật toán Kruskal
    public static void main(String[] args) {
        int V = 4; // Số đỉnh
        int E = 5; // Số cạnh
        KruskalAlgorithm graph = new KruskalAlgorithm(V, E);

        // Thêm cạnh
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.KruskalMST();
    }
}
