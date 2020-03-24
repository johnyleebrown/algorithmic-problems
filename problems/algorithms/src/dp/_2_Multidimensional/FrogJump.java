package dp._2_Multidimensional;

import util.tester.Tester;

import java.util.Arrays;

/**
 * $INSERT_PROBLEM_NUMBER
 *
 * ======
 *
 * Task.
 *
 * $INSERT_TASK
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FrogJump
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution {
		private static boolean[][] dp;
		private static boolean[][] seen;
		private static int max;
		public boolean canCross(int[] a) {
			int n = a.length;
			max = a[n - 1];
			dp = new boolean[max + 1][max + 1];
			seen = new boolean[max + 1][max + 1];
			Arrays.fill(seen[0], true);
			for (int i = 0; i < n; i++) {
				for (int k = 0; k <= max; k++) {
					dp[a[i]][k] = true;
				}
			}
			dfs(1,1);
			return dp[max][max];
		}
		private boolean dfs(int ind, int k) {
			System.out.println(ind+" "+k);
			if (k < 0||ind > max)
				return false;
			if (seen[ind][k])
				return dp[ind][k];
			if (!dp[ind][k])
				return false;
			// System.out.println("goto "+(ind+k+1));
			boolean ans = dfs(ind + k + 1, k + 1);
			// System.out.println("goto "+(ind+k));
			ans=ans||dfs(ind + k, k);
			if (ind+k-1!=ind)
				ans=ans||dfs(ind + k - 1, k - 1);
			dp[ind][k]=ans;
			seen[ind][k]=true;
			return dp[ind][k];
		}

        public Solution(){}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add().expect()
				.run();
	}
}
