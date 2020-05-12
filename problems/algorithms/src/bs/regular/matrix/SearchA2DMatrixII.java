package bs.regular.matrix;

/**
 * 240
 */
public class SearchA2DMatrixII {
	/**
	 * Start at upper right corner and go left or down.
	 */
	public static class Solution1 {
		public boolean searchMatrix(int[][] a, int t) {
			int n = a.length;
			if (n == 0) return false;
			int m = a[0].length;
			if (m == 0) return false;
			int i = 0;
			int j = m - 1;
			while (a[i][j] != t) {
				if (t > a[i][j]) i++;
				else j--;
				if (i == n || j == -1) return false;
			}
			return true;
		}
	}

	/**
	 * Find w/ bs the start row looking at m-1 row numbers, then regular bs.
	 */
	public static class Solution2 {
		int n, m;

		public boolean searchMatrix(int[][] a, int t) {
			n = a.length;
			if (n == 0)
				return false;
			m = a[0].length;
			if (m == 0)
				return false;
			int startI = bsClosest(a, t);
			if (startI == -1)
				return false;
			while (startI < n && a[startI][m - 1] >= t && a[startI][0] <= t) {
				if (regbs(a[startI], t) != -1) {
					return true;
				}
				startI++;
			}
			return false;
		}

		private int bsClosest(int[][] a, int t) {
			if (a[0][0] > t || a[n - 1][m - 1] < t)
				return -1;
			return bsNextSmallest(a, t);
		}

		private int bsNextSmallest(int[][] a, int t) {
			int lo = 0;
			int hi = n - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid][m - 1] < t) {
					lo = mid + 1;
				}
				else if (a[mid][m - 1] > t) {
					hi = mid - 1;
				}
				else {
					return mid;
				}
			}
			return lo;
		}

		private int regbs(int[] a, int t) {
			int lo = 0;
			int hi = m - 1;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (a[mid] < t) {
					lo = mid + 1;
				}
				else if (a[mid] > t) {
					hi = mid - 1;
				}
				else {
					return mid;
				}
			}
			return -1;
		}
	}
}
