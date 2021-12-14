package array.traverse;

/**
 * @company fb
 * @url https://leetcode.com/discuss/interview-question/1450908/Facebook-Interview-Question
 */
public class SumOfMatrixBorders {

	interface S {

		public int[] solve(int[][] matrix);
	}

	/**
	 * ans[i] если i <= j <= n - 1 - i
	 * либо ans[j]
	 */
	public static class Solution1 implements S {

		public int[] solve(int[][] a) {
			int n = a.length, m = n / 2;
			int[] ans = new int[m];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int x = i < m ? i : n - i - 1;
					if (j >= x && j <= n - 1 - x) {
						ans[x] += a[i][j];
					} else {
						int ind = j < m ? j : n - j - 1;
						ans[ind] += a[i][j];
					}
				}
			}

			return ans;
		}
	}

	/**
	 * Some solution.
	 */
	public static class Solution2 implements S {

		public int[] solve(int[][] matrix) {
			int rowSize = matrix.length;
			int[] ans = new int[rowSize / 2];

			for (int row = 0; row < rowSize / 2; row++) {
				for (int col = row; col < rowSize - row - 1; col++) {
					ans[row] += matrix[row][col];
					ans[row] += matrix[rowSize - col - 1][row];
					ans[row] += matrix[rowSize - row - 1][rowSize - col - 1];
					ans[row] += matrix[col][rowSize - row - 1];
				}
			}

			return ans;
		}
	}
}
