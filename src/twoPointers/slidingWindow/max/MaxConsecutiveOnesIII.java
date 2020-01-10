package twoPointers.slidingWindow.max;

/*
 * 1004
 *
 * Check 424
 */
class MaxConsecutiveOnesIII
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

