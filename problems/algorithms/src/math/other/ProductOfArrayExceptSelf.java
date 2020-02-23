package math.other;

/**
 * 238
 *
 * ======
 *
 * Companies: Amazon
 */
class ProductOfArrayExceptSelf
{
	/**
	 * Two passes from different sides.
	 */
	static class Solution
	{
		public int[] productExceptSelf(int[] nums)
		{
			int[] temp1 = new int[nums.length];
			temp1[0] = nums[0];
			for (int i = 1; i < nums.length; i++)
			{
				temp1[i] = temp1[i - 1] * nums[i];
			}

			int[] temp2 = new int[nums.length];
			temp2[nums.length - 1] = nums[nums.length - 1];
			for (int i = nums.length - 2; i >= 0; i--)
			{
				temp2[i] = temp2[i + 1] * nums[i];
			}

			int[] ans = new int[nums.length];
			for (int i = 0; i < nums.length; i++)
			{
				int left = i == 0 ? 1 : temp1[i - 1];
				int right = i == nums.length - 1 ? 1 : temp2[i + 1];
				ans[i] = left * right;
			}

			return ans;
		}
	}
}
