package dp.lis;

import java.util.Arrays;

/**
 * 646
 */
public class MaximumLengthOfPairChain {

	/**
	 * lis
	 *
	 * sort ASC by ends because we want to create an increasing chain
	 */
	public static class Solution {

		public int findLongestChain(int[][] pairs) {
			int n = pairs.length, ans = 1;
			int[] dp = new int[n];
			Arrays.fill(dp, 1);
			Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
			for (int i = 1; i < n; i++) {
				for (int j = i - 1; j >= 0; j--) {
					if (pairs[j][1] < pairs[i][0]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
						ans = Math.max(ans, dp[i]);
					}
				}
			}
			return ans;
		}
	}
}
