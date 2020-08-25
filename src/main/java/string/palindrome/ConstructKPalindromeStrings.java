package string.palindrome;

/**
 * 1400
 *
 * ======
 *
 * Task.
 *
 * Given a string s and an integer k. You should construct k non-empty
 * palindrome strings using all the characters in s.
 *
 * Return True if you can use all the characters in s to construct k palindrome
 * strings or False otherwise.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ConstructKPalindromeStrings {
	/**
	 * Count unique chars, count odd and even chars. The idea is to count the lo
	 * and hi for possible k. It turns out that hi is the same for all cases -
	 * it is n. If the number of pairs is the same as num of loners - we can
	 * merge all of them together - the min for k will be number of pairs. If
	 * not - 2 cases - [pp](c)(c)(c) => min = num of loners - or -
	 * [pp][pp][pp](c) => {pppp} {ppc} or {pppcppp} => min = c.
	 */
	public static class Solution {
		public boolean canConstruct(String s, int k) {
			if (s.length() < k) {
				return false;
			}
			if (s.length() == k) {
				return true;
			}
			int n = s.length();
			int[] ch = new int[26];
			for (int i = 0; i < n; i++) {
				ch[s.charAt(i) - 'a']++;
			}
			int c = 0;
			for (int i = 0; i < 26; i++) {
				if (ch[i] % 2 != 0) {
					c++;
				}
			}
			int pp = (n - c) / 2;
			return c == pp ? k >= pp : k >= c;
		}
	}
}