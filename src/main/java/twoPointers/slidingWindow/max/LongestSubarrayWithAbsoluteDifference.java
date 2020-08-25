package twoPointers.slidingWindow.max;

import java.util.PriorityQueue;

/**
 * LongestSubarrayWithAbsoluteDifference
 *
 * ======
 *
 * Task.
 *
 * Find longest subarray with absolute diff between any two elements less than
 * K.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class LongestSubarrayWithAbsoluteDifference {
    public static class Solution {
        public int[] solve(int[] ar, int k) {
            int l = 0;
            int n = ar.length;
            PriorityQueue<int[]> pqIncreasing = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            PriorityQueue<int[]> pqDecreasing = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            int ansl = 0;
            int ans = 0;
            for (int r = 0; r < n; r++) {
                pqIncreasing.add(new int[]{r, ar[r]});
                pqDecreasing.add(new int[]{r, ar[r]});
                while (Math.abs(pqIncreasing.peek()[1] - pqDecreasing.peek()[1]) >= k) {
                    int finalL = l;
                    pqDecreasing.removeIf(x -> x[0] == finalL);
                    pqIncreasing.removeIf(x -> x[0] == finalL);
                    l++;
                }
                if (r - l + 1 > ans) {
                    ansl = l;
                    ans = r - l + 1;
                }
            }
            int[] ret = new int[ans];
            for (int i = ansl, j = 0; i < ansl + ans; i++, j++) ret[j] = ar[i];
            return ret;
        }
    }
}