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
/******************************************************************************
 *  Compilation:  javac AcyclicSP.java
 *  Execution:    java AcyclicSP V E
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Topological.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWDAG.txt
 *
 *  Computes shortest paths in an edge-weighted acyclic digraph.
 *
 *  % java AcyclicSP tinyEWDAG.txt 5
 *  5 to 0 (0.73)  5->4  0.35   4->0  0.38
 *  5 to 1 (0.32)  5->1  0.32
 *  5 to 2 (0.62)  5->7  0.28   7->2  0.34
 *  5 to 3 (0.61)  5->1  0.32   1->3  0.29
 *  5 to 4 (0.35)  5->4  0.35
 *  5 to 5 (0.00)
 *  5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52
 *  5 to 7 (0.28)  5->7  0.28
 *
 ******************************************************************************/

package com.iluwatar.algorithm.theories.on.book.robert.kevin.algorithms.Graphs;

import com.iluwatar.algorithm.theories.on.book.robert.kevin.algorithms.Fundamentals.Stack;
import com.iluwatar.algorithm.theories.on.book.robert.kevin.system.In;
import com.iluwatar.algorithm.theories.on.book.robert.kevin.system.StdOut;

/**
 * The {@code AcyclicSP} class represents a data type for solving the
 * single-source shortest paths problem in edge-weighted directed acyclic
 * graphs (DAGs). The edge weights can be positive, negative, or zero.
 * <p>
 * This implementation uses a topological-sort based algorithm.
 * The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in the
 * worst case, where <em>V</em> is the number of vertices and
 * <em>E</em> is the number of edges.
 * Each instance method takes &Theta;(1) time.
 * It uses &Theta;(<em>V</em>) extra space (not including the
 * edge-weighted digraph).
 * <p>
 * This correctly computes shortest paths if all arithmetic performed is
 * without floating-point rounding error or arithmetic overflow.
 * This is the case if all edge weights are integers and if none of the
 * intermediate results exceeds 2<sup>52</sup>. Since all intermediate
 * results are sums of edge weights, they are bounded by <em>V C</em>,
 * where <em>V</em> is the number of vertices and <em>C</em> is the maximum
 * absolute value of any edge weight.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class AcyclicSP {
    private final double[] distTo;         // distTo[v] = distance  of shortest s->v path
    private final DirectedEdge[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path


    /**
     * Computes a shortest paths tree from {@code s} to every other vertex in
     * the directed acyclic graph {@code G}.
     *
     * @param G the acyclic digraph
     * @param s the source vertex
     * @throws IllegalArgumentException if the digraph is not acyclic
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // visit vertices in topological order
        Topological topological = new Topological(G);
        if (!topological.hasOrder()) {
            throw new IllegalArgumentException("Digraph is not acyclic.");
        }
        for (int v : topological.order()) {
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    /**
     * Unit tests the {@code AcyclicSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        AcyclicSP sp = new AcyclicSP(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }

    // relax edge e
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * Returns the length of a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     * {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Is there a path from the source vertex {@code s} to vertex {@code v}?
     *
     * @param v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     * {@code s} to vertex {@code v}, and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path from the source vertex {@code s} to vertex {@code v}.
     *
     * @param v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     * as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2022, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/