package string.palindrome;

/**
 * 1400
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
			int[] count = new int[26]; for (int i = 0; i < s.length(); ++i) {
				count[s.charAt(i) - 'a']++;
			} int oddNum = 0; for (int i = 0; i < 26; ++i) {
				oddNum += count[i] % 2;
			} return oddNum <= k && k <= s.length();
		}
	}
}