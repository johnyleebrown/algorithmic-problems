package dp.lcs;

import java.util.Arrays;

/**
 * 712
 */
public class MinimumASCIIDeleteSumForTwoStrings {

	/**
	 * We have 4 cases:
	 * if same chars - we take the prev result
	 * else - either we delete both chars, or just one
	 */
	public static class Solution {

		public int minimumDeleteSum(String s1, String s2) {
			int n1 = s1.length(), n2 = s2.length();

			// dp[i][j] - lowest ascii sum for 0..i,0..j
			int[][] dp = new int[n1 + 1][n2 + 1];

			// prefill cases when we have one string and don't have the other
			for (int i = 1; i <= n1; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
			}
			for (int j = 1; j <= n2; j++) {
				dp[0][j] = s2.charAt(j - 1) + dp[0][j - 1];
			}

			// bottom up
			for (int i = 1; i <= n1; i++) {
				for (int j = 1; j <= n2; j++) {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						int val1 = s1.charAt(i - 1) + dp[i - 1][j];
						int val2 = s2.charAt(j - 1) + dp[i][j - 1];
						int val3 = s1.charAt(i - 1) + s2.charAt(j - 1) + dp[i - 1][j - 1];
						dp[i][j] = Math.min(val1, Math.min(val2, val3));
					}
				}
			}

			return dp[n1][n2];
		}
	}
}
