package math.bits;

/**
 * 268
 */
public class MissingNumber {
	public static class Solution {
		public int missingNumber(int[] nums) {
			int missing = nums.length;
			for (int i = 0; i < nums.length; i++) {
				missing ^= i ^ nums[i];
			}
			return missing;
		}
	}
}
