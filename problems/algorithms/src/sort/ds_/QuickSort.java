package sort.ds_;

import util.utility.Gen;

import static sort.ds_.Shuffle.shuffleIntArray;
import static util.utility.Utility.exch;
import static util.utility.Validation.isSorted;

/**
 * QuickSort
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

		QuickSort s = new QuickSort();
		s.sortIntArray(inp);

		System.out.println("OK");
	}
}
