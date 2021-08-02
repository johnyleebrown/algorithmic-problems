package graph.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 827. Making A Large Island
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 *
 * https://leetcode.com/problems/making-a-large-island/
 */
public class MakingALargeIsland {
	/**
	 * next solution - optimize by collecting all the 0 locations on the first run
	 */
	public static class Solution1 {
		private static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

		public int largestIsland(int[][] grid) {
			Map<Integer, Integer> map = new HashMap<>();
			findIslands(grid, map);
			int n = grid.length;
			int m = grid[0].length;
			int res = 0;
			if (map.size() == 0) return 1;
			map.put(0, 0);
			// could put all 0s into the queue
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 0) {
						Set<Integer> set = new HashSet<>();
						for (int[] dir : dirs) {
							int nextI = dir[0] + i;
							int nextJ = dir[1] + j;
							if (nextI < 0 || nextJ < 0 || nextI >= n || nextJ >= m) {
								continue;
							}
							set.add(grid[nextI][nextJ]);
						}
						int local = 0;
						for (int x : set) {
							local += map.get(x);
						}
						res = Math.max(res, local);
					}
				}
			}
			return res + 1 == 1 ? n * m : res + 1;
		}

		void findIslands(int[][] grid, Map<Integer, Integer> map) {
			int n = grid.length;
			int m = grid[0].length;
			int islandName = 2;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 1) {
						int res = dfs(i, j, grid, islandName);
						map.put(islandName, res);
						islandName++;
					}
				}
			}
		}

		int dfs(int i, int j, int[][] grid, int islandName) {
			if (grid[i][j] == 0 || grid[i][j] > 1) {
				return 0;
			}
			grid[i][j] = islandName;
			int res = 0;
			for (int[] dir : dirs) {
				int nextI = dir[0] + i;
				int nextJ = dir[1] + j;
				if (nextI < 0 || nextJ < 0 || nextI >= grid.length || nextJ >= grid[0].length) {
					continue;
				}
				res += dfs(nextI, nextJ, grid, islandName);
			}
			return res + 1;
		}
	}
}
