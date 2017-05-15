package com.wx.coding.tests;

import org.testng.annotations.Test;

public class BSTTest {

    @Test
    public void testBSTRank() {
        int[] data = new int[] {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,
                69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        int[] expected = new int[] {0,1,1,3,2,3,5,0,4,0,5,1,6,2,9,2,14,10,17,14,16,7,16,
                7,22,2,0,25,1,20,29,15,4,6,28,20,20,16,37,18};


    }
}
