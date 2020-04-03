package graph.dfs;

/**
 * 130
 */
public class SurroundedRegions {
	class Solution {
		private int[][] pattern = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

		public void solve(char[][] board) {
			if (board.length == 0 || board[0].length == 0) {
				return;
			}

			int n = board.length, m = board[0].length;
			boolean[][] seen = new boolean[n][m];

			for (int j = 0; j < m; j++) {
				// top
				setThirdColor(0, j, board, seen, n, m);

				// bottom
				setThirdColor(n - 1, j, board, seen, n, m);
			}
			for (int i = 1; i < n; i++) {
				// right
				setThirdColor(i, m - 1, board, seen, n, m);

				// left
				setThirdColor(i, 0, board, seen, n, m);
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (board[i][j] == 'O') {
						board[i][j] = 'X';
					}
					else if (board[i][j] == '*') {
						board[i][j] = 'O';
					}
				}
			}
		}

		private void setThirdColor(int i, int j, char[][] board, boolean[][] seen, int n, int m) {
			if (i < 0 || j < 0 || i >= n || j >= m) {
				return;
			}

			if (board[i][j] == 'O' && !seen[i][j]) {
				board[i][j] = '*';
				dfs(i, j, board, seen, n, m);
			}
		}

		private void dfs(int old_i, int old_j, char[][] board, boolean[][] seen, int n, int m) {
			for (int[] cell : pattern) {
				int i = old_i + cell[0], j = old_j + cell[1];
				setThirdColor(i, j, board, seen, n, m);
			}
		}
	}
}