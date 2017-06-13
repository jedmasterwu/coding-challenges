package com.wx.coding.tests;

import com.wx.coding.problems.ArrayProblems;
import com.wx.coding.problems.ExpressionTree;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class SimpleTests {

    @Test
    public void testMedian() {
        int[] nums = new int[]{2, 1, 3, 5, 1, 21, 4, 4, 5};
        ArrayProblems median = new ArrayProblems();
        System.out.println(median.median(nums));
    }

    @Test
    public void testSprialOrder() {
        int[][] matrix = {
                {1,  2,  3,  4,  5},
                {14, 15, 16, 17, 6},
                {13, 20, 19, 18, 7},
                {12, 11, 10,  9, 8}
        };

        new ArrayProblems().spiralOrder(matrix).forEach(System.out::println);
    }

    @Test
    public void testExpressionTreeBuild() {
        String[] expression = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};

        ExpressionTree et = new ExpressionTree();
        et.build(expression);
    }

    @Test
    public void testRotateNinty() {
        int[][] matrix = {
                {4, 8, 12, 16},
                {3, 7, 11, 15},
                {2, 6, 10, 14},
                {1, 5,  9, 13}
        };

        ArrayProblems.rotateNinety(matrix);
        ArrayProblems.print(matrix);
    }

    @Test
    public void testSearchRotated() {
        int[] nums = {5,6,7,8,9,1,2,3,4};

        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 1));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 2));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 3));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 4));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 5));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 6));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 7));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 8));
        AssertJUnit.assertTrue(ArrayProblems.searchRotated(nums, 9));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, 0));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, 10));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, -1));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, Integer.MAX_VALUE));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, Integer.MIN_VALUE));
        AssertJUnit.assertFalse(ArrayProblems.searchRotated(nums, 18));
    }
}
