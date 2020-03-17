package array.counter;

/**
 * 348
 *
 * ======
 *
 * Task.
 *
 * Design a Tic-tac-toe game that is played between two players on a n x n
 * grid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block. Once a
 * winning condition is reached, no more moves is allowed. A player who succeeds
 * in placing n of their marks in a horizontal, vertical, or diagonal row wins
 * the game.
 *
 * ======
 *
 * Similar: Sudoku validation.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DesignTicTacToe
{
	/**
	 * Counters for each person for rows, cols and 2 diags.
	 */
	public static class Solution
	{
		class TicTacToe
		{
			int[][] rows;
			int[][] cols;
			int[] d1 = new int[3];
			int[] d2 = new int[3];
			int n;

			public TicTacToe(int n)
			{
				rows = new int[n][3];
				cols = new int[n][3];
				this.n = n;
			}

			public int move(int r, int c, int p)
			{
				rows[r][p]++;
				if (rows[r][p] == n)
					return p;
				cols[c][p]++;
				if (cols[c][p] == n)
					return p;
				if (r - c == 0)
				{
					d1[p]++;
					if (d1[p] == n)
						return p;
				}
				if (r + c == n - 1)
				{
					d2[p]++;
					if (d2[p] == n)
						return p;
				}
				return 0;
			}
		}
	}
}
