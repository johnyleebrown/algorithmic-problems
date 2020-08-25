package a0.array.regular;

/**
 * 643
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
 * And you need to output the maximum average value.
 */
public class MaximumAverageSubarrayI {

    // O(n), O(1)
    class Solution {
        public double findMaxAverage(int[] nums, int k) {
            double s = 0;
            for (int i = 0; i < k; i++) s += nums[i];
            double max = s;
            for (int i = 1; i + k - 1 < nums.length; i++) {
                s += nums[i + k - 1] - nums[i - 1];
                max = Math.max(max, s);
            }
            return max / k;
        }
    }
}
