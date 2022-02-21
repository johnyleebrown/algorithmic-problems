package array.counter;

/**
 * 348
 */
public class DesignTicTacToe {

	/**
	 * Counters for each person for rows, cols and 2 diags.
	 */
	public static class Solution {

		class TicTacToe {

			int[][] rows;
			int[][] cols;
			int[] d1 = new int[3];
			int[] d2 = new int[3];
			int n;

			public TicTacToe(int n) {
				rows = new int[n][3];
				cols = new int[n][3];
				this.n = n;
			}

			public int move(int r, int c, int p) {
				rows[r][p]++;
				if (rows[r][p] == n)
					return p;
				cols[c][p]++;
				if (cols[c][p] == n)
					return p;
				if (r - c == 0) {
					d1[p]++;
					if (d1[p] == n)
						return p;
				}
				if (r + c == n - 1) {
					d2[p]++;
					if (d2[p] == n)
						return p;
				}
				return 0;
			}
		}
	}
}
