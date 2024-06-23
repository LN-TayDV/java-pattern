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
package com.iluwatar.algorithm.theories.backtracking.hamiltonian.path;

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
