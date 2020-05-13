package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * MinimumSizeDisjointSubarraysSum
 *
 * ======
 *
 * Task.
 *
 * Find two disjoint subarrays whose sum equals to K and the sum of length of
 * both the arrays are minimum.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class MinimumSizeDisjointSubarraysSum {
    /**
     * Extend MinimumSizeSubarraySum. Keep the first subarray end index of each
     * length. This way if we will find another subarray, we will check if the
     * index is > then end of first one, if yes, we will record the length.
     */
    public static class Solution {
        public int solve(int[] ar, int k) {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(0, -1);
            int n = ar.length;
            int ans = Integer.MAX_VALUE;
            Map<Integer, Integer> m2 = new HashMap<>(); //len, first interval right edge
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += ar[i];
                if (m.containsKey(sum - k)) {
                    int lastIndex = m.get(sum - k);
                    int len = i - lastIndex;
                    if (m2.containsKey(len)) {
                        int firstSubarrayEnd = m2.get(len);
                        if (firstSubarrayEnd < lastIndex) {
                            ans = Math.min(ans, len);
                        }
                    } else {
                        m2.put(len, i);
                    }
                } else {
                    m.put(sum, i);
                }
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }
}