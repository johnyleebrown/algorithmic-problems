package sort.heap;

import java.util.PriorityQueue;

/**
 * 1341
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of
 * the k weakest rows in the matrix ordered from the weakest to the strongest.
 *
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or
 * they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that
 * is, always ones may appear first and then zeros.
 *
 * ======
 *
 * Source: Leetcode
 */
public class TheKWeakestRowsInAMatrix
{
	/**
	 * Priority queue.
	 */
	class Solution
	{
		public int[] kWeakestRows(int[][] mat, int k)
		{
			int n = mat.length;
			int m = mat[0].length;

			// 0 - ind, 1 - num of s
			PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) ->
			{
				if (a[1].equals(b[1]))
				{
					return a[0] - b[0];
				}

				return a[1] - b[1];
			});

			for (int i = 0; i < n; i++)
			{
				int c = 0;
				for (int j = 0; j < m; j++)
				{
					if (mat[i][j] == 0)
					{
						break;
					}
					if (mat[i][j] == 1)
					{
						c++;
					}
				}

				pq.add(new Integer[]{i, c});
			}

			int[] res = new int[k];
			for (int i = 0; i < k; i++)
			{
				res[i] = pq.poll()[0];
			}

			return res;
		}
	}
}