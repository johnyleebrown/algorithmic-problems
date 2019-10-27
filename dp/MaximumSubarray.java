/*
 * 53
 * Google
 *
 * Either we take a new element and continue or we start from the
 * current element.
 */
class Solution
{
	public int maxSubArray(int[] nums)
	{
		int curSum = 0, maxSum = Integer.MIN_VALUE;
		for (int k: nums)
		{
			if (k > curSum + k)
			{
				curSum = k;
			}
			else
			{
				curSum += k;
			}

			maxSum = Math.max(maxSum, curSum);
		}
		return maxSum == Integer.MIN_VALUE ? 0 : maxSum;
	}
}

