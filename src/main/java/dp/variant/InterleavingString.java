package dp.variant;

import java.util.HashMap;
import java.util.Map;

/**
 * 97
 */
public class InterleavingString {

	/**
	 * SF memo
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
}
