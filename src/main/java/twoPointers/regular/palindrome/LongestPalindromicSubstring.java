package twoPointers.regular.palindrome;

/**
 * 5
 *
 * ======
 *
 * Task.
 *
 * Given a string s, find the longest palindromic substring in s. You may assume
 * that the maximum length of s is 1000.
 */
public class LongestPalindromicSubstring
{
	/**
	 * The idea is to explore each character in both directions, we could have 2
	 * cases, if the palindrome length is odd or it is even, if it is odd 2
	 * pointers i and j will be k and k + 1, otherwise they will be k and k.
	 */
	static class Solution
	{
		private int finalI;
		private int finalJ;
		private int max = Integer.MIN_VALUE;

		public String longestPalindrome(String s)
		{
			if (s.length() == 0)
			{
				return "";
			}

			for (int i = 0; i < s.length(); i++)
			{
				explore(i, i, s);
				explore(i, i + 1, s);
			}

			return s.substring(finalI, finalJ + 1);
		}

		private void explore(int i, int j, String s)
		{
			int newi = i, newj = j;
			while (newi >= 0 && newj < s.length() && s.charAt(newi) == s.charAt(newj))
			{
				newi--;
				newj++;
			}

			updateMax(newi, newj);
		}

		private void updateMax(int i, int j)
		{
			if ((j - 1) - (i + 1) + 1 > max)
			{
				max = (j - 1) - (i + 1) + 1;
				finalI = i + 1;
				finalJ = j - 1;
			}
		}
	}
}