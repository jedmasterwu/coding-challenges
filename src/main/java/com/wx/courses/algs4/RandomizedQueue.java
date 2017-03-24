package com.wx.courses.algs4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] indices;
        private int index;

        RandomizedQueueIterator() {
            index = 0;
            indices = StdRandom.permutation(mSize);
        }

        @Override
        public boolean hasNext() {
            return index < indices.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the com.wx.courses.algs4.RandomizedQueue");
            }
            return mItems[indices[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supporetd");
        }
    }

    private static final int INITIAL_SIZE = 2;
    private int mSize;
    private Item[] mItems;

    public RandomizedQueue() {
        mSize = 0;
        mItems = (Item[]) new Object[INITIAL_SIZE];
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    public void enqueue(Item item) {
        validateNotNull(item);

        if (mSize == mItems.length) {
            resize(mItems.length << 1);
        }

        mItems[mSize++] = item;
    }

    public Item dequeue() {
        validateNotEmpty();

        // Get random index to remove, and replace it with the last item
        // Finally remove the duplicate reference last item
        int randIndex = StdRandom.uniform(mSize);
        Item item = mItems[randIndex];
        if (randIndex != mSize - 1) {
            mItems[randIndex] = mItems[mSize - 1];
        }
        mItems[--mSize] = null;

        if (mSize > 0 && (mSize == (mItems.length >> 2))) {
            resize(mItems.length >> 1);
        }
        return item;
    }

    public Item sample() {
        validateNotEmpty();
        return mItems[StdRandom.uniform(mSize)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize(int size) {
        Item[] resized = (Item[]) new Object[size];
        for (int i = 0; i < mSize; i++) {
            resized[i] = mItems[i];
            mItems[i] = null;
        }
        mItems = resized;
    }

    private void validateNotEmpty() {
        if (mSize == 0) {
            throw new NoSuchElementException("Can't remove from an empty deque");
        }
    }

    private void validateNotNull(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null element to the deque");
        }
    }

    public static void main(String[] args) {

    }
}

