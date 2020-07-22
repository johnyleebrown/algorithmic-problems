package bs.modified;

/**
 * 1060
 *
 * ======
 *
 * Task.
 *
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost
 * number of the array.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MissingElementInSortedArray {
	/**
	 * - Idea:
	 * I we know the length if subarray, and we know that we have unique and sorted numbers, then
	 * we can say how many items are missing. So at each step we check if k is in the missing
	 * numbers count range.
	 * - part I:
	 * Check edge case - if kth missing number will be out of ar
	 * - part II:
	 * Binary search
	 * - part III:
	 * Get the kth missing number from lo
	 */
	public static class Solution {
		public int missingElement(int[] ar, int k) {

			int n = ar.length;

			// edge case
			int all_miss = get_miss(ar, n - 1);
			if (k > all_miss) {
				return ar[n - 1] + k - all_miss;
			}

			// bs
			int lo = 0, hi = n - 1;
			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				int l_miss = get_miss(ar, mid);
				if (k <= l_miss) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}

			// travel from lo
			int x_miss = get_miss(ar, lo);
			return ar[lo] - (x_miss - k + 1);
		}

		// calculate the count of missing numbers
		int get_miss(int[] ar, int i) {
			int x_must_have = ar[i] - ar[0] + 1;
			int x_actually_is = i + 1;
			return x_must_have - x_actually_is;
		}
	}
}