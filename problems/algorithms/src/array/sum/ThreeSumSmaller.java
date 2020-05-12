package array.sum;

import java.util.Arrays;

/**
 * 259
 *
 * ======
 *
 * Task.
 *
 * Given an array of n integers nums and a target, find the number of index
 * triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] +
 * nums[j] + nums[k] < target.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ThreeSumSmaller {
    /**
     * If a + b < t,
     * we can guarantee that all pairs with a and numbers < b are < t:
     * [..(a)....(b)..] [..(a)...(.)b..] .. [..(a)(.)...b..]
     */
    public static class Solution {
        public int threeSumSmaller(int[] nums, int t) {
            Arrays.sort(nums);
            int n = nums.length;
            int ans = 0;
            for (int i = 0; i < n - 2; i++) {
                int l = i + 1;
                int r = n - 1;
                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum < t) {
                        ans += r - l;
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return ans;
        }
    }
}