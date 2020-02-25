package math.random;

import java.util.Random;

/**
 * 398
 */
public class RandomPickIndex
{
	/**
	 * Reservoir sampling. Treating target elements as stream, so for each
	 * target element probability to be chosen will be 1/countTargets.
	 */
	class Solution
	{
		private int[] nums;

		public Solution(int[] nums)
		{
			this.nums = nums;
		}

		public int pick(int target)
		{
			int choice = -1;
			int countTargets = 0;

			for (int i = 0; i < nums.length; i++)
			{
				if (nums[i] == target)
				{
					countTargets++;
					Random r = new Random();
					int x = r.nextInt(countTargets);
					if (x == 0)
					{
						choice = i;
					}
				}
			}

			return choice;
		}
	}
}
