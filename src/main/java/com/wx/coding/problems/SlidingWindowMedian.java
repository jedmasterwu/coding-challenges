package com.wx.coding.problems;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SlidingWindowMedian {
    private TreeMap<Integer, Integer> largerHalf = new TreeMap<>();
    private TreeMap<Integer, Integer> smallerHalf = new TreeMap<>(Collections.reverseOrder());
    private int largerHalfSize;
    private int smallerHalfSize;

    public int[] dataStreamMedian(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        PriorityQueue<Integer> biggerHalf = new PriorityQueue<>();
        PriorityQueue<Integer> lesserHalf = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (biggerHalf.isEmpty() || num > biggerHalf.peek()) {
                biggerHalf.add(num);
            } else {
                lesserHalf.add(num);
            }

            if (biggerHalf.size() > lesserHalf.size() + 1) {
                lesserHalf.add(biggerHalf.poll());
            }

            if (biggerHalf.size() < lesserHalf.size() - 1) {
                biggerHalf.add(lesserHalf.poll());
            }

            int lesserSize = lesserHalf.size();
            int biggerSize = biggerHalf.size();
            if (lesserSize == biggerSize || lesserSize == biggerSize + 1) {
                result[i] = lesserHalf.peek();
            } else {
                result[i] = biggerHalf.peek();
            }
        }

        return result;
    }

    public double[] medianSlidingWindowLeetCode(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        clearMaps();
        int n = nums.length;
        largerHalfSize = 0;
        smallerHalfSize = 0;
        for (int i = 0; i < n; i++) {
            // Insert current number
            int num = nums[i];
            if (largerHalfSize == 0 || num > largerHalf.firstKey()) {
                largerHalfSize = put(largerHalf, num, largerHalfSize);
            } else {
                smallerHalfSize = put(smallerHalf, num, smallerHalfSize);
            }

            // Remove element out of window
            if (i >= k) {
                removeOutOfWindowNumber(nums[i - k]);
            }

            // Balance ordered maps
            balance();

            // Get Median
            if (i >= k - 1) {
                result[i - k + 1] = findMedianLeetCode();
            }
        }

        return result;
    }

    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        clearMaps();

        int n = nums.length;
        largerHalfSize = 0;
        smallerHalfSize = 0;
        for (int i = 0; i < n; i++) {
            // Insert current number
            int num = nums[i];
            if (largerHalfSize == 0 || num > largerHalf.firstKey()) {
                largerHalfSize = put(largerHalf, num, largerHalfSize);
            } else {
                smallerHalfSize = put(smallerHalf, num, smallerHalfSize);
            }

            // Remove element out of window
            if (i >= k) {
                removeOutOfWindowNumber(nums[i - k]);
            }

            // Balance ordered maps
            balance();

            // Get Median
            if (i >= k - 1) {
                result.add(findMedian());
            }
        }

        return result;
    }

    private void balance() {
        if (smallerHalfSize > largerHalfSize + 1) {
            Integer key = smallerHalf.firstKey();
            smallerHalfSize = remove(smallerHalf, key, smallerHalfSize);
            largerHalfSize = put(largerHalf, key, largerHalfSize);
        }

        if (smallerHalfSize < largerHalfSize - 1){
            Integer key = largerHalf.firstKey();
            largerHalfSize = remove(largerHalf, key, largerHalfSize);
            smallerHalfSize = put(smallerHalf, key, smallerHalfSize);
        }
    }

    private void removeOutOfWindowNumber(int toRemove) {
        if (smallerHalf.containsKey(toRemove)) {
            smallerHalfSize = remove(smallerHalf, toRemove, smallerHalfSize);
        } else {
            largerHalfSize = remove(largerHalf, toRemove, largerHalfSize);
        }
    }

    private int put(TreeMap<Integer, Integer> map, Integer key, int size) {
        map.put(key, map.getOrDefault(key, 0) + 1);
        return ++size;
    }

    private int remove(TreeMap<Integer, Integer> map, Integer key, int size) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
        return --size;
    }

    private void clearMaps() {
        largerHalf.clear();
        smallerHalf.clear();
    }

    private Integer findMedian() {
        if (smallerHalfSize > 0 && (smallerHalfSize == largerHalfSize || smallerHalfSize == largerHalfSize + 1)) {
            return smallerHalf.firstKey();
        } else {
            return largerHalf.firstKey();
        }
    }

    private double findMedianLeetCode() {
        if (smallerHalfSize == largerHalfSize) {
            return (1.0 * smallerHalf.firstKey() + largerHalf.firstKey()) / 2.0;
        } else if (smallerHalfSize == largerHalfSize + 1) {
            return (double) smallerHalf.firstKey();
        }

        return (double) largerHalf.firstKey();
    }
}
