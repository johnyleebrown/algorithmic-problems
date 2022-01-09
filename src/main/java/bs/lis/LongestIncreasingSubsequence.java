package bs.lis;

/**
 * 300
 */
public class LongestIncreasingSubsequence {
	/**
	 * We create an increasing array of elements while iterating the original array.
	 * If the cur element is bigger then the last el of the incr ar.
	 */
	public static class Solution {
		public int lengthOfLIS(int[] a) {
			int n = a.length;
			if (n == 0) {
				return 0;
			}

			int[] increasingSubsequence = new int[n];
			increasingSubsequence[0] = a[0];
			int j = 1; // pointer to the insertion point of last arr

			for (int i = 1; i < n; i++) {
				if (a[i] > increasingSubsequence[j - 1]) { // if still increasing
					increasingSubsequence[j] = a[i];
					j++;
				} else {
					int insertPosition = bs(j - 1, increasingSubsequence, a[i]);
					increasingSubsequence[insertPosition] = a[i];
				}
			}

			return j; // insertion point is the total len is the lis
		}

		// searching for pos where to insert if not exists
		private int bs(int hi, int[] a, int t) {
			int lo = 0;

			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid] == t) {
					return mid;
				} else if (a[mid] > t) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}

			return lo;
		}
	}
}
