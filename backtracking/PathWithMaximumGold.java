/*
 * 1219
 * Google
 */
class Solution
{
	private boolean[][] seen;
	private int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
	private int max = Integer.MIN_VALUE;
	private final int maxcount = 25;

	public int getMaximumGold(int[][] grid)
	{
		int n = grid.length, m = grid[0].length;
		seen = new boolean[n][m];
		for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
		{
			if (hasNoGold(i, j, grid))
			{
				continue;
			}
			dfs(i, j, grid, grid[i][j], 1);
		}
		return max == Integer.MIN_VALUE ? 0 : max;
	}

	private void dfs(int i, int j, int[][] a, int amount, int c)
	{
		if (c > maxcount)
		{
			return;
		}
		max = Math.max(amount, max);
		seen[i][j] = true;
		for (int[] dir: dirs)
		{
			int newi = i + dir[0];
			int newj = j + dir[1];
			if (canVisitCell(newi, newj, a))
			{
				dfs(newi, newj, a, amount + a[newi][newj], c + 1);
			}
		}
		seen[i][j] = false;
	}

	private boolean canVisitCell(int i, int j, int[][] a)
	{
		return isValidCell(i, j, a)
			&& !hasBeenThere(i, j)
			&& !hasNoGold(i, j, a);
	}

	private boolean hasBeenThere(int i, int j)
	{
		return seen[i][j];
	}

	private boolean hasNoGold(int i, int j, int[][] a)
	{
		return a[i][j] == 0;
	}

	private boolean isValidCell(int i, int j, int[][] a)
	{
		return i >= 0 && j >= 0 && i < a.length && j < a[0].length;
	}
}

