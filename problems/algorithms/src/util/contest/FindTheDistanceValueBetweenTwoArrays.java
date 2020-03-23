package util.contest;

import util.tester.Tester;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1385
 *
 * ======
 *
 * Task.
 *
 * Given two integer arrays arr1 and arr2, and the integer d, return the
 * distance value between the two arrays.
 *
 * The distance value is defined as the number of elements arr1[i] such that
 * there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindTheDistanceValueBetweenTwoArrays
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution
	{
		public int findTheDistanceValue(int[] a, int[] b, int d)
		{
			TreeSet<Integer> t = new TreeSet<>();
			for (int i = 0; i < b.length; i++)
			{
				t.add(b[i]);
			}
			int res = 0;
			System.out.println(Arrays.toString(b));
			for (int i = 0; i < a.length; i++)
			{
				System.out.println((a[i] - d) + " " + t.ceiling(a[i] - d));
				System.out.println((a[i] + d) + " " + t.floor(a[i] + d));
				if (t.ceiling(a[i] - d) == null && t.floor(a[i] + d) == null)
				{
					res++;
				}
			}
			return res;
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new int[]{4, 5, 8}, new int[]{10, 9, 1, 8}, 2).expect(2)
				.run();
	}
}
