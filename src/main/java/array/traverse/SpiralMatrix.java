package array.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 54
 */
public class SpiralMatrix {

	/**
	 * Robot cleaner style
	 */
	public static class Solution0 {

		int i = 0, j = 0, d = 1, iStart = 0, iEnd, jStart = 0, jEnd, count;

		public List<Integer> spiralOrder(int[][] matrix) {
			count = matrix.length * matrix[0].length;
			iEnd = matrix.length - 1;
			jEnd = matrix[0].length - 1;
			List<Integer> ans = new ArrayList<>();
			while (--count >= 0) {
				ans.add(matrix[i][j]);
				if (reachedLimit()) {
					turnRight();
				}
				move();
			}
			return ans;
		}

		private boolean reachedLimit() {
			if (d == 0) return i == iStart;
			else if (d == 1) return j == jEnd;
			else if (d == 2) return i == iEnd;
			else return j == jStart;
		}

		private void turnRight() {
			if (d == 0) jStart++;
			else if (d == 1) iStart++;
			else if (d == 2) jEnd--;
			else iEnd--;
			d = (d + 1) % 4;
		}

		private void move() {
			if (d == 0) i--;
			else if (d == 1) j++;
			else if (d == 2) i++;
			else j--;
		}
	}

	public static class Solution {

		public List<Integer> spiralOrder(int[][] ar) {
			List<Integer> ans = new ArrayList<>();
			if (ar == null) {
				return ans;
			}
			int n = ar.length;
			if (n == 0) {
				return ans;
			}
			int m = ar[0].length;
			if (m == 0) {
				return ans;
			}
			dir d = dir.R;
			int i = 0;
			int j = 0;
			int c = m * n;
			int x = 0;
			while (--c >= 0) {
				ans.add(ar[i][j]);
				if (d == dir.R) {
					if (j != m - 1 - x) {
						j++;
					} else {
						i++;
						d = dir.D;
					}
				} else if (d == dir.D) {
					if (i != n - 1 - x) {
						i++;
					} else {
						j--;
						d = dir.L;
					}
				} else if (d == dir.L) {
					if (j != x) {
						j--;
					} else {
						i--;
						d = dir.U;
						x++;
					}
				} else {
					if (i != x) {
						i--;
					} else {
						j++;
						d = dir.R;
					}
				}
			}
			return ans;
		}

		enum dir {
			U, D, L, R;
		}
	}

	public static class Solution2 {

		public List<Integer> spiralOrder(int[][] a) {
			List<Integer> res = new LinkedList<>();
			int n = a.length;
			if (n == 0) {
				return res;
			}
			int m = a[0].length;
			if (m == 0) {
				return res;
			}
			int l = 0;//3
			int r = m - 1;//1
			int t = 1;//4
			int b = n - 1;//2
			int max = n * m;
			int dir = 1;
			int i = 0;
			int j = 0;
			while (max > 0) {
				if (dir == 1) {
					while (j <= r) {
						res.add(a[i][j]);
						j++;
						max--;
					}
					dir = 2;
					r--;
					i++;
					j--;
				} else if (dir == 2) {
					while (i <= b) {
						res.add(a[i][j]);
						i++;
						max--;
					}
					dir = 3;
					b--;
					j--;
					i--;
				} else if (dir == 3) {
					while (j >= l) {
						res.add(a[i][j]);
						j--;
						max--;
					}
					dir = 4;
					l++;
					i--;
					j++;
				} else {
					while (i >= t) {
						res.add(a[i][j]);
						i--;
						max--;
					}
					dir = 1;
					t++;
					j++;
					i++;
				}
			}
			return res;
		}
	}

	/**
	 * Recursive. One method call goes around all perimeter.
	 */
	public static class Solution3 {

		public List<Integer> spiralOrder(int[][] matrix) {
			// Check for edge cases for the matrix
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
				return new ArrayList<Integer>();

			List<Integer> res = new ArrayList<Integer>();
			// Run our recursion starting with the first and last eligible rows and the first and last eligible columns
			spiralOut(res, matrix, 0, matrix.length - 1, 0, matrix[0].length - 1);
			return res;
		}

		private void spiralOut(List<Integer> res, int[][] m, int r1, int r2, int c1,
				int c2) {
			// Return if we've exhausted all values to the point of invalid indices
			if (r1 > r2 || c1 > c2) return;

			// TOP ROW: left to right
			for (int c = c1; c <= c2; c++) {
				res.add(m[r1][c]);
			}

			// RIGHT COLUMN: top to bottom
			for (int r = r1 + 1; r <= r2; r++) {
				res.add(m[r][c2]);
			}

			// Return if we've processed the last row/column because we'd otherwise repeat values
			if (r1 == r2 || c1 == c2) return;

			// BOTTOM ROW: right to left
			for (int c = c2 - 1; c >= c1; c--) {
				res.add(m[r2][c]);
			}

			// LEFT COLUMN: bottom to top
			for (int r = r2 - 1; r >= r1 + 1; r--) {
				res.add(m[r][c1]);
			}

			// Recursion through the inner matrix
			spiralOut(res, m, r1 + 1, r2 - 1, c1 + 1, c2 - 1);
		}
	}
}
