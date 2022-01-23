package array.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 448
 */
public class FindAllNumbersDisappearedInAnArray {

	public static class Solution {
		public List<Integer> findDisappearedNumbers(int[] nums) {
			List<Integer> ret = new ArrayList<Integer>();
			for (int i = 0; i < nums.length; i++) {
				int val = Math.abs(nums[i]) - 1;
				if (nums[val] > 0) nums[val] = -nums[val];
			}
			for (int i = 0; i < nums.length; i++)
				if (nums[i] > 0) ret.add(i + 1);
			return ret;
		}
	}

	public static class Solution2 {
		public List<Integer> findDisappearedNumbers(int[] nums) {
			List<Integer> res = new ArrayList<>();
			int n = nums.length;
			for (int i = 0; i < nums.length; i++)
				nums[(nums[i] - 1) % n] += n;
			for (int i = 0; i < nums.length; i++)
				if (nums[i] <= n) res.add(i + 1);
			return res;
		}
	}
}
