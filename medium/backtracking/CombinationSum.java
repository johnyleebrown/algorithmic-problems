package medium.backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
/*
 * 39
 * no dups, number can be taken unlim num of times
 * find unique combinations
 */
public class CombinationSum
{
	/*
	 * loop over one number over and over until the sum is target
	 * then append other numbers
	 * use start so not to stumblr upon dups
	 * sort and check the prev if equals for optimization
	 *
	 * O(n! + nlogn)
	 */
	class Solution {
		private List<List<Integer>> combinations = new LinkedList();

		public List<List<Integer>> combinationSum(int[] candidates, int target)
		{
			Arrays.sort(candidates); // optimization for future comparisons
			backtrack(candidates, new ArrayList<>(), 0, target);
			return combinations;
		}

		public void backtrack(int[] nums, List<Integer> combination, 
				int start, int leftOver)
		{
			if (leftOver < 0) return;
			if (leftOver == 0) combinations.add(new ArrayList(combination));
			else
			{
				for (int i = start; i < nums.length; i++)
				{
					if (nums[i] > leftOver) break; // optimization
					if (i != 0 && nums[i] == nums[i - 1]) continue; // optimization
					combination.add(nums[i]);
					backtrack(nums, combination, i, leftOver - nums[i]);
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
	/*
	2 3 6 7
	2 2 2 2
	2 2 2 3
	2 2 2 6
	2 2 2 7
	2 2 3
	*/
}

