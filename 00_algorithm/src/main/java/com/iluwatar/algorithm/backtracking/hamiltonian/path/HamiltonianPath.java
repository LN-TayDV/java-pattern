package com.iluwatar.algorithm.backtracking.hamiltonian.path;

public class HamiltonianPath {
    private int V, pathCount;
    private int[] path;
    private int[][] graph;

    // Kiểm tra xem có thể thêm đỉnh vào đường đi không
    private boolean isSafe(int v, int pos) {
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        return true;
    }

    // Tìm đường đi Hamilton
    private boolean hamiltonianPathUtil(int pos) {
        if (pos == V) {
            return true;
        }
        for (int v = 1; v < V; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;
                if (hamiltonianPathUtil(pos + 1)) {
                    return true;
                }
                path[pos] = -1;
            }
        }
        return false;
    }

    // Giải bài toán đường đi Hamilton
    public boolean solve(int[][] g) {
        V = g.length;
        path = new int[V];
        graph = g;
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }
        path[0] = 0;
        if (!hamiltonianPathUtil(1)) {
            System.out.println("Không có đường đi Hamilton");
            return false;
        }
        printSolution();
        return true;
    }

    // In đường đi Hamilton
    private void printSolution() {
        System.out.print("Đường đi Hamilton: ");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HamiltonianPath hamiltonian = new HamiltonianPath();
        int[][] graph = {
            {0, 1, 1, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 1, 0, 0, 0},
            {1, 1, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 1, 1},
            {0, 0, 1, 0, 1, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 1, 0}
        };
        hamiltonian.solve(graph);
    }
}
