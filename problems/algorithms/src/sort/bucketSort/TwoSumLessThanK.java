package sort.bucketSort;

/**
 * 1099
 *
 * ======
 *
 * Task.
 *
 * Given an array A of integers and integer K, return the maximum S such that
 * there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist
 * satisfying this equation, return -1.
 *
 * ======
 *
 * Source: Leetcode
 */
public class TwoSumLessThanK {
    public static class Solution {
        public int twoSumLessThanK(int[] a, int k) {
            int[] ar = new int[1001];
            for (int i : a) ar[i]++;
            int r = Math.min(k, 1000);
            int l = 1;
            int ans = -1;
            while (l < r) {
                if (l + r < k && ar[r] != 0) {
                    if (ar[l] != 0) ans = Math.max(ans, l + r);
                    l++;
                } else {
                    r--;
                }
            }
            return ans;
        }
    }
}