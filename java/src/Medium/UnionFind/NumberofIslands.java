package Medium.UnionFind;

/**
 * 200
 * <p>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberofIslands {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] marked = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!marked[i][j] && grid[i][j] != '0') {
                    count++;
                    search(marked, i, j, grid);
                }
            }
        }
        return count;
    }

    private void search(boolean[][] marked, int i, int j, char[][] grid) {
        if (marked[i][j]) return;
        marked[i][j] = true;
        if (grid[i][j] == '0') return;
        if (i + 1 < grid.length) search(marked, i + 1, j, grid);
        if (j + 1 < grid[0].length) search(marked, i, j + 1, grid);
        if (j - 1 >= 0) search(marked, i, j - 1, grid);
        if (i - 1 >= 0) search(marked, i - 1, j, grid);
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
