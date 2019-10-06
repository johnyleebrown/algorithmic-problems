package array;

// 15
public class ThreeSum 
{
	/**
	 * Three pointers. 
	 * O(n^2), O(1).
	 */
	class Solution
	{
		public List<List<Integer>> threeSum(int[] nums)
		{
			Arrays.sort(nums);
			List<List<Integer>> ans = new ArrayList<>();
			int n = nums.length;

			for (int p1 = 0; p1 < n - 2; p1++)
			{
				// we don't want duplicates
				if (p1 == 0 || (p1 > 0 && nums[p1] != nums[p1 - 1]))
				{
					int p2 = p1 + 1;
					int p3 = n - 1;

					// how much do we need more if we have only p1
					int sum = 0 - nums[p1];

					while (p2 < p3)
					{
						// if we found all 3 numbers that sum up to 0
						if (nums[p2] + nums[p3] == sum)
						{
							ans.add(Arrays.asList(nums[p1], nums[p2], nums[p3]));
							p2 = skipDuplicatesOnTheLeft(p2, p3, nums);
							p3 = skipDuplicatesOnTheRight(p3, p2, nums);
						}

						// skipping smaller numbers
						else if (nums[p2] + nums[p3] < sum)
						{
							p2 = skipDuplicatesOnTheLeft(p2, p3, nums);
						}

						// skipping larger numbers
						else
						{
							p3 = skipDuplicatesOnTheRight(p3, p2, nums);
						}
					}
				}
			}

			return ans;
		}

		private int skipDuplicatesOnTheLeft(int p2, int p3, int[] nums)
		{
			while (p2 < p3 && nums[p2] == nums[p2 + 1])
			{
				p2++;
			}
			p2++;

			return p2;
		}

		private int skipDuplicatesOnTheRight(int p3, int p2, int[] nums)
		{
			while (p2 < p3 && nums[p3] == nums[p3 - 1])
			{
				p3--;
			}
			p3--;

			return p3;
		}
	}
}

