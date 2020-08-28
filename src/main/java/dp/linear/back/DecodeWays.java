package dp.linear.back;

/**
 * 91
 */
public class DecodeWays {
	/**
	 * State transition: dp[i]=dp[i-2]+dp[i-1]
	 */
	public static class Solution {
		public int numDecodings(String s) {
			int n = s.length();
			int[] dp = new int[n + 1];
			dp[0] = 1;
			dp[1] = s.charAt(0) == '0' ? 0 : 1;
			for (int i = 2; i <= n; i++) {
				boolean isNumber = isSubstringANumber(s, i - 2, i - 1);
				if (isNumber) {
					dp[i] += dp[i - 2];
				}
				if (s.charAt(i - 1) != '0') {
					dp[i] += dp[i - 1];
				}
			}
			return dp[n];
		}

		private boolean isSubstringANumber(String s, int i, int j) {
			if (s.charAt(i) == '0') return false;
			return Integer.parseInt(String.valueOf(s.charAt(i)) + s.charAt(j)) < 27;
		}
	}

	/**
	 * Longer version of s1.
	 */
	public static class Solution2 {
		public int numDecodings(String s) {
			int n = s.length();
			if (s.charAt(0) == '0') return 0;
			int[] dp = new int[n + 1];
			dp[0] = 1;
			dp[1] = 1;
			for (int i = 2; i <= n; i++) {
				boolean isNumber = isSubstringANumber(s, i - 2, i - 1);
				if (!isNumber && s.charAt(i - 1) == '0') {
					return 0;
				}
				if (isNumber) {
					if (s.charAt(i - 1) == '0')
						dp[i] = dp[i - 2];
					else
						dp[i] = dp[i - 1] + dp[i - 2];
				} else {
					dp[i] = dp[i - 1];
				}
			}
			return dp[n];
		}

		private boolean isSubstringANumber(String s, int i, int j) {
			if (s.charAt(i) == '0') return false;
			return Integer.parseInt(String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(j))) < 27;
		}
	}
}
