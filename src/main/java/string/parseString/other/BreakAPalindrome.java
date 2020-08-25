package string.parseString.other;

/**
 * 1328
 *
 * ======
 *
 * Task.
 *
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English
 * letter so that the
 * string becomes the lexicographically smallest possible string that isn't a palindrome.
 *
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BreakAPalindrome {
	/**
	 * SF.
	 */
	public static class Solution {
		public String breakPalindrome(String palindrome) {
			char[] s = palindrome.toCharArray();
			int n = s.length;

			for (int i = 0; i < n / 2; i++) {
				if (s[i] != 'a') {
					s[i] = 'a';
					return String.valueOf(s);
				}
			}
			s[n - 1] = 'b'; //if all 'a'
			return n < 2 ? "" : String.valueOf(s);
		}
	}
}