package a0.array.regular;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 697
 Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 */
public class DegreeOfAnArray {
    // O(n), O(n)
    class Solution {
        public int findShortestSubArray(int[] nums) {
            Map<Integer, Integer> left = new HashMap(), right = new HashMap(), count = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                int x = nums[i];
                // inserting first found values
                if (left.get(x) == null) left.put(x, i);
                // last found values will replace first ones
                right.put(x, i);
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
            int ans = nums.length;
            int degree = Collections.max(count.values());
            for (int x : count.keySet())
                if (count.get(x) == degree) ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            return ans;
        }
    }
}
