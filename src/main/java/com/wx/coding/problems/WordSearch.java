package com.wx.coding.problems;

import com.wx.utils.Trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    private static final int[][] MOVES = new int[][] {
            new int[] {-1, 0},  // up
            new int[] {0, 1},   // right
            new int[] {1, 0},   // down
            new int[] {0, -1}   // left
    };
    private Trie dict;
    private int r;
    private int c;
    private byte[] visited;

    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        r = board.length;
        c = board[0].length;
        visited = new byte[(r * c / 8) + 1];
        ArrayList<String> result = new ArrayList<>();
        dict = new Trie();
        for (String word : words) {
            dict.add(word);
        }

        char[] word = new char[r * c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                search(board, i, j, result, word, 0);
            }
        }

        return result;
    }

    private void search(char[][] board, int i, int j, List<String> result, char[] buffer, int len) {
        int flattened = flatten(i, j);
        buffer[len++] = board[i][j];
        visit(flattened);
        short response = dict.contains(buffer, len);
        if (Trie.isWord(response)) {
            result.add(new String(buffer, 0, len));
        }

        if (Trie.isPrefix(response)) {
            for (int[] move : MOVES) {
                int newRow = i + move[0];
                int newCol = j + move[1];

                if (valid(newRow, newCol) && !visited(flatten(newRow, newCol))) {
                    search(board, newRow, newCol, result, buffer, len);
                }
            }
        }

        unvisit(flattened);
    }

    private int flatten(int i, int j) {
        return i * c + j;
    }

    private boolean visited(int pos) {
        byte container = visited[pos >> 3];
        return ((container >> (pos % 8)) & 1) == 1;
    }

    private void visit(int pos) {
        visited[pos >> 3] |= 1 << (pos % 8);
    }

    private void unvisit(int pos) {
        visited[pos >> 3] &= ~(1 << (pos % 8));
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }
}
