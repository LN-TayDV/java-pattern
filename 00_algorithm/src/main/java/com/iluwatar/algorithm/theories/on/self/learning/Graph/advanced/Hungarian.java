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

import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Edge;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Graph;
import com.iluwatar.algorithm.theories.on.self.learning.Graph.elements.type.with.map.Vertex;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Thuật toán tìm kiếm kết hợp trong đồ thị (Graph Matching)
 *
 * Hungarian Algorithm: Tìm kiếm kết hợp tối ưu trong đồ thị lưỡng phân (bipartite graph).
 */
public class Hungarian {

    // Tìm kiếm ghép nối tối ưu cho ma trận chi phí
    public static <T, W extends Number & Comparable<W>> List<Pair<T>> algorithm(Graph<T, W> graph) {
        List<Pair<T>> result = new ArrayList<>();

        // Convert the graph to a cost matrix
        List<Vertex<T>> vertices = new ArrayList<>(graph.getVertices());
        int size = vertices.size();
        int[][] costMatrix = new int[size][size];

        // Initialize cost matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Vertex<T> u = vertices.get(i);
                Vertex<T> v = vertices.get(j);
                Edge<T, W> edge = graph.getEdge(u, v);
                costMatrix[i][j] = (edge != null) ? edge.getWeight().intValue() : Integer.MAX_VALUE;
            }
        }

        // Apply Hungarian Algorithm here
        Matrix matrix = new Matrix(costMatrix);
        int[][] optimalMatching = hungarianAlgorithmImplementation(matrix);

        // Convert the result to list of pairs
        for (int i = 0; i < size; i++) {
            int j = -1;
            for (int k = 0; k < size; k++) {
                if (optimalMatching[i][k] == 0) {
                    j = k;
                    break;
                }
            }
            if (j != -1) {
                result.add(new Pair<>(vertices.get(i), vertices.get(j)));
            }
        }

        return result;
    }

    // Placeholder for Hungarian Algorithm implementation
    private static int[][] hungarianAlgorithmImplementation(Matrix matrix) {
        // Implement the Hungarian Algorithm or use a library for the actual algorithm
        // Placeholder implementation (returns the input matrix unchanged)
        return matrix.getData();
    }

    @AllArgsConstructor
    @Data
    public static class Pair<T> {
        private Vertex<T>  u;
        private Vertex<T>  v;
    }

    public static class Matrix {
        private final int rows;
        private final int cols;
        private final int[][] data;

        public Matrix(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.data = new int[rows][cols];
        }

        public Matrix(int[][] data) {
            this.rows = data.length;
            this.cols = data[0].length;
            this.data = data;
        }

        public double get(int row, int col) {
            return data[row][col];
        }

        public void set(int row, int col, int value) {
            data[row][col] = value;
        }

        public int getRows() {
            return rows;
        }

        public int getCols() {
            return cols;
        }

        public int[][] getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        // Tạo đồ thị lưỡng phân
        Graph<String, Integer> graph = new Graph<>(false); // false cho đồ thị vô hướng

        // Thêm các đỉnh
        Vertex<String> a = graph.addVertex("A");
        Vertex<String> b = graph.addVertex("B");
        Vertex<String> c = graph.addVertex("C");
        Vertex<String> d = graph.addVertex("D");

        // Thêm các cạnh với trọng số
        graph.addEdge("A", "B", 0);
        graph.addEdge("A", "C", 15);
        graph.addEdge("B", "D", 20);
        graph.addEdge("C", "D", 0);

        // In đồ thị ra màn hình
        System.out.println("Graph:");
        System.out.println(graph);

        // Tìm ghép nối tối ưu bằng thuật toán Hungarian
        List<Hungarian.Pair<String>> optimalPairs = Hungarian.algorithm(graph);

        // In kết quả ghép nối tối ưu ra màn hình
        System.out.println("Optimal Matching:");
        for (Hungarian.Pair<String> pair : optimalPairs) {
            System.out.println(pair.getU() + " - " + pair.getV());
        }
    }
}
