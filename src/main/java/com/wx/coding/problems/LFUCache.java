package com.wx.coding.problems;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    private int capacity;
    private Node head;
    private Map<Integer, Integer> valueMap;
    private Map<Integer, Node> nodeMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = null;
        valueMap = new HashMap<>();
        nodeMap = new HashMap<>();
    }

    public int get(int key) {
        Integer val = valueMap.getOrDefault(key, -1);
        if (val != -1) {
            increaseCount(key);
        }
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (!valueMap.containsKey(key)) {
            if (valueMap.size() == capacity) {
                removeLFU();
            }
            addToHead(key);
        } else {
            increaseCount(key);
        }
        valueMap.put(key, value);
    }

    private void addToHead(int key) {
        if (head == null || head.freq != 1) {
            Node one = new Node(1);
            one.next = head;
            if (head != null) {
                head.prev = one;
            }
            head = one;
        }
        head.keys.add(key);
        nodeMap.put(key, head);
    }

    private void removeLFU() {
        assert head != null;
        Integer toRemove = head.keys.iterator().next();
        valueMap.remove(toRemove);
        nodeMap.remove(toRemove);
        head.keys.remove(toRemove);
        if (head.keys.isEmpty()) {
            remove(head);
        }
    }

    private void increaseCount(int key) {
        Node node = nodeMap.get(key);
        node.keys.remove(key);
        if (node.next == null || node.next.freq != node.freq + 1) {
            Node nextFreq = new Node(node.freq + 1);
            nextFreq.next = node.next;
            if (node.next != null) {
                node.next.prev = nextFreq;
            }
            nextFreq.prev = node;
            node.next = nextFreq;
        }
        node.next.keys.add(key);
        nodeMap.put(key, node.next);

        if (node.keys.isEmpty()) {
            remove(node);
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        if (prev == null) {
            head = node.next;
        } else {
            prev.next = node.next;
        }

        Node next = node.next;
        if (next != null) {
            next.prev = prev;
        }
    }

    private static class Node {
        int freq;
        LinkedHashSet<Integer> keys;
        Node next;
        Node prev;

        Node(int freq) {
            this.freq = freq;
            keys = new LinkedHashSet<>();
            next = null;
            prev = null;
        }
    }
}
