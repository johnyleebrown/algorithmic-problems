package dp.linear;

/**
 * 122
 */
public class BestTimeToBuyAndSellStockII {
	/**
	 * Just sell at the closest peak.
	 */
	public static class Solution {
		public int maxProfit(int[] prices) {
			int maxprofit = 0;
			for (int i = 1; i < prices.length; i++) {
				if (prices[i] > prices[i - 1]) {
					maxprofit += prices[i] - prices[i - 1];
				}
			}
			return maxprofit;
		}
	}
}
