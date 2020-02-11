package graph.shortestPaths.bfs.board;

import java.util.ArrayList;
import java.util.List;

/**
 * 1197
 */
public class MinimumKnightMoves
{
	class Solution
	{
		private int[][] dirs = new int[][]{
				{3, 1}, {1, 2}, {-1, 2}, {-2, 1},
				{-2, -1}, {-1, -2}, {1, -2}, {2, -1}
		};

		private int max = 601, avg = 300;

		public int minKnightMoves(int x, int y)
		{
			if (x == 0 && y == 0) return 0;

			boolean[][] seen = new boolean[max][max];

			List<Cell> q = new ArrayList<>();
			q.add(new Cell(avg, avg));

			int count = 0;
			while (!q.isEmpty())
			{
				count++;

				int size = q.size();
				while (--size >= 0)
				{
					Cell cell = q.remove(0);

					for (int[] dir : dirs)
					{
						int newi = cell.i + dir[0], newj = cell.j + dir[1];

						if ((newi - avg) == y && (newj - avg) == x) return count;

						if (!isInBounds(newi, newj) || seen[newi][newj]) continue;

						seen[newi][newj] = true;
						q.add(new Cell(newi, newj));
					}
				}
			}

			return count;
		}

		private class Cell
		{
			int i, j;

			Cell(int i, int j)
			{
				this.i = i;
				this.j = j;
			}
		}

		boolean isInBounds(int i, int j)
		{
			return i >= 0 && i <= 600 && j >= 0 && j <= 600;
		}
	}
}
