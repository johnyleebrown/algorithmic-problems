package backtracking.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40
 */
public class CombinationSumII
{
	class Solution
	{
		private List<List<Integer>> combinations = new ArrayList<>();

		public List<List<Integer>> combinationSum2(int[] candidates, int target)
		{
			if (candidates == null || candidates.length == 0)
				return combinations;

			// optimization for comparison further
			Arrays.sort(candidates);

			backtrack(candidates, new ArrayList<>(), 0, target);
			return combinations;
		}

		public void backtrack(int[] nums, List<Integer> combination, int start,
		                      int leftOver)
		{
			if (leftOver < 0)
			{
				return;
			}
			if (leftOver == 0)
			{
				combinations.add(new ArrayList<>(combination));
			}
			else
			{
				for (int i = start; i < nums.length; i++)
				{
					// optimization
					if (nums[i] > leftOver) break;

					// optimization
					// i != start means not the first one 
					// and not the first one in recursion
					if (i != start && nums[i] == nums[i - 1]) continue;

					combination.add(nums[i]);
					backtrack(nums, combination, i + 1, leftOver - nums[i]);
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
}

