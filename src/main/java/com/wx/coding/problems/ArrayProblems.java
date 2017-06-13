package com.wx.coding.problems;

import java.util.ArrayList;
import java.util.List;

public class ArrayProblems {

    public static boolean searchRotated(int[] nums, int target) {
        if (nums == null) {
            return false;
        }

        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int midVal = nums[mid];

            if (target == midVal) {
                return true;
            }

            int loVal = nums[lo];
            int hiVal = nums[hi];
            if (midVal < hiVal) {
                if (target > midVal && target <= hiVal) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                if (target < midVal && target >= loVal) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return false;
    }

    public static boolean searchRotatedDuplicates(int[] nums, int target) {
        if (nums == null) {
            return false;
        }

        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int midVal = nums[mid];

            if (target == midVal) {
                return true;
            }

            int hiVal = nums[hi];
            int loVal = nums[lo];
            if (midVal > hiVal) {
                if (target < midVal && target >= loVal) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else if (midVal < hiVal) {
                if (target > midVal && target <= hiVal) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                hi--;
                if (loVal == hiVal) {
                    lo++;
                }
            }
        }

        return false;
    }

    public static void rotateNinety(int[][] a) {
        int start = 0;
        int end = a.length - 1;

        while (start < end) {
            for (int i = 0; start + i < end; i++) {
                int tmp = a[start][start + i];
                a[start][start + i] = a[end - i][start];
                a[end - i][start] = a[end][end - i];
                a[end][end - i] = a[start + i][end];
                a[start + i][end] = tmp;
            }
            start++;
            end--;
        }
    }

    public static void print(int[][] a) {
        System.out.println("[");
        for (int r = 0; r < a.length; r++) {
            int[] row = a[r];
            System.out.print("   [");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i != row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (r != a.length - 1) {
                System.out.println(",");
            } else {
                System.out.println("");
            }
        }
        System.out.println("]");
    }

    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/
     *
     * @param a1 Sorted array #1
     * @param a2 Sorted array #2
     * @return Median of the two sorted arrays
     */
    public double findMedianSortedArrays(int[] a1, int[] a2) {
        int n1 = a1.length;
        int n2 = a2.length;

        assert n1 > 0 || n2 > 0; // At least one array needs to be non empty

        // Make sure the first array has length <= to length of second array for simplicity
        if (n2 < n1) {
            return findMedianSortedArrays(a2, a1);
        }

        // if the smaller array is empty
        if (n1 == 0) {
            return (1.0 * a2[(n2 - 1) >> 1] + a2[n2 >> 1]) / 2.0;
        }

        // Low and high cut positions as 0 based indices
        int lo = 0;
        int hi = 2 * n1;
        while (lo <= hi) {
            int cut1 = (hi + lo) >> 1;
            int cut2 = n1 + n2 - cut1;

            double L1 = (cut1 == 0) ? Double.NEGATIVE_INFINITY : a1[(cut1 - 1) >> 1];
            double R1 = (cut1 == n1 << 1) ? Double.POSITIVE_INFINITY : a1[cut1 >> 1];
            double L2 = (cut2 == 0) ? Double.NEGATIVE_INFINITY : a2[(cut2 - 1) >> 1];
            double R2 = (cut2 == n2 << 1) ? Double.POSITIVE_INFINITY : a2[cut2 >> 1];

            if (L1 > R2) {
                // cut1 is too high
                hi = cut1 - 1;
            } else if (L2 > R1) {
                // cut1 is too low
                lo = cut1 + 1;
            } else {
                // We've found the right place to cut
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
            }
        }

        return -1;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rstart = 0;
        int rend = matrix.length - 1;
        int cstart = 0;
        int cend = matrix[0].length - 1;

        while (rstart <= rend && cstart <= cend) {
            // Add top row
            for (int i = rstart, j = cstart; j <= cend; j++) {
                result.add(matrix[i][j]);
            }
            rstart++;

            // Add right col
            for (int i = rstart, j = cend; i <= rend; i++) {
                result.add(matrix[i][j]);
            }
            cend--;

            // Add bottom row
            if (rstart <= rend) {
                for (int i = rend, j = cend; j >= cstart; j--) {
                    result.add(matrix[i][j]);
                }
            }
            rend--;

            if (cstart <= cend) {
                for (int i = rend, j = cstart; i >= rstart; i--) {
                    result.add(matrix[i][j]);
                }
            }
            cstart++;
        }

        return result;
    }

    /**
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
