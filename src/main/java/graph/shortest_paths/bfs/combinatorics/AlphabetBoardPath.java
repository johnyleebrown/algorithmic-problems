package graph.shortest_paths.bfs.combinatorics;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1138
 */
public class AlphabetBoardPath
{
	class Solution
	{
		private char[][] b = new char[][]{
				{'a', 'b', 'c', 'd', 'e'},
				{'f', 'g', 'h', 'i', 'j'},
				{'k', 'l', 'm', 'n', 'o'},
				{'p', 'q', 'r', 's', 't'},
				{'u', 'v', 'w', 'x', 'y'},
				{'z'}
		};

		private int n;

		private Dir[] dirs;
		private Cell prev;

		public String alphabetBoardPath(String target)
		{
			n = b.length;
			dirs = new Dir[]{new Dir("D", 1, 0), new Dir("R", 0, 1),
					new Dir("U", -1, 0), new Dir("L", 0, -1)};
			prev = new Cell(0, 0);

			String res = "";
			for (int i = 0; i < target.length(); i++)
			{
				res += bfs(prev, target.charAt(i)) + "!";
			}

			return res;
		}

		private String bfs(Cell start, char end)
		{
			if (theRightCell(start, end))
			{
				return "";
			}

			Queue<Cell> q = new LinkedList<>();
			q.add(start);

			while (!q.isEmpty())
			{
				int s = q.size();

				while (--s >= 0)
				{
					Cell cellAt = q.poll();

					for (Dir cellTo : dirs)
					{
						Cell newCell = new Cell(cellAt.i + cellTo.dir.i, cellAt.j + cellTo.dir.j);

						if (isInvalidCell(newCell))
						{
							continue;
						}

						newCell.path = cellAt.path + cellTo.title;

						if (theRightCell(newCell, end))
						{
							String ret = newCell.path;
							prev = newCell;
							prev.path = "";
							return ret;
						}

						q.add(newCell);
					}
				}
			}

			return "";
		}

		private boolean theRightCell(Cell c, char end)
		{
			return b[c.i][c.j] == end;
		}

		private boolean isInvalidCell(Cell c)
		{
			if (c.i >= 0 && c.i < 5 && c.j >= 0 && c.j < 5)
			{
				return false;
			}
			else if (c.i == 5 && c.j == 0)
			{
				return false;
			}

			return true;
		}

		private class Dir
		{
			String title;
			Cell dir;

			public Dir(String title, int i, int j)
			{
				this.title = title;
				this.dir = new Cell(i, j);
			}
		}

		private class Cell
		{
			int i, j;
			String path;

			public Cell(int i, int j)
			{
				this.i = i;
				this.j = j;
				this.path = "";
			}
		}
	}
}
