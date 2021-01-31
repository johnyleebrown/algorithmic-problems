package math.bits;

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
public class SortIntegersByTheNumberOf1Bits {
	/**
	 * Straightforward.
	 */
	private static class Solution {
		public int[] sortByBits(int[] a) {
			Integer[] b = new Integer[a.length];
			for (int i = 0; i < b.length; i++)
				b[i] = a[i];

			Arrays.sort(b, Comparator.comparing(i -> Integer.bitCount(i)));

			for (int i = 0; i < b.length; ++i)
				a[i] = b[i];

			return a;
		}
	}
}