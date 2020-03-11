package array.prefix;

import util.tester.Tester;

/**
 * 1365
 *
 * ======
 *
 * Task.
 *
 * Given the array nums, for each nums[i] find out how many numbers in the array
 * are smaller than it. That is, for each nums[i] you have to count the number
 * of valid j's such that j != i and nums[j] < nums[i].
 *
 * ======
 *
 * Source: Leetcode
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber
{
	/**
	 * Prefix count. For each kind of number we have a count, the going from 0
	 * to n, calc a running count.
	 */
	private static class Solution
	{
		public int[] smallerNumbersThanCurrent(int[] a)
		{
			int n = a.length;
			int max = 100 + 1;
			int[] c = new int[max];

			for (int i : a)
			{
				c[i]++;
			}

			int prefixCount = 0;
			for (int i = 0; i < max; i++)
			{
				if (c[i] != 0)
				{
					int t = c[i];
					c[i] = prefixCount;
					prefixCount += t;
				}
			}

			for (int i = 0; i < n; i++)
			{
				a[i] = c[a[i]];
			}

			return a;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new int[]{8,1,2,2,3}).expect(new int[]{4,0,1,1,3})
				.run();
	}
}
