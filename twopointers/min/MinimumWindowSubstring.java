/**
 * 76
 *
 * Task.
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 *
 * Solution.
 *
 * Classic sliding window.
 */
public class MinimumWindowSubstring
{
	class Solution
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

			// get counts of chars in t string
			Integer[] mapT = new Integer[256];
			for (int i = 0; i < t.length(); i++)
			{
				// the chars that are not in t will be marked as null
				// so we could identify them later
				if (mapT[t.charAt(i)] == null)
				{
					mapT[t.charAt(i)] = 0;
				}

				mapT[t.charAt(i)]++;
			}

			int l = 0;
			int allCharsFromTInWindowCount = 0;
			int minL = 0, minR = 0;
			int minLen = Integer.MAX_VALUE;

			for (int r = 0; r < s.length(); r++)
			{
				// if the char at pos r counts as seen
				if (mapT[s.charAt(r)] != null)
				{
					mapT[s.charAt(r)]--;

					if (mapT[s.charAt(r)] >= 0)
					{
						allCharsFromTInWindowCount++;
					}
				}

				// when we covered all chars from t string
				while (allCharsFromTInWindowCount == t.length())
				{
					if (mapT[s.charAt(l)] != null)
					{
						// returning the count
						mapT[s.charAt(l)]++;

						// the count became 1 means that we moved from the char at l
						// the char at l is no longer a part of the window
						// and we can not longer minimize the window
						if (mapT[s.charAt(l)] == 1)
						{
							allCharsFromTInWindowCount--;
						}
					}

					l++;
				}

				// if l was ever changed
				if (l > 0)
				{
					if (r - l < minLen)
					{
						minL = l;
						minR = r;
						minLen = r - l;
					}
				}
			}

			return minLen == Integer.MAX_VALUE ? "" : s.substring(minL - 1, minR + 1);
		}
	}
}
