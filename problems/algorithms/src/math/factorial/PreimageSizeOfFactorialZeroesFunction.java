package math.factorial;

/**
 * 793
 */
public class PreimageSizeOfFactorialZeroesFunction
{
	/**
	 * Time complexity: O(log n) Space complexity: O(1)
	 */
	private static class Solution
	{
		// (<= K zeroes) - (<= K - 1 zeroes)
		public static int solution(int K)
		{
			return findRange(K) - findRange(K - 1);
		}

		// binary search
		static int findRange(int k)
		{
			long low = 0, high = Long.MAX_VALUE;

			while (high > low)
			{
				long mid = low + (high - low) / 2;

				if (getZeroes(mid) > k) high = mid - 1;
				else low = mid + 1;
			}

			return (int) low - 1;
		}

		// every 5^n 5,25,125.. extra zero
		static long getZeroes(long N)
		{
			long count = 0;

			for (long i = 5; N / i >= 1; i = i * 5) count += N / i;

			return count;
		}
	}
}