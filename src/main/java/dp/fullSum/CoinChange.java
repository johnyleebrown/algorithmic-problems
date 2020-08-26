package dp.fullSum;

import java.util.Arrays;

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

	/**
	 * Iterative.
	 */
	public static class Solution2 {
		public int coinChange(int[] coins, int s) {
			int[] dp = new int[s + 1];
			Arrays.fill(dp, Integer.MAX_VALUE);
			//if we have sum 0, we need 0 coins to get it
			dp[0] = 0;
			for (int i = 1; i <= s; i++) {
				for (int k : coins) {
					//if we don't have state dp[i-ar[j]] (curSum-curCoin) then we cant make curSum
					if (k <= i && dp[i - k] != Integer.MAX_VALUE) {
						dp[i] = Math.min(1 + dp[i - k], dp[i]);
					}
				}
			}
			return dp[s] == Integer.MAX_VALUE ? -1 : dp[s];
		}
	}
}
