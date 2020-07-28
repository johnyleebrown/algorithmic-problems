package graph.dfs;

/**
 * 130
 */
public class SurroundedRegions {
	/**
	 * Color recursively O connected to the border to 3rd color(i.e. '*'), then just color O->X
	 * and *->O.
	 */
	public static class Solution {
		private static final int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

		public void solve(char[][] ar) {
			int n = ar.length;
			if (n == 0) {
				return;
			}
			int m = ar[0].length;
			if (m == 0) {
				return;
			}

			for (int j = 0; j < m; j++) {
				dfs(0, j, ar, n, m); // top
				dfs(n - 1, j, ar, n, m); // bottom
			}
			for (int i = 1; i < n; i++) {
				dfs(i, m - 1, ar, n, m); // right
				dfs(i, 0, ar, n, m); // left
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (ar[i][j] == 'O') {
						ar[i][j] = 'X';
					} else if (ar[i][j] == '*') {
						ar[i][j] = 'O';
					}
				}
			}
		}

		private void dfs(int i, int j, char[][] ar, int n, int m) {
			if (i < 0 || j < 0 || i >= n || j >= m || ar[i][j] != 'O') {
				return;
			}
			ar[i][j] = '*';
			for (int[] cell : dirs) {
				dfs(i + cell[0], j + cell[1], ar, n, m);
			}
		}
	}
}