package dp.linear;

/**
 * 121
 */
public class BestTimeToBuyAndSellStock {

	/*
	  Check solutions one by one.
	 */

	/**
	 * Brute force.
	 */
	public static class Solution1 {
		public int maxProfit(int[] ar) {
			int n = ar.length;
			int profit = 0;
			for (int i = 0; i < n - 1; i++) {
				int buy = ar[i];
				for (int j = i + 1; j < n; j++) {
					profit = Math.max(profit, ar[j] - buy);
				}
			}
			return profit;
		}
	}

	/**
	 * Save previous min value for each index.
	 * Then go and compare differences.
	 */
	public static class Solution2 {
		public int maxProfit(int[] ar) {
			int n = ar.length;
			int[] prevMinValues = new int[n];
			int minSoFar = 0;

			for (int i = 0; i < n; i++) {
				if (ar[minSoFar] < ar[i]) {
					prevMinValues[i] = minSoFar;
				} else {
					prevMinValues[i] = i;
				}
				if (ar[minSoFar] > ar[i]) {
					minSoFar = i;
				}
			}

			int res = 0;
			for (int i = 0; i < n; i++) {
				res = Math.max(res, ar[i] - ar[prevMinValues[i]]);
			}

			return res;
		}
	}

	/**
	 * S2 done in one pass.
	 */
	public static class Solution3 {
		public int maxProfit(int[] ar) {
			int n = ar.length;
			int minSoFar = n > 0 ? ar[0] : 0;
			int res = 0;
			for (int i = 0; i < n; i++) {
				if (minSoFar < ar[i]) {
					res = Math.max(res, ar[i] - minSoFar);
				}
				minSoFar = Math.min(ar[i], minSoFar);
			}
			return res;
		}
	}
}
