package com.wx.courses.algs4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.FileNotFoundException;

public class Permutation {
    public static void main(String[] args) throws FileNotFoundException {
        int k = Integer.parseInt(args[0]);

        if (k > 0) {
            RandomizedQueue<String> rq = new RandomizedQueue<>();

            int itemNumber = 0;
            while (!StdIn.isEmpty()) {
                String current = StdIn.readString();

                itemNumber++;
                if (rq.size() == k) {
                    // We have k items in our com.wx.courses.algs4.RandomizedQueue, but we still need to go through the rest of the list
                    if (StdRandom.uniform(1, itemNumber + 1) <= k) {
                        rq.dequeue();
                        rq.enqueue(current);
                    }
                } else {
                    rq.enqueue(current);
                }
            }

            for (String s : rq) {
                StdOut.println(s);
            }
        }
    }
}