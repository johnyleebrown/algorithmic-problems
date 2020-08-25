package array.traverse;

/**
 * 861
 */
public class ScoreAfterFlippingMatrix {
	public static class Solution {
		public int matrixScore(int[][] A) {
			for (int i = 0; i < A.length; i++) {
				if (A[i][0] == 0) flipRow(A[i]);
			}

			for (int i = 0; i < A[0].length; i++) {
				if (moreZeroes(i, A)) flipColumn(i, A);
			}

			int res = 0;
			for (int i = 0; i < A.length; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < A[0].length; j++) sb.append(A[i][j]);
				res += Integer.parseInt(sb.toString(), 2);
			}

			return res;
		}

		boolean moreZeroes(int col, int[][] matrix) {
			int count = 0;
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][col] == 1) count++;
				else count--;
			}
			return count < 0;
		}

		void flipColumn(int col, int[][] matrix) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][col] = flip(matrix[i][col]);
			}
		}

		void flipRow(int[] row) {
			for (int i = 0; i < row.length; i++) {
				row[i] = flip(row[i]);
			}
		}

		int flip(int cell) {
			if (cell == 1) return 0;
			else return 1;
		}
	}
}