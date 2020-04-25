package twoPointers.slidingWindow.max;

import util.tester.Tester;

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
    public static void main(String[] args) {
        new Tester(new Solution())
                .add(new int[]{2, 5, 10, 7, 3, 9, 0, 6, 11, 17}, 6).expect(new int[]{5, 10, 7})
                .add(new int[]{2, 5, 8, 7, 3, 8, 0, 6, 11, 17}, 6).expect(new int[]{5, 8, 7, 3, 8})
                .add(new int[]{2, 5, 8, 7, 3, 8, 0, 6, 11, 17}, 5).expect(new int[]{5, 8, 7})
                .add(new int[]{2, 5, 8, 7, 3, 8, 0, 6, 11, 17}, 3).expect(new int[]{8, 7})
                .add(new int[]{3, 5, 8, 7, 3, 8, 0, 6, 11, 17}, 3).expect(new int[]{3, 5})
                .add(new int[]{1, 2, 4}, 2).expect(new int[]{1, 2})
                .add(new int[]{8, 3, 15, 7, 6, 4}, 2).expect(new int[]{7, 6})
                .add(new int[]{96, 11, 35, 23, 77, 60, 75, 71, 15, 91, 43, 4, 54, 60, 35, 84, 84, 35, 17, 8, 75, 14, 70, 81, 42, 82, 36, 33, 46, 51, 90, 50, 12, 15, 21, 30, 16, 46, 42, 68, 41, 31, 23, 1, 87, 94, 80, 41}, 36).expect(new int[]{12, 15, 21, 30, 16, 46, 42})
                .run();
    }

    /**
     * TODO solve with sliding window maximum
     */
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