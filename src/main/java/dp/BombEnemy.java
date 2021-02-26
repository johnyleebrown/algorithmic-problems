package dp;

/**
 * 361. Bomb Enemy
 *
 * ======
 *
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
 * return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits
 * the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 *
 * Example:
 *
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Explanation: For the given grid,
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * Placing a bomb at (1,1) kills 3 enemies.
 *
 * ======
 *
 * https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {
	/**
	 * BF.
	 */
	public static class Solution {
		public int maxKilledEnemies(char[][] a) {
			int n = a.length;
			if (n == 0) return 0;
			int m = a[0].length;
			if (m == 0) return 0;
			int ans = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (a[i][j] != '0') continue;
					int e = 0;
					// go down
					for (int k = i + 1; k < n && a[k][j] != 'W'; k++) {
						if (a[k][j] == 'E') {
							e++;
						}
					}
					// go up
					for (int k = i - 1; k >= 0 && a[k][j] != 'W'; k--) {
						if (a[k][j] == 'E') {
							e++;
						}
					}
					// go right
					for (int k = j + 1; k < m && a[i][k] != 'W'; k++) {
						if (a[i][k] == 'E') {
							e++;
						}
					}
					// go left
					for (int k = j - 1; k >= 0 && a[i][k] != 'W'; k--) {
						if (a[i][k] == 'E') {
							e++;
						}
					}
					if (a[i][j] == 'E') {
						e++;
					}
					ans = Math.max(ans, e);
				}
			}
			return ans == Integer.MIN_VALUE ? 0 : ans;
		}
	}
}