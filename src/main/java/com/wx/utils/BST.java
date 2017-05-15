package com.wx.utils;

import java.util.Iterator;
import java.util.Stack;

public class BST<Key extends Comparable<Key>> {

    private class Node {
        private int count;
        private Key key;
        private Node left;
        private Node right;

        Node(Key key) {
            this.key = key;
            count = 1;
            left = null;
            right = null;
        }
    }

    private class BSTIterator implements Iterator<Node> {
        Stack<Node> nodeStack = new Stack<>();

        public BSTIterator(Node root) {
            while (root != null) {
                nodeStack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        @Override
        public Node next() {
            Node next = null;
            if (!nodeStack.isEmpty()) {
                next = nodeStack.pop();
                Node toPush = next.right;
                while(toPush != null) {
                    nodeStack.push(toPush);
                    toPush = toPush.left;
                }
            }

            return next;
        }
    }

    private Node root = null;

    public int size() {
        return size(root);
    }

    public void put(Key key) {
        root = put(root, key);
    }

    /**
     * @param key
     * @return Returns number of keys less than the input key in this BST
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int size(Node node) {
        return node == null ? 0 : node.count;
    }

    private Node put(Node node, Key key) {
        if (node == null) {
            return new Node(key);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key);
        } else {
            node.right = put(node.right, key);
        }

        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return size(node.left);
        } else if (cmp < 0) {
            return rank(node.left, key);
        } else {
            return 1 + size(node.left) + rank(node.right, key);
        }
    }
}
