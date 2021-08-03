package twoPointers.slidingWindow.max;

/**
 * 487. Max Consecutive Ones II
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can
 * flip at most one 0.
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s. After flipping,
 * the maximum number of consecutive 1s is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 *
 * Follow up: What if the input numbers come in one by one as an infinite stream? In other words,
 * you can't store all numbers coming from the stream as it's too large to hold in memory. Could you
 * solve it efficiently?
 *
 * https://leetcode.com/problems/max-consecutive-ones-ii/
 */
public class MaxConsecutiveOnesII {
	public static class Solution1 {
		public int findMaxConsecutiveOnes(int[] nums) {
			int l = 0;
			int zeros = 0;
			int max = 0;

			for (int r = 0; r < nums.length; r++) {
				if (nums[r] == 0) {
					zeros++;
				}

				while (zeros == 2) {
					if (nums[l] == 0) {
						zeros--;
					}
					l++;
				}

				max = Math.max(max, r - l + 1);
			}

			return max;
		}
	}

	public static class Solution2 {
		public int findMaxConsecutiveOnes(int[] nums) {
			int prevPrev = -1;
			int prev = -1;
			int ans = 0;
			for (int i = 0; i <= nums.length; i++) {
				if (i == nums.length || nums[i] == 0) {
					ans = Math.max(ans, i - prevPrev - 1);
					prevPrev = prev;
					prev = i;
				}
			}
			return ans;
		}
	}
}
