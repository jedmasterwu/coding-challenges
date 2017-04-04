package com.wx.coding.tests;

import com.wx.courses.algs4.Point;
import com.wx.utils.MergeSort;
import com.wx.utils.QuickSort;
import edu.princeton.cs.algs4.Stopwatch;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
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
            compare("Iteration " + i, points, copy);

            Comparator<Point> comparator = points[i].slopeOrder();
            MergeSort.sort(points, comparator);
            Arrays.sort(copy, comparator);
            compare("Iteration " + i, points, copy);
        }
    }

    @Test
    public void testQuickSort() throws FileNotFoundException {
        File[] testFiles = new File("src/test/resources/collinear/").listFiles(
                (dir, name) -> name.endsWith(".txt")
        );

        Stopwatch stopwatch2 = new Stopwatch();
        for (File testFile : testFiles) {
            Scanner scanner = new Scanner(new FileInputStream(testFile.getAbsolutePath()));
            int k = scanner.nextInt();
            Point[] points = new Point[k];
            Point[] copy = new Point[k];
            for (int i = 0; i < k; i++) {
                points[i] = new Point(scanner.nextInt(), scanner.nextInt());
                copy[i] = points[i];
            }

            System.out.print("Testing file " + testFile.getName() + " ... ");
            QuickSort.qsort(points);
            Arrays.sort(copy);
            compare(testFile.getName(), points, copy);
            System.out.println("passed!");
        }
        System.out.println("Regular quicksort took: " + stopwatch2.elapsedTime() + " seconds!");

        System.out.println("========================================================================");

        Stopwatch stopwatch = new Stopwatch();
        for (File testFile : testFiles) {
            Scanner scanner = new Scanner(new FileInputStream(testFile.getAbsolutePath()));
            int k = scanner.nextInt();
            Point[] points = new Point[k];
            Point[] copy = new Point[k];
            for (int i = 0; i < k; i++) {
                points[i] = new Point(scanner.nextInt(), scanner.nextInt());
                copy[i] = points[i];
            }

            System.out.print("Testing file " + testFile.getName() + " ... ");
            QuickSort.sort(points);
            Arrays.sort(copy);
            compare(testFile.getName(), points, copy);
            System.out.println("passed!");
        }
        System.out.println("3way quicksort took: " + stopwatch.elapsedTime() + " seconds!");
    }

    private <T> void compare(String message, T[] a, T[] b) {
        for (int i = 0; i < a.length; i++) {
            AssertJUnit.assertTrue(
                    message + ", mismatch at index " + i + ": " + a[i] + ", " + b[i],
                    ((Comparable) a[i]).compareTo(b[i]) == 0
            );
        }
    }
}
