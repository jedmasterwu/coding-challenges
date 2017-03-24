package com.wx.utils;

public class UnionFind {

    private int[] ids;
    private int[] sizeArray;
    private int count;

    public UnionFind(int size) {
        this.count = size;
        this.ids = new int[size];
        this.sizeArray = new int[size];

        for (int i = 0; i < size; i++) {
            ids[i] = i;
            sizeArray[i] = 1;
        }
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i != j) {
            if (sizeArray[i] > sizeArray[j]) {
                ids[j] = i;
                sizeArray[i] += sizeArray[j];
            } else {
                ids[i] = j;
                sizeArray[j] += sizeArray[i];
            }
            count--;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int[] getTree() {
        return ids;
    }

    public int getCount() {
        return count;
    }

    private int root(int num) {
        while(num != ids[num]) {
            // path compression optimization: one extra step to move each node visited to its grandparent
            // consider this tree: [0, 1, 1, 2] and the operation root(3)
            // We would get [0, 1, 1, 1] and compress our tree height
            ids[num] = ids[ids[num]];
            num = ids[num];
        }

        return num;
    }
}
