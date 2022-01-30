package dp.lis;

/**
 * 2111
 */
public class MinimumOperationsToMakeTheArrayKIncreasing {

	/**
	 * TLE
	 */
	public static class Solution {

		public int kIncreasing(int[] ar, int k) {
			int n = ar.length, ans = 0;
			if (k == n) return 0;
			int[] dp = new int[n];
			int[] maxes = new int[n];
			for (int i = k; i < n; i++) {
				dp[i] = 1;
				int cnt = i / k + 1, cn = cnt;
				for (int j = i - k; j >= 0; j -= k, cn--) {
					if (dp[j] == 0) dp[j] = 1;
					if (ar[j] <= ar[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
					if (dp[j] == cn) {
						break;
					}
				}
				maxes[i] = Math.max(dp[i], maxes[i - k]);
				if (i >= Math.max(k, n - k)) {
					ans += cnt - maxes[i];
				}
			}
			return ans;
		}
	}
}
