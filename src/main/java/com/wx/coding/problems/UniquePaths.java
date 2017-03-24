package com.wx.coding.problems;

/**
 * Created by wuming on 3/13/17.
 */
public class UniquePaths {

    // Given a matrix of size m x n where 1 <= n, m  <= 100
    // This will return the number of paths from top left to bottom right
    // where you can only go right or down from any given square
    public static long getPaths(int m, int n) {
        long[][] mat = new long[m][n];

        // Set the outer edges to 1 since there's no option but to go down from there
        for (int r = m - 1, c = 0; c < n; c++) {
            mat[r][c] = 1;
        }
        for (int r = 0, c = n - 1; r < m - 1; r++) {
            mat[r][c] = 1;
        }

        for (int r = m - 2; r >= 0; --r) {
            for (int c = n - 2; c >= 0; --c) {
                mat[r][c] = mat[r+1][c] + mat[r][c+1];
            }
        }

        return mat[0][0];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int m = obstacleGrid.length;

        for (int r = m - 1; r >= 0; --r) {
            for (int c = n - 1; c >= 0; --c) {
                if (obstacleGrid[r][c] == 1) {
                    obstacleGrid[r][c] = 0;
                } else {
                    if (r == m - 1 && c == n - 1) {
                        obstacleGrid[r][c] = 1;
                    } else {
                        obstacleGrid[r][c] = (isValid(r + 1, c, m, n) ? obstacleGrid[r + 1][c] : 0) +
                                (isValid(r, c + 1, m, n) ? obstacleGrid[r][c + 1] : 0);
                    }
                }
            }
        }

        return obstacleGrid[0][0];
    }

    private static boolean isValid(int row, int col, int height, int width) {
        return (row >= 0 && row < height) && (col >= 0 && col < width);
    }
}
