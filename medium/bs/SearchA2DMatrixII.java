public class SearchA2DMatrixII
{
	class Solution {
		public boolean searchMatrix(int[][] matrix, int target)
		{
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
			int n = matrix.length, m = matrix[0].length;
			int i = 0, j = m - 1;
			while (i < n && j >= 0)
			{
				if (target == matrix[i][j]) return true;
				else if (matrix[i][j] > target) j--;
				else if (matrix[i][j] < target) i++;
			}

			return false;
		}
	}
}
