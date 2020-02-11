package twoPointers.slidingWindow.min;

/**
 * 1234
 *
 * ======
 *
 * Task.
 *
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the
 * original string s balanced.
 *
 * Return 0 if the string is already balanced.
 */
public class ReplaceTheSubstringForBalancedString
{
	static class Solution
	{
		public int balancedString(String s)
		{
			int n = s.length();
			int k = n / 4;
			// bad chars - unique chars that should be replaced
			int badCharsTotalCount = 0;
			int[] map = new int[26];

			for (int i = 0; i < n; i++)
			{
				map[s.charAt(i) - 'A']++;
				if (map[s.charAt(i) - 'A'] == k + 1)
				{
					badCharsTotalCount++;
				}
			}

			if (badCharsTotalCount == 0)
			{
				return 0;
			}

			int result = Integer.MAX_VALUE;
			int badCharsWindowCount = 0;
			int l = 0;

			for (int r = 0; r < n; r++)
			{
				map[s.charAt(r) - 'A']--;
				if (map[s.charAt(r) - 'A'] == k)
				{
					badCharsWindowCount++;
				}

				// reducing window size when we have good conditions
				while (badCharsWindowCount == badCharsTotalCount)
				{
					result = Math.min(result, r - l + 1);

					map[s.charAt(l) - 'A']++;
					if (map[s.charAt(l) - 'A'] == k + 1)
					{
						badCharsWindowCount--;
					}

					l++;
				}
			}

			return result == Integer.MAX_VALUE ? 0 : result;
		}
	}
}
