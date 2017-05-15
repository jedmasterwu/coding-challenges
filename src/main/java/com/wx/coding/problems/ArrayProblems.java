package com.wx.coding.problems;

public class ArrayProblems {

    /**
     *
     * https://leetcode.com/problems/merge-sorted-array/#/description
     *
     * @param a Sorted array with m elements but has size m + n
     * @param m Number of elements in array a
     * @param b Sorted array with n elments
     * @param n Number of elements in array b
     * @return Returns array a, but with all elements from a and b in sorted order
     */
    public int[] merge(int[] a, int m, int[] b, int n) {
        assert a.length == m + n;

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[k--] = b[j--];
            } else {
                a[k--] = a[i--];
            }
        }

        while (j >= 0) {
            a[k--] = b[j--];
        }

        return a;
    }

    /**
     * https://www.lintcode.com/problem/merge-two-sorted-arrays
     * https://leetcode.com/problems/merge-sorted-array/#/description
     *
     * @param a Sorted array a
     * @param b Sorted array b
     * @return New array with a and b merged in sorted order
     */
    public int[] merge(int[] a, int[] b) {
        if (a == null || a.length == 0) {
            return b;
        }

        if (b == null || b.length == 0) {
            return a;
        }

        int m = a.length - 1;
        int n = b.length - 1;
        int[] result = new int[m + n + 2];
        int k = m + n + 1;
        while (m >= 0 && n >= 0) {
            int toAdd = (a[m] > b[n]) ? a[m--] : b[n--];
            result[k--] = toAdd;
        }

        while (m >= 0) {
            result[k--] = a[m--];
        }

        while (n >= 0) {
            result[k--] = b[n--];
        }

        return result;
    }

    public int kth(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        int j = -1;
        while (j != k) {
            print(nums);
            j = partition(nums, lo, hi);
            System.out.print(" --------> ");
            print(nums);
            System.out.println("");
            if (j < k) {
                lo = j + 1;
            } else {
                hi = j - 1;
            }
        }
        return nums[k];
    }

    public int median(int[] nums) {
        return kth(nums, (nums.length - 1) >> 1);
    }

    private int partition(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return lo;
        }
        int i = lo;
        int j = hi + 1;
        int partition = nums[lo];
        while (i < j) {
            while (nums[++i] < partition) {
                if (i == hi) {
                    break;
                }
            }
            while (nums[--j] >= partition) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }

        swap(nums, lo, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void print(int[] nums) {
        System.out.print("[");

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.print("]");
    }
}
