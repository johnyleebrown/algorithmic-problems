package medium.backtracking;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;

/*
 * 90
 */
public class SubsetsII
{
	/*
	 * Solution with seen set
	 */
	class Solution1 {
		
		private List<List<Integer>> combinations = new LinkedList<>();
		
		public List<List<Integer>> subsetsWithDup(int[] nums) {
			if (nums == null || nums.length == 0) return new LinkedList<>();
			Arrays.sort(nums);
			Set<String> seen = new HashSet<>();
			generate("", seen, 0, new LinkedList<>(), nums);
			return combinations;
		} 
		
		private void generate(String cur, Set<String> seen, 
				int start, List<Integer> combination, int[] nums)
		{
			if (!seen.add(cur)) return;
			combinations.add(new LinkedList<>(combination));
			for (int i = start; i < nums.length; i++)
			{
				combination.add(nums[i]);
				generate(cur + "-" + nums[i], seen, i + 1, combination, nums);
				combination.remove(combination.size() - 1);
			}
		}
	}

	/*
	 * Solution with checking for dups in array
	 */
	class Solution {

		private List<List<Integer>> combinations = new LinkedList<>();
		
		public List<List<Integer>> subsetsWithDup(int[] nums) {
			if (nums == null || nums.length == 0) return new LinkedList<>();
			Arrays.sort(nums);
			generate(0, new LinkedList<>(), nums);
			return combinations;
		}

		private void generate(int start, List<Integer> combination, int[] nums)
		{
			combinations.add(new LinkedList<>(combination));
			for (int i = start; i < nums.length; i++)
			{
				if (i != start && nums[i] == nums[i - 1]) continue; 
				combination.add(nums[i]);
				generate(i + 1, combination, nums);
				combination.remove(combination.size() - 1);
			}
		}
	}
}

