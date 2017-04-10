package com.wx.courses.algs4;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] mBlocks;
    private final int mSize;
    private int mEmptyIndex;
    private int mHamming = 0;
    private int mManhattan = 0;

    public Board(int[][] blocks) {
        mSize = blocks.length;
        mBlocks = new int[mSize][mSize];

        for (int i = 0; i < mSize; i++) {
            for (int j = 0; j < mSize; j++) {
                int block = blocks[i][j];
                int flattened = flatten(i, j);
                mBlocks[i][j] = block;
                if (block == 0) {
                    mEmptyIndex = flattened;
                } else if (block != flattened) {
                    mHamming++;
                    mManhattan += Math.abs(i - getIdealRow(block)) + Math.abs(j - getIdealCol(block));
                }
            }
        }
    }

    public int dimension() {
        return mSize;
    }

    public int hamming() {
        return mHamming;
    }

    public int manhattan() {
        return mManhattan;
    }

    public boolean isGoal() {
        return mHamming == 0 && mManhattan == 0;
    }

    public Board twin() {
        int r1 = 0;
        int c1 = 0;
        for (int i = 1; i <= 4; i++) {
            r1 = getIdealRow(mEmptyIndex);
            c1 = getIdealCol(mEmptyIndex);
            switch (i) {
                case 1: // Up
                    r1--;
                    break;
                case 2: // Left
                    c1--;
                    break;
                case 3: // Down
                    r1++;
                    break;
                case 4: // Right
                    c1++;
                    break;
                default:
                    break;
            }

            if (isValidIndex(r1) && isValidIndex(c1)) {
                break;
            }
        }

        // Get seconde index
        int r2 = 0;
        int c2 = 0;
        for (int i = 1; i <= 4; i++) {
            r2 = r1;
            c2 = c1;
            switch (i) {
                case 1: // Up
                    r2--;
                    break;
                case 2: // Left
                    c2--;
                    break;
                case 3: // Down
                    r2++;
                    break;
                case 4: // Right
                    c2++;
                    break;
                default:
                    break;
            }

            if (isValidIndex(r2) && isValidIndex(c2) && mBlocks[r2][c2] != 0) {
                break;
            }
        }

        // Swap those two adjacent blocks and create the new board, then swap them back
        swap(r1, c1, r2, c2);
        Board twin = new Board(mBlocks);
        swap(r1, c1, r2, c2);

        return twin;
    }

    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board other = (Board) y;
        if (mSize == other.dimension()) {
            for (int i = 0; i < mSize; i++) {
                for (int j = 0; j < mSize; j++) {
                    if (mBlocks[i][j] != other.mBlocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Iterable<Board> neighbors() {
        int emptyRow = getIdealRow(mEmptyIndex);
        int emptyCol = getIdealCol(mEmptyIndex);
        List<Board> neighbors = new LinkedList<>();

        for (int i = 1; i <= 4; i++) {
            int r = emptyRow;
            int c = emptyCol;
            switch (i) {
                case 1: // Up
                    r--;
                    break;
                case 2: // Left
                    c--;
                    break;
                case 3: // Down
                    r++;
                    break;
                case 4: // Right
                    c++;
                    break;
                default:
                    break;
            }

            if (isValidIndex(r) && isValidIndex(c)) {
                swap(r, c, emptyRow, emptyCol);
                neighbors.add(new Board(mBlocks));
                swap(r, c, getIdealRow(mEmptyIndex), getIdealCol(mEmptyIndex));
            }

        }
        return neighbors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (builder.length() == 0) {
            // Build the string representation once
            String format = String.format("%%%dd ", String.valueOf(mSize * mSize - 1).length());
            builder.append(mSize).append('\n');
            for (int i = 0; i < mSize; i++) {
                for (int j = 0; j < mSize; j++) {
                    builder.append(String.format(format, mBlocks[i][j]));
                }
                builder.setCharAt(builder.length() - 1, '\n');
            }
        }

        return builder.toString();
    }

    private int flatten(int r, int c) {
        return r * mSize + c + 1;
    }

    private int getIdealRow(int block) {
        return (block - 1) / mSize;
    }

    private int getIdealCol(int block) {
        return (block - 1) % mSize;
    }

    private void swap(int r1, int c1, int r2, int c2) {
        int tempBlock = mBlocks[r1][c1];
        mBlocks[r1][c1] = mBlocks[r2][c2];
        mBlocks[r2][c2] = tempBlock;
    }

    private boolean isValidIndex(int rc) {
        return rc >= 0 && rc < mSize;
    }
}
