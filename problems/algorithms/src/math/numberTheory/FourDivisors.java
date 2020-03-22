package math.numberTheory;

import java.util.HashMap;
import java.util.Map;

/**
 * 1390
 *
 * ======
 *
 * Task.
 *
 * Given an integer array nums, return the sum of divisors of the integers in
 * that array that have exactly four divisors.
 *
 * If there is no such integer in the array, return 0.
 *
 * ======
 *
 * Similar: FindNumbersWithNDivisorsInAGivenRange.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FourDivisors
{
	/**
	 * 4 divisors are 1, n, n/k, k. If n/k == k we ignore this case, because we
	 * can count this number only once. We count until sqrt.
	 */
	public static class Solution
	{
		public int sumFourDivisors(int[] a)
		{
			int sum = 0;
			// optimization
			Map<Integer, Integer> cache = new HashMap<>();

			for (int i : a)
			{
				// optimization
				if (cache.containsKey(i))
				{
					sum += cache.get(i);
					continue;
				}

				// this way we can understand if there are > than 4 divisors
				boolean fourDivisors = false;
				int localSum = 0;

				for (int j = 2; j * j <= i; j++)
				{
					if (i % j == 0)
					{
						if (i / j == j)
						{
							fourDivisors = false;
							break;
						}
						else
						{
							if (fourDivisors)
							{
								fourDivisors = false;
								break;
							}
							localSum += 1 + i / j + j + i;
							fourDivisors = true;
						}
					}
				}

				if (fourDivisors)
				{
					sum += localSum;
					// optimization
					cache.put(i, localSum);
				}
			}

			return sum;
		}
	}
}
