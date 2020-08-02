package twoPointers.slidingWindow.count;

/**
 * 1358
 *
 * ======
 *
 * Task.
 *
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all
 * these characters a, b and c.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
	/**
	 * Another strategy here - record result when good condition, and then move LEFT edge.
	 * How i arrived here - for count i usually use (same as max) - bad condition to move LEFT,
	 * then record ans with "+= r-l+1" - this way we say that all subarrays in [l,r] are good so
	 * we can count them this way.
	 *
	 * Here though, it is easier to come up with good condition - and when it happens - record an
	 * answer, only then - move LEFT.
	 *
	 * Shorting the window when we have good condition. Meanwhile, we update
	 * result with number of substrings we can have with substring from l to r.
	 * n - r means we count all substrings that end at l.
	 */
	private static class Solution {
		public int numberOfSubstrings(String s) {
			int l = 0;
			int n = s.length();
			int res = 0;
			int[] counts = new int[3];

			for (int r = 0; r < n; r++) {
				counts[s.charAt(r) - 'a']++;

				while (counts[0] >= 1 && counts[1] >= 1 && counts[2] >= 1) {
					// n strings containing substring from l to r
					// "[[abc|a]b]c]"
					// we processed abc, then we can count abc with n-r
					res += n - r;

					counts[s.charAt(l) - 'a']--;
					l++;
				}
			}

			return res;
		}
	}
}