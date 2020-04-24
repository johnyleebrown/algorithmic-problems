package array.traverse;

/**
 * 941
 *
 * ======
 *
 * Task.
 *
 * Given an array A of integers, return true if and only if it is a valid
 * mountain array.
 *
 * Recall that A is a mountain array if and only if:
 *
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidMountainArray {
    /**
     * Find max, go from left and from right.
     */
    public static class Solution {
        public boolean validMountainArray(int[] a) {
            if (a.length < 3) return false;
            int maxv = -1;
            int maxi = -1;
            for (int i = 0; i < a.length; i++) {
                if (a[i] > maxv) {
                    maxv = a[i];
                    maxi = i;
                }
            }
            if (maxi == 0 || maxi == a.length - 1) return false;
            for (int i = 0; i < maxi; i++) {
                if (a[i + 1] <= a[i]) {
                    return false;
                }
            }
            for (int i = maxi; i < a.length - 1; i++) {
                if (a[i + 1] >= a[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}