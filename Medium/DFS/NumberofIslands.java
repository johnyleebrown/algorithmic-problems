package Medium.DFS;

/**
 * 200
 * https://leetcode.com/problems/number-of-islands/
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count
 * the number of islands. An island is surrounded by water and
 * is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 * Similar Idea:
 * {@link SurroundedRegions}, {@link MaxAreaOfIsland}
 */
public class NumberofIslands {

    /**
     * 99%, 40%
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution1 {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            int counter = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        grid[i][j] = '2';
                        helper(i, j, grid, new int[]{-1, 0, 0, 1}, new int[]{0, -1, 1, 0});
                        counter++;
                    }
                }
            }

            return counter;
        }

        void helper(int i, int j, char[][] grid, int[] is, int[] js) {
            for (int k = 0; k < 4; k++) {
                if (j + js[k] >= 0 && j + js[k] < grid[0].length && i + is[k] >= 0 && i + is[k] < grid.length) {
                    if (grid[i + is[k]][j + js[k]] == '1') {
                        grid[i + is[k]][j + js[k]] = '2';
                        helper(i + is[k], j + js[k], grid, is, js);
                    }
                }
            }
        }
    }

/*
[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"],["1","1","1","1","1"]]
[]
[["1","1","1","1","0"]]
[["0","0","0","0","0"]]
[["0"]]
[["1"]]
[["1","1","1"],["0","1","0"],["1","1","1"]]
[["1","0","1","1","1"],["1","0","1","0","1"],["1","1","1","0","1"]]
 */
}
