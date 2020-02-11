package twoPointers.slidingWindow.min;

/**
 * 76
 *
 * ======
 *
 * Task.
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 */
public class MinimumWindowSubstring
{
	/**
	 * Classic sliding window.
	 */
	static class Solution
	{
		public String minWindow(String s, String t)
		{
			if (s == null || s.length() == 0)
			{
				return "";
			}
			if (t == null || t.length() == 0)
			{
				return s;
			}

			int[] tCharsMap = new int[256];
			for (int i = 0; i < t.length(); i++)
			{
				tCharsMap[t.charAt(i)]++;
			}

			int l = 0;
			int allCharsFromTInWindowCount = 0;
			int minLeftPointer = 0;
			int minLength = Integer.MAX_VALUE;

			for (int r = 0; r < s.length(); r++)
			{
				tCharsMap[s.charAt(r)]--;
				if (tCharsMap[s.charAt(r)] >= 0)
				{
					allCharsFromTInWindowCount++;
				}

				// when we covered all chars from t string
				while (allCharsFromTInWindowCount == t.length())
				{
					tCharsMap[s.charAt(l)]++;
					if (tCharsMap[s.charAt(l)] >= 1)
					{
						allCharsFromTInWindowCount--;
					}

					l++;
				}

				// if l was changed
				if (l > 0)
				{
					if (r - l < minLength)
					{
						minLeftPointer = l;
						minLength = r - l;
					}
				}
			}

			return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeftPointer - 1, minLeftPointer + minLength + 1);
		}
	}
}
