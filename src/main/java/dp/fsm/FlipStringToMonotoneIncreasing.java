package dp.fsm;

import java.util.Arrays;

/**
 * 926
 */
public class FlipStringToMonotoneIncreasing {

	/**
	 * State dp[i][j] - min number of changes for string of length i so that string ends
	 * with j.
	 * State machine
	 * We can have 0 at i if i-1 is 0.
	 * so when we count dp[i][0] state we use only dp[i-1][0]
	 * in other words we can get to 0 only from 0 and not from 1 because this string would
	 * be invalid
	 */
	public static class Solution1 {

		public int minFlipsMonoIncr(String s) {
			int dp0 = 0, dp1 = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '0') {
					dp1 = Math.min(dp0, dp1) + 1;
				} else {
					dp1 = Math.min(dp0, dp1);
					dp0 = dp0 + 1;
				}
			}
			return Math.min(dp0, dp1);
		}
	}

	/**
	 * dfs dp
	 */
	public static class Solution2 {

		int[][] dp;

		public int minFlipsMonoIncr(String s) {
			int i = 0;
			while (i < s.length() && isZero(s, i)) {
				i++;
			}
			int j = s.length() - 1;
			while (j >= i && isOne(s, j)) {
				j--;
			}
			if (i == j) return 0;
			dp = new int[2][s.length() + 1];
			Arrays.fill(dp[0], Integer.MAX_VALUE);
			Arrays.fill(dp[1], Integer.MAX_VALUE);
			return dfs(s, i, j + 1, 0);
		}

		int dfs(String s, int i, int end, int flag) {
			if (dp[flag][i] != Integer.MAX_VALUE) {
				return dp[flag][i];
			}
			if (i == end) return 0;

			int ans = 0;
			if (isOne(s, i)) {
				// 1->1, all right should be 1
				int noFlip = dfs(s, i + 1, end, 1);
				if (flag == 1) {
					ans = noFlip;
				} else {
					// 1 -> 0
					int flip = dfs(s, i + 1, end, 0) + 1;
					ans = Math.min(flip, noFlip);
				}
			} else {
				// 0->1
				int flip = dfs(s, i + 1, end, 1) + 1;
				if (flag == 1) {
					ans = flip;
				} else {
					// 0->0
					int noFlip = dfs(s, i + 1, end, 0);
					ans = Math.min(flip, noFlip);
				}
			}

			dp[flag][i] = ans;
			return ans;
		}

		private boolean isOne(String s, int i) {
			return s.charAt(i) == '1';
		}

		private boolean isZero(String s, int i) {
			return s.charAt(i) == '0';
		}
	}
}
