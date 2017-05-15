package com.wx.utils;

public class QuickSort {

    public static void qsort(Comparable[] array) {
        // Shuffle is needed to avoid worst case
        RandomHelper.shuffle(array);
        qsort(array, 0, array.length - 1);
    }

    public static void sort(Comparable[] array) {
        // Shuffle is needed to avoid worst case
        RandomHelper.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void qsort(Comparable[] array, int lo, int hi) {
        if (lo < hi) {
            int j = partition(array, lo, hi);
            qsort(array, lo, j - 1);
            qsort(array, j + 1, hi);
        }
    }

    private static void sort(Comparable[] array, int lo, int hi) {
        if (lo < hi) {
            int i = lo;
            int lt = lo;
            int gt = hi;
            Comparable v = array[lo];

            while (i <= gt) {
                int cmp = array[i].compareTo(v);
                if (cmp < 0) {
                    SortHelper.exchange(array, lt++, i++);
                } else if (cmp > 0) {
                    SortHelper.exchange(array, gt--, i);
                } else {
                    i++;
                }
            }

            sort(array, lo, lt - 1);
            sort(array, gt + 1, hi);
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
