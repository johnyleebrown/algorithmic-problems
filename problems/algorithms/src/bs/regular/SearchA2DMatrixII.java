package bs.regular;

/**
 * 240
 */
public class SearchA2DMatrixII
{
	class Solution
	{
		public boolean searchMatrix(int[][] matrix, int target)
		{
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
				return false;

			int n = matrix.length;
			int m = matrix[0].length;
			int i = 0;
			int j = m - 1;

			while (i < n && j >= 0)
			{
				if (target == matrix[i][j])
					return true;
				else if (matrix[i][j] > target)
					j--;
				else if (matrix[i][j] < target)
					i++;
			}

			return false;
		}
	}

	/**
	 * Find w/ bs the start row looking at m-1 row numbers, then regular bs.
	 */
	class Solution2
	{
		int n, m;

		public boolean searchMatrix(int[][] a, int t)
		{
			n = a.length;
			if (n == 0)
				return false;
			m = a[0].length;
			if (m == 0)
				return false;
			int startI = bsClosest(a, t);
			if (startI == -1)
				return false;
			while (startI < n && a[startI][m - 1] >= t && a[startI][0] <= t)
			{
				if (regbs(a[startI], t) != -1)
				{
					return true;
				}
				startI++;
			}
			return false;
		}

		private int bsClosest(int[][] a, int t)
		{
			if (a[0][0] > t || a[n - 1][m - 1] < t)
				return -1;
			return bsNextSmallest(a, t);
		}

		private int bsNextSmallest(int[][] a, int t)
		{
			int lo = 0;
			int hi = n - 1;
			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if (a[mid][m - 1] < t)
				{
					lo = mid + 1;
				}
				else if (a[mid][m - 1] > t)
				{
					hi = mid - 1;
				}
				else
				{
					return mid;
				}
			}
			return lo;
		}

		private int regbs(int[] a, int t)
		{
			int lo = 0;
			int hi = m - 1;
			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if (a[mid] < t)
				{
					lo = mid + 1;
				}
				else if (a[mid] > t)
				{
					hi = mid - 1;
				}
				else
				{
					return mid;
				}
			}
			return -1;
		}
	}
}
