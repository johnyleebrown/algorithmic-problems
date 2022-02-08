package dp.fsm;

/**
 * 309
 */
public class BestTimeToBuyAndSellStockWithCooldown {

	/**
	 * Similar to 926.
	 * Split states onto ones when we want to sell stock and when we want to buy one.
	 * dp[i][0] - if we want to buy on ith day
	 * dp[i][1] - if we want to sell on ith day
	 */
	public static class Solution {

		public int maxProfit(int[] prices) {
			int n = prices.length;
			int[][] dp = new int[n][2];
			int ans = 0;

			// base
			dp[0][0] = -prices[0];
			if (n > 1) {
				dp[1][0] = -Math.min(prices[0], prices[1]);
				dp[1][1] = Math.max(0, prices[1] - prices[0]);
				ans = Math.max(ans, Math.max(dp[1][0], dp[1][1]));
			}

			for (int i = 2; i < n; i++) {
				// wanna buy on ith
				// buy or hold
				dp[i][0] = Math.max(dp[i - 2][1] - prices[i], dp[i - 1][0]);

				// wanna sell
				// sell or hold
				dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);

				ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
			}

			return ans;
		}
	}
}
