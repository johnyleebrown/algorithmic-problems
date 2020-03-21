package util.contest;

import util.tester.Tester;

/**
 * 1388
 *
 * ======
 *
 * Task.
 *
 * There is a pizza with 3n slices of varying size, you and your friends will
 * take slices of pizza as follows:
 *
 * You will pick any pizza slice. Your friend Alice will pick next slice in anti
 * clockwise direction of your pick. Your friend Bob will pick next slice in
 * clockwise direction of your pick. Repeat until there are no more slices of
 * pizzas.
 *
 * Sizes of Pizza slices is represented by circular array slices in clockwise
 * direction.
 *
 * Return the maximum possible sum of slice sizes which you can have.
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class PizzaWith3nSlices
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	class Solution {
		int[][] dp;
		int n;
		public int maxSizeSlices(int[] a) {
			n = a.length;
			dp = new int[n + 1][n + 1];
			int res = -1;
			boolean[] seen = new boolean[n];
			for (int i = 0; i <= n; i++) {
				dp[i][i] = - 1;
			}
			for (int i = 0; i < n; i++) {
				dp[i][] = dfs(i, i, a, seen);
				res = Math.max(res, );
			}
			return res;
		}
		int dfs(int start, int cur, int[] a, boolean[] seen){
			if (dp[start][cur] != 0) {
				return dp[start][cur];
			}
			for (int i = 0; i < n; i++) {
				if (i == cur) {
					continue;
				}
				if (seen[i]) {
					continue;
				}
				int prev = g(i,-1); int next = g(i,1);
				if (seen[prev] || seen[next]) {
					continue;
				}
				seen[i] = seen[prev] = seen[next] = true;
				dp[start][cur] = Math.max(dp[start][cur], dfs());
				seen[i] = seen[prev] = seen[next] = false;
			}
			return dp[start][cur];
		}
		int g(int a, int b){
			int x = a + b;
			if (x == n + 2) {
				return 1;
			} else if (x == n + 1) {
				return 0;
			} else if (x == -1) {
				return n - 1;
			} else if (x == -2) {
				return n - 2;
			}
			return x;
		}
	}
}
