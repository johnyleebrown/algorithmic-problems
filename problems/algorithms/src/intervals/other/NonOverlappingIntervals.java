package intervals.other;

import java.util.Arrays;

/**
 * 435
 *
 * ======
 *
 * Task.
 *
 * Given a collection of intervals, find the minimum number of intervals you
 * need to remove to make the rest of the intervals non-overlapping.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NonOverlappingIntervals {
    /**
     * Classic greedy non overlapping interval solution.
     */
    public static class Solution {
        public int eraseOverlapIntervals(int[][] ar) {
            if (ar.length <= 1) return 0;
            Arrays.sort(ar, (a, b) -> a[1] - b[1]);
            int ans = 1;
            int prev = ar[0][1];
            for (int i = 1; i < ar.length; i++) {
                if (ar[i][0] >= prev) {
                    ans++;
                    prev = ar[i][1];
                }
            }
            return ar.length - ans;
        }
    }
}