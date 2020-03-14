package sort.ds_;

import util.utility.Gen;

import static sort.ds_.Shuffle.shuffleIntArray;
import static util.utility.Utility.exch;
import static util.utility.Validation.isSorted;

/**
 * InsertionSort
 */
public class InsertionSort
{
	public void sortIntArray(int[] a)
	{
		shuffleIntArray(a);
		sort(a);
		isSorted(a);
	}

	private void sort(int[] a)
	{
		int n = a.length;
		for (int i = 1; i < n; i++)
		{
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--)
			{
				exch(a, j, j - 1);
			}
		}
	}

	public static void main(String[] args)
	{
		int n = 12;
		int[] inp = Gen.genIntArray(n, n);

		InsertionSort s = new InsertionSort();
		s.sortIntArray(inp);

		System.out.println("OK");
	}
}
