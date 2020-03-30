package sort._ds;

import util.utility.Gen;

import java.util.Arrays;

import static sort._ds.Shuffle.shuffleIntArray;
import static util.utility.Utility.exch;
import static util.utility.Validation.isSorted;

/**
 * QuickSort
 *
 * @formatter:off
 * Complexity:
 * - Time: Worst O(n^2), Avg O(nlogn), Best O(nlogn)
 * - Space: O(logn)
 *
 * Stats:
 * - Faster than merge sort by 40%
 * - Random shuffle - works against worst case
 * - Not stable
 * - In place
 * - For small arrays - insertion sort (20% faster)
 * @formatter:on
 */
public class QuickSort
{
	public void sortIntArray(int[] a)
	{
		shuffleIntArray(a);
		sort(a, 0, a.length - 1);
		isSorted(a);
	}

	private void sort(int[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
		int p = partition(a, lo, hi);
		sort(a, lo, p - 1);
		sort(a, p + 1, hi);
	}

	private int partition(int[] a, int lo, int hi)
	{
		int v = a[lo];
		int i = lo;
		int j = hi + 1;

		while (true)
		{
			while (a[++i] < v)
				if (i == hi) break;

			while (a[--j] > v)
				if (j == lo) break;

			if (i >= j)
				break;

			exch(a, i, j);
		}

		exch(a, lo, j);
		return j;
	}

	public static void main(String[] args)
	{
		int n = 12;
		int[] inp = Gen.genIntArray(n, n);
		int[] copy = Arrays.copyOf(inp, inp.length);

		QuickSort s = new QuickSort();
		s.sortIntArray(inp);

		Arrays.sort(copy);
		if (Arrays.equals(inp, copy)) {
			System.out.println("OK");
		} else {
			throw new RuntimeException("ERROR.");
		}
	}
}
