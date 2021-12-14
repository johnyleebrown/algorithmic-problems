package regular.array;

/**
 * 283
 */
public class MoveZeroes {

	public static class Solution {

		public void moveZeroes(int[] nums) {
			int l = 0, r = 0;
			while (r < nums.length) {
				if (nums[r] != 0) {
					if (l < r) {
						nums[l] = nums[r];
						nums[r] = 0;
					}
					l++;
				}
				r++;
			}
		}
	}

	public static class Solution2 {

		public void moveZeroes(int[] nums) {
			int l = 0, r = 0;
			while (l < nums.length && r < nums.length) {
				if (nums[l] != 0) {
					l++;
				} else if (nums[r] == 0 || l >= r) {
					r++;
				} else {
					nums[l] = nums[r];
					nums[r] = 0;
					l++;
					r++;
				}
			}
		}
	}
}
