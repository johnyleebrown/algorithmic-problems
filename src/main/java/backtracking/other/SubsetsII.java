package backtracking.other;

import java.util.*;

/**
 * 90. Subsets II
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power
 * set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {
	/**
	 * Sol with checking for dups in array
	 */
	public static class Solution {
		public List<List<Integer>> subsetsWithDup(int[] nums) {
			List<List<Integer>> ans = new ArrayList<>();
			Arrays.sort(nums);
			gen(nums, ans, new ArrayList<>(), 0);
			return ans;
		}

		void gen(int[] nums, List<List<Integer>> res, List<Integer> cur, int i) {
			res.add(new ArrayList<>(cur));
			for (int j = i; j < nums.length; j++) {
				if (j != i && nums[j] == nums[j - 1]) continue;
				cur.add(nums[j]);
				gen(nums, res, cur, j + 1);
				cur.remove(cur.size() - 1);
			}
		}
	}
}