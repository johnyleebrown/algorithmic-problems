package regular.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 1380
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix of distinct numbers, return all lucky numbers in the
 * matrix in any order.
 *
 * A lucky number is an element of the matrix such that it is the minimum
 * element in its row and maximum in its column.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LuckyNumbersInAMatrix
{
	public static class Solution
	{
		public List<Integer> luckyNumbers(int[][] a)
		{
			int n = a.length;
			int m = a[0].length;
			int[] minRows = new int[n];
			int[] maxCols = new int[m];


			for (int i = 0; i < n; i++)
			{
				minRows[i] = a[i][0];
				for (int j = 0; j < m; j++)
				{
					minRows[i] = Math.min(minRows[i], a[i][j]);
					maxCols[j] = Math.max(maxCols[j], a[i][j]);
				}
			}

			List<Integer> res = new ArrayList<>();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					if (a[i][j] == minRows[i] && a[i][j] == maxCols[j])
						res.add(a[i][j]);

			return res;
		}
	}
}
