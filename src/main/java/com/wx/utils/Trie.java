package com.wx.utils;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private static final char ROOT = (char) -1;
    public static final short NOT_FOUND = 0x0;
    public static final short IS_WORD = 0x1;
    public static final short IS_PREFIX = 0x2;
    private static class TrieNode {
        char c;
        Map<Character, TrieNode> children;
        boolean isLeaf;

        TrieNode(char c) {
            this.c = c;
            children = new HashMap<>();
            this.isLeaf = false;
        }
    }

    public static boolean isWord(short code) {
        return (code & IS_WORD) == IS_WORD;
    }

    public static boolean isPrefix(short code) {
        return (code & IS_PREFIX) == IS_PREFIX;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode(ROOT);
    }

    public void add(String str) {
        TrieNode node = root;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char letter = str.charAt(i);
            TrieNode next = node.children.get(letter);
            if (next == null) {
                next = new TrieNode(letter);
                node.children.put(letter, next);
            }
            node = next;
        }
        node.isLeaf = true;
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
        if (node.isLeaf) {
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
        if (node.isLeaf) {
            result |= IS_WORD;
        }

        if (node.children.size() > 0) {
            result |= IS_PREFIX;
        }
        return result;
    }
}
