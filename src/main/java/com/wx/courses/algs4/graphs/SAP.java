package com.wx.courses.algs4.graphs;

import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private Digraph mDigraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException("Graph can't be null");
        }

        mDigraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        return 0;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        checkVertex(v);
        checkVertex(w);
        return 0;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> a, Iterable<Integer> b) {
        int shortest = Integer.MAX_VALUE;
        for (Integer v : a) {
            for (Integer w : a) {
                int current = length(v, w);
                if (current != -1 && current < shortest) {
                    shortest = current;
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> a, Iterable<Integer> b) {
        int shortest = Integer.MAX_VALUE;
        int ancestor = -1;
        for (Integer v : a) {
            for (Integer w : b) {
                int current = length(v, w);
                if (current != -1 && current < shortest) {
                    ancestor = ancestor(v, w);
                }
            }
        }

        return ancestor;
    }

    private void checkVertex(int v) {
        if (v < 0 || v >= mDigraph.V()) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
