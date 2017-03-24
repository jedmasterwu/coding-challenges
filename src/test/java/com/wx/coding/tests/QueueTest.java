package com.wx.coding.tests;

import com.wx.courses.algs4.Deque;
import com.wx.courses.algs4.RandomizedQueue;
import edu.princeton.cs.algs4.Stopwatch;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

public class QueueTest {

    @Test
    public void testDequeBoundaryOperations() {
        Stopwatch stopwatch = new Stopwatch();
        Deque<Integer> deque = new Deque<>();

        AssertJUnit.assertTrue(deque.isEmpty());
        try {
            deque.addFirst(null);
            AssertJUnit.fail();
        } catch (NullPointerException e) {
            // Do nothing
        }
        for (int i = 0, j = 1; i < 1000000000; i++, j++) {
            if (i % 2 == 0) {
                if (j % 2 == 0) {
                    deque.addFirst(i);
                } else {
                    deque.addLast(i);
                }
                AssertJUnit.assertTrue(deque.size() == 1);
                AssertJUnit.assertFalse(deque.isEmpty());
            } else {
                Integer value = (j % 2 == 0) ? deque.removeFirst() : deque.removeLast();
                AssertJUnit.assertTrue(value == i - 1);
                AssertJUnit.assertTrue(deque.isEmpty());
                AssertJUnit.assertTrue(deque.size() == 0);
            }
        }

        AssertJUnit.assertTrue(deque.size() == 0);
        try {
            deque.removeLast();
            AssertJUnit.fail();
        } catch (NoSuchElementException e) {
            //
        }

        System.out.println("Test took " + stopwatch.elapsedTime() + " seconds!");
    }

    @Test
    public void testDequeOrder() {
        Deque<Integer> deque1 = new Deque<>();
        Deque<Integer> deque2 = new Deque<>();

        for (int i = 0; i < 100000; i++) {
            deque1.addFirst(i);
            deque2.addLast(i);
        }

        for (int i = 0; i < 100000; i++) {
            Integer a = deque1.removeLast();
            Integer b = deque2.removeFirst();
            AssertJUnit.assertEquals(a, b);

            deque1.addFirst(a);
            deque2.addLast(b);
        }
    }

    @Test
    public void testDequeIterator() {
        Deque<Integer> deque = new Deque<>();

        int i;
        for (i = 0; i < 1000000; i++) {
            deque.addFirst(i);
        }

        for (Integer val : deque) {
            AssertJUnit.assertTrue(--i == val);
        }
    }

    @Test
    public void randomizedDequeTest() {
        Stopwatch stopwatch = new Stopwatch();
        Deque<Integer> deque = new Deque<>();
        LinkedList<Integer> standard = new LinkedList<>();
        Random random = new Random();

        try {
            for (int i = 0; i < 100000000; i++) {
                int operation = random.nextInt(4);
//                System.out.println(String.format(" --------- i = %d, operation = %d ------------", i, operation));
                switch (operation) {
                    case 0: // addFirst
                        deque.addFirst(i);
                        standard.addFirst(i);
                        AssertJUnit.assertEquals(deque.size(), standard.size());
                        break;
                    case 1: // addLast
                        deque.addLast(i);
                        standard.addLast(i);
                        AssertJUnit.assertEquals(deque.size(), standard.size());
                        break;
                    case 2: // removeFirst
                    case 3: // removeLast
                        Integer a = null, b = null;
                        boolean empty = false;
                        try {
                            a = (operation == 2) ? deque.removeFirst() : deque.removeLast();
                        } catch (NoSuchElementException e) {
//                            System.out.println("Remove while empty case");
                            empty = true;
                            AssertJUnit.assertTrue(deque.isEmpty() && deque.size() == 0);
                        }

                        try {
                            b = (operation == 2) ? standard.removeFirst() : standard.removeLast();
                        } catch (NoSuchElementException e) {
                            AssertJUnit.assertTrue(empty);
                            AssertJUnit.assertTrue(standard.isEmpty() && standard.size() == 0);
//                            System.out.println("Remove while empty case passed");
                        }

                        if (!empty) {
//                            System.out.println(
//                                    String.format("Operation: %s - com.wx.courses.algs4.Deque: %d, LinkedList: %d",
//                                            (operation == 2) ? "removeFirst" : "removeLast",
//                                            a,
//                                            b
//                                    )
//                            );
                            AssertJUnit.assertEquals(a, b);
                        }
                        break;
                }
            }

            AssertJUnit.assertEquals(deque.size(), standard.size());
            Iterator<Integer> dequeIter = deque.iterator();
            Iterator<Integer> standardIter = standard.iterator();
            while (dequeIter.hasNext()) {
                AssertJUnit.assertEquals(dequeIter.next(), standardIter.next());
            }
        } catch (NullPointerException e) {

        }

        System.out.println("Took: " + stopwatch.elapsedTime() + " seconds");
    }

    @Test
    public void testRandomizedQueue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        AssertJUnit.assertTrue(rq.size() == 0 && rq.isEmpty());
        for (int i = 1; i <= 40; i++) {
            rq.enqueue(i);
            AssertJUnit.assertEquals(rq.size(), i);
        }
        AssertJUnit.assertFalse(rq.isEmpty());

        for (int i = 1; i <= 40; i++) {
            System.out.println(rq.dequeue());
            AssertJUnit.assertEquals(rq.size(), 40 - i);
        }
        AssertJUnit.assertTrue(rq.size() == 0 && rq.isEmpty());
    }
}
