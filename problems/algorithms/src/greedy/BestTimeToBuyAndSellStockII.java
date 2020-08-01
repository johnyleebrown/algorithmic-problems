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
}
