package com.wx.coding.tests;

import com.wx.coding.problems.ArrayProblems;
import com.wx.coding.problems.Median;
import org.testng.annotations.Test;

public class SimpleTests {

    @Test
    public void testMedian() {
        int[] nums = new int[] {2, 1, 3, 5, 1, 21, 4, 4, 5};
        int[] copy = nums.clone();
        ArrayProblems median = new ArrayProblems();
        Median ref = new Median();
        System.out.println(median.median(nums));
        System.out.println("================================================");
        System.out.println(ref.median(copy));
    }
}
