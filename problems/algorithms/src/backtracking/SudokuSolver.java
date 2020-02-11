package backtracking;

/**
 * 37
 */
public class SudokuSolver
{
	class Solution
	{
		// to solve we try different combinations of filling the cells
		// since every cell has to be filled out with numbers 1-9 then
		// we can choose as a starting position first empty cell
		// and iterate over it's valid variants

		public void solveSudoku(char[][] board)
		{
			solver(board);
		}

		private boolean solver(char[][] board)
		{
			for (int i = 0; i < 9; i++)
				for (int j = 0; j < 9; j++)
				{
					if (board[i][j] != '.') continue;
					for (char k = '1'; k <= '9'; k++)
					{
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

		private boolean isValidCell(int i, int j, char k, char[][] board)
		{
			for (int ind = 0; ind < 9; ind++)
			{
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