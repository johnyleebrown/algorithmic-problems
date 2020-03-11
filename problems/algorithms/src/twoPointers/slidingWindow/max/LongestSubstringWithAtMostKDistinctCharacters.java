package twoPointers.slidingWindow.max;

import util.tester.Tester;

/**
 * 340
 */
public class LongestSubstringWithAtMostKDistinctCharacters
{
	public class Solution
	{
		public int lengthOfLongestSubstringKDistinct(String s, int k)
		{
			int l = 0;
			int maxLength = Integer.MIN_VALUE;
			int counter = 0;
			int[] map = new int[256];

			for (int r = 0; r < s.length(); r++)
			{
				map[s.charAt(r++)]++;
				if (map[s.charAt(r)] == 1)
				{
					counter++;
				}

				while (counter > k)
				{
					map[s.charAt(l)]--;
					if (map[s.charAt(l)] == 0)
					{
						counter--;
					}

					l++;
				}

				maxLength = Math.max(maxLength, r - l);
			}

			return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
		}
	}

	public static class SolutionTest
	{
		public int lengthOfLongestSubstringKDistinct(String s, int k)
		{
			int res = 0;
			int l = 0;
			int n = s.length();
			int[] map = new int[256];
			int c = 0;

			for (int r = 0; r < n; r++)
			{
				map[s.charAt(r)]++;
				if (map[s.charAt(r)] == 1)
				{
					c++;
				}

				// good c: <= k
				while (c > k) // bad condition
				{
					map[s.charAt(l)]--;
					if (map[s.charAt(l)] == 0)
					{
						c--;
					}

					l++;
				}

				res = Math.max(res, r - l + 1);
			}

			return res;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new SolutionTest())
				.add("eceba", 2).expect(3)
				.add("", 0).expect(0)
				.add("bacc", 2).expect(3)
				.add("aa", 1).expect(2)
				.add("a", 2).expect(1)
				.add("aakaaaaaa", 2).expect(9)
				.run();
	}
}
