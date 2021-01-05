package dp.fullSum;

import java.util.Arrays;

/**
 * 416
 *
 * ======
 *
 * Given a non-empty array nums containing only positive integers, find if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * ======
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
	/*
	Subproblem - does some subset's sum from 0 to i equals to x?
    2 parameters here - i and x - so 2d array for cache [i][sum] - [n][totalSum]
    Recurrence relation = dfs(nums,i+1,sum-nums[i],dp)||dfs(nums,i+1,sum,dp)
    Either we add number at i or not
	 */

	/**
	 * Top-down
	 */
	public static class Solution {
		public boolean canPartition(int[] nums) {
			int sum = Arrays.stream(nums).sum();

			// edge case
			if ((sum & 1) == 1) {
				return false;
			}

			sum /= 2;

			// edge case
			for (int num : nums) {
				if (num > sum) {
					return false;
				}
			}

			return dfs(nums, 0, sum, new Boolean[nums.length][sum + 1]);
		}

		private boolean dfs(int[] nums, int i, int sum, Boolean[][] dp) {
			if (sum < 0) {
				return false;
			}
			if (i == nums.length) {
				return false;
			}
			if (nums[i] == sum) {
				return true;
			}
			if (dp[i][sum] != null) {
				return dp[i][sum];
			}
			boolean ans = dfs(nums, i + 1, sum - nums[i], dp) || dfs(nums, i + 1, sum, dp);
			dp[i][sum] = ans;
			return ans;
		}
	}

	/**
	 * Bottom-up
	 */
	public static class Solution2 {
		public boolean canPartition(int[] nums) {
			int sum = Arrays.stream(nums).sum();

			// edge case
			if ((sum & 1) == 1) {
				return false;
			}

			sum /= 2;

			// edge case
			for (int num : nums) {
				if (num > sum) {
					return false;
				}
			}

			boolean[][] dp = new boolean[nums.length][sum + 1];

			// base
			for (int i = 0; i <= sum; i++) {
				dp[0][i] = nums[0] == i;
			}

			for (int i = 1; i < nums.length; i++) {
				for (int j = 1; j <= sum; j++) {
					if (j >= nums[i]) {
						dp[i][j] = dp[i - 1][j - nums[i]] || dp[i - 1][j];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

			return dp[nums.length - 1][sum];
		}
	}


}