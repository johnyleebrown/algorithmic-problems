import java.util.ArrayList;
import java.util.List;

/**
 * 562
 *
 * Company.
 *
 * Google
 *
 * Experimented with bfs to add diags, but could be done faster.
 */
class LongestLineOfConsecutiveOneInMatrix
{
	class Solution
	{
		public int longestLine(int[][] M)
		{
			int max = Integer.MIN_VALUE;
			int n = M.length;
			if (n == 0) return 0;
			int m = M[0].length;
			if (m == 0) return 0;

			// h
			for (int i = 0; i < n; i++)
			{
				int local = 0;
				for (int j = 0; j < m; j++)
				{
					if (M[i][j] == 1)
					{
						local++;
						max = Math.max(max, local);
					}
					else
					{
						local = 0;
					}
				}
			}

			// v
			for (int j = 0; j < m; j++)
			{
				int local = 0;
				for (int i = 0; i < n; i++)
				{
					if (M[i][j] == 1)
					{
						local++;
						max = Math.max(max, local);
					}
					else
					{
						local = 0;
					}
				}
			}

			// diag
			List<int[]> q = new ArrayList<>();
			q.add(new int[]{0, 0});
			diagTraverse(new boolean[n][m], q, M, 1);
			q.add(new int[]{0, m - 1});
			diagTraverse(new boolean[n][m], q, M, -1);

			return max == Integer.MIN_VALUE ? 0 : max;
		}

		private boolean isValid(int i, int j, int n, int m)
		{
			return i >= 0 && i < n && j >= 0 && j < m;
		}

		private void diagTraverse(boolean[][] seen, List<int[]> q, int[][] M, int extra)
		{
			while (!q.isEmpty())
			{
				int size = q.size();
				int local = 0;

				while (--size >= 0)
				{
					int[] x = q.remove(0);

					if (seen[x[0]][x[1]]) continue;
					seen[x[0]][x[1]] = true;

					if (M[x[0]][x[1]] == 1)
					{
						local++;
						slidingWindow.min.max = Math.max(slidingWindow.min.max, local);
					}
					else
					{
						local = 0;
					}

					int iR = x[0];
					int jR = x[1] + extra;
					if (isValid(iR, jR, n, m))
					{
						q.add(new int[]{iR, jR});
					}

					int iB = x[0] + 1;
					int jB = x[1];
					if (isValid(iB, jB, n, m))
					{
						q.add(new int[]{iB, jB});
					}
				}
			}
		}
	}
}

