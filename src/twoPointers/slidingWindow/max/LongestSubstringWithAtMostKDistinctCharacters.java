package twoPointers.slidingWindow.max;

/**
 * 340
 */
class LongestSubstringWithAtMostKDistinctCharacters
{
	static class Solution
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
}
