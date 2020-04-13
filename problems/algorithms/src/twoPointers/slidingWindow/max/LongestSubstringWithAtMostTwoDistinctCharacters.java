package twoPointers.slidingWindow.max;

/**
 * 159
 *
 * =====
 *
 * Task.
 *
 * Given a string, find the length of the longest substring T that contains at
 * most 2 distinct characters.
 *
 * =====
 *
 * Tests.
 *
 * "ececcbb", 5; "ececececec", 10; "", 0; "ec", 2;
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
	public static class Solution {
		public int lengthOfLongestSubstringTwoDistinct(String s) {
			int l = 0;
			int result = 0;
			int twoDistinctCharCounter = 0;
			int[] map = new int[256];

			for (int r = 0; r < s.length(); r++) {
				map[s.charAt(r)]++;
				if (map[s.charAt(r)] == 1) {
					twoDistinctCharCounter++;
				}

				while (twoDistinctCharCounter > 2) {
					map[s.charAt(l)]--;
					if (map[s.charAt(l)]-- == 0) {
						twoDistinctCharCounter--;
					}

					l++;
				}

				result = Math.max(result, r - l + 1);
			}

			return result;
		}
	}
}