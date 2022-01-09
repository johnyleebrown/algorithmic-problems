package sort.quick_sort;

/**
 * 973
 */
public class KClosestPointsToOrigin {

	/**
	 * Quick sort partitioning O(n), O(1)
	 */
	private static class Solution {

		public int[][] kClosest(int[][] points, int K) {
			int lo = 0, hi = points.length - 1;
			while (lo < hi) {
				int p = findPartition(lo, hi, points);
				if (p > K) {
					hi = p - 1;
				} else if (p < K) {
					lo = p + 1;
				} else {
					break;
				}
			}

			return convertListToArray(points, K);
		}

		private int[][] convertListToArray(int[][] ar, int K) {
			int[][] ans = new int[K][2];
			for (int i = 0; i < K; i++) {
				ans[i] = ar[i];
			}
			return ans;
		}

		private int findPartition(int lo, int hi, int[][] a) {
			int[] v = a[lo];
			int i = lo;
			int j = hi + 1;

			while (true) {
				while (less(a[++i], v)) {
					if (i == hi) {
						break;
					}
				}

				while (less(v, a[--j])) {
					if (j == lo) {
						break;
					}
				}

				if (i >= j) {
					break;
				}

				exch(a, i, j);
			}

			exch(a, lo, j);
			return j;
		}

		private boolean less(int[] a, int[] b) {
			return a[0] * a[0] + a[1] * a[1] < b[0] * b[0] + b[1] * b[1];
		}

		private void exch(int[][] a, int i, int j) {
			int[] temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
}
