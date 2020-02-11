package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77
 */
public class Combinations
{
	class Solution
	{
		private List<List<Integer>> list = new ArrayList<>();

		public List<List<Integer>> combine(int n, int k)
		{
			if (k < 1)
			{
				return list;
			}

			generate(new ArrayList<>(), 1, k, n);
			return list;
		}

		private void generate(List<Integer> combination, int start, int k, int n)
		{
			if (k == combination.size())
			{
				list.add(new ArrayList<>(combination));
			}
			else
			{
				for (int i = start; i <= n; i++)
				{
					combination.add(i);
					generate(combination, i + 1, k, n);
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
}

