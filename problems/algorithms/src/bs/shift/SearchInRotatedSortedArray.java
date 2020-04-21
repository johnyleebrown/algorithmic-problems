package bs.shift;

/**
 * 33
 */
public class SearchInRotatedSortedArray {
	public static class Solution {
		public int search(int[] a, int t) {
			int n = a.length;
			if (n == 0) {
				return -1;
			}
			if (t < a[0] && t > a[n - 1]) {
				return -1;
			}

			int lo = 0;
			int hi = n - 1;

			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				if (t < a[0]) {//answer is in right part
					if (a[mid] < a[0]) {
						if (a[mid] == t) return mid;
						if (a[mid] < t) lo = mid + 1;
						else hi = mid - 1;
					} else {
						lo = mid + 1;
					}
				} else {//answer is in left part
					if (a[mid] < a[0]) {
						hi = mid - 1;
					} else {
						if (a[mid] == t) return mid;
						if (a[mid] < t) lo = mid + 1;
						else hi = mid - 1;
					}
				}
			}
			return -1;
		}
	}
}

