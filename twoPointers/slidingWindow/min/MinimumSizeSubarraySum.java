// 209
class Solution 
{
	public int minSubArrayLen(int s, int[] nums) 
	{
		if (nums.length == 0) return 0;
		int n = nums.length, i = 0, j = 0, sum = 0, count = Integer.MAX_VALUE;
		while (j < n)
		{
			sum += nums[j++];

			// if sum is larger lets find the minimum 
			// of count of numbers by moving the i index
			while (sum >= s)
			{
				// j - i = th elength of current window
				count = Math.min(count, j - i);

				// this is where we get greedy, decrease 
				// the sum because we left the element
				sum -= nums[i++];
			}
		}
		return count != Integer.MAX_VALUE ? count : 0;
	}
}

