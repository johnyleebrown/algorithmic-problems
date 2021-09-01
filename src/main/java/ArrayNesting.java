/**
 * 565. Array Nesting
 * https://leetcode.com/problems/array-nesting/
 */
public class ArrayNesting {

	public static class Solution {

	}

	/**
	 * Find the longest graph
	 */
	public static class Solution2 {
		public int arrayNesting(int[] nums) {
			int ans = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != -1) {
					int count = 0;
					int k = nums[i];
					while (k != i) {
						k = nums[k];
						count++;
					}
					ans = Math.max(ans, count + 1);
				}
			}
			return ans;
		}
	}
}
