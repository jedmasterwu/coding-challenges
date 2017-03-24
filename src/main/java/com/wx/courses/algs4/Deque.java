package com.wx.courses.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        Node(Item item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    private static class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        private Node<Item> sentinel;

        DequeIterator(Node<Item> root) {
            sentinel = root;
            current = root.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the deque");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

    private int mSize;
    private Node<Item> mSentinel;

    public Deque() {
        mSize = 0;
        mSentinel = new Node<>(null); // Dummy node
        mSentinel.next = mSentinel;
        mSentinel.prev = mSentinel;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public int size() {
        return mSize;
    }

    /**
     * Validate that the item to add is not null, otherwise this will throw a NoSuchElementException
     *
     * @param item The item to add
     */
    public void addFirst(Item item) {
        validateNotNull(item);

        // Create the new node to add
        // Connect old head to new one, then connect new head to sentinel node
        Node<Item> newHead = new Node<>(item);
        mSentinel.next.prev = newHead;
        newHead.next = mSentinel.next;
        newHead.prev = mSentinel;
        mSentinel.next = newHead;
        mSize++;
    }

    public void addLast(Item item) {
        validateNotNull(item);

        Node<Item> newTail = new Node<>(item);
        mSentinel.prev.next = newTail;
        newTail.prev = mSentinel.prev;
        newTail.next = mSentinel;
        mSentinel.prev = newTail;
        mSize++;
    }

    public Item removeFirst() {
        validateNotEmpty();

        Node<Item> head = mSentinel.next;
        Item item = head.item;
        head.item = null;

        mSentinel.next = head.next;
        head.next.prev = mSentinel;
        head.next = null;
        head.prev = null;
        mSize--;

        return item;
    }

    public Item removeLast() {
        validateNotEmpty();

        Node<Item> tail = mSentinel.prev;
        Item item = tail.item;
        tail.item = null;

        tail.prev.next = mSentinel;
        mSentinel.prev = tail.prev;
        tail.prev = null;
        tail.next = null;
        mSize--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<>(mSentinel);
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
