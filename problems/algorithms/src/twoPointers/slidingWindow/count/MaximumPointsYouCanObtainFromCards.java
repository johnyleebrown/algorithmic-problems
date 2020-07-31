package twoPointers.slidingWindow.count;

/**
 * 1423
 *
 * ======
 *
 * Task.
 *
 * There are several cards arranged in a row, and each card has an associated number of points The
 * points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to
 * take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumPointsYouCanObtainFromCards {
	/**
	 * Since we need to evaluate items only on left and right sides, not in between
	 * (left|subarray|right), we can safely find a largest (totalSum-subarraySum) by using
	 * sliding window.
	 */
	public static class Solution {
		public int maxScore(int[] ar, int k) {
			int n = ar.length;
			int totalSum = 0;
			int slidingSum = 0;
			for (int i = 0; i < n; i++) {
				totalSum += ar[i];
				if (i == n - k - 1) {
					slidingSum = totalSum;
				}
			}
			if (n == k) return totalSum;
			k = n - k - 1;
			int ans = totalSum - slidingSum;
			int r = k + 1;
			int l = 0;
			while (r < n) {
				slidingSum += -ar[l++] + ar[r++];
				ans = Math.max(ans, totalSum - slidingSum);
			}
			return ans;
		}
	}
}