package dp.fullSum;

/**
 * 322
 */
public class CoinChange {
	/**
	 * 1. dp[i] - smallest n of c for amount i
	 * 2. with what ends with - either we take a count + 1 or we don't
	 */
	public static class Solution {
		public int coinChange(int[] ar, int x) {
			return gen(x, ar, new int[x + 1]);
		}

		int gen(int sum, int[] ar, int[] dp) {
			if (sum < 0) {
				return -1;
			}
			if (sum == 0) {
				return 0;
			}
			if (dp[sum] != 0) {
				return dp[sum];
			}
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < ar.length; i++) {
				int ret = gen(sum - ar[i], ar, dp);
				if (ret != -1) {
					ans = Math.min(ans, ret + 1);
				}
			}
			if (ans == Integer.MAX_VALUE) {
				ans = -1;
			}
			dp[sum] = ans;
			return ans;
		}
	}
}
