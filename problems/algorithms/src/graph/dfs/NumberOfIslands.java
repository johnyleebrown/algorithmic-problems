package graph.dfs;

/**
 * 200
 */
public class NumberOfIslands {
	public static class Solution {
		private int n, m;
		private int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

		public int numIslands(char[][] ar) {
			n = ar.length;
			if (n == 0) return 0;
			m = ar[0].length;
			if (m == 0) return 0;
			boolean[][] seen = new boolean[n][m];
			int c = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (dfs(i, j, seen, ar)) {
						c++;
					}
				}
			}
			return c;
		}

		private boolean dfs(int i, int j, boolean[][] seen, char[][] ar) {
			if (!isValid(i, j) || seen[i][j] || ar[i][j] != '1') {
				return false;
			}
			seen[i][j] = true;
			for (int[] d : dirs) {
				dfs(i + d[0], j + d[1], seen, ar);
			}
			return true;
		}

		private boolean isValid(int i, int j) {
			return i >= 0 && j >= 0 && i < n && j < m;
		}
	}
}

