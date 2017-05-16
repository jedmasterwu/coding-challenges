package com.wx.utils;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static final short NOT_FOUND = 0x0;
    public static final short IS_WORD = 0x1;
    public static final short IS_PREFIX = 0x2;

    public static class TrieNode {
        private String word;
        private Map<Character, TrieNode> children;

        TrieNode() {
            this.word = null;
            children = new HashMap<>();
        }

        public TrieNode getChild(Character c) {
            return children.get(c);
        }

        public String getWord() {
            return this.word;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String str) {
        TrieNode node = root;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char letter = str.charAt(i);
            TrieNode next = node.children.get(letter);
            if (next == null) {
                next = new TrieNode();
                node.children.put(letter, next);
            }
            node = next;
        }
        node.word = str;
    }

    public short contains(char[] buffer, int len) {
        TrieNode node = root;
        for (int i = 0; i < len; i++) {
            char letter = buffer[i];
            TrieNode next = node.children.get(letter);
            if (next == null) {
                return NOT_FOUND;
            }
            node = next;
        }

        short result = 0;
        if (node.word != null) {
            result |= IS_WORD;
        }

        if (node.children.size() > 0) {
            result |= IS_PREFIX;
        }
        return result;
    }

    public short contains(String prefix) {
        TrieNode node = root;
        int length = prefix.length();
        for (int i = 0; i < length; i++) {
            char letter = prefix.charAt(i);
            TrieNode next = node.children.get(letter);
            if (next == null) {
                return NOT_FOUND;
            }
            node = next;
        }

        short result = 0;
        if (node.word != null) {
            result |= IS_WORD;
        }

        if (node.children.size() > 0) {
            result |= IS_PREFIX;
        }
        return result;
    }

    public TrieNode getRoot() {
        return this.root;
    }
}
