package string.parseString.other;

/**
 * 2138
 */
public class DivideAStringIntoGroupsOfSizeK {

	/**
	 * SF
	 */
	public static class Solution {

		public String[] divideString(String s, int k, char fill) {
			int n = s.length();
			int newLength = (n % k == 0 ? 0 : 1) + n / k;
			String[] ans = new String[newLength];

			StringBuilder sb = new StringBuilder(s);
			for (int i = 0, j = 0; i < s.length(); i += k, j++) {
				ans[j] = sb.substring(i, Math.min(i + k, n));
			}

			if (newLength > 0 && ans[newLength - 1].length() < k) {
				int diff = k - ans[newLength - 1].length();
				StringBuilder sb2 = new StringBuilder();
				while (--diff >= 0) {
					sb2.append(fill);
				}
				ans[newLength - 1] += sb2.toString();
			}
			return ans;
		}
	}
}
