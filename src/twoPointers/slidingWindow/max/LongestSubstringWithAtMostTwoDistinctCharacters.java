package twoPointers.slidingWindow.max;

/**
 * 159
 */
class LongestSubstringWithAtMostTwoDistinctCharacters
{
	static class Solution
	{
		public int lengthOfLongestSubstringTwoDistinct(String s)
		{
			int l = 0;
			int result = Integer.MIN_VALUE;
			int twoDistinctCharCounter = 0;
			int[] map = new int[256];

			for (int r = 0; r < s.length(); r++)
			{
				map[s.charAt(r)]++;
				if (map[s.charAt(r)] == 1)
				{
					twoDistinctCharCounter++;
				}

				while (twoDistinctCharCounter > 2)
				{
					map[s.charAt(l)]--;
					if (map[s.charAt(l)]-- == 0)
					{
						twoDistinctCharCounter--;
					}

					l++;
				}

				result = Math.max(result, r - l + 1);
			}

			return result == Integer.MIN_VALUE ? 0 : result;
		}
	}
}
