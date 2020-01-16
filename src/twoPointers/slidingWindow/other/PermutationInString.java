package twoPointers.slidingWindow.other;

/**
 * 567
 *
 * ======
 *
 * Task.
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
 * one of the first string's permutations is the substring of the second string.
 *
 * ======
 *
 * Similar: 438
 */
public class PermutationInString
{
	static class Solution
	{
		public boolean checkInclusion(String s1, String s2)
		{
			if (s1.length() > s2.length())
			{
				return false;
			}

			int[] map = new int[26];
			for (int i = 0; i < s1.length(); i++)
			{
				map[s1.charAt(i) - 'a']++;
			}

			int matchedLettersCount = 0;
			int l = 0;

			for (int r = 0; r < s2.length(); r++)
			{
				map[s2.charAt(r) - 'a']--;
				if (map[s2.charAt(r) - 'a'] >= 0)
				{
					matchedLettersCount++;
				}

				while (matchedLettersCount == s1.length())
				{
					if (r - l + 1 == s1.length())
					{
						return true;
					}

					map[s2.charAt(l) - 'a']++;
					if (map[s2.charAt(l) - 'a'] >= 1)
					{
						matchedLettersCount--;
					}

					l++;
				}
			}

			return false;
		}
	}
}
