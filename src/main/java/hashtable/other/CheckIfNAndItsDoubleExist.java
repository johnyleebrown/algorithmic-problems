package hashtable.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 1346
 *
 * ======
 *
 * Task.
 *
 * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N =
 * 2 * M).
 *
 * ======
 *
 * Source: Leetcode
 */
public class CheckIfNAndItsDoubleExist
{
	/**
	 * Straightforward.
	 */
	class Solution
	{
		public boolean checkIfExist(int[] arr)
		{
			Map<Integer, Integer> s = new HashMap<>();
			for (int i = 0; i < arr.length; i++)
			{
				if (s.containsKey(arr[i] * 2) || (arr[i] % 2 == 0 && s.containsKey(arr[i] / 2)))
				{
					return true;
				}
				s.put(arr[i], i);
			}
			return false;
		}
	}
}