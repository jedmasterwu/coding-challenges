package com.wx.courses.algs4.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private Map<String, Set<Integer>> nymToIdMap = new HashMap<>();
    private List<Set<String>> idToNymMap = new ArrayList<>();
    private int synsetsCount = 0;
    private Digraph digraph;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In reader = new In(synsets);
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            synsetsCount++;
            parse(line);
        }

        reader = new In(hypernyms);
        digraph = new Digraph(synsetsCount);
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            updateDigraph(line);
        }

        sap = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nymToIdMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word can't be null");
        }
        return nymToIdMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            return sap.length(nymToIdMap.get(nounA), nymToIdMap.get(nounB));
        }

        return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            int ancestor = sap.ancestor(nymToIdMap.get(nounA), nymToIdMap.get(nounB));
            if (ancestor != -1) {
                return String.join(" ", idToNymMap.get(ancestor));
            }
        }
        return null;
    }

    private void parse(String line) {
        String[] parts = line.split(",");
        Integer id = Integer.parseInt(parts[0]);

        String[] nyms = parts[1].split(" ");
        Set<String> synset = new HashSet<>();
        for (String nym : nyms) {
            nymToIdMap.computeIfAbsent(nym, key -> new HashSet<>()).add(id);
            synset.add(nym);
        }
        idToNymMap.add(synset);
    }

    private void updateDigraph(String line) {
        String[] ids = line.split(",");
        Integer v = Integer.parseInt(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            Integer w = Integer.parseInt(ids[i]);
            digraph.addEdge(v, w);
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
