package com.wx.coding.tests;

import com.wx.courses.algs4.Point;
import com.wx.utils.MergeSort;
import com.wx.utils.QuickSort;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortTest {

    @Test
    public void testMergeSort() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/test/resources/collinear/input10000.txt"));
        int k = scanner.nextInt();
        Point[] points = new Point[k];
        Point[] copy = new Point[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
            copy[i] = points[i];
        }

        for (int i = 0; i < k; i++) {
            MergeSort.sort(points);
            Arrays.sort(copy);
            compare(i, points, copy);

            Comparator<Point> comparator = points[i].slopeOrder();
            MergeSort.sort(points, comparator);
            Arrays.sort(copy, comparator);
            compare(i, points, copy);
        }
    }

    @Test
    public void testQuickSort() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/test/resources/collinear/input10000.txt"));
        int k = scanner.nextInt();

        Point[] points = new Point[k];
        Point[] copy = new Point[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
            copy[i] = points[i];
        }

        //for (int i = 0; i < k; i++) {
            QuickSort.sort(points);
            Arrays.sort(copy);
            compare(0, points, copy);

//            Comparator<Point> comparator = points[i].slopeOrder();
//            MergeSort.sort(points, comparator);
//            Arrays.sort(copy, comparator);
//            compare(i, points, copy);
       // }
    }

    private <T> void compare(int iteration, T[] a, T[] b) {
        for (int i = 0; i < a.length; i++) {
            AssertJUnit.assertTrue(
                    "Iteration " + i + ", mismatch at index " + i + ": " + a[i] + ", " + b[i],
                    ((Comparable) a[i]).compareTo(b[i]) == 0
            );
        }
    }
}
