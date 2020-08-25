package bs.count;

/**
 * 275
 *
 * ======
 *
 * Task.
 *
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N
 * papers have at least h citations each, and the other N − h papers have no more than h citations
 * each."
 *
 * ======
 *
 * Source: Leetcode
 */
public class HIndexII {
	/**
	 * In the end return length not ar[lo] because "h have at least h citations".
	 */
	public static class Solution {
		public int hIndex(int[] ar) {
			int n = ar.length;
			if (n == 0) return 0;
			int lo = 0;
			int hi = n - 1;
			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				int val = ar[mid];
				// good, [mid,end] have value >= val
				if (n - 1 - mid >= val) {
					lo = mid + 1;
				}
				// bad
				else {
					hi = mid - 1;
				}
			}
			return n - lo;
		}
	}
}