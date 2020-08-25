package bs.valueSearch;

/**
 * 35
 *
 * ======
 *
 * Task.
 *
 * Given a sorted array and a target value, return the index if the target is found. If not, return
 * the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SearchInsertPosition {
	/**
	 * Standard bs returns insert position as well.
	 */
	public static class Solution {
		public int searchInsert(int[] ar, int x) {
			int n = ar.length;
			int lo = 0;
			int hi = n - 1;
			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				if (ar[mid] == x) {
					return mid;
				}
				if (ar[mid] < x) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			return lo;
		}
	}
}