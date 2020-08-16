package dp.linear.forward;

/**
 * 45
 *
 * ======
 *
 * Task.
 *
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 *
 * Each element in the array represents your maximum jump length at that
 * position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGameII {
	/**
	 * DP forward precalc.
	 */
	public static class Solution {
		public int jump(int[] a) {
			int n = a.length;
			int[] dp = new int[n];
			for (int i = 0; i < n; i++) {
				int newCount = dp[i] + 1;
				for (int j = i + 1; j <= Math.min(i + a[i], n - 1); j++) {
					if (dp[j] != 0) {
						dp[j] = Math.min(dp[j], newCount);
					} else {
						dp[j] = newCount;
					}
				}
			}
			return dp[n - 1];
		}
	}
}
