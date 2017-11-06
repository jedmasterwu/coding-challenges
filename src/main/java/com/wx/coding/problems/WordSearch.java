package com.wx.coding.problems;

import com.wx.utils.Trie;
import com.wx.utils.Trie.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WordSearch {
    private static final int[][] MOVES = new int[][] {
            new int[] {-1, 0},  // up
            new int[] {0, 1},   // right
            new int[] {1, 0},   // down
            new int[] {0, -1}   // left
    };
    private int r;
    private int c;

    public Set<String> wordSearchII(char[][] board, ArrayList<String> words) {
        r = board.length;
        c = board[0].length;
        Set<String> result = new HashSet<>();
        Trie dict = new Trie();
        words.forEach(dict::add);

        TrieNode root = dict.getRoot();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                search(board, i, j, root, result);
            }
        }

        return result;
    }

    private void search(char[][] board, int i, int j, TrieNode node, Set<String> result) {
        // Visit current position so we don't look at it again in our current search
        char current = board[i][j];
        board[i][j] = 0;
        TrieNode next = node.getChild(current);

        if (next != null) {
            String word = next.getWord();
            if (word != null) {
                result.add(word);
            }

            for (int[] move : MOVES) {
                int newRow = i + move[0];
                int newCol = j + move[1];

                if (valid(newRow, newCol) && board[newRow][newCol] != 0) {
                    search(board, newRow, newCol, next, result);
                }
            }
        }
        board[i][j] = current;
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c;
    }
}
