package com.wx.courses.algs4;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

public class KdTree {

    private int mSize;
    private KdNode mRoot;

    public KdTree() {
        mSize = 0;
        mRoot = null;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public void insert(Point2D p) {
        checkArgument(p);
        mRoot = insert(p, mRoot, KdNode.XORDER, 0.0, 1.0, 0.0, 1.0);
    }

    public boolean contains(Point2D p) {
        checkArgument(p);

        KdNode current = mRoot;
        while (current != null) {
            Point2D key = current.key;
            if (p.equals(key)) {
                return true;
            }

            Comparator<Point2D> comparator = getComparator(current.order);
            int cmp = comparator.compare(p, key);
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public void draw() {
        if (mRoot != null) {
            short levels = 0;
            LinkedList<KdNode> currentLevel = new LinkedList<>();
            LinkedList<KdNode> nextLevel = new LinkedList<>();
            currentLevel.add(mRoot);
            while (!currentLevel.isEmpty()) {
                KdNode current = currentLevel.removeFirst();
                draw(current);
                if (current.left != null) {
                    nextLevel.addLast(current.left);
                }

                if (current.right != null) {
                    nextLevel.addLast(current.right);
                }

                if (currentLevel.isEmpty()) {
                    levels++;
                    currentLevel.addAll(nextLevel);
                    nextLevel.clear();
                }
            }

            StdOut.println("2dTree has " + levels + " levels");
        }
    }

    private void draw(KdNode node) {
        if (node != null) {
            double xstart, xend, ystart, yend;
            StdDraw.setPenRadius();
            if (node.order == KdNode.XORDER) {
                StdDraw.setPenColor(StdDraw.RED);
                xstart = node.key.x();
                xend = xstart;
                ystart = node.rect.ymin();
                yend = node.rect.ymax();
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                ystart = node.key.y();
                yend = ystart;
                xstart = node.rect.xmin();
                xend = node.rect.xmax();
            }
            StdDraw.line(xstart, ystart, xend, yend);

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.key.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkArgument(rect);
        TreeSet<Point2D> set = new TreeSet<>();
        range(rect, mRoot, set);
        return set;
    }

    public Point2D nearest(Point2D p) {
        checkArgument(p);
        return nearest(p, mRoot, Double.MAX_VALUE);
    }

    private void checkArgument(Object o) {
        if (o == null) {
            throw new NullPointerException("Argument can't be null");
        }
    }

    private void range(RectHV rect, KdNode node, TreeSet<Point2D> set) {
        if (node == null) {
            return;
        }

        boolean searchLeft = false;
        boolean searchRight = false;
        Point2D current = node.key;
        if (rect.contains(current)) {
            set.add(current);
            searchLeft = true;
            searchRight = true;
        } else {
            if (node.order == KdNode.XORDER) {
                double x = node.key.x();
                if (x >= rect.xmin() && x <= rect.xmax()) {
                    double ymin = node.rect.ymin();
                    double ymax = node.rect.ymax();
                    if ((ymin >= rect.ymin() && ymin <= rect.ymax()) || (ymax >= rect.ymin() && ymax <= rect.ymax()) ||
                            (ymax > rect.ymax() && ymin < rect.ymin())) {
                        searchLeft = true;
                        searchRight = true;
                    }
                } else {
                    if (rect.xmax() < x) {
                        searchLeft = true;
                    } else {
                        searchRight = true;
                    }
                }
            } else {
                double y = node.key.y();
                if (y >= rect.ymin() && y <= rect.ymax()) {
                    double xmin = node.rect.xmin();
                    double xmax = node.rect.xmax();
                    if ((xmin >= rect.xmin() && xmin <= rect.xmax()) || (xmax >= rect.xmin() && xmax <= rect.xmax()) ||
                            (xmax > rect.xmax() && xmin < rect.xmin())) {
                        searchLeft = true;
                        searchRight = true;
                    }
                } else {
                    if (rect.ymax() < y) {
                        searchLeft = true;
                    } else {
                        searchRight = true;
                    }
                }
            }
        }

        if (searchLeft) {
            range(rect, node.left, set);
        }

        if (searchRight) {
            range(rect, node.right, set);
        }
    }

    private Point2D nearest(Point2D p, KdNode node, double minDistSq) {
        // Reached the end of a branch, return null
        if (node == null) {
            return null;
        }

        // Optimization: if we've already discovered a closer point, no need to check this node or it's subtrees
        if (node.rect.distanceSquaredTo(p) >= minDistSq) {
            return null;
        }

        Point2D current = node.key;
        double distSq = p.distanceSquaredTo(node.key);
        double newMin = Math.min(distSq, minDistSq);

        Point2D nearest = distSq < minDistSq ? current : null;
        Comparator<Point2D> comparator = getComparator(node.order);
        int cmp = comparator.compare(p, current);

        Point2D result = nearest(p, cmp < 0 ? node.left : node.right, newMin);
        if (result != null) {
            nearest = result;
            newMin = p.distanceSquaredTo(nearest);
        }

        result = nearest(p, cmp < 0 ? node.right : node.left, newMin);
        if (result != null) {
            nearest = result;
        }
        return nearest;
    }

    private KdNode insert(Point2D p, KdNode node, byte order, double xmin, double xmax, double ymin, double ymax) {
        if (node == null) {
            mSize++;
            return new KdNode(p, order, xmin, xmax, ymin, ymax);
        }

        if (!node.key.equals(p)) {
            Comparator<Point2D> comparator = node.order == KdNode.XORDER ? Point2D.X_ORDER : Point2D.Y_ORDER;
            int cmp = comparator.compare(p, node.key);
            byte nextOrder = switchOrder(node.order);
            if (cmp < 0) {
                if (node.order == KdNode.XORDER) {
                    xmax = node.key.x();
                } else {
                    ymax = node.key.y();
                }
                node.left = insert(p, node.left, nextOrder, xmin, xmax, ymin, ymax);
            } else {
                if (node.order == KdNode.XORDER) {
                    xmin = node.key.x();
                } else {
                    ymin = node.key.y();
                }
                node.right = insert(p, node.right, nextOrder, xmin, xmax, ymin, ymax);
            }
        }

        return node;
    }

    private Comparator<Point2D> getComparator(byte order) {
        return order == KdNode.XORDER ? Point2D.X_ORDER : Point2D.Y_ORDER;
    }

    private byte switchOrder(byte order) {
        return (byte) ~order;
    }

    private static class KdNode {
        private static final byte XORDER = 0xF;
        private final Point2D key;
        private final RectHV rect;
        private final byte order;
        private KdNode left = null;
        private KdNode right = null;

        KdNode(Point2D p, byte o, double xmin, double xmax, double ymin, double ymax) {
            key = p;
            rect = new RectHV(xmin, ymin, xmax, ymax);
            order = o;
        }
    }
}
