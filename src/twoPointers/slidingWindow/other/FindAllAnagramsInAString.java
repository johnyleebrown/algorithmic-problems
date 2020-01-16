package twoPointers.slidingWindow.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 438
 *
 * ======
 *
 * Task.
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s. Strings consists of
 * lowercase English letters only and the length of both strings s and p will not be larger than 20,100. The order of
 * output does not matter.
 *
 * ======
 *
 * Similar: 567
 */
public class FindAllAnagramsInAString
{
	/**
	 * Using the sliding window template.
	 */
	static class Solution
	{
		public List<Integer> findAnagrams(String s, String p)
		{
			List<Integer> result = new ArrayList<>();
			if (s == null || s.isEmpty())
			{
				return result;
			}

			// getting a map of chars from p
			int[] map = new int[26];
			for (int i = 0; i < p.length(); i++)
			{
				map[p.charAt(i) - 'a']++;
			}

			int matchedLettersCount = 0;

			// sliding window template
			int l = 0;
			for (int r = 0; r < s.length(); r++)
			{
				map[s.charAt(r) - 'a']--;
				if (map[s.charAt(r) - 'a'] >= 0)
				{
					matchedLettersCount++;
				}

				// when ensured all the chars from p are in the window
				while (matchedLettersCount == p.length())
				{
					if (r - l + 1 == p.length())
					{
						result.add(l);
					}

					map[s.charAt(l) - 'a']++;
					if (map[s.charAt(l) - 'a'] >= 1)
					{
						matchedLettersCount--;
					}

					l++;
				}
			}

			return result;
		}
	}

	/**
	 * Since it is needed to have all the letters from p string, fixed window size is used to add and remove counts on
	 * the chars inside the window. If the count is right, the left pointer is added to the result.
	 */
	class Solution2
	{
		public List<Integer> findAnagrams(String s, String p)
		{
			List<Integer> result = new ArrayList<>();
			if (s == null || s.isEmpty() || p.length() > s.length())
			{
				return result;
			}

			// getting a map of chars from p
			int[] map = new int[26];
			for (int i = 0; i < p.length(); i++)
			{
				map[p.charAt(i) - 'a']++;
			}

			int matchedLettersCount = 0;

			// moving right pointer to the correct position, which is the length of p string
			int r = 0;
			for (; r < p.length() - 1; r++)
			{
				map[s.charAt(r) - 'a']--;
				if (map[s.charAt(r) - 'a'] >= 0)
				{
					matchedLettersCount++;
				}
			}

			// updating counter while moving left and right pointers in sync
			int l = 0;
			for (; r < s.length(); r++)
			{
				map[s.charAt(r) - 'a']--;
				if (map[s.charAt(r) - 'a'] >= 0)
				{
					matchedLettersCount++;
				}

				if (matchedLettersCount == p.length())
				{
					result.add(l);
				}

				map[s.charAt(l) - 'a']++;
				if (map[s.charAt(l) - 'a'] >= 1)
				{
					matchedLettersCount--;
				}

				l++;
			}

			return result;
		}
	}
}
