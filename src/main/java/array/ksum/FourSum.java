package array.ksum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 18
 *
 * ======
 *
 * Task.
 *
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FourSum {
    /**
     * Using general template.
     */
    public static class Solution {
        private static final List<List<Integer>> ans = new LinkedList<>();

        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            ksum(0, 4, nums, new LinkedList<>(), target);
            return ans;
        }

        private void ksum(int start, int n, int[] nums, List<Integer> others, int cur) {
            if (n == 2) {
                int left = start;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == cur) {
                        addList(others, nums[left], nums[right]);
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < cur) {
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
                }
            } else {
                for (int i = start; i < nums.length - n + 1; i++) {
                    if (i > start && nums[i] == nums[i - 1]) continue;
                    others.add(nums[i]);
                    ksum(i + 1, n - 1, nums, others, cur - nums[i]);
                    others.remove(others.size() - 1);
                }
            }
        }

        private void addList(List<Integer> local, int... nums) {
            ans.add(new LinkedList<>(local));
            for (int i : nums) ans.get(ans.size() - 1).add(i);
        }
    }
}