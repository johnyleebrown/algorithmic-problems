package dp.other;

/**
 * 813
 */
public class LargestSumOfAverages {
	/**
	 * dp(i, k) = max(average(i, N), max_{j > i}(average(i, j) + dp(j, k-1))).
	 */
	public static class Solution {
		public double largestSumOfAverages(int[] A, int K) {
			int N = A.length;
			double[][] dp = new double[N + 1][N + 1];
			double cur = 0;

			for (int i = 0; i < N; ++i) {
				cur += A[i];
				dp[i + 1][1] = cur / (i + 1);
			}

			return dfs(N, K, A, dp);
		}

		public double dfs(int N, int K, int[] A, double[][] dp) {
			if (dp[N][K] > 0) {
                return dp[N][K];
            }
			double cur = 0;

			for (int i = N - 1; i > 0; i--) {
				cur += A[i];
				dp[N][K] = Math.max(dp[N][K], dfs(i, K - 1, A, dp) + cur / (N - i));
			}

			return dp[N][K];
		}
	}
}
