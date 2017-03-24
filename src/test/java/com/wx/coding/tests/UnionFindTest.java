package com.wx.coding.tests;

import com.wx.utils.UnionFind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class UnionFindTest {

    private static Logger logger = LoggerFactory.getLogger(UnionFindTest.class);

    @Test
    public void testUnionFind() {
        int size = 20;
        UnionFind uf = new UnionFind(size);
        for (int i = 0; i < size; i+= 2) {
            uf.union(i, i + 1);
        }

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int p = rand.nextInt(size);
            int q = rand.nextInt(size);
            uf.union(p, q);
            logger.info("Union({}, {})", p, q);
            logger.info("{}", uf.getTree());
        }
    }
}
