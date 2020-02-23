package twoPointers.slidingWindow.max;

/**
 * 1208
 *
 * ======
 *
 * Task.
 *
 * You are given two strings s and t of the same length. You want to change s to
 * t. Changing the i-th character of s to i-th character of t costs |s[i] -
 * t[i]| that is, the absolute difference between the ASCII values of the
 * characters.
 *
 * You are also given an integer maxCost.
 *
 * Return the maximum length of a substring of s that can be changed to be the
 * same as the corresponding substring of twith a cost less than or equal to
 * maxCost.
 *
 * If there is no substring from s that can be changed to its corresponding
 * substring from t, return 0.
 */
public class GetEqualSubstringsWithinBudget
{
	static class Solution
	{
		public int equalSubstring(String s, String t, int maxCost)
		{
			int result = 0;
			int l = 0;
			int sum = 0;

			for (int r = 0; r < s.length(); r++)
			{
				sum += getDiff(s, t, r);

				// moving left pointer if bad condition is met
				while (sum > maxCost)
				{
					sum -= getDiff(s, t, l);
					l++;
				}

				result = Math.max(result, r - l + 1);
			}

			return result;
		}

		private int getDiff(String s, String t, int r)
		{
			return Math.abs(s.charAt(r) - t.charAt(r));
		}
	}
}
