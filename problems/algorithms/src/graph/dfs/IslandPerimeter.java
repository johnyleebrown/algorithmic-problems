package graph.dfs;

/**
 * 463
 *
 * ======
 *
 * Task.
 *
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0
 * represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely
 * surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the
 * island). One cell is a square with side length 1. The grid is rectangular, width and height don't
 * exceed 100. Determine the perimeter of the island.
 *
 * ======
 *
 * Source: Leetcode
 */
public class IslandPerimeter {
	/**
	 * Dfs solution (go left, down, right, up), count where we can and can't go. If cell is
	 * invalid or the cell is water +1, if we have been there +0.
	 */
	public static class Solution {
		static int n, m;
		static int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

		public int islandPerimeter(int[][] ar) {
			n = ar.length;
			if (n == 0) return 0;
			m = ar[0].length;
			if (m == 0) return 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (ar[i][j] == 1) {
						return dfs(i, j, ar, new boolean[n][m]);
					}
				}
			}
			return 0;
		}

		private int dfs(int i, int j, int[][] ar, boolean[][] seen) {
			if (i < 0 || j < 0 || i >= n || j >= m) {
				return 1;
			}
			if (seen[i][j]) {
				return 0;
			}
			if (ar[i][j] == 0) {
				return 1;
			}
			seen[i][j] = true;
			int ans = 0;
			for (int[] dir : dirs) {
				ans += dfs(i + dir[0], j + dir[1], ar, seen);
			}
			return ans;
		}
	}
}