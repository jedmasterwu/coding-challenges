package com.wx.courses.algs4;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final byte OPEN = 0x1;
    private static final byte CONNECTED_TOP = 0x2;
    private static final byte CONNECTED_BOTTOM = 0x4;
    private static final byte FULL = OPEN | CONNECTED_TOP;
    private static final byte PERCOLATES = FULL | CONNECTED_BOTTOM;
    private static final int[][] INDEX_CHANGES = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Right
            {0, 1}   // Left
    };

    private int mOpenSites = 0;
    private int mSize;
    private byte[] mGrid;
    private boolean mPercolates;
    private WeightedQuickUnionUF mUnionFind;

    /***
     * Represent each tile in the grid as a bit in our byte array. Number of bytes needed is (n^2 / 8) + 1
     *
     * Initialization requires O(n^2) time and O(n^2) space
     *
     * @param n The width and height of the grid
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid mSize cannot be negative");
        }

        // Initialize member variables
        this.mSize = n;
        this.mPercolates = false;
        int requiredSize = n * n;
        this.mGrid = new byte[requiredSize];
        this.mUnionFind = new WeightedQuickUnionUF(requiredSize);
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        // First open the specified site
        int index = convert(row, col);
        mGrid[index] |= OPEN;
        mOpenSites++;

        // If it's a top or bottom row tile, need to connect them to the fake top bottom
        // If there's only one row, then it needs to be connected to both
        if (row == 1) {
            mGrid[index] |= CONNECTED_TOP;
        }
        if (row == mSize) {
            mGrid[index] |= CONNECTED_BOTTOM;
        }

        // Now we need to connect the newly opened site to any adjacent open sites
        int status = 0;
        for (int[] change : INDEX_CHANGES) {
            try {
                int rowToCheck = row + change[0];
                int colToCheck = col + change[1];

                if (isOpen(rowToCheck, colToCheck)) {
                    int currentIndex = convert(rowToCheck, colToCheck);
                    int root = mUnionFind.find(currentIndex);
                    mUnionFind.union(index, root);
                    status |= mGrid[root];
                }
            } catch (IndexOutOfBoundsException e) {
                // Not a valid grid tile, ignore;
            }
        }

        int currentConnectedGroupRoot = mUnionFind.find(index);
        mGrid[currentConnectedGroupRoot] |= (status | mGrid[index]);
        if ((mGrid[currentConnectedGroupRoot] & PERCOLATES) == PERCOLATES) {
            mPercolates = true;
        }
    }

    public boolean isOpen(int row, int col) {
        checkIndices(row, col);
        return (getGridState(row, col) & OPEN) != 0;
    }

    public boolean isFull(int row, int col) {
        checkIndices(row, col);
        return (mGrid[mUnionFind.find(convert(row, col))] & FULL) == FULL;
    }

    public int numberOfOpenSites() {
        return mOpenSites;
    }

    public boolean percolates() {
        return mPercolates;
    }

    private int convert(int row, int col) {
        return ((row - 1) * mSize) + col - 1;
    }

    private void checkIndices(int row, int col) {
        if (row <= 0 || col <= 0 || row > mSize || col > mSize) {
            throw new IndexOutOfBoundsException(indexToString(row, col) + " is invalid");
        }
    }

    private int getGridState(int row, int col) {
        int index = convert(row, col);

        return mGrid[index];
    }

    private String indexToString(int row, int col) {
        return String.format("(row: %d, col: %d)", row, col);
    }

    public static void main(String[] args) {
    }
}