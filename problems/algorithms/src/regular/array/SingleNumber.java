package regular.array;

/**
 * 136
 *
 * ======
 *
 * Task.
 *
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SingleNumber {
	public static class Solution {
		public int singleNumber(int[] nums) {
			int x = nums[0];
			for (int i = 1; i < nums.length; i++) {
				x ^= nums[i];
			}
			return x;
		}
	}
}