package graph.shortestPaths.bfs.board;

import java.util.*;

/**
 * 773
 */
public class SlidingPuzzle
{
	class Solution
	{
		private String toStr(int[][] arr)
		{
			return Arrays.deepToString(arr);
		}

		public int slidingPuzzle(int[][] board)
		{
			// edge cases
			if (board == null || board.length == 0 || board[0].length == 0) return -1;
			int[][] targetBoard = new int[][]{{1, 2, 3}, {4, 5, 0}};
			if (Arrays.deepEquals(board, targetBoard)) return 0;

			// essentials
			int n = board.length, m = board[0].length;
			int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
			Set<String> seen = new HashSet<>();
			seen.add(toStr(board));

			// queues
			Queue<int[][]> q = new LinkedList<>();
			q.add(board);
			Queue<int[]> cellsWithZero = new LinkedList<>();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
				{
					if (board[i][j] == 0) cellsWithZero.add(new int[]{i, j});
				}

			int moves = 0;
			while (!q.isEmpty())
			{
				moves++;
				int size = q.size();
				while (--size >= 0)
				{
					int[] baseCell = cellsWithZero.poll();
					int i = baseCell[0], j = baseCell[1];
					int[][] newBoard = q.poll();
					for (int[] dir : dirs)
					{
						// coordinates of possible move
						int newI = i + dir[0], newJ = j + dir[1];
						// if we cant go there continue
						if (!isValidCell(newI, newJ, n, m)) continue;
						// copy array with replaced cells
						int[][] possibleMatch = copyArr(newBoard, n, m, i, j, newI, newJ);
						// if we already seen that match we dont put it in the queue 	
						if (!seen.add(toStr(possibleMatch))) continue;
						// if it is the variant we ve been looking 4, return moves
						if (Arrays.deepEquals(possibleMatch, targetBoard)) return moves;

						q.add(possibleMatch);
						cellsWithZero.add(new int[]{newI, newJ});
					}
				}
			}

			return -1;
		}

		private boolean isValidCell(int i, int j, int n, int m)
		{
			return i >= 0 && j >= 0 && i < n && j < m;
		}

		private int[][] copyArr(int[][] board, int n, int m, int oldI, int oldJ, int newI, int newJ)
		{
			int[][] newArray = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
				{
					newArray[i][j] = board[i][j];
				}
			newArray[newI][newJ] = board[oldI][oldJ];
			newArray[oldI][oldJ] = board[newI][newJ];
			return newArray;
		}
	}
}
