package sort.ds_;

import util.utility.Gen;

import static sort.ds_.Shuffle.shuffleIntArray;
import static util.utility.Validation.isSorted;

/**
 * MergeSort
 */
public class MergeSort
{
	public void sortIntArray(int[] a)
	{
		shuffleIntArray(a);
		sort(a, new int[a.length], 0, a.length - 1);
		isSorted(a);
	}

	private void sort(int[] a, int[] aux, int lo, int hi)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(lo, mid, hi, a, aux);
	}

	private void merge(int lo, int mid, int hi, int[] a, int[] aux)
	{
		for (int k = lo; k <= hi; k++)
		{
			aux[k] = a[k];
		}

		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++)
		{
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[j] < aux[i]) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}

	public static void main(String[] args)
	{
		int n = 12;
		int[] inp = Gen.genIntArray(n, n);

		MergeSort s = new MergeSort();
		s.sortIntArray(inp);

		System.out.println("OK");
	}
}
