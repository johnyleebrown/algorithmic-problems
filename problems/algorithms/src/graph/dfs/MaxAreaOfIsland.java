package graph.dfs;

/**
 * 695
 */
class MaxAreaOfIsland
{
	class Solution
	{
		private int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
		private int n, m;

		public int maxAreaOfIsland(int[][] grid)
		{
			int maxArea = 0;
			n = grid.length;
			m = grid[0].length;

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
				{
					if (grid[i][j] == 1)
					{
						grid[i][j] = 2;
						maxArea = Math.max(maxArea, dfs(i, j, grid));
					}
				}

			return maxArea;
		}

		private int dfs(int i, int j, int[][] grid)
		{
			int area = 1;

			for (int[] dir : dirs)
			{
				int newi = i + dir[0];
				int newj = j + dir[1];

				if (!isValid(newi, newj))
					continue;

				if (grid[newi][newj] == 1)
				{
					grid[newi][newj] = 2;
					area += dfs(newi, newj, grid);
				}
			}

			return area;
		}

		private boolean isValid(int i, int j)
		{
			return i >= 0 && i < n && j >= 0 && j < m;
		}
	}
}