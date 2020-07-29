package graph.shortestPaths.bfs.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 286
 *
 * ======
 *
 * Task.
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to
 * represent INF as you may assume that the distance to a gate is less than
 * 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is
 * impossible to reach a gate, it should be filled with INF.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WallsAndGates {
	/**
	 * We are acknowledging that whoever gets to the INF cell first will have
	 * the actual shortest distance. Think abt it - we add 2 sources, and we
	 * take them from the queue in the same order and go through their children
	 * in the same order.
	 */
	public static class Solution {
		private static int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        private static final int INF = 2147483647;

		public void wallsAndGates(int[][] ar) {
			int n = ar.length;
			if (n == 0) return;
			int m = ar[0].length;
			Deque<Cell> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (ar[i][j] == 0) q.add(new Cell(i, j));
				}
			}
			while (!q.isEmpty()) {
				Cell x = q.removeFirst();
				for (int[] d : dirs) {
					int ni = d[0] + x.i;
					int nj = d[1] + x.j;
					if (ni < 0 || nj < 0 || ni >= n || nj >= m || ar[ni][nj] != INF)
						continue;
					ar[ni][nj] = ar[x.i][x.j] + 1;
					q.addLast(new Cell(ni, nj));
				}
			}
		}

		private static class Cell {
			int i, j;

			Cell(int ii, int jj) {
				i = ii;
				j = jj;
			}
		}
	}
}