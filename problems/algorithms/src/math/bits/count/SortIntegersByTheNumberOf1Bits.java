package math.bits.count;

import util.tester.Tester;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1356
 *
 * ======
 *
 * Task.
 *
 * Given an integer array arr. You have to sort the integers in the array in
 * ascending order by the number of 1's in their binary representation and in
 * case of two or more integers have the same number of 1's you have to sort
 * them in ascending order.
 *
 * Return the sorted array.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SortIntegersByTheNumberOf1Bits
{
	/**
	 * Straightforward.
	 */
	private static class Solution
	{
		public int[] sortByBits(int[] a)
		{
			Integer[] b = new Integer[a.length];
			for (int i = 0; i < b.length; i++)
				b[i] = a[i];

			Arrays.sort(b, Comparator.comparing(i -> Integer.bitCount(i)));

			for (int i = 0; i < b.length; ++i)
				a[i] = b[i];

			return a;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}).expect(new int[]{0, 1, 2, 4, 8, 3, 5, 6, 7})
				.add(new int[]{1024,512,256,128,64,32,16,8,4,2,1}).expect(new int[]{1,2,4,8,16,32,64,128,256,512,1024})
				.add(new int[]{10, 100, 1000, 10000}).expect(new int[]{10, 100, 10000, 1000})
				.run();
	}
}