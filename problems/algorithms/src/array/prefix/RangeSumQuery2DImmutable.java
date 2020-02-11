package array.prefix;

/**
 * 304
 */
public class RangeSumQuery2DImmutable
{
    /**
     * Prefix matrix, same as prefix sum, but with slight mods.
     */
	static class Solution
	{
		static int[][] prefixmatrix;

		public Solution(int[][] matrix)
		{
			int n = matrix.length;
			if (n == 0)
			{
				return;
			}
			int m = matrix[0].length;
			if (m == 0)
			{
				return;
			}
			prefixmatrix = new int[n][m];
			for (int i = 0; i < n; i++)
			{
				int rowprefixsum = 0;
				for (int j = 0; j < m; j++)
				{
					rowprefixsum += matrix[i][j];
					int top = i - 1 >= 0 ? prefixmatrix[i - 1][j] : 0;
					prefixmatrix[i][j] = top + rowprefixsum;
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2)
		{
			int upperright = row1 - 1 >= 0
					? prefixmatrix[row1 - 1][col2]
					: 0;

			int lowerleft = col1 - 1 >= 0
					? prefixmatrix[row2][col1 - 1]
					: 0;

			int upperleft = col1 - 1 >= 0 && row1 - 1 >= 0
					? prefixmatrix[row1 - 1][col1 - 1]
					: 0;

			return prefixmatrix[row2][col2] - upperright - lowerleft + upperleft;
		}
	}
}
