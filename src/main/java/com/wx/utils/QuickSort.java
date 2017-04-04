package com.wx.utils;

import java.util.Comparator;

public class QuickSort {

    public static void sort(Comparable[] array) {
        RandomHelper.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
    }

    private static void sort(Comparable[] array, int lo, int hi) {
        if (lo < hi) {
            int j = partition(array, lo, hi);
            sort(array, lo, j - 1);
            sort(array, j + 1, hi);
        }
    }

    private static int partition(Comparable[] array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (i < j) {
            // Find an element on the left that's bigger than the partitioning item
            while (SortHelper.less(array[++i], array[lo])) {
                if (i == hi) {
                    break;
                }
            }
            // Find an element on the right that's less than the partitioning item
            while (SortHelper.less(array[lo], array[--j])) {
                if (j == lo) {
                    break;
                }
            }

            // If pointers cross then all items are in place except the partitioning item and item j
            if (i >= j) {
                break;
            }
            SortHelper.exchange(array, i, j);
        }

        // Move the final piece in place
        SortHelper.exchange(array, lo, j);
        return j;
    }
}
