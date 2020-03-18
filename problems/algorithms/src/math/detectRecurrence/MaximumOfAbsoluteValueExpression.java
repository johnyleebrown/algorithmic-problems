package math.detectRecurrence;

import util.tester.Tester;

/**
 * 1131
 *
 * ======
 *
 * Task.
 *
 * Given two arrays of integers with equal lengths, return the maximum value
 * of:
 *
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumOfAbsoluteValueExpression
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution
	{
		public int solve()
		{
			return -1;
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add().expect()
				.run();
	}
}
