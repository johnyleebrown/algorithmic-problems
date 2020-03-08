package string.regular;

import util.test.Tester;

/**
 * 1374
 *
 * ======
 *
 * Task.
 *
 * Given an integer n, return a string with n characters such that each
 * character in such string occurs an odd number of times.
 *
 * The returned string must contain only lowercase English letters. If there are
 * multiples valid strings, return any of them.
 *
 * ======
 *
 * Source: Leetcode
 */
public class GenerateAStringWithCharactersThatHaveOddCounts
{
	/**
	 * SF.
	 */
	public static class Solution
	{
		public String generateTheString(int n)
		{
			char[] ch = new char[n];
			for (int i = 0; i < n; i++)
				ch[i] = 'a';
			if (n % 2 == 0)
				ch[n - 1] = 'b';
			return String.valueOf(ch);
		}
	}
}
