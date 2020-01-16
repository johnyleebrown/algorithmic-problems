package twoPointers.slidingWindow.other;

/**
 * 992
 *
 * ======
 *
 * Task.
 *
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number
 * of different integers in that subarray is exactly K. Return the number of good subarrays of A.
 *
 * ======
 *
 * Similar: 1248
 */
public class SubarraysWithKDifferentIntegers
{
	/**
	 * Solved as subproblem of max type of problem - LongestSubstringWithAtMostKDistinctCharacters.
	 */
	static class Solution
	{
		public int subarraysWithKDistinct(int[] A, int K)
		{
			return subarraysWithAtMostKDistinct(A, K) - subarraysWithAtMostKDistinct(A, K - 1);
		}

		public int subarraysWithAtMostKDistinct(int[] A, int K)
		{
			if (K == 0)
			{
				return 0;
			}

			int result = 0;
			int l = 0;
			int[] map = new int[A.length + 1];
			int count = 0;

			for (int r = 0; r < A.length; r++)
			{
				map[A[r]]++;
				if (map[A[r]] == 1)
				{
					count++;
				}

				while (count > K)
				{
					map[A[l]]--;
					if (map[A[l]] == 0)
					{
						count--;
					}

					l++;
				}

				result += r - l + 1;
			}

			return result;
		}
	}
}
