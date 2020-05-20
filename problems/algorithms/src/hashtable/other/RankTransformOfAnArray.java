package hashtable.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1331
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr, replace each element with its rank.
 *
 * The rank represents how large the element is. The rank has the following rules:
 *
 * Rank is an integer starting from 1. The larger the element, the larger the rank. If two elements are equal, their
 * rank must be the same. Rank should be as small as possible.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RankTransformOfAnArray
{
	/**
	 * Straightforward.
	 */
	class Solution
	{
		public int[] arrayRankTransform(int[] arr)
		{
			int n = arr.length;

			int[] newArray = new int[n];
			for (int i = 0; i < n; i++)
			{
				newArray[i] = arr[i];
			}
			Arrays.sort(newArray);

			int x = 1;
			Map<Integer, Integer> m = new HashMap<>();
			for (int i = 0; i < n; i++)
			{
				if (!m.containsKey(newArray[i]))
				{
					m.put(newArray[i], x++);
				}
			}

			for (int i = 0; i < n; i++)
			{
				arr[i] = m.get(arr[i]);
			}

			return arr;
		}
	}
}