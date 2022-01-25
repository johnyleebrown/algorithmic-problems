package sort.quick_sort;

/**
 * 973
 */
public class KClosestPointsToOrigin {

	/**
	 * Quick sort partitioning O(n), O(1)
	 */
	private static class Solution {

		public int[][] kClosest(int[][] ar, int k) {
			// quick select
			int lo = 0, hi = ar.length - 1;
			while (lo < hi) {
				int splitIndex = partition(lo, hi, ar);
				if (splitIndex == k) {
					break;
				} else if (splitIndex < k) {
					lo = splitIndex + 1;
				} else {
					hi = splitIndex - 1;
				}
			}
			return convert(ar, k);
		}

		int[][] convert(int[][] ar, int k) {
			int[][] ans = new int[k][2];
			for (int i = 0; i < k; i++) {
				ans[i] = ar[i];
			}
			return ans;
		}

		int partition(int lo, int hi, int[][] ar) {
			int[] v = ar[lo];
			int i = lo, j = hi + 1;
			while (true) {
				while (less(ar[++i], v)) {
					if (i == hi) break;
				}
				while (larger(ar[--j], v)) {
					if (j == lo) break;
				}
				if (i >= j) break;
				exch(ar, i, j);
			}
			exch(ar, lo, j);
			return j;
		}

		void exch(int[][] ar, int i, int j) {
			int[] t = ar[i];
			ar[i] = ar[j];
			ar[j] = t;
		}

		boolean less(int[] p1, int[] p2) {
			return dist(p1) < dist(p2);
		}

		boolean larger(int[] p1, int[] p2) {
			return dist(p1) > dist(p2);
		}

		int dist(int[] p) {
			return p[0] * p[0] + p[1] * p[1];
		}
	}
}
