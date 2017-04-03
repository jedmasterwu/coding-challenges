package com.wx.utils;

import java.util.Comparator;

public class MergeSort {

    public static void sort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        sort(array, aux, 0, array.length - 1);
    }

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        T[] aux = array.clone();
        sort(array, aux, 0, array.length - 1, comparator);

    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int firstHalf = lo;
        int secondHalf = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (firstHalf > mid) {
                a[i] = aux[secondHalf++];
            } else if (secondHalf > hi) {
                a[i] = aux[firstHalf++];
            } else if (less(aux[secondHalf], aux[firstHalf])) {
                a[i] = aux[secondHalf++];
            } else {
                a[i] = aux[firstHalf++];
            }
        }
    }

    private static <T> void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<? super T> comparator) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int firstHalf = lo;
        int secondHalf = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (firstHalf > mid) {
                a[i] = aux[secondHalf++];
            } else if (secondHalf > hi) {
                a[i] = aux[firstHalf++];
            } else if (less(comparator, aux[secondHalf], aux[firstHalf])) {
                a[i] = aux[secondHalf++];
            } else {
                a[i] = aux[firstHalf++];
            }
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);

            // If it's already sorted do nothing.
            if (less(a[mid + 1], a[mid])) {
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    private static <T> void sort(T[] a, T[] aux, int lo, int hi, Comparator<? super T> comparator) {
        if (lo < hi) {
            int mid = (lo + hi) >> 1;
            sort(a, aux, lo, mid, comparator);
            sort(a, aux, mid + 1, hi, comparator);

            if (less(comparator, a[mid + 1], a[mid])) {
                merge(a, aux, lo, mid, hi, comparator);
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static <T> boolean less(Comparator<? super T> comparator, T a, T b) {
        return comparator.compare(a, b) < 0;
    }
}
