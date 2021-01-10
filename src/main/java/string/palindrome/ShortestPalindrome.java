package string.palindrome;

/**
 * 214
 *
 * ======
 *
 * Given a string s, you can convert it to a palindrome by adding characters in front of it. Find
 * and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 *
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: "dcbabcd"
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of lowercase English letters only.
 *
 * ======
 *
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {
	/**
	 * KMP
	 * Concat string s + "#" + reverse and find s in reverse
	 * KMP helps us to find the biggest prefix which is also a suffix
	 */
	public static class Solution1 {
		public String shortestPalindrome(String s) {
			StringBuilder sb = new StringBuilder(s).reverse();
			String reversedAndOrigin = s + "#" + sb.toString();
			int n = reversedAndOrigin.length();

			int[] p = new int[n + 1];
			p[0] = -1;

			for (int i = 1; i <= n; i++) {
				int k = p[i - 1];
				while (k >= 0) {
					if (reversedAndOrigin.charAt(i - 1) == reversedAndOrigin.charAt(k)) {
						break;
					}
					k = p[k];
				}
				p[i] = k + 1;
			}

			return sb.substring(0, s.length() - p[n]) + s;
		}
	}

	/**
	 * Using just 1 string, no concatenation.
	 */
	public static class Solution2 {
		public String shortestPalindrome(String s) {
			int n = s.length();
			if (s.length() == 0 || checkIfPalindrome(s)) {
				return s;
			}

			int[] p = getPrefixArray(s);

			int k = -1;
			for (int i = 0; i < n; i++) {
				while (k >= 0) {
					if (s.charAt(n - 1 - i) == s.charAt(k)) {
						break;
					}
					k = p[k];
				}
				k++;
			}

			// compile the answer
			StringBuilder sb = new StringBuilder();
			for (int i = s.length() - 1; i >= k; i--) {
				sb.append(s.charAt(i));
			}
			sb.append(s);
			return sb.toString();
		}

		private int[] getPrefixArray(String s) {
			int n = s.length();
			int[] p = new int[n + 1];
			p[0] = -1;

			for (int i = 1; i <= n; i++) {
				int k = p[i - 1];
				while (k >= 0) {
					if (s.charAt(i - 1) == s.charAt(k)) {
						break;
					}
					k = p[k];
				}
				p[i] = k + 1;
			}

			return p;
		}

		private boolean checkIfPalindrome(String s) {
			for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
				if (s.charAt(i) != s.charAt(j)) {
					return false;
				}
			}
			return true;
		}
	}
}