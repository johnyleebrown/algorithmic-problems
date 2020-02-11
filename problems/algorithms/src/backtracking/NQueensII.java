package backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * 52
 */
public class NQueensII
{
	/**
	 * similar to sudoku validation - using set to check if the position is set
	 * using 3 sets: diag1, diag2, cols to keep track of seen cells no need to
	 * keep track of rows cuz we think we are on the right row every time
	 *
	 * O(n!) / O(n)
	 *
	 * can be optimizen by using 3 boolean arrays (3 times faster)
	 */
	class Solution
	{
		Set<Integer> cols = new HashSet<>();
		Set<Integer> diags1 = new HashSet<>();
		Set<Integer> diags2 = new HashSet<>();

		int solutionsCount = 0;

		public int totalNQueens(int n)
		{
			// edge case from leetcode
			if (n == 0) return 1;
			findSolutions(n, 0);
			return solutionsCount;
		}

		private void findSolutions(int n, int i)
		{
			for (int j = 0; j < n; j++)
			{
				// if is valid
				if (cols.contains(j)) continue;
				int diag1 = i - j;
				if (diags1.contains(diag1)) continue;
				int diag2 = i + j;
				if (diags2.contains(diag2)) continue;

				// if at the last row and is valid means we got all the queens right 
				if (i == n - 1)
				{
					solutionsCount++;
				}
				else
				{
					// add to seen sets
					cols.add(j);
					diags1.add(diag1);
					diags2.add(diag2);

					// check next row since we can't put nothing on this one anymore
					findSolutions(n, i + 1);

					// clean after yourself after found a solution
					cols.remove(j);
					diags1.remove(diag1);
					diags2.remove(diag2);
				}
			}
		}
	}
}