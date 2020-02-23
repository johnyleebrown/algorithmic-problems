package sort.ds_;

import java.util.Arrays;

/**
 * Counting_sort
 *
 * =====
 *
 * Description.
 *
 * =====
 *
 * Example.
 * a = [3, 1, 2, 3]
 * count = [0, 1, 1, 2]
 * result = [1, 2, 3, 3]
 */
public class CountingSort
{
	/**
	 * For positive values only.
	 */
	public static void sort(int[] a)
	{
		int n = a.length;

		// pre-processing
		// find max array value, so we could create count array
		int maxArrayValue = Integer.MIN_VALUE;
		for (int i : a)
			maxArrayValue = Math.max(maxArrayValue, i);

		// set counts for all values from array
		int[] count = new int[maxArrayValue + 1];
		for (int i = 0; i < n; i++)
		{
			// updating count of value at i
			count[a[i]]++;
		}

		// index to fill array with sorted values
		int j = 0;

		// for each type of value
		for (int i = 0; i <= maxArrayValue; i++)
		{
			// while value i exists in array
			while (count[i] > 0)
			{
				a[j] = i;
				j++;

				count[i]--;
			}
		}
	}

	public static void main(String[] args)
	{
		int[] t1 = new int[]{1,6,23,5,11,3,1,34,1,3,1,23};
		int[] t2 = Arrays.copyOf(t1, t1.length);
		Arrays.sort(t2);
		sort(t1);
		System.out.println(Arrays.equals(t1, t2));
	}
}
