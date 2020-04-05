package dp.fullSum;

import java.util.Arrays;

/**
 * 64
 */
public class MinimumPathSum
{
	/**
	 * dp[][]=min sum from 0,0 to i,j
	 *
	 * how ends=take this cell or not
	 */
	class Solution
	{
		private int[][] dp;

		public int minPathSum(int[][] a)
		{
			int n = a.length;
			int m = a[0].length;
			dp = new int[n + 1][m + 1];
			for (int i = 0; i <= n; i++)
			{
				Arrays.fill(dp[i], -1);
			}
			return dfs(n, m, a);
		}

		private int dfs(int i, int j, int[][] a)
		{
			if (i == 1 && j == 1)
				return a[i - 1][j - 1];
			if (i == 1)
				return dfs(i, j - 1, a) + a[i - 1][j - 1];
			if (j == 1)
				return dfs(i - 1, j, a) + a[i - 1][j - 1];
			else if (dp[i][j] != -1)
				return dp[i][j];
			dp[i][j] = Math.min(dfs(i - 1, j, a), dfs(i, j - 1, a)) + a[i - 1][j - 1];
			return dp[i][j];
		}
	}
}
