package twoPointers.slidingWindow.max;

/**
 * 487
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
