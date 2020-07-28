package unionFind.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 305
 */
public class NumberOfIslandsII {
	public static class Solution {
		private static final int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

		public List<Integer> numIslands2(int n, int m, int[][] ar) {
			List<Integer> ans = new ArrayList<>();
			if (n == 0 || m == 0) {
				return ans;
			}
			UF uf = new UF(n, m);
			for (int[] cell : ar) {
				uf.add(cell[0], cell[1]);
				ans.add(uf.getCount());
			}
			return ans;
		}

		private class UF {
			Cell[][] par;
			int c;
			int[][] rank;
			int n, m;

			public UF(int n, int m) {
				this.n = n;
				this.m = m;
				rank = new int[n][m];
				par = new Cell[n][m];
			}

			public void union(int i1, int j1, int i2, int j2) {

				Cell p1 = find(i1, j1);
				Cell p2 = find(i2, j2);

				if (p1.equals(p2)) {
					return;
				}

				if (rank[p1.i][p1.j] > rank[p2.i][p2.j]) {
					par[p2.i][p2.j] = p1;
				} else if (rank[p1.i][p1.j] < rank[p2.i][p2.j]) {
					par[p1.i][p1.j] = p2;
				} else {
					rank[p1.i][p1.j]++;
					par[p2.i][p2.j] = p1;
				}

				c--;
			}

			public void add(int i, int j) {
				if (par[i][j] != null) return;
				par[i][j] = new Cell(i, j);
				c++;
				for (int[] d : dirs) {
					int newi = i + d[0];
					int newj = j + d[1];
					if (isValid(newi, newj) && par[newi][newj] != null) {
						union(i, j, newi, newj);
					}
				}
			}

			private boolean isValid(int i, int j) {
				return i >= 0 && j >= 0 && i < n && j < m;
			}

			private Cell find(int i, int j) {
				Cell cur = par[i][j];
				while (cur != par[cur.i][cur.j]) {
					Cell p = par[cur.i][cur.j];
					Cell pp = par[p.i][p.j];
					par[cur.i][cur.j] = pp;
					cur = pp;
				}
				return cur;
			}

			public int getCount() {
				return c;
			}
		}

		private class Cell {
			int i, j;

			public Cell(int i, int j) {
				this.i = i;
				this.j = j;
			}

			public boolean equals(Object o) {
				Cell other = (Cell) o;
				return other.i == this.i && other.j == this.j;
			}
		}
	}
}
