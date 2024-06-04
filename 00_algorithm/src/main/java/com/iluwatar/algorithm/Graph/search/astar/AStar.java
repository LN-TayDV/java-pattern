package com.iluwatar.algorithm.Graph.search.astar;

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
