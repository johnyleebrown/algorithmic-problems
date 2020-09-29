package twoPointers.slidingWindow.max;

/**
 * 1493
 *
 * ======
 *
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 *
 * Return 0 if there is no such subarray.
 *
 * ======
 *
 * Company:{Yandex}
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
	/**
	 * Regular max 2 pointers.
	 */
	public static class Solution {
		public int longestSubarray(int[] ar) {
			int n = ar.length;
			if (n == 0) return 0;
			int l = 0;
			int c = 0;
			int max = 0;
			for (int r = 0; r < n; r++) {
				if (ar[r] == 0) c++;
				while (c > 1) {
					if (ar[l] == 0) c--;
					l++;
				}
				max = Math.max(max, r - l);
			}
			return max;
		}
	}
}