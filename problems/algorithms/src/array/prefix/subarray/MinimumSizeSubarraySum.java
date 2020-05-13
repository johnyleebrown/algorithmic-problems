package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * MinimumSizeSubarraySum
 *
 * ======
 *
 * Task.
 *
 * Find minimum size subarray with sum equals k.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class MinimumSizeSubarraySum {
    public static class Solution implements S {
        public int solve(int target, int[] ar) {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(0, -1);//base case
            int sum = 0;
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < ar.length; i++) {
                sum += ar[i];
                if (m.containsKey(sum - target)) {
                    ans = Math.min(ans, i - m.get(sum - target));
                }
                m.put(sum, i); // put the last value so the dist is always minimum
            }
            return ans;
        }
    }

    /**
     * Brute force
     */
    public static class Solution2 implements S {
        public int solve(int s, int[] ar) {
            int ans = Integer.MAX_VALUE;
            int n = ar.length;
            for (int len = 1; len <= n; len++) {
                for (int i = 0; i < n - len + 1; i++) {
                    int sum = 0;
                    for (int j = i; j < i + len; j++) {
                        sum += ar[j];
                        if (sum == s) {
                            ans = Math.min(ans, len);
                        }
                    }
                }
            }
            return ans;
        }
    }

    interface S {
        int solve(int s, int[] ar);
    }
}