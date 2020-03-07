package twoPointers.slidingWindow.max;

/**
 * 1004
 *
 * ======
 *
 * Task.
 *
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only
 * 1s.
 *
 * ======
 *
 * Similar: 424
 */
public class MaxConsecutiveOnesIII
{
	static class Solution
	{
		public int longestOnes(int[] A, int K)
		{
			int l = 0;
			int result = 0;

			// count zeros because we need to replace them
			int zerosCount = 0;

			for (int r = 0; r < A.length; r++)
			{
				// use right window boundary
				if (A[r] == 0)
				{
					zerosCount++;
				}

				while (zerosCount > K)
				{
					// use left window boundary
					if (A[l] == 0)
					{
						zerosCount--;
					}

					l++;
				}

				// update result
				result = Math.max(result, r - l + 1);
			}

			return result;
		}
	}
}
