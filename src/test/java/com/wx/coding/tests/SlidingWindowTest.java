package com.wx.coding.tests;

import com.wx.coding.problems.SlidingWindowMedian;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SlidingWindowTest {

    @Test
    public void testSlidingWindowMedian() {
        int[] data = new int[] {7,7,7,7,7,7,7,7,7,7,7,7};
        int k = 5;
        int[] answer = new int[] {7,7,7,7,7,7,7,7};

        ArrayList<Integer> result = new SlidingWindowMedian().medianSlidingWindow(data, k);

        for (int i = 0; i < answer.length; i++) {
            int expected = answer[i];
            Integer actual = result.get(i);
            AssertJUnit.assertTrue("#" + (i + 1) +": expected: " + expected + " vs actual: " + actual, expected == actual);
        }
    }

    @Test
    public void testSlidingWindowDouble() {
        int[] data = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 4;
        double[] answer = new double[] {0,1,1,4,5.5};

        double[] result = new SlidingWindowMedian().medianSlidingWindowLeetCode(data, k);

        for (int i = 0; i < answer.length; i++) {
            double expected = answer[i];
            double actual = result[i];
            AssertJUnit.assertTrue("#" + (i + 1) +": expected: " + expected + " vs actual: " + actual, expected == actual);
        }
    }
}
