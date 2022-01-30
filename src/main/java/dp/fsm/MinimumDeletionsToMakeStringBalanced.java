package dp.fsm;

/**
 * 1653
 */
public class MinimumDeletionsToMakeStringBalanced {

	/**
	 * $INSERT_EXPLANATION
	 */
	public static class Solution {

		public int minimumDeletions(String s) {
			int n = s.length();
			// min deletions in 0..i so string ends with a(0) or b(1)
			int[][] dp = new int[n + 1][2];

			for (int i = 1; i <= n; i++) {
				// at a
				if (s.charAt(i - 1) == 'a') {
					// end with a
					dp[i][0] = dp[i - 1][0];
					// ends with b
					dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + 1;
				}
				// at b
				else {
					// want to end with a
					dp[i][0] = dp[i - 1][0] + 1;
					// ends with b
					dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]);
				}
			}

			return Math.min(dp[n][0], dp[n][1]);
		}
	}
}
