package twoPointers.slidingWindow.max;

/**
 * 340
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
	/**
	 * Find max - bad condition = if number of distinct chars > k. To help us count distinct
	 * chars is a count array, if count becomes 1, it means this char wasnt used before, so
	 * distinct++, if becomes 0 -> distinct--.
	 */
	public static class Solution {
		public int lengthOfLongestSubstringKDistinct(String s, int k) {

			int l = 0;
			int n = s.length();
			int[] counts = new int[256];
			int distinctChats = 0;
			int ans = 0;

			for (int r = 0; r < n; r++) {

				// add right edge char to sliding window
				counts[s.charAt(r)]++;
				if (counts[s.charAt(r)] == 1) {
					distinctChats++;
				}

				// bad condition
				while (distinctChats > k) {

					// remove left edge char from sliding window
					counts[s.charAt(l)]--;
					if (counts[s.charAt(l)] == 0) {
						distinctChats--;
					}

					l++;
				}

				ans = Math.max(ans, r - l + 1);
			}

			return ans;
		}
	}
}
