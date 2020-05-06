package array.prefix.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * SmallestIntervalOfBoxes
 *
 * ======
 *
 * Task.
 *
 * Given a sequence of boxes, each with some pens in it and a number X( > 0),
 * choose two non-overlapping intervals of boxes such that:
 *
 * For each interval, the total number of pens in it is exactly X.
 * The total number of boxes chosen is as small as possible.
 *
 * Output the total number of boxes chosen.
 *
 * Find size of smallest interval possible where sum of interval = X
 *
 * ======
 *
 * Source: Leetcode
 */
public class SmallestIntervalOfBoxes {
    /**
     * @formatter:off
     * $INSERT_EXPLANATION.
     * @formatter:on
     */
    public static class Solution {
        public int solve(int[] ar, int x) {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(0, 1);
            int sum = 0;
            for (int i : ar) {
                sum += i;
                if (m.containsKey(sum - x)) {

                }
                m.put(sum, m.getOrDefault(sum, 0) + 1);
            }
            return -1;
        }
    }
}