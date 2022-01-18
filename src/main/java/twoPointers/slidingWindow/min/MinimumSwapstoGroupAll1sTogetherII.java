package twoPointers.slidingWindow.min;

/**
 * 2134
 */
public class MinimumSwapstoGroupAll1sTogetherII {

	/**
	 * Whenever you are faced with a circular array question, you can just append the array
	 * to itself to get rid of the circular array part of the problem.
	 * Count number of ones in nums, let the number of ones be stored in the variable ones
	 * Append nums to nums because we have to look at it as a circular array
	 * Find the maximum number of ones in a window of size ones in the new array
	 * Number of swaps = ones - maximum number of ones in a window of size ones
	 */
	public static class Solution {

		public int minSwaps(int[] nums) {
			int n = nums.length, ones = 0;
			int[] ar = new int[n * 2];

			for (int i = 0; i < n; i++) {
				ar[i] = nums[i % n];
				ar[i + n] = nums[i];
				if (nums[i] == 1) ones++;
			}

			int r = 0, curOnes = 0;
			for (; r < ones; r++) {
				if (ar[r] == 1) curOnes++;
			}

			int ans = Integer.MAX_VALUE, l = 0;
			while (r < n * 2) {
				if (ar[r] == 1) curOnes++;
				if (ar[l] == 1) curOnes--;
				ans = Math.min(ans, ones - curOnes);
				l++;
				r++;
			}
			return ans;
		}
	}
}
