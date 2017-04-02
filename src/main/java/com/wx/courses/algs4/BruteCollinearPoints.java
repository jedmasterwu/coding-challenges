package com.wx.courses.algs4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] localPoints;
    private List<LineSegment> segmentList;
    private int numSegments = 0;


    public BruteCollinearPoints(Point[] points) {
        checkNotNull(points);

        // Check for dupes
        Arrays.sort(localPoints);
        int n = localPoints.length;
        for (int i = 0; i < n - 1; i++) {
            if (!less(localPoints[i], localPoints[i + 1])) {
                throw new IllegalArgumentException("Points contain duplicates");
            }
        }

        segmentList = new LinkedList<>();
        if (n >= 4) {
            for (int a = 0; a < n - 3; a++) {
                Point p = localPoints[a];
                for (int b = a + 1; b < n - 2; b++) {
                    Point q = localPoints[b];
                    double pq = p.slopeTo(q);
                    for (int c = b + 1; c < n - 1; c++) {
                        Point r = localPoints[c];
                        double pr = p.slopeTo(r);

                        if (pq != pr) {
                            continue;
                        }

                        for (int d = c + 1; d < n; d++) {
                            Point s = localPoints[d];
                            double ps = p.slopeTo(s);


                            if (pr == ps) {
                                numSegments++;
                                segmentList.add(new LineSegment(p, s));
                                break; // No need to check the rest of the list since we are guaranteed to only have
                                // a max of 4 collinear localPoints
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return numSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[segmentList.size()];
        segmentList.toArray(segments);
        return segments;
    }

    private boolean less(Point a, Point b) {
        return a.compareTo(b) < 0;
    }

    private void checkNotNull(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Input is null");
        }
        localPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) {
                throw new NullPointerException();
            }
            localPoints[i] = p;
        }
    }
}
