package com.wx.courses.algs4;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private int mSize;
    private int mTrials;
    private double[] mProbabilities;
    private double mMean;
    private double mStddev;
    private double mConfidenceLo;
    private double mConfidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid size and trials cannot be less than 0");
        }
        mSize = n;
        mTrials = trials;
        mProbabilities = new double[mTrials];

        doTrials();
        calculateStats();
    }

    public double mean() {
        return mMean;
    }

    public double stddev() {
        return mStddev;
    }

    public double confidenceLo() {
        return mConfidenceLo;
    }

    public double confidenceHi() {
        return mConfidenceHi;
    }

    private void doTrials() {
        for (int i = 0; i < mTrials; i++) {
            Percolation percolation = new Percolation(mSize);
            do {
                int row, col;
                do {
                    row = getIndex();
                    col = getIndex();
                } while (percolation.isOpen(row, col));
                percolation.open(row, col);
            } while (!percolation.percolates());
            mProbabilities[i] = ((double) percolation.numberOfOpenSites())/ (mSize * mSize);
        }
    }

    private void calculateStats() {
        mMean = StdStats.mean(mProbabilities);
        mStddev = StdStats.stddev(mProbabilities);

        double interval = 1.96 * mStddev/Math.sqrt(mTrials);
        mConfidenceLo = mMean - interval;
        mConfidenceHi = mMean + interval;
    }

    private int getIndex() {
        return StdRandom.uniform(mSize) + 1;
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        Stopwatch stopwatch = new Stopwatch();
        PercolationStats stats = new PercolationStats(n, trials);
        String output = String.format(
                "mean                    = %f\nstddev                  = %f\n95%% confidence interval = [%f, %f]",
                stats.mean(),
                stats.stddev(),
                stats.confidenceLo(),
                stats.confidenceHi()
        );

        System.out.println(output);
        System.out.println("Total time: " + stopwatch.elapsedTime() + " seconds");
    }
}
