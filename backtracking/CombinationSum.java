/*
 * 39
 *
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
			if (leftOver < 0) 
			{
				return;
			}

			if (leftOver == 0)
			{
				combinations.add(new ArrayList(combination));
			}
			else
			{
				for (int i = start; i < nums.length; i++)
				{
					combination.add(nums[i]);
					backtrack(nums, combination, i, leftOver - nums[i]);
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
}

