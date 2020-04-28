package sort._ds;

import util.utility.Gen;

import java.util.Arrays;

import static sort._ds.Shuffle.shuffleIntArray;
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
		int[] inp = Gen.genIntArray(n, n, false);
		int[] copy = Arrays.copyOf(inp, inp.length);

		InsertionSort s = new InsertionSort();
		s.sortIntArray(inp);

		Arrays.sort(copy);
		if (Arrays.equals(inp, copy)) {
			System.out.println("OK");
		} else {
			throw new RuntimeException("ERROR.");
		}
	}
}
