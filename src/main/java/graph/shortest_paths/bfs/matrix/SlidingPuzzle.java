package graph.shortest_paths.bfs.matrix;

import java.util.LinkedList;
import java.util.List;

/**
 * 773
 */
public class SlidingPuzzle {
	/**
	 * 123450 = 001 010 011 100 101 000 -> 42792
	 *
	 * 543210 = 101 100 011 010 001 000 -> 181896
	 */
	public static class Solution {
		private static int[][] d = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		private static int[] finalState = new int[]{1, 2, 3, 4, 5, 0};

		public int slidingPuzzle(int[][] ar) {
			if (ar.length == 0) {
				return 0;
			}

			List<Cell> q = new LinkedList<>();
			q.add(new Cell(ar));
			int ans = 0;
			boolean[] seen = new boolean[181896 + 1];

			while (!q.isEmpty()) {
				int s = q.size();
				while (--s >= 0) {
					Cell cur = q.remove(0);
					if (isFinalState(cur)) return ans;
					for (int[] dd : d) {
						int ni = cur.i + dd[0];
						int nj = cur.j + dd[1];
						if (ni < 0 || nj < 0 || ni >= 2 || nj >= 3) continue;
						Cell nc = new Cell(cur);
						nc.moveZero(ni, nj);
						int st = getBitMask(nc.b);
						if (seen[st]) continue;
						seen[st] = true;
						q.add(nc);
					}
				}
				ans++;
			}
			return -1;
		}

		private int getBitMask(int[][] ar) {
			int ans = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					ans |= ar[i][j];
					if (i != 1 || j != 2) {
						ans = ans << 3;
					}
				}
			}
			return ans;
		}

		private boolean isFinalState(Cell cur) {
			int k = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					if (cur.b[i][j] != finalState[k++]) {
						return false;
					}
				}
			}
			return true;
		}

		private class Cell {
			int[][] b = new int[2][3];
			int i, j;

			public Cell(int[][] b) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						if (b[i][j] == 0) {
							this.i = i;
							this.j = j;
						}
						this.b[i][j] = b[i][j];
					}
				}
			}

			public Cell(Cell c) {
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 3; j++) {
						b[i][j] = c.b[i][j];
					}
				}
				this.i = c.i;
				this.j = c.j;
			}

			public void moveZero(int ni, int nj) {
				int t = b[ni][nj];
				b[ni][nj] = 0;
				b[i][j] = t;
				i = ni;
				j = nj;
			}
		}
	}
}
