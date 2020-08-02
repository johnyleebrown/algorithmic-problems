package array.prefix.other;

/**
 * 1343
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr and two integers k and threshold.
 *
 * Return the number of sub-arrays of size k and average greater than or equal
 * to threshold.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
	/**
	 * Straightforward.
	 */
	class Solution {
		public int numOfSubarrays(int[] arr, int k, int threshold) {
			int res = 0;
			int n = arr.length;
			int[] pre = new int[n];
			pre[0] = arr[0];
			int i = 1;
			for (; i < k - 1; i++) {
				pre[i] = pre[i - 1] + arr[i];
			}
			if (k == 1)
				if (pre[0] >= threshold)
					res++;
			for (; i < n; i++) {
				pre[i] = pre[i - 1] + arr[i];
				int wSum = pre[i] - (i - k >= 0 ? pre[i - k] : 0);
				if (wSum / k >= threshold) {
					res++;
				}
			}
			return res;
		}
	}
}