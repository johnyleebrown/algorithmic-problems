package array.minmax;

/**
 * 581
 *
 * ======
 *
 * Task.
 *
 * Given an integer array, you need to find one continuous subarray that if you
 * only sort this subarray in ascending order, then the whole array will be
 * sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * ======
 * TODO
 * Source: Leetcode
 */
public class ShortestUnsortedContinuousSubarray {
    /**
     * Find numbers that are not in the right place. l-r and r-l, and take min
     * and max.
     */
    public static class Solution {
        public int findUnsortedSubarray(int[] ar) {
            int n = ar.length;
            int notInPlaceMinInd = n;
            int notInPlaceMaxInd = -1;
            int max = ar[0];
            for (int i = 0; i < n; i++) {
                if (ar[i] < max) {
                    notInPlaceMinInd = Math.min(notInPlaceMinInd, i);
                    notInPlaceMaxInd = Math.max(notInPlaceMaxInd, i);
                }
                max = Math.max(max, ar[i]);
            }
            int min = ar[n - 1];
            for (int i = n - 1; i >= 0; i--) {
                if (ar[i] > min) {
                    notInPlaceMinInd = Math.min(notInPlaceMinInd, i);
                    notInPlaceMaxInd = Math.max(notInPlaceMaxInd, i);
                }
                min = Math.min(min, ar[i]);
            }
            return notInPlaceMinInd == n ? 0 : notInPlaceMaxInd - notInPlaceMinInd + 1;
        }
    }
}