package dp.stateMachine;

import java.util.Arrays;

/**
 * 1223
 *
 * ======
 *
 * Task.
 *
 * A die simulator generates a random number from 1 to 6 for each roll. You
 * introduced a constraint to the generator such that it cannot roll the number
 * i more than rollMax[i] (1-indexed) consecutive times.
 *
 * Given an array of integers rollMax and an integer n, return the number of
 * distinct sequences that can be obtained with exact n rolls.
 *
 * Two sequences are considered different if at least one element differs from
 * each other. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DiceRollSimulation {
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution {
		public int dieSimulator(int n, int[] a) {
			int[][][] dp = new int[n + 1][6 + 1][15 + 1];
			for (int i = 3; i <= 6; i++) Arrays.fill(dp[2][i], 6);
			for (int i = 1; i <= 2; i++) Arrays.fill(dp[2][i], 5);
			int res = 0;
			for (int i = 1; i <= 6; i++) {
				res += dfs(n, i, a[i], dp, a);
			}
			return res;
		}

		/**
		 * k=how many times we can end with itself
		 * curI=cur last digit
		 */
		int dfs(int n, int curI, int k, int[][][] dp, int[] a) {
			if (dp[n][curI][k] != 0) {
				return dp[n][curI][k];
			}
			int res = 0;
			for (int newI = 1; newI <= 6; newI++) {
				if (newI != curI) {
					for (int newK = a[newI]; newK >= 1; newK--) {
						res += dfs(n - 1, newI, newK, dp, a);
					}
				} else {
					for (int newK = k - 1; newK >= 1; newK--) {
						res += dfs(n - 1, newI, newK, dp, a);
					}
				}
			}
			dp[n][curI][k] = res;
			return dp[n][curI][k];
		}
	}
}