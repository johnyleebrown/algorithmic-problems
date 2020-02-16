package sort.regular;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1338
 *
 * ======
 *
 * Task.
 *
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ReduceArraySizeToTheHalf
{
	/**
	 * Greedy.
	 */
	class Solution
	{
		public int minSetSize(int[] arr)
		{
			int n = arr.length;

			Map<Integer, Integer> m = new HashMap<>();
			for (int i = 0; i < n; i++)
			{
				m.put(arr[i], m.getOrDefault(arr[i], 0) + 1);
			}

			int[] sizes = new int[m.size()];
			int ind = 0;
			for (int i : m.keySet())
			{
				sizes[ind++] = m.get(i);
			}
			Arrays.sort(sizes);

			int target = n / 2;
			int res = 0;
			for (int i = sizes.length - 1; i >= 0 && target > 0; i--, res++)
			{
				target -= sizes[i];
			}

			return res;
		}
	}
}