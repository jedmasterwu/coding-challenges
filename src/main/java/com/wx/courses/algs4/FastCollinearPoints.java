package com.wx.courses.algs4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segmentsList;

    public FastCollinearPoints(Point[] points) {
        Point[] localPoints = checkNotNull(points);

        // Check for dupes
        Arrays.sort(localPoints);
        int n = localPoints.length;
        for (int i = 0; i < n - 1; i++) {
            if (localPoints[i].compareTo(localPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Points contain duplicates");
            }
        }

        segmentsList = new LinkedList<>();
        if (n >= 4) {
            for (int current = 0; current < n; current++) {
                Arrays.sort(localPoints);
                Point p = localPoints[current];
                Arrays.sort(localPoints, p.slopeOrder());

                // The current point should always be first
                assert localPoints[0].compareTo(p) == 0;

                int i = 1;
                while (i < n) {
                    Point q = localPoints[i];
                    double slope = p.slopeTo(q);
                    Point min = min(p, q);
                    int count = 2;
                    while (++i < n) {
                        q = localPoints[i];
                        if (slope != p.slopeTo(q)) {
                            break;
                        }
                        min = min(min, q);
                        count++;
                    }

                    if (count >= 4 && p.compareTo(min) == 0) {
                        segmentsList.add(new LineSegment(p, localPoints[i - 1]));
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        return segmentsList.toArray(segments);
    }

    private Point[] checkNotNull(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Input is null");
        }
        Point[] localPoints = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            if (point == null) {
                throw new NullPointerException();
            }
            localPoints[i] = point;
        }

        return localPoints;
    }

    private Point min(Point a, Point b) {
        return a.compareTo(b) < 0 ? a : b;
    }
}
