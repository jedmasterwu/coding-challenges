package com.wx.coding.tests;

import com.wx.coding.problems.UniquePaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Created by wuming on 3/13/17.
 */
public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void testIntegers() {

        int negativeOverflow = Integer.MIN_VALUE - Integer.MAX_VALUE;
        System.out.println(String.format("Int Max: %d, Int Min: %d, Overflowed: %d",
                Integer.MAX_VALUE, Integer.MIN_VALUE, negativeOverflow));
    }

    @Test
    public void testUniquePaths() {
        for (int m = 1; m <= 10; m++) {
            for (int n = 1; n<= 10; n++) {
                logger.info("{} x {} --> {}", m, n, UniquePaths.getPaths(m, n));
            }
        }

        long max = UniquePaths.getPaths(100, 100);
        logger.info("{} x {} --> {} ||||| Long.Max = {},  max < long.max {}", 100, 100, max, Long.MAX_VALUE, max <= Long.MAX_VALUE);
    }

    @Test
    public void testUniquePathsTwo() {
        int[][] test =
        new int[][] {
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0},
                {1,0,0,0,0,0,0,1,0,0}
        };

        logger.info("{}", UniquePaths.uniquePathsWithObstacles(test));
        logger.info("'A' - 'a' = {}", (int) 'A' - 'a');
    }
}
