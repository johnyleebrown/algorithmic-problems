package twoPointers.slidingWindow.count;

import util.test.Tester;

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
public class NumberOfSubstringsContainingAllThreeCharacters
{
	/**
	 *
	 */
	private static class Solution
	{
		public int numberOfSubstrings(String s)
		{
			int l = 0;
			int n = s.length();
			int[] map = new int[26];
			int uniqueCharsCount = 0;
			int res = 0;

			for (int r = 0; r < n; r++)
			{
				map[s.charAt(r) - 'a']++;
				if (map[s.charAt(r) - 'a'] == 1)
				{
					uniqueCharsCount++;
				}

				while (uniqueCharsCount == 3)
				{
//					res += n - r;

					map[s.charAt(l) - 'a']--;
					if (map[s.charAt(l) - 'a'] == 0)
					{
						uniqueCharsCount--;
					}

					l++;
				}

				res+=l;
			}

			return res;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add("abcaaa").expect(10)
				.add("aaacb").expect(3)
				.add("abc").expect(1)
				.run();
	}
}