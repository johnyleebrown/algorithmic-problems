package array.traverse;

/**
 * 498
 */
public class DiagonalTraverse {
	class Solution {
		public int[] findDiagonalOrder(int[][] matrix) {
			if (matrix.length == 0 || matrix[0].length == 0) return new int[]{};
			int n = matrix.length, m = matrix[0].length;
			int[] ans = new int[n * m];
			boolean up = true;
			int c = 0;

			for (int i = 0, j = 0; i < n && j < m; ) {
				ans[c++] = matrix[i][j];

				if (up) {
					up = false;
					if (j == m - 1) i++;
					else if (i == 0) j++;
					else {
						i--;
						j++;
						up = true;
					}
				} else {
					up = true;
					if (i == n - 1) j++;
					else if (j == 0) i++;
					else {
						i++;
						j--;
						up = false;
					}
				}
			}

			return ans;
		}
	}
}
