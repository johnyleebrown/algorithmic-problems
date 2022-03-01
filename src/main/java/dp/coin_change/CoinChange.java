package dp.coin_change;

import java.util.Arrays;

/**
 * 322
 */
public class CoinChange {

	/**
	 * Top Down 1d
	 *
	 * 1. dp[i] - smallest n of c for amount i 2. with what ends with - either we take a
	 * count + 1 or we don't
	 */
	public static class Solution1 {

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
	 * Bottom Up 1d
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

	/**
	 * Bottom Up 2d
	 */
	public static class Solution3 {

		public int coinChange(int[] coins, int amount) {
			int n = coins.length;
			int[][] dp = new int[amount + 1][n + 1];

			// base
			// no solution is represented as Integer.MAX_VALUE
			// first row is zeros - edge case - we need 0 coins to make up value 0
			for (int i = 1; i <= amount; i++) {
				dp[i][0] = Integer.MAX_VALUE;
			}

			// we will be always dragging the best solution with us
			// towards the upper right corner
			for (int i = 1; i <= amount; i++) {
				for (int j = 1; j <= n; j++) {
					int coin = coins[j - 1];

					// we can't take dp[i - coin][j] - there is no solution there
					// either we take the coin or not
					// we can take the coin, then add 1 to the prev best solution w/o this coin
					// this best solution is in our column
					// or we don't take that coin, the best solution for this amount is on the left
					if (i - coin >= 0 && dp[i - coin][j] != Integer.MAX_VALUE) {
						dp[i][j] = Math.min(dp[i][j - 1], dp[i - coin][j] + 1);
					}
					// by default, we take the best solution to date
					else {
						dp[i][j] = dp[i][j - 1];
					}
				}
			}

			return dp[amount][n] == Integer.MAX_VALUE ? -1 : dp[amount][n];
		}
	}
}
