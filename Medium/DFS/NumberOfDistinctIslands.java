package medium.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 694
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same
 * as another if and only if one island can be translated (and not rotated or
 * reflected) to equal the other.
 *
 * Example 1: 11000 11000 00011 00011 Given the above grid map, return 1.
 * Example 2: 11011 10000 00001 11011 Given the above grid map, return 3.
 *
 * Notice that: 11 1 and 1 11 are considered different island shapes, because we
 * do not consider reflection / rotation. Note: The length of each dimension in
 * the given grid does not exceed 50.
 *
 * Similar: {@link MaxAreaOfIsland} {@link NumberofIslands} {@link SurroundedRegions}
 */
public class NumberOfDistinctIslands {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        Set<String> set = new HashSet<>();
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 2;
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, grid, n, m, sb, new int[][]{{-1,0},{0,-1},{0,1},{1,0}});
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    private void dfs(int old_i, int old_j, int[][] grid, int n, int m, StringBuilder sb,
                     int[][] cells) {
        for (int k = 0; k < 4; k++) {
            int i = old_i + cells[k][0], j = old_j + cells[k][1];
            if (i >= 0 && j >= 0 && i < n && j < m && grid[i][j] == 1) {
                grid[i][j] = 2;
                sb.append(k);
                dfs(i, j, grid, n, m, sb, cells);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands n = new NumberOfDistinctIslands();

        int ans = n.numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        });

        System.out.println(ans); // 1

        ans = n.numDistinctIslands(new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 1}
        });

        System.out.println(ans); // 2

        ans = n.numDistinctIslands(new int[][]{
                {1, 0, 1, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 1}
        });

        System.out.println(ans); // 3

        ans = n.numDistinctIslands(new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        });

        System.out.println(ans); // 3

        ans = n.numDistinctIslands(new int[][]{
                {1, 1, 1, 1},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {1, 1, 0, 1}
        });

        System.out.println(ans); // 2

        ans = n.numDistinctIslands(new int[][]{
                {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
        });

        System.out.println(ans); // 14
    }
}
