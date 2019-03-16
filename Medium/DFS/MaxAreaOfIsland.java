package Medium.DFS;

/**
 * 695
 * https://leetcode.com/problems/max-area-of-island/
 *
 * Given a non-empty 2D array grid of 0's and 1's, an
 * island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded
 * by water.
 *
 * Find the maximum area of an island in the given 2D array.
 * (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11,
 * because the island must be connected 4-directionally.
 *
 * Similar idea:
 * {@link NumberofIslands}, {@link SurroundedRegions}
 */
public class MaxAreaOfIsland {
    /**
     * Beats 97.64%, 5%
     * Complexity: O(n), O(n)
     */
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0, n = grid.length, m = grid[0].length;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 2;
                        maxArea = Math.max(maxArea, dfs(i, j, grid, n, m,
                                new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}}));
                    }
                }
            }

            return maxArea;
        }

        private int dfs(int old_i, int old_j, int[][] grid, int n, int m, int[][] path) {
            int area = 1;

            for (int[] cell : path) {

                int i = old_i + cell[0], j = old_j + cell[1];

                if (i >= 0 && i < n && j >= 0 && j < m) {
                    if (grid[i][j] == 1) {
                        grid[i][j] = 2;
                        area += dfs(i, j, grid, n, m, path);
                    }
                }
            }

            return area;
        }
    }
}
