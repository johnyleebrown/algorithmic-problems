package dp.coin_change;

import java.util.Arrays;

/**
 * 518
 */
public class CoinChange2 {

	/**
	 * Similarly to knapsack problem we use 2d array to store number of combinations for
	 * each coin for
	 * each change amount
	 */
	public static class Solution {

		public int change(int amount, int[] coins) {
			int n = coins.length;
			int[][] dp = new int[amount + 1][n + 1];

			// we can cover 0 amount in 1 combination
			Arrays.fill(dp[0], 1);

			for (int i = 1; i <= amount; i++) {
				for (int j = 1; j <= n; j++) {

					// we can this coin to (cur amount - nominal of cur coin) combination
					if (i > coins[j - 1]) {
						dp[i][j] = dp[i - coins[j - 1]][j] + dp[i][j - 1];
					}
					// haven't used this method before
					else if (coins[j - 1] == i) {
						dp[i][j] = dp[i][j - 1] + 1;
					}
					// cant cover
					else {
						dp[i][j] = dp[i][j - 1];
					}
				}
			}

			return dp[amount][n];
		}
	}
}
