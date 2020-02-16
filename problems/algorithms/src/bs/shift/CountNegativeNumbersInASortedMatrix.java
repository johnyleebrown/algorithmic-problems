package bs.shift;

/**
 * 1351
 *
 * ======
 *
 * Task.
 *
 * Given a m * n matrix grid which is sorted in non-increasing order both
 * row-wise and column-wise.
 *
 * Return the number of negative numbers in grid.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountNegativeNumbersInASortedMatrix
{
	class Solution
	{
		public int countNegatives(int[][] a)
		{
			int n = a.length;
			int m = a[0].length;
			int res = 0;
			for (int i = 0; i < n; i++)
			{
				if (a[i][0] < 0)
					res += m;
				else
					res += s(a, i, m);
			}
			return res;
		}

		private int s(int[][] a, int i, int m)
		{
			int lo = 0;
			int hi = m - 1;
			while (lo <= hi)
			{
				int mid = (hi - lo) / 2 + lo;
				if (a[i][mid] < 0 && a[i][mid - 1] >= 0)
					return m - mid;
				if (a[i][mid] >= 0)
					lo++;
				else if (a[i][mid] < 0)
					hi--;
			}
			return 0;
		}
	}
}