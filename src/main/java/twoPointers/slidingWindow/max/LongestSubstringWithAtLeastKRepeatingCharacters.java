package twoPointers.slidingWindow.max;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {

	/**
	 * Using sliding window technique for a number(from 1 to 26) of unique
	 * characters. Updating result only when ensured that all chars in the
	 * window are repeated k times. In this case not enough variables in a
	 * window to shorten it, because it is unknown whether a bigger window could
	 * be an answer too, this is why there needed to be another variable to link
	 * to the window, like number if unique characters in it.
	 */
	static class Solution {

		public int longestSubstring(String s, int k) {
			if (s == null || s.length() == 0) {
				return 0;
			}
			if (k == 0) {
				return s.length();
			}

			// different kinds of chars (unique chars)
			int uniqueCharsCount = countUniqueChars(s);
			int result = 0;

			for (int i = 1; i <= uniqueCharsCount; i++) {
				result = Math.max(result,
						getLongestSubstringForNumberOfUniqueChars(s, k, i));
			}

			return result;
		}

		private int countUniqueChars(String s) {
			int uniqueCharsCount = 0;
			int[] map = new int[26];
			// or use set

			for (int i = 0; i < s.length(); i++) {
				int charIndex = s.charAt(i) - 'a';

				map[charIndex]++;
				if (map[charIndex] == 1) {
					uniqueCharsCount++;
				}
			}

			return uniqueCharsCount;
		}

		private int getLongestSubstringForNumberOfUniqueChars(String s, int k,
				int uniqueCharsCountTarget) {
			int l = 0;
			int result = 0;
			int[] map = new int[26];
			int uniqueCharsCount = 0;
			int numOfCharsRepeatedKTimes = 0;

			for (int r = 0; r < s.length(); r++) {
				int charIndex = s.charAt(r) - 'a';
				map[charIndex]++;
				// became 1 and was 0 => counts as unique
				if (map[charIndex] == 1) {
					uniqueCharsCount++;
				}

				if (map[charIndex] == k) {
					numOfCharsRepeatedKTimes++;
				}

				while (uniqueCharsCount > uniqueCharsCountTarget) {
					charIndex = s.charAt(l) - 'a';
					map[charIndex]--;
					// was 1 and became 0
					if (map[charIndex] == 0) {
						uniqueCharsCount--;
					}

					// was k and became k - 1
					if (map[charIndex] == k - 1) {
						numOfCharsRepeatedKTimes--;
					}

					l++;
				}

				// all chars in window are repeated k times
				// taking a snapshot of result before stepping on a bad char
				if (numOfCharsRepeatedKTimes == uniqueCharsCount) {
					result = Math.max(result, r - l + 1);
				}
			}

			return result;
		}
	}

	/**
	 * same just countUniqueLetters is different
	 */
	public static class Solution2 {

		public int longestSubstring(String s, int k) {
			int longestSubstringLength = 0;
			int uniqueLettersCount = countUniqueLetters(s);
			for (int i = 1; i <= uniqueLettersCount; i++) {
				longestSubstringLength = Math.max(longestSubstringLength,
						longestSubstringHelper(s, k, i));
			}
			return longestSubstringLength;
		}

		public int countUniqueLetters(String s) {
			int uniqueLettersMask = 0;
			int uniqueLettersCount = 0;
			for (int i = 0; i < s.length(); i++) {
				int letterIndex = (s.charAt(i) - 'a');
				int mask = (1 << letterIndex);
				if ((uniqueLettersMask & mask) == 0) {
					uniqueLettersCount++;
				}
				uniqueLettersMask |= mask;
			}
			return uniqueLettersCount;
		}

		public int longestSubstringHelper(String s, int k, int uniqueCount) {
			int uniqueLetterCount = 0;
			int goodLetterCount = 0;
			int[] letterCountsArray = new int[26];
			int longestSubstringLength = 0;

			for (int r = 0, l = 0; r < s.length(); r++) {

				int rightCharIndex = s.charAt(r) - 'a';
				letterCountsArray[rightCharIndex]++;
				if (letterCountsArray[rightCharIndex] == 1) {
					uniqueLetterCount++;
				}
				if (letterCountsArray[rightCharIndex] == k) {
					goodLetterCount++;
				}

				while (uniqueLetterCount > uniqueCount) {
					int leftCharIndex = s.charAt(l) - 'a';
					letterCountsArray[leftCharIndex]--;
					if (letterCountsArray[leftCharIndex] == 0) {
						uniqueLetterCount--;
					}
					if (letterCountsArray[leftCharIndex] == k - 1) {
						goodLetterCount--;
					}
					l++;
				}

				if (goodLetterCount == uniqueCount) {
					longestSubstringLength = Math.max(longestSubstringLength, r - l + 1);
				}
			}

			return longestSubstringLength;
		}
	}
}
