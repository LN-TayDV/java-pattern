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
package com.iluwatar.algorithm.theories.on.internet.search.astar;

import java.util.*;

public class AStar {

    public static void main(String[] args) {
        // Tạo các đỉnh
        Node n1 = new Node("A", 1);
        Node n2 = new Node("B", 1);
        Node n3 = new Node("C", 1);
        Node n4 = new Node("D", 1);
        Node n5 = new Node("E", 1);

        // Tạo các cạnh
        n1.adjacencies.add(new Edge(n2, 1));
        n1.adjacencies.add(new Edge(n3, 3));
        n2.adjacencies.add(new Edge(n4, 1));
        n3.adjacencies.add(new Edge(n4, 1));
        n4.adjacencies.add(new Edge(n5, 1));

        // Chạy thuật toán A*
        aStarSearch(n1, n5);
    }

    public static void aStarSearch(Node source, Node goal) {
        Set<Node> explored = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        source.gScores = 0;
        source.fScores = source.hScores;
        queue.add(source);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value.equals(goal.value)) {
                System.out.println("Đường đi ngắn nhất: ");
                printPath(current);
                return;
            }

            explored.add(current);

            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double temp_g_scores = current.gScores + e.cost;
                double temp_f_scores = temp_g_scores + child.hScores;

                if ((explored.contains(child)) && (temp_f_scores >= child.fScores)) {
                    continue;
                } else if ((!queue.contains(child)) || (temp_f_scores < child.fScores)) {
                    child.parent = current;
                    child.gScores = temp_g_scores;
                    child.fScores = temp_f_scores;

                    if (queue.contains(child)) {
                        queue.remove(child);
                    }
                    queue.add(child);
                }
            }
        }
    }

    public static void printPath(Node target) {
        Node n = target;

        if (n != null) {
            printPath(n.parent);
            System.out.print(n.value + " ");
        }
    }
}
