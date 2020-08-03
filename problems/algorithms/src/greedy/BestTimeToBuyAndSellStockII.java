package greedy;

/**
 * 122
 */
public class BestTimeToBuyAndSellStockII {
	/**
	 * Sell stock at the local peak - greedy choice, if after is a higher value - we will correct
	 * our purchase, if not - we are not loosing anything because we sold the stock.
	 */
	public static class Solution {
		public int maxProfit(int[] ar) {
			int ans = 0;
			for (int i = 1; i < ar.length; i++) {
				if (ar[i - 1] < ar[i]) {
					ans += ar[i] - ar[i - 1];
				}
			}
			return ans;
		}
	}

	/**
	 * DP.
	 */
	public static class Solution2 {
		Integer[] dp;

		public int maxProfit(int[] ar) {
			dp = new Integer[ar.length];
			return f(0, ar);
		}

		public int f(int start, int[] ar) {
			if (start >= ar.length) return 0;
			if (dp[start] != null) return dp[start];
			int ans = 0;
			for (int i = start; i < ar.length - 1; i++) {
				for (int j = i + 1; j < ar.length; j++) {
					if (ar[j] > ar[i]) {
						ans = Math.max(ans, ar[j] - ar[i] + f(j + 1, ar));
					}
				}
			}
			dp[start] = ans;
			return ans;
		}
	}
}
