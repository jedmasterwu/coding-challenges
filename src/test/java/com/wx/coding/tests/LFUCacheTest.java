package com.wx.coding.tests;

import com.wx.coding.problems.LFUCache;
import com.wx.coding.problems.LFUCacheV2;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class LFUCacheTest {

    @Test
    public void testLFUCache1() {
        LFUCacheV2 cache = new LFUCacheV2(2);

        cache.put(1,1);
        cache.put(2,2);
        AssertJUnit.assertEquals(cache.get(1), 1);
        cache.put(3,3);
        AssertJUnit.assertEquals(cache.get(2), -1);
        AssertJUnit.assertEquals(cache.get(3), 3);
        cache.put(4,4);
        AssertJUnit.assertEquals(cache.get(1), -1);
        AssertJUnit.assertEquals(cache.get(3), 3);
        AssertJUnit.assertEquals(cache.get(4), 4);
    }

    @Test
    public void testLFUCache2() {
        LFUCache cache = new LFUCache(2);

        cache.put(2,1);
        cache.put(1,1);
        cache.put(2,3);
        cache.put(4,1);
        AssertJUnit.assertEquals(cache.get(1), -1);
        AssertJUnit.assertEquals(cache.get(2), 3);
        AssertJUnit.assertEquals(cache.get(4), 1);
    }
    
    @Test
    public void testLFUCache3() {
        LFUCacheV2 cache = new LFUCacheV2(10);
        cache.put(10, 13);
        cache.put(3, 17);
        cache.put(6, 11);
        cache.put(10, 5);
        cache.put(9, 10);
        AssertJUnit.assertEquals(cache.get(13), -1);
        cache.put(2, 19);
        AssertJUnit.assertEquals(cache.get(2), 19);
        AssertJUnit.assertEquals(cache.get(3), 17);
        cache.put(5, 25);
        AssertJUnit.assertEquals(cache.get(8), -1);
        cache.put(9, 22);
        cache.put(5, 5);
        cache.put(1, 30);
        AssertJUnit.assertEquals(cache.get(11), -1);
        cache.put(9, 12);
        AssertJUnit.assertEquals(cache.get(7), -1);
        AssertJUnit.assertEquals(cache.get(5), 5);
        AssertJUnit.assertEquals(cache.get(8), -1);
        AssertJUnit.assertEquals(cache.get(9), 12);
        cache.put(4, 30);
        cache.put(9, 3);
        AssertJUnit.assertEquals(cache.get(9), 3);
        AssertJUnit.assertEquals(cache.get(10), 5);
        AssertJUnit.assertEquals(cache.get(10), 5);
        cache.put(6, 14);
        cache.put(3, 1);
        AssertJUnit.assertEquals(cache.get(3), 1);
        cache.put(10, 11);
        AssertJUnit.assertEquals(cache.get(8), -1);
        cache.put(2, 14);
        AssertJUnit.assertEquals(cache.get(1), 30);
        AssertJUnit.assertEquals(cache.get(5), 5);
        AssertJUnit.assertEquals(cache.get(4), 30);
        cache.put(11, 4);
        cache.put(12, 24);
        cache.put(5, 18);
        AssertJUnit.assertEquals(cache.get(13), -1);
        cache.put(7, 23);
        AssertJUnit.assertEquals(cache.get(8), -1);
        AssertJUnit.assertEquals(cache.get(12), 24);
        cache.put(3, 27);
        cache.put(2, 12);
        AssertJUnit.assertEquals(cache.get(5), 18);
        cache.put(2, 9);
        cache.put(13, 4);
        cache.put(8, 18);
        cache.put(1, 7);
        AssertJUnit.assertEquals(cache.get(6), 14);
        cache.put(9, 29);
        cache.put(8, 21);
        AssertJUnit.assertEquals(cache.get(5), 18);
        cache.put(6, 30);
        cache.put(1, 12);
        AssertJUnit.assertEquals(cache.get(10), 11);
        cache.put(4, 15);
        cache.put(7, 22);
        cache.put(11, 26);
        cache.put(8, 17);
        cache.put(9, 29);
        AssertJUnit.assertEquals(cache.get(5), 18);
        cache.put(3, 4);
        cache.put(11, 30);
        AssertJUnit.assertEquals(cache.get(12), -1);
        cache.put(4, 29);
        AssertJUnit.assertEquals(cache.get(3), 4);
        AssertJUnit.assertEquals(cache.get(9), 29);
        AssertJUnit.assertEquals(cache.get(6), 30);
        cache.put(3, 4);
        AssertJUnit.assertEquals(cache.get(1), 12);
        AssertJUnit.assertEquals(cache.get(10), 11);
        cache.put(3, 29);
        cache.put(10, 28);
        cache.put(1, 20);
        cache.put(11, 13);
        AssertJUnit.assertEquals(cache.get(3), 29);
        cache.put(3, 12);
        cache.put(3, 8);
        cache.put(10, 9);
        cache.put(3, 26);
        AssertJUnit.assertEquals(cache.get(8), 17);
        AssertJUnit.assertEquals(cache.get(7), -1);
        AssertJUnit.assertEquals(cache.get(5), 18);
        cache.put(13, 17);
        cache.put(2, 27);
        cache.put(11, 15);
        AssertJUnit.assertEquals(cache.get(12), -1);
        cache.put(9, 19);
        cache.put(2, 15);
        cache.put(3, 16);
        AssertJUnit.assertEquals(cache.get(1), 20);
        cache.put(12, 17);
        cache.put(9, 1);
        cache.put(6, 19);
        AssertJUnit.assertEquals(cache.get(4), 29);
        AssertJUnit.assertEquals(cache.get(5), 18);
        AssertJUnit.assertEquals(cache.get(5), 18);
        cache.put(8, 1);
        cache.put(11, 7);
        cache.put(5, 2);
        cache.put(9, 28);
        AssertJUnit.assertEquals(cache.get(1), 20);
        cache.put(2, 2);
        cache.put(7, 4);
        cache.put(4, 22);
        cache.put(7, 24);
        cache.put(9, 26);
        cache.put(13, 28);
        cache.put(11, 26);
    }
}
