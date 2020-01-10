/*
 * 766
 * Google
 */
class Solution
{
	public boolean isToeplitzMatrix(int[][] matrix)
	{
		int n = matrix.length;
		int m = matrix[0].length;

		for (int i = n - 1, j = 0; i >= 0; i--)
		{
			int newI = i, newJ = j;
			Integer compareWith = null;

			while (newI < n && newJ < m && newI >= 0 && newJ >= 0)
			{
				if (compareWith != null && compareWith != matrix[newI][newJ])
				{
					return false;
				}

				compareWith = matrix[newI][newJ];
				newJ++;
				newI++;
			}
		}

		for (int i = 0, j = 1; j < m; j++)
		{
			int newI = i, newJ = j;
			Integer compareWith = null;

			while (newI < n && newJ < m && newI >= 0 && newJ >= 0)
			{
				if (compareWith != null && compareWith != matrix[newI][newJ])
				{
					return false;
				}

				compareWith = matrix[newI][newJ];
				newJ++;
				newI++;
			}
		}

		return true;
	}
}

