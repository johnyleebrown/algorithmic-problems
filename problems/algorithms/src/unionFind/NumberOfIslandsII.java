package unionFind;

import java.util.LinkedList;
import java.util.List;

/**
 * 305
 *
 * ======
 *
 * Companies: Google
 */
class NumberOfIslandsII
{
	class Solution
	{
		int count;
		int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		boolean[][] seen;

		public List<Integer> numIslands2(int m, int n, int[][] positions)
		{
			int temp = n;
			n = m;
			m = temp;
			seen = new boolean[n][m];
			List<Integer> ans = new LinkedList<>();
			UF uf = new UF(n, m);

			for (int[] p : positions)
			{
				if (!seen[p[0]][p[1]])
				{
					count++;

					for (int[] dir : dirs)
					{
						int exI = dir[0] + p[0];
						int exJ = dir[1] + p[1];
						if (!isInBounds(exI, exJ, n, m))
						{
							continue;
						}
						uf.union(exI, exJ, p[0], p[1]);
					}
				}

				ans.add(count);
			}
			return ans;
		}

		private boolean isInBounds(int i, int j, int n, int m)
		{
			return i >= 0 && j >= 0 && i < n && j < m;
		}

		// parent[i][j][p] - i, j - coord
		private class UF
		{
			int[][][] parents;

			public UF(int n, int m)
			{
				this.parents = new int[n][m][2];

				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < m; j++)
					{
						this.parents[i][j][0] = i;
						this.parents[i][j][1] = j;
					}
				}
			}

			public void union(int exI, int exJ, int newI, int newJ)
			{
				// set a flag that there is a value there
				seen[newI][newJ] = true;

				// was it set?
				if (!seen[exI][exJ])
				{
					return;
				}

				// find parent of ex
				int[] neightboorParentCell = find(exI, exJ);
				int neigboorParentI = neightboorParentCell[0];
				int neigboorParentJ = neightboorParentCell[1];

				// if parents are the same - one component already
				if (neigboorParentI == newI && neigboorParentJ == newJ)
				{
					return;
				}

				// set a new parent of neightboor
				this.parents[neigboorParentI][neigboorParentJ][0] = newI;
				this.parents[neigboorParentI][neigboorParentJ][1] = newJ;

				// reduce count cuz we are merging islands
				count--;
			}

			public int[] find(int i, int j)
			{
				if (parents[i][j][0] == i && parents[i][j][1] == j)
				{
					return parents[i][j];
				}

				return find(parents[i][j][0], parents[i][j][1]);
			}
		}
	}
}
