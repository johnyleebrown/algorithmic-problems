// 787
public class CheapestFlightsWithinKStops
{
	public static class Solution1 
	{
		public static int dp(int n, int[][] flights, int src, int dst, int K) {
			int INF = Integer.MAX_VALUE / 2;
			int[][] dp = new int[K + 2][n];
			for (int i = 0; i < dp.length; i++)
				Arrays.fill(dp[i], INF);
			dp[0][src] = 0;

			// loop K + 1 times to fill the path from src to dst
			for (int i = 1; i < dp.length; i++) {
				dp[i][src] = 0;
				for (int[] flight : flights)
					dp[i][flight[1]] = Math.min(dp[i][flight[1]], 
							dp[i - 1][flight[0]] + flight[2]);
			}

			return dp[K + 1][dst] >= INF / 2 ? -1 : dp[K + 1][dst];
		}

		// space O(n) optimized
		public static int dpOptimized(int n, int[][] flights, int src, 
				int dst, int K) {
			int INF = Integer.MAX_VALUE / 2;
			int[][] dp = new int[2][n];
			Arrays.fill(dp[0], INF);
			Arrays.fill(dp[1], INF);
			dp[0][src] = 0;
			dp[1][src] = 0;

			int x = 0;
			for (int i = 0; i <= K; i++) {
				for (int[] f : flights)
					dp[1 - x][f[1]] = Math.min(dp[1 - x][f[1]], dp[x][f[0]] + f[2]);
				x = 1 - x;
			}

			return dp[(K + 1) % 2][dst] >= INF / 2 ? -1 : dp[(K + 1) % 2][dst];
		}
	}
}

