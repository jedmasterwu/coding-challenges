package com.wx.coding.problems;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheV2 {
    private int capacity;
    private int minFreq;
    private Map<Integer, Integer> valueMap;
    private Map<Integer, Integer> freqMap;
    private Map<Integer, LinkedHashSet<Integer>> cache;

    public LFUCacheV2(int capacity) {
        this.capacity = capacity;
        valueMap = new HashMap<>();
        freqMap = new HashMap<>();
        cache = new HashMap<>();
        cache.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        Integer val = valueMap.getOrDefault(key, -1);
        if (val != -1) {
            increaseFreq(key);
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
            freqMap.put(key, 1);
            cache.get(1).add(key);
            minFreq = 1;
        } else {
            increaseFreq(key);
        }

        valueMap.put(key, value);
    }

    private void increaseFreq(int key) {
        int freq = freqMap.get(key);
        LinkedHashSet<Integer> hashList = cache.get(freq);
        hashList.remove(key);

        if (hashList.isEmpty() && freq == minFreq) {
            minFreq++;
        }

        LinkedHashSet<Integer> toAdd;
        if (!cache.containsKey(++freq)) {
            toAdd = new LinkedHashSet<>();
            cache.put(freq, toAdd);
        } else {
            toAdd = cache.get(freq);
        }
        toAdd.add(key);
        freqMap.put(key, freq);
    }

    private void removeLFU() {
        LinkedHashSet<Integer> hashList = cache.get(minFreq);
        Integer key = hashList.iterator().next();
        hashList.remove(key);
        freqMap.remove(key);
        valueMap.remove(key);
    }
}
