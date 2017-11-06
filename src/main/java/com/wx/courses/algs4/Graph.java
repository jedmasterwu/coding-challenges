package com.wx.courses.algs4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private final int vertices;
    private List<List<Integer>> adjList;
    private int edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new LinkedList<>());
        }
        edges = 0;
    }

    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
        adjList.get(w).add(v);
        edges++;
    }

    public int vertices() {
        return this.vertices;
    }

    public int edges() {
        return edges;
    }
}
