package math.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 398
 */
public class RandomPickIndex {

	/**
	 * Reservoir sampling. Treating target elements as stream, so for each
	 * target element probability to be chosen will be 1/countTargets.
	 *
	 * Randomly get a # from 0.. targetCount-1 in other words 1/targetCount the
	 * 0 can be any # from 0.. targetCount-1 but we use 0 because it works for
	 * all targetCount sizes.
	 */
	static class Solution {

		private int[] nums;

		public Solution(int[] nums) {
			this.nums = nums;
		}

		public int pick(int target) {
			int choice = -1;
			int countTargets = 0;

			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == target) {
					countTargets++;
					int x = new Random().nextInt(countTargets);
					if (x == 0) {
						choice = i;
					}
				}
			}

			return choice;
		}
	}

	/**
	 * SF. But memory is O(n).
	 */
	class Solution2 {

		int[] nums;
		Random rand;

		public Solution2(int[] nums) {
			this.nums = nums;
			this.rand = new Random();
		}

		public int pick(int target) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == target)
					list.add(i);
			}

			return list.get(rand.nextInt(list.size()));
		}
	}
}
