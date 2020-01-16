package twoPointers.slidingWindow.count;

/**
 * 1248
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it. Return
 * the number of nice sub-arrays.
 */
public class CountNumberOfNiceSubarrays
{
	static class Solution
	{
		public int numberOfSubarrays(int[] nums, int k)
		{
			return numberOfSubarraysAtMostK(nums, k) - numberOfSubarraysAtMostK(nums, k - 1);
		}

		public int numberOfSubarraysAtMostK(int[] nums, int k)
		{
			int l = 0;
			int result = 0;
			int oddNumbersInWindowCount = 0;

			for (int r = 0; r < nums.length; r++)
			{
				if (nums[r] % 2 != 0)
				{
					oddNumbersInWindowCount++;
				}

				while (oddNumbersInWindowCount > k)
				{
					if (nums[l] % 2 != 0)
					{
						oddNumbersInWindowCount--;
					}
					l++;
				}

				result += r - l + 1;
			}

			return result;
		}
	}
}
