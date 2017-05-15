package com.wx.courses.algs4;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> mPoints;

    public PointSET() {
        mPoints = new TreeSet<>();
    }

    public boolean isEmpty() {
        return mPoints.isEmpty();
    }

    public int size() {
        return mPoints.size();
    }

    public void insert(Point2D p) {
        checkArgument(p);
        mPoints.add(p);
    }

    public boolean contains(Point2D p) {
        checkArgument(p);
        return mPoints.contains(p);
    }

    public void draw() {
        for (Point2D point : mPoints) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkArgument(rect);

        LinkedList<Point2D> points = new LinkedList<>();
        for (Point2D point : mPoints) {
            if (rect.contains(point)) {
                points.add(point);
            }
        }
        return points;
    }

    public Point2D nearest(Point2D p) {
        checkArgument(p);

        if (mPoints.contains(p)) {
            return p;
        }
        Point2D closest = null;
        double minDist = Double.MAX_VALUE;

        for (Point2D point : mPoints) {
            double curDist = p.distanceSquaredTo(point);
            if (curDist < minDist) {
                minDist = curDist;
                closest = point;
            }
        }
        return closest;
    }

    private void checkArgument(Object o) {
        if (o == null) {
            throw new NullPointerException("");
        }
    }
}