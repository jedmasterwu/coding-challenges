package com.wx.coding.tests;

import com.wx.courses.algs4.FastCollinearPoints;
import com.wx.courses.algs4.LineSegment;
import com.wx.courses.algs4.Point;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CollinearTest {

    @Test
    public void testPoint() {
        Point origin = new Point(0, 0);
        Point origin2 = new Point(0, 0);
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        Point c = new Point(-3, -3);
        Point d = new Point(4, 0);
        Point e = new Point(-4, 0);
        Point f = new Point(0, 1);

        Point[] points = {origin, origin2, a, b, c, d, e, f};

        Assert.assertEquals(origin.compareTo(origin2), 0);
        Assert.assertEquals(origin.compareTo(e), 1);
        Assert.assertEquals(e.compareTo(origin), -1);
        Assert.assertEquals(origin.compareTo(d), -1);
        Assert.assertEquals(d.compareTo(origin), 1);

        Assert.assertEquals(origin.slopeTo(a), origin.slopeTo(b));
        Assert.assertEquals(origin.slopeTo(a), origin.slopeTo(c));
        Assert.assertEquals(origin.slopeTo(f), Double.POSITIVE_INFINITY);
        Assert.assertEquals(origin.slopeTo(origin2), Double.NEGATIVE_INFINITY);

        Arrays.sort(points, origin.slopeOrder());
        for(Point p : points) {
            StdOut.println(p.toString());
        }
    }

    @Test
    public void testSlopeOrder() {
        In in = new In("/Users/wuming/Downloads/collinear/crossinglines.txt");

        int k = in.readInt();
        Point[] points = new Point[k];
        Point[] copy = new Point[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }

        Arrays.sort(points);
        for (int i = 0; i < k; i++) {
            copy[i] = points[i];
        }

        for (Point p : points) {
            StdOut.println("=================================== " + p + " ===================================" );
            Arrays.sort(copy);
            Arrays.sort(copy, p.slopeOrder());
            for (Point c : copy) {
                StdOut.print(c + "    ");
            }
            StdOut.println();
        }
    }

    @Test
    public void testDrawPoints() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("/Users/wuming/Downloads/collinear/rs1423.txt"));
        int k = scanner.nextInt();
        Point[] points = new Point[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-1000, 32768);
        StdDraw.setYscale(-1000, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

        while(!StdIn.readString().equalsIgnoreCase("exit")){}
    }
}
