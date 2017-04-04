package com.wx.utils;

import java.util.Random;

public class RandomHelper {

    private static final Random random = new Random(System.currentTimeMillis());

    private RandomHelper() {
        // No outside instantiation
    }

    public static void shuffle(Object[] array) {
        int n = array.length;
        for (int i = 0; i < n; ++i) {
            // Exhange item i with an item in the range between i and n - 1
            SortHelper.exchange(array, i, i + uniform(n - i));
        }
    }

    public static int uniform(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument must be positive");
        }
        return random.nextInt(n);
    }

    public static int uniform(int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
        }

        return a + uniform(b - a);
    }
}
