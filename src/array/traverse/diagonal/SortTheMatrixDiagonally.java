package array.traverse.diagonal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1329
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right
 * then return the sorted array.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SortTheMatrixDiagonally
{
	/**
	 * Straightforward. Traverse a diagonal, while recording items to list, the sort the list, then traverse the same
	 * path and fill the sorted items.
	 */
	class Solution
	{
		public int[][] diagonalSort(int[][] mat)
		{
			int n = mat.length;
			int m = mat[0].length;

			int iGlobal = n - 1;
			int jGlobal = 0;
			while (iGlobal >= 0)
			{
				List<Integer> local = new ArrayList<>();

				int i = iGlobal;
				int j = jGlobal;
				while (i < n && j < m)
				{
					local.add(mat[i][j]);
					i++;
					j++;
				}

				Collections.sort(local);
				int k = 0;

				i = iGlobal;
				j = jGlobal;
				while (i < n && j < m)
				{
					mat[i][j] = local.get(k++);
					i++;
					j++;
				}

				iGlobal--;
			}

			iGlobal = 0;
			jGlobal = 1;
			while (jGlobal < m)
			{
				List<Integer> local = new ArrayList<>();

				int i = iGlobal;
				int j = jGlobal;
				while (i < n && j < m)
				{
					local.add(mat[i][j]);
					i++;
					j++;
				}

				Collections.sort(local);
				int k = 0;

				i = iGlobal;
				j = jGlobal;
				while (i < n && j < m)
				{
					mat[i][j] = local.get(k++);
					i++;
					j++;
				}

				jGlobal++;
			}

			return mat;
		}
	}
}
