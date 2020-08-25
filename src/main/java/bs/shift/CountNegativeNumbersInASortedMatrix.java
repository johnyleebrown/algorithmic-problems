package bs.shift;

/**
 * 1351
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix grid which is sorted in non-increasing order both
 * row-wise and column-wise.
 *
 * Return the number of negative numbers in grid.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountNegativeNumbersInASortedMatrix {
    /**
     * Check on every turn where is the edge between numbers below zero and
     * others. Edge case - index 0.
     */
    public static class Solution {
        private int m;

        public int countNegatives(int[][] a) {
            m = a[0].length;
            int sum = 0;

            for (int i = 0; i < a.length; i++) {
                int x = bs(a[i]);
                sum += x == -1 ? 0 : m - x;
            }

            return sum;
        }

        private int bs(int[] a) {
            int lo = 0, hi = m - 1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (a[mid] < 0) {
                    if (mid - 1 >= 0 && a[mid - 1] >= 0 || mid == 0) {
                        return mid;
                    } else {
                        hi = mid - 1;
                    }
                } else {

                    if (mid + 1 < m && a[mid + 1] < 0) {
                        return mid + 1;
                    } else {
                        lo = mid + 1;
                    }
                }
            }

            return -1;
        }
    }
}