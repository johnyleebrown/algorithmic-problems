/*
 * 695
 * Google
 */
class Solution
{
    private static int[][] path = new int[][]{{0,-1},{-1,0},{1,0},{0,1}};
    private static int n, m;

    public int maxAreaOfIsland(int[][] grid)
    {
        int maxArea = 0;
        n = grid.length;
        m = grid[0].length;

        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
        {
            if (grid[i][j] == 1)
            {
                grid[i][j] = 2;
                maxArea = Math.max(maxArea, dfs(i, j, grid));
            }

        }

        return maxArea;
    }

    private int dfs(int old_i, int old_j, int[][] grid)
    {
        int area = 1;

        for (int[] cell: path)
        {
            int i = old_i + cell[0], j = old_j + cell[1];

            if (i >= 0 && i < n && j >= 0 && j < m)
            {
                if (grid[i][j] == 1)
                {
                    grid[i][j] = 2;
                    area += dfs(i, j, grid);
                }
            }
        }

        return area;
    }
}

