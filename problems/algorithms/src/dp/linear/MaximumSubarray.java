package dp.linear;

/**
 * 53
 *
 * =====
 *
 * Reference.
 *
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class MaximumSubarray {
	/**
	 * Kadane's algorithm.
	 */
	public static class Solution {
		public int maxSubArray(int[] nums) {
			if (nums == null || nums.length == 0) {
				return 0;
			}

			int curMax = nums[0];
			int max = nums[0];
			for (int i = 1; i < nums.length; i++) {
				curMax = Math.max(nums[i], curMax + nums[i]);
				max = Math.max(curMax, max);
			}

			return max;
		}
	}
}