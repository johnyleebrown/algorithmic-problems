package dp.subsequences;

import util.tester.Tester;

/**
 * 516
 *
 * ======
 *
 * Task.
 *
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestPalindromicSubsequence
{
	/**
	 * Substring dp.
	 */
	public static class Solution
	{
		public int longestPalindromeSubseq(String s)
		{
			int n = s.length();
			int[][] a = new int[n][n];

			// init
			for (int i = 0; i < n; i++)
				a[i][i] = 1;

			return dfs(a, 0, n - 1, s);
		}

		private int dfs(int[][] a, int l, int r, String s)
		{
			if (l >= r || a[l][r] != 0)
				return a[l][r];

			if (s.charAt(l) == s.charAt(r))
				a[l][r] = dfs(a, l + 1, r - 1, s) + 2;
			else
				a[l][r] = Math.max(dfs(a, l, r - 1, s), dfs(a, l + 1, r, s));

			return a[l][r];
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add("bbbab").expect(4)
				.add("cbbd").expect(2)
				.add("asdasdashdasgsfas").expect(9)
				.run();
	}
}
