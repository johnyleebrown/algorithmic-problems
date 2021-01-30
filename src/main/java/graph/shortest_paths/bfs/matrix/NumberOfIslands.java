package graph.shortest_paths.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200
 */
public class NumberOfIslands
{
	/*
	 * BFS
	 */
	class Solution {
		public int numIslands(char[][] grid) {
			if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
			int count = 0, n = grid.length, m = grid[0].length;
			int[][] dirs = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
			boolean[][] seen = new boolean[n][m];

			for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) 
			{
				if (!isValid(i, j, n, m, seen, grid)) continue;
				
				count++;
				
				Queue<int[]> queue = new LinkedList<>();
				queue.add(new int[]{i, j});
				
				while (!queue.isEmpty())
				{
					// pealing dosn't matter here
					int[] cell = queue.poll();
					for (int[] dir: dirs)
					{
						int newI = cell[0] + dir[0], newJ = cell[1] + dir[1];
						if (!isValid(newI, newJ, n, m, seen, grid)) continue;
						seen[newI][newJ] = true;
						queue.add(new int[]{newI, newJ});
					}
				}
			}

			return count;
		}
	}

	private boolean isValid(int i, int j, int n, int m, boolean[][] seen, char[][] grid)
	{
		return isInBounds(i, j, n, m) && !seen[i][j] && grid[i][j] == '1';
	}

	private boolean isInBounds(int i, int j, int n, int m)
	{
		return i >= 0 && j >= 0 && i < n && j < m;
	}

}

