package twoPointers.slidingWindow.max;

/**
 * 487
 */
class MaxConsecutiveOnesII
{
	static class Solution
	{
		public int findMaxConsecutiveOnes(int[] nums)
		{
			int start = 0;
			int end = 0;
			int zeros = 0;
			int maxLen = Integer.MIN_VALUE;

			while (end < nums.length)
			{
				if (nums[end] == 0)
				{
					zeros++;
				}

				if (zeros > 1)
				{
					if (nums[start++] == 0)
					{
						zeros--;
					}
				}

				maxLen = Math.max(maxLen, end - start + 1);
				end++;
			}

			return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
		}
	}
}
