package bs.regular;

/**
 * 334
 *
 * ======
 *
 * Task.
 *
 * Given an unsorted array return whether an increasing subsequence of length 3
 * exists or not in the array.
 *
 * ======
 *
 * Similar: LIS.
 *
 * ======
 *
 * Source: Leetcode
 */
public class IncreasingTripletSubsequence {
	/**
	 * Similar as LIS, we create a increasing sequence of elements along the
	 * array traversal.
	 */
	public static class Solution {
		public boolean increasingTriplet(int[] a) {
			int n = a.length;
			if (n == 0) {
				return false;
			}
			int[] increasingSubsequence = new int[3];
			increasingSubsequence[0] = a[0];
			int j = 1;
			for (int i = 1; i < n; i++) {
				if (a[i] > increasingSubsequence[j - 1]) {
					increasingSubsequence[j++] = a[i];
				}
				else {
					int pos = bs(j - 1, increasingSubsequence, a[i]);
					increasingSubsequence[pos] = a[i];
				}
				if (j == 3) {
					return true;
				}
			}
			return j >= 3;
		}

		private int bs(int hi, int[] a, int t) {
			int lo = 0;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid] == t) {
					return mid;
				}
				else if (a[mid] > t) {
					hi = mid - 1;
				}
				else {
					lo = mid + 1;
				}
			}
			return lo;
		}
	}
}
