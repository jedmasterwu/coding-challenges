package com.wx.coding.tests;

import com.wx.courses.algs4.KdTree;
import com.wx.courses.algs4.PointSET;
import edu.princeton.cs.algs4.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.TreeSet;

public class KdTreeTest {

    @Test
    public void testKdTree() {
        In in = new In("src/test/resources/kdtree/input10K.txt");
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();

        AssertJUnit.assertEquals(brute.isEmpty(), kdtree.isEmpty());

        int i = 0;
        Point2D[] dupes = new Point2D[200];
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);

            if (i++ % 50 == 0) {
                dupes[(i - 1) / 50] = p;
            }

            if (!brute.contains(p)) {
                StdOut.println("Brute not working");
            }
            if (!kdtree.contains(p)) {
                StdOut.println("KdTree not working");
            }
        }
        Point2D boundary = new Point2D(1.0, 0.0);
        brute.insert(boundary);
        kdtree.insert(boundary);
        for (Point2D dupe : dupes) {
            brute.insert(dupe);
            kdtree.insert(dupe);
        }

        if (kdtree.size() != brute.size()) {
            StdOut.println("Sizes are different");
        }

        RectHV rect = new RectHV(1.0, 0.0, 1.0, 0.0);
        TreeSet<Point2D> set = new TreeSet<>();
        for (Point2D p : brute.range(rect)) {
            set.add(p);
        }

        int size = 0;
        for (Point2D p : kdtree.range(rect)) {
            size++;
            if (!set.contains(p)) {
                StdOut.println("Contains invalid answer");
            }
        }
        if (size != set.size()) {
            StdOut.println("Ref size: " + set.size() + " vs kdsize: " + size);
        }

        for (int j = 0; j < 100000; j++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D random = new Point2D(x, y);

            if (kdtree.contains(random) != brute.contains(random)) {
                StdOut.println("Contains failed at point: " + random);
            }

            Point2D kdresult = kdtree.nearest(random);
            double kddist = kdresult.distanceSquaredTo(random);
            Point2D bruteresult = brute.nearest(random);
            double brutedist = bruteresult.distanceSquaredTo(random);
            if (kdresult.compareTo(bruteresult) != 0) {
                System.out.println(
                        "Failed! kdtree: " + kdresult + " - " + kddist + ", brute: "
                                + bruteresult + " - " + brutedist + " ||| " + (kddist > brutedist)
                );
            }
        }

        StdDraw.setXscale(-0.05, 1.05);
        StdDraw.setYscale(-0.05, 1.05);
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
        kdtree.draw();
    }

    @Test
    public void testKdRange() {
        In in = new In("src/test/resources/kdtree/input10K.txt");

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        boolean isDragging = false;     // is the user dragging a rectangle

        // draw the points
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.show();

        while (true) {

            // user starts to drag a rectangle
            if (StdDraw.mousePressed() && !isDragging) {
                x0 = StdDraw.mouseX();
                y0 = StdDraw.mouseY();
                isDragging = true;
                continue;
            }

            // user is dragging a rectangle
            else if (StdDraw.mousePressed() && isDragging) {
                x1 = StdDraw.mouseX();
                y1 = StdDraw.mouseY();
                continue;
            }

            // mouse no longer pressed
            else if (!StdDraw.mousePressed() && isDragging) {
                isDragging = false;
            }


            RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                    Math.max(x0, x1), Math.max(y0, y1));
            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();

            // draw the rectangle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius();
            rect.draw();

            // draw the range search results for brute-force data structure in red
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            TreeSet<Point2D> bruteResult = new TreeSet<>();
            for (Point2D p : brute.range(rect)) {
                bruteResult.add(p);
                p.draw();
            }

            // draw the range search results for kd-tree in blue
            StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(StdDraw.BLUE);
            int size = 0;
            for (Point2D p : kdtree.range(rect)) {
                size++;
                if (!bruteResult.contains(p)) {
                    StdOut.println("KdTree has wrong answer");
                }
                p.draw();
            }

            if (size != bruteResult.size()) {
                StdOut.println("Missing answers");
            }

            StdDraw.show();
            StdDraw.pause(40);
        }
    }
}
