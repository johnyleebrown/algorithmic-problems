package bs.range;

/**
 * 34
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

	public static class Solution {

		public int[] searchRange(int[] a, int t) {
			return new int[]{
					findFirstPosition(a.length, t, a),
					findLastPosition(a.length, t, a)
			};
		}

		private int findFirstPosition(int n, int x, int[] a) {
			int lo = -1;
			int hi = n;
			while (hi - lo > 1) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid] < x) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return hi >= n || a[hi] != x ? -1 : hi;
		}

		private int findLastPosition(int n, int x, int[] a) {
			int lo = -1;
			int hi = n;
			while (hi - lo > 1) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid] <= x) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return lo <= -1 || a[lo] != x ? -1 : lo;
		}
	}
}