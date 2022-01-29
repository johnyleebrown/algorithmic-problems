package dp.lis;

import java.util.Arrays;

/**
 * 300
 */
public class LongestIncreasingSubsequence {
	/**
	 * O(n^2).
	 *
	 * At each i go back to find max increasing sequence.
	 */
	public static class Solution {

		public int lengthOfLIS(int[] nums) {
			int ans = 1;
			int[] dp = new int[nums.length];
			Arrays.fill(dp, 1);
			for (int i = 1; i < nums.length; i++) {
				for (int j = i - 1; j >= 0; j--) {
					if (nums[j] < nums[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
						ans = Math.max(ans, dp[i]);
					}
				}
			}
			return ans;
		}
	}
}
