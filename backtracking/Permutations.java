package medium.backtracking;

import java.util.LinkedList;
import java.util.List;

// 46
public class Permutations
{
	/*
	 * Given a collection of distinct integers return all possible permutations
	 * 
	 * O(n!) 
	 *
	 * Optimization I = keep the list of possible nums 
	 * to try in a sepaarate list so you don;t check for contains every time
	 */
	class Solution {
		private List<List<Integer>> answer = new LinkedList<>();

		public List<List<Integer>> permute(int[] nums) {
			helper(new LinkedList<>(), nums);
			return answer;	
		}

		private void helper(List<Integer> combination, int[] nums)
		{
			// break point
			if (combination.size() == nums.length)
			{
				answer.add(new LinkedList<>(combination));
			}
			else
			{
				// iteration through variants
				for (int num: nums)
				{
					if (combination.contains(num)) continue;
					combination.add(num);
					helper(combination, nums);
					combination.remove(combination.size() - 1);
				}
			}
		}
	}
}

