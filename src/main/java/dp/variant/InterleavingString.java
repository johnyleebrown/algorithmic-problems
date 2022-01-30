package dp.variant;

import java.util.HashMap;
import java.util.Map;

/**
 * 97
 */
public class InterleavingString {

	/**
	 * SF memo
	 *
	 * Replace 3 var memo with 2 var memo -> h(i1,i2) since i3=i1+i2
	 */
	public static class Solution {

		Map<Integer, Boolean> m = new HashMap<>();

		public boolean isInterleave(String s1, String s2, String s3) {
			return dfs(0, 0, 0, s1, s2, s3);
		}

		boolean dfs(int i1, int i2, int i3, String s1, String s2, String s3) {
			int h = hash(i1, i2, i3);
			if (m.get(h) != null) return m.get(h);

			if (i3 == s3.length() && i1 == s1.length() && i2 == s2.length()) return true;
			if (i3 == s3.length()) return false;
			if (i1 == s1.length() && i2 == s2.length()) return false;

			boolean ans = false;
			if (i1 < s1.length() && s3.charAt(i3) == s1.charAt(i1)) {
				ans = dfs(i1 + 1, i2, i3 + 1, s1, s2, s3);
			}
			if (i2 < s2.length() && s3.charAt(i3) == s2.charAt(i2)) {
				ans |= dfs(i1, i2 + 1, i3 + 1, s1, s2, s3);
			}

			m.put(h, ans);
			return ans;
		}

		int hash(int i1, int i2, int i3) {
			return i1 + 1 + 100 * (i2 + 1) + 100 * 200 * (i3 + 1);
		}
	}

	/**
	 * Bottom Up
	 */
	public static class Solution2 {

		public boolean isInterleave(String s1, String s2, String s3) {

			int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
			if (s3.length() != s1.length() + s2.length())
				return false;
			boolean[][] dp = new boolean[n1 + 1][n2 + 1];
			dp[0][0] = true;
			for (int i1 = 1; i1 <= n1; i1++) {
				if (ch(s1, i1 - 1) == ch(s3, i1 - 1)) {
					dp[i1][0] = dp[i1 - 1][0];
				}
			}
			for (int i2 = 1; i2 <= n2; i2++) {
				if (ch(s2, i2 - 1) == ch(s3, i2 - 1)) {
					dp[0][i2] = dp[0][i2 - 1];
				}
			}
			for (int i1 = 1; i1 <= n1; i1++) {
				for (int i2 = 1; i2 <= n2; i2++) {
					int i3 = i1 + i2 - 1;
					boolean ans = false;
					if (ch(s1, i1 - 1) == ch(s3, i3)) {
						ans = dp[i1 - 1][i2];
					}
					if (ch(s2, i2 - 1) == ch(s3, i3)) {
						ans |= dp[i1][i2 - 1];
					}
					dp[i1][i2] = ans;
				}
			}

			return dp[n1][n2];
		}

		char ch(String s, int i) {
			return s.charAt(i);
		}
	}
}
