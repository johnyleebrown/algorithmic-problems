package array.prefix;

/**
 * 308
 *
 * ======
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper
 * left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) =
 * (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * ======
 *
 * https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class RangeSumQuery2DMutable {
	/**
	 * Calc prefix sums on rows.
	 * Sumrange - sum differences of cols on rows.
	 */
	public static class Solution {
		public static class NumMatrix {

			private int[][] pre;

			public NumMatrix(int[][] a) {
				if (a.length == 0) return;
				pre = new int[a.length][a[0].length];
				for (int i = 0; i < a.length; i++) {
					for (int j = 0; j < a[0].length; j++) {
						pre[i][j] = (j > 0 ? pre[i][j - 1] : 0) + a[i][j];
					}
				}
			}

			public void update(int row, int col, int val) {
				int original = pre[row][col] - (col > 0 ? pre[row][col - 1] : 0);
				int incr = val - original;
				for (int i = col; i < pre[0].length; i++) {
					pre[row][i] += incr;
				}
			}

			public int sumRegion(int row1, int col1, int row2, int col2) {
				int ans = 0;
				for (int i = row1; i <= row2; i++) {
					ans += pre[i][col2] - (col1 > 0 ? pre[i][col1 - 1] : 0);
				}
				return ans;
			}
		}
	}
}