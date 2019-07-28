package medium.backtracking;

// 216
public class CombinationSumIII
{
	class Solution 
	{
		private List<List<Integer>> combs = new ArrayList<>();
		
		public List<List<Integer>> combinationSum3(int k, int n) 
		{
			generate(new ArrayList<>(), k, n, 0, 1);
			return combs;    
		}
		
		private void generate(List<Integer> comb, int k, int n, int combSum, int start)
		{
			if (combSum == n && comb.size() == k) combs.add(new ArrayList(comb));
			else
			{
				for (int i = start; i <= 9; i++)
				{
					// optimization
					if (comb.size() > k) break;
					if (combSum + i > n) continue;
					
					comb.add(i);
					generate(comb, k, n, combSum + i, i + 1);
					comb.remove(comb.size() - 1);
				}
			}
		}

	}

}

