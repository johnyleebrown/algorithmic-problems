package twoPointers.slidingWindow.min;

/**
 * 209
 *
 * ======
 *
 * Task.
 *
 * Given an array of n positive integers and a positive integer s, find the
 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
 * one, return 0 instead.
 */
public class MinimumSizeSubarraySum {
	public static class Solution {
		public int minSubArrayLen(int s, int[] nums) {
			int l = 0;
			int n = nums.length;
			int ans = n + 1;
			int sum = 0;
			for (int r = 0; r < n; r++) {
				sum += nums[r];
				while (sum >= s) {
					sum -= nums[l];
					l++;
				}
				if (l > 0) {
					ans = Math.min(ans, r - l + 2);
				}
			}
			return ans == n + 1 ? 0 : ans;
		}
	}
}
