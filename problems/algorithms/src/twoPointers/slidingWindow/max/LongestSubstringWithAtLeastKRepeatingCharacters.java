package twoPointers.slidingWindow.max;

/**
 * 395
 *
 * ======
 *
 * Task.
 *
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every
 * character in T appears no less than k times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters
{
	/**
	 * Using sliding window technique for a number(from 1 to 26) of unique characters. Updating result only when ensured
	 * that all chars in the window are repeated k times. In this case not enough variables in a window to shorten it,
	 * because it is unknown whether a bigger window could be an answer too, this is why there needed to be another
	 * variable to link to the window, like number if unique characters in it.
	 */
	static class Solution
	{
		public int longestSubstring(String s, int k)
		{
			if (s == null || s.length() == 0)
			{
				return 0;
			}
			if (k == 0)
			{
				return s.length();
			}

			// different kinds of chars (unique chars)
			int uniqueCharsCount = countUniqueChars(s);
			int result = 0;

			for (int i = 1; i <= uniqueCharsCount; i++)
			{
				result = Math.max(result, getLongestSubstringForNumberOfUniqueChars(s, k, i));
			}

			return result;
		}

		private int countUniqueChars(String s)
		{
			int uniqueCharsCount = 0;
			int[] map = new int[26];

			for (int i = 0; i < s.length(); i++)
			{
				int charIndex = s.charAt(i) - 'a';

				map[charIndex]++;
				if (map[charIndex] == 1)
				{
					uniqueCharsCount++;
				}
			}

			return uniqueCharsCount;
		}

		private int getLongestSubstringForNumberOfUniqueChars(String s, int k, int uniqueCharsCountTarget)
		{
			int l = 0;
			int result = 0;
			int[] map = new int[26];
			int uniqueCharsCount = 0;
			int numOfCharsRepeatedKTimes = 0;

			for (int r = 0; r < s.length(); r++)
			{
				int charIndex = s.charAt(r) - 'a';

				map[s.charAt(r) - 'a']++;

				// became 1 and was 0 => counts as unique
				if (map[charIndex] == 1)
				{
					uniqueCharsCount++;
				}

				if (map[charIndex] == k)
				{
					numOfCharsRepeatedKTimes++;
				}

				while (uniqueCharsCount > uniqueCharsCountTarget)
				{
					charIndex = s.charAt(l) - 'a';

					map[charIndex]--;

					// was 1 and became 0
					if (map[charIndex] == 0)
					{
						uniqueCharsCount--;
					}

					// was k and became k - 1
					if (map[charIndex] == k - 1)
					{
						numOfCharsRepeatedKTimes--;
					}

					l++;
				}

				// all chars in window are repeated k times
				// taking a snapshot of result before stepping on a bad char
				if (numOfCharsRepeatedKTimes == uniqueCharsCount)
				{
					result = Math.max(result, r - l + 1);
				}
			}

			return result;
		}
	}
}
