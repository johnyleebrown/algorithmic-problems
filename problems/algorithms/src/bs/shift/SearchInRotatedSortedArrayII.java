package bs.shift;

/**
 * 81
 *
 * ======
 *
 * Task.
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return
 * false.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SearchInRotatedSortedArrayII {
	/**
	 * Added a case if lo == hi, in this case we slightly move right pointer because we are at a
	 * duplicate.
	 */
	public static class Solution {
		public boolean search(int[] a, int x) {

			int n = a.length;

			// edge
			if (n == 0) return false;

			int lo = 0;
			int hi = n - 1;

			// edge
			if (x < a[lo] && x > a[hi]) return false;

			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				System.out.println("> " + mid);
				if (x == a[mid]) {
					return true;
				}

				// 1 step: where mid at
				// less than lo = right part
				if (a[mid] < a[lo]) {
					// 2 step: where target at
					if (x > a[hi] || x < a[mid]) {
						hi = mid - 1;
					} else {
						lo = mid + 1;
					}
				}

				// more than hi = left part
				else if (a[mid] > a[hi]) {
					// 2 step: where target at
					if (x < a[lo] || x > a[mid]) {
						lo = mid + 1;
					} else {
						hi = mid - 1;
					}
				} else {
					// ADDED, at a duplicate => move
					// also lo++ works but smh slower on leetcode
					if (a[lo] == a[hi]) {
						hi--;
					} else {
						if (x < a[mid]) {
							hi = mid - 1;
						} else {
							lo = mid + 1;
						}
					}
				}
			}

			return false;
		}
	}
}