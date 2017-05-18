package com.wx.coding.tests;

import com.wx.coding.problems.ArrayProblems;
import org.testng.annotations.Test;

public class SimpleTests {

    @Test
    public void testMedian() {
        int[] nums = new int[] {2, 1, 3, 5, 1, 21, 4, 4, 5};
        ArrayProblems median = new ArrayProblems();
        System.out.println(median.median(nums));
    }
}
