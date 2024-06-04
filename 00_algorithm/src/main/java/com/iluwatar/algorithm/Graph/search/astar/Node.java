package com.iluwatar.algorithm.Graph.search.astar;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    public final String value;
    public double gScores;
    public final double hScores;
    public double fScores = 0;
    public ArrayList<Edge> adjacencies = new ArrayList<>();
    public Node parent;

    public Node(String val, double hVal) {
        value = val;
        hScores = hVal;
    }

    public String toString() {
        return value;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.fScores, o.fScores);
    }
}
