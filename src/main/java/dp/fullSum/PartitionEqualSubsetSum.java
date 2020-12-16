package dp.fullSum;

import java.util.Arrays;

/**
 * 416
 */
public class PartitionEqualSubsetSum {
	/**
	 * Either we add i or not.
	 */
	public static class Solution {
		public boolean canPartition(int[] ar) {
			int sum = 0;
			int max = ar[0];
			for (int i : ar) {
				max = Math.max(max, i);
				sum += i;
			}
			if (sum % 2 == 1) return false;
			int target = sum / 2;
			if (max > target) return false;
			Arrays.sort(ar); // we go from right to faster gain sum
			return dfs(ar.length - 1, 0, target, ar);
		}

		private boolean dfs(int i, int sum, int target, int[] ar) {
			if (i < 0 || sum > target) return false;
			if (sum == target) return true;
			return dfs(i - 1, sum + ar[i], target, ar)
			       || dfs(i - 1, sum, target, ar);
		}
	}


	public static class Solution2 {
		public boolean canPartition(int[] nums) {
			int sum = Arrays.stream(nums).sum();
			if ((sum & 1) == 1) return false;
			sum /= 2;
			for (int num : nums) if (num > sum) return false;
			boolean[][] dp = new boolean[nums.length][sum + 1];
			for (int i = 0; i < nums.length; i++) dp[i][0] = false;
			for (int i = 0; i <= sum; i++) dp[0][i] = nums[0] == i;
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


    /*
    [1,2,3,5,6,3]
    subproblem - does some subset's sum from 0 to i equals to x?
    2 parameters here - i and x - so 2d array for cache [i][sum] - [n][totalSum]
    recurrence relation=dfs(nums,i+1,sum-nums[i],dp)||dfs(nums,i+1,sum,dp);
    */
	}
}
