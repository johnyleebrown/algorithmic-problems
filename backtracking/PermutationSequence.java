package medium.backtracking;

// 60
public class PermutationSequence
{
	/*
	 * going iteratively from 1 until we seen kth combination
	 *
	 * O(n!)
	 *
	 * optimized with solution in math package: count 
	 * the exact place of this permutation
	 */
	class Solution 
	{	
		private int limit;
		private String result = null;
		
		public String getPermutation(int n, int k) 
		{
			limit = k;
			generate(new boolean[n + 1], "", n);
			return result;
		}
		
		private boolean generate(boolean[] seen, String combination, int n)
		{
			if (combination.length() == n) 
			{
				limit--;
				if (limit == 0) 
				{
					result = combination;
					return true;
				}
			}
			else
			{
				for (int i = 1; i <= n; i++)
				{
					if (seen[i]) continue;
					seen[i] = true;
					if (generate(seen, combination + i, n)) return true;
					seen[i] = false;
				}
			}
			
			return false;
		}
	}
}

/*
3
3
4
9
8
11
9
196883
*/

