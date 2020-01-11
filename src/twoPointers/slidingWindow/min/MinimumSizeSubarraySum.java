package twoPointers.slidingWindow.min;

/**
 * 209
 *
 * ======
 *
 * Task.
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 */
class MinimumSizeSubarraySum
{
	class Solution
	{
		public int minSubArrayLen(int s, int[] nums)
		{
			int l = 0;
			int minLen = nums.length;
			int sum = 0;

			for (int r = 0; r < nums.length; r++)
			{
				// use right window boundary
				sum += nums[r];

				while (sum >= s)
				{
					// use left window boundary
					sum -= nums[l];

					l++;
				}

				// update result
				if (l > 0)
				{
					minLen = Math.min(minLen, r - l + 2);
				}
			}

			return l > 0 ? minLen : 0;
		}
	}
}
