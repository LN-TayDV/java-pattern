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
package com.iluwatar.algorithm.theories.on.internet.greedy.dijkstra;

import java.util.*;

public class DijkstraAlgorithm {
    private static final int V = 9;

    // Hàm hỗ trợ để tìm đỉnh có key nhỏ nhất từ tập các đỉnh chưa được thăm
    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // In kết quả
    private static void printSolution(int[] dist) {
        System.out.println("Đỉnh \t\t Khoảng cách từ nguồn");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    // Thuật toán Dijkstra để tìm đường đi ngắn nhất từ một đỉnh nguồn đến các đỉnh còn lại trong đồ thị có trọng số
    public static void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V]; // Lưu trữ khoảng cách ngắn nhất từ nguồn đến đỉnh i
        boolean[] mstSet = new boolean[V]; // true nếu đỉnh i đã được xác định khoảng cách ngắn nhất

        // Khởi tạo khoảng cách ngắn nhất từ nguồn đến tất cả các đỉnh là vô cùng và mstSet[] là false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Khoảng cách từ nguồn đến chính nó luôn bằng 0
        dist[src] = 0;

        // Tìm đường đi ngắn nhất cho tất cả các đỉnh
        for (int count = 0; count < V - 1; count++) {
            // Chọn đỉnh có khoảng cách ngắn nhất từ tập các đỉnh chưa được thăm
            int u = minKey(dist, mstSet);

            // Đánh dấu đỉnh này đã được thăm
            mstSet[u] = true;

            // Cập nhật khoảng cách của các đỉnh kề của đỉnh vừa thăm
            for (int v = 0; v < V; v++) {
                if (!mstSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // In kết quả
        printSolution(dist);
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 7, 0, 10, 15, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 10, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 0, 0, 0, 0, 6, 7, 0}};

        dijkstra(graph, 0);
    }
}

