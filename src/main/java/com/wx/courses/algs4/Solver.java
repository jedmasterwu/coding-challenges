package com.wx.courses.algs4;

import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;

public class Solver {

    private LinkedList<Board> solution = null;
    private boolean isSolvable = false;
    private int moves = -1;

    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException("Initial board can't be null");
        }

        MinPQ<SearchNode> minPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();
        Board twin = initial.twin();

        minPQ.insert(new SearchNode(initial, null, 0));
        twinPQ.insert(new SearchNode(twin, null, 0));

        while (true) {
            SearchNode current = minPQ.delMin();
            if (current.board.isGoal()) {
                solution = new LinkedList<>();
                moves = current.moves;
                isSolvable = true;
                do {
                    solution.addFirst(current.board);
                    current = current.previous;
                } while (current != null);
                return;
            } else {
                for (Board board : current.board.neighbors()) {
                    if (current.previous == null || !board.equals(current.previous.board)) {
                        minPQ.insert(new SearchNode(board, current, current.moves + 1));
                    }
                }
            }

            SearchNode twinCurrent = twinPQ.delMin();
            if (twinCurrent.board.isGoal()) {
                return;
            } else {
                for (Board board : twinCurrent.board.neighbors()) {
                    if (twinCurrent.previous == null || !board.equals(twinCurrent.previous.board)) {
                        twinPQ.insert(new SearchNode(board, twinCurrent, twinCurrent.moves + 1));
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private static class SearchNode implements Comparable<SearchNode> {

        private final Board board;
        private final SearchNode previous;
        private final int moves;

        public SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
        }

        @Override
        public int compareTo(SearchNode o) {
            int manhattanDiff = board.manhattan() + moves - o.board.manhattan() - o.moves;
            if (manhattanDiff == 0) {
                return board.hamming() - o.board.hamming();
            }
            return manhattanDiff;
        }
    }
}
