package sort._ds;

import util.utils.Gen;

import java.util.Arrays;

import static sort._ds.Shuffle.shuffleIntArray;
import static util.utils.Validation.isSorted;

/**
 * MergeSort
 *
 * @formatter:off
 * Complexity:
 * - Time: Worst O(nlogn), Avg O(nlogn), Best O(nlogn)
 * - Space: O(n)
 *
 * Stats:
 * - Stable
 * - Not in place
 * @formatter:on
 */
public class MergeSort {
	public static void main(String[] args) {
		int n = 12;
		int[] inp = Gen.genIntArray(n, n, false);
		int[] copy = Arrays.copyOf(inp, inp.length);


		MergeSort s = new MergeSort();
		s.sortIntArray(inp);

		Arrays.sort(copy);
		if (Arrays.equals(inp, copy)) {
			System.out.println("OK");
		} else {
			throw new RuntimeException("ERROR.");
		}
	}

	public void sortIntArray(int[] a) {
		shuffleIntArray(a);
		sort(a, new int[a.length], 0, a.length - 1);
		isSorted(a);
	}

	private void sort(int[] a, int[] aux, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(lo, mid, hi, a, aux);
	}

	private void merge(int lo, int mid, int hi, int[] a, int[] aux) {
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (aux[j] < aux[i]) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
}
