package com.wx.utils;

import java.util.Comparator;

public class SortHelper {

    static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    static <T> boolean less(Comparator<? super T> comparator, T a, T b) {
        return comparator.compare(a, b) < 0;
    }

    static <T> void exchange(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
