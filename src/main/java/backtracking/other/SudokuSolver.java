package backtracking.other;

/**
 * 37
 */
public class SudokuSolver {
	/**
	 * Backtracking classic. As seen array - keep track of x axis, y axis and
	 * cubes. First, we preprocess, set what is already filled.
	 */
	private static class Solution1 {
		private boolean[][][] a = new boolean[3][10][10];

		public void solveSudoku(char[][] b) {
			preProcess(b);
			gen(b, 0, 0);
		}

		private void preProcess(char[][] b) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (b[i][j] == '.')
						continue;

					int cubeId = calculateCube(i, j);
					int val = getIntFromChar(b[i][j]);

					a[0][cubeId][val] = a[1][i][val] = a[2][j][val] = true;
				}
			}
		}

		private boolean gen(char[][] b, int ii, int jj) {
			if (ii == 8 && jj == 8) {
				return true;
			} else {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (b[i][j] != '.')
							continue;

						for (char k = '1'; k <= '9'; k++) {
							if (!isValid(i, j, k))
								continue;

							setFlags(i, j, k, true, b);

							if (gen(b, i, j))
								return true;

							setFlags(i, j, k, false, b);
						}

						// if we could nt been able to set anything correct
						// otherwise the gen would ve returned true
						return false;
					}
				}

				return true;
			}
		}

		void setFlags(int i, int j, char c, boolean f, char[][] b) {
			b[i][j] = f ? c : '.';
			int cubeId = calculateCube(i, j);
			int val = getIntFromChar(c);
			a[0][cubeId][val] = a[1][i][val] = a[2][j][val] = f;
		}

		int calculateCube(int i, int j) {
			return (i / 3) * 3 + j / 3;
		}

		int getIntFromChar(int c) {
			return c - '0';
		}

		private boolean isValid(int i, int j, char c) {
			int cubeId = calculateCube(i, j);
			int val = getIntFromChar(c);
			return !a[0][cubeId][val] && !a[1][i][val] && !a[2][j][val];
		}
	}

	class Solution2 {
		// to solve we try different combinations of filling the cells
		// since every cell has to be filled out with numbers 1-9 then
		// we can choose as a starting position first empty cell
		// and iterate over it's valid variants

		public void solveSudoku(char[][] board) {
			solver(board);
		}

		private boolean solver(char[][] board) {
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++) {
					if (board[i][j] != '.') continue;
					for (char k = '1'; k <= '9'; k++) {
						if (!isValidCell(i, j, k, board)) continue;

						board[i][j] = k;

						if (solver(board)) return true;
						else board[i][j] = '.';
					}

					// return false because we tried to generate a number
					// for a cell but haven't found the right one
					return false;
				}

			return true;
		}

		private boolean isValidCell(int i, int j, char k, char[][] board) {
			for (int ind = 0; ind < 9; ind++) {
				// need to check if in the row or in the col there is no k
				if (board[i][ind] == k || board[ind][j] == k) return false;

				// need to check if in the block there in no k
				// [i - i%3, j - j%3] - the starting cell of the block
				// ind/3 - guarantees vals from 0 to 2
				if (board[i - i % 3 + ind / 3][j - j % 3 + ind % 3] == k)
					return false;
			}

			return true;
		}
	}
}