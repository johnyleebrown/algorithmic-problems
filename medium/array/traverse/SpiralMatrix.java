// 54
public class SpiralMatrix
{
	class Solution 
	{
		private enum Dir
		{
			R, D, L, U;
		}

		public List<Integer> spiralOrder(int[][] matrix) 
		{
			List<Integer> ans = new ArrayList<>();
			if (matrix.length == 0 || matrix[0].length == 0) return ans;
			
			// starting state
			Dir cur = Dir.R;

			int r = matrix[0].length - 1, d = matrix.length - 1, l = 0, u = 0;
			int i = u, j = l;

			while (i >= u && i <= d && j >= l && j <= r)
			{
				ans.add(matrix[i][j]);

				if (cur == Dir.R)
				{
					if (j == r) // going right until right border
					{
						cur = Dir.D; // changed status - going down now
						i++; // moved the pointer
						u++; // decr upper border
					}
					else j++;
				}
				else if (cur == Dir.D) 
				{
					if (i == d) // going down until down border
					{
						cur = Dir.L; // changed status - going left now
						j--; // moved the pointer
						r--; // decr right border
					}
					else i++;
				}
				else if (cur == Dir.L)
				{
					if (j == l) // going left until left border
					{
						cur = Dir.U; // changed status - going up now
						i--; // moved the pointer
						d--; // incr bottom border
					}
					else j--;
				}
				else if (cur == Dir.U)
				{
					if (i == u) // going up until upper border 
					{
						cur = Dir.R; // changed status - now right
						j++; // moved the pointer
						l++; // incr left border
					}
					else i--;
				}            
			}

			return ans;
		}
	}	
}

