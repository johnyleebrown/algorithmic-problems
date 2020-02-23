package twoPointers.slidingWindow.max;

/**
 * 424
 *
 * ======
 *
 * Task.
 *
 * Given a string s that consists of only uppercase English letters, you can
 * perform at most k operations on that string. In one operation, you can choose
 * any character of the string and change it to any other uppercase English
 * character. Find the length of the longest sub-string containing all repeating
 * letters you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement
{
	/**
	 * Let's assume we don't have k, then min number of changes is a length of
	 * the entire string - number of times of the maximum occurring character in
	 * the string. We want to find max(end - start + 1) when (end - start + 1) -
	 * maxNumberOfSameChars <= k.
	 */
	class Solution
	{
		public int characterReplacement(String s, int k)
		{
			int l = 0;
			int[] map = new int[256];
			int maxLen = 0;
			int popularCharCount = 0;

			for (int r = 0; r < s.length(); r++)
			{
				// right window side char counter
				map[s.charAt(r)]++;

				// updating popular char count
				popularCharCount = Math.max(popularCharCount, map[s.charAt(r)]);

				// moving left window side if operationsNeededCount is not what we want
				while (countOperationsNeededForWindow(r, l, popularCharCount) > k)
				{
					map[s.charAt(l)]--;
					l++;
				}

				maxLen = Math.max(maxLen, r - l + 1);
			}

			return maxLen;
		}

		private int countOperationsNeededForWindow(int r, int l, int popularCharCount)
		{
			// we take (r-l+1) since we have popular count only for this section
			// we always take into account only current window
			int currentWindowLength = (r - l + 1);

			return currentWindowLength - popularCharCount;
		}
	}
}
