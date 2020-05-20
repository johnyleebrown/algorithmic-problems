package backtracking.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47
 */
public class PermutationsII
{
	class Solution
	{
		private List<List<Integer>> combinations = new ArrayList<>();

		public List<List<Integer>> permuteUnique(int[] nums)
		{
			if (nums == null || nums.length == 0) return new ArrayList<>();
			Arrays.sort(nums);
			generate(new boolean[nums.length], new ArrayList<>(), nums);
			return combinations;
		}

		private void generate(boolean[] seen, List<Integer> combination, int[] nums)
		{
			if (combination.size() == nums.length)
				combinations.add(new ArrayList<>(combination));
			else
			{
				for (int i = 0; i < nums.length; i++)
				{
					// we dont want to take into account values 
					// that we ve seen before while in recursion
					if (seen[i]) continue;

					// nums[i] == nums[i - 1] and !seen[i - 1]
					// means we are on the first level of recursion 
					// and we have started with this before
					if (i != 0 && nums[i] == nums[i - 1] && !seen[i - 1])
						continue;

					seen[i] = true;
					combination.add(nums[i]);

					generate(seen, combination, nums);

					seen[i] = false;
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
}