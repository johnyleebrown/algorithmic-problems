package array.sum;

import java.util.Arrays;

/**
 * 16
 *
 * ======
 *
 * Task.
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ThreeSumClosest {
    /**
     * Same as {@link ThreeSum} with light mods.
     */
    public static class Solution {
        public int threeSumClosest(int[] nums, int t) {
            Arrays.sort(nums);
            int n = nums.length;
            int ans = 0;
            int sum = Integer.MAX_VALUE;
            for (int i = 0; i < n - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                int left = i + 1;
                int right = n - 1;
                while (left < right) {
                    int s = nums[i] + nums[left] + nums[right];
                    if (s <= t) {
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                    } else {
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    }
                    if (Math.abs(t - s) < sum) {
                        sum = Math.abs(t - s);
                        ans = s;
                    }
                }
            }
            return ans;
        }
    }
}