package string;

import java.util.Arrays;

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
	 * @formatter:off
     * $INSERT_EXPLANATION.
     * @formatter:on
	 */
	public static class Solution {

		public int[] getPrefixArray(String pattern, String text) {
			int[] p = new int[pattern.length() + 1]; // prefix array
			p[0] = -1; // default value

			for (int i = 1; i <= pattern.length(); i++) {
				int k = p[i - 1];
				while (k >= 0) {
					if (pattern.charAt(i - 1) == text.charAt(k)) {
						break;
					}
					k = p[k];
				}
				p[i] = k + 1;
			}

			return p;
		}

		public String shortestPalindrome(String s) {
			String reversed = new StringBuilder(s).reverse().toString();
			System.out.println(Arrays.toString(getPrefixArray(reversed, s)));
			return null;
		}
	}
}