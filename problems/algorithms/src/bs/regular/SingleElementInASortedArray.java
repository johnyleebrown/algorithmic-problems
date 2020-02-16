package bs.regular;

/**
 * 540
 */
public class SingleElementInASortedArray
{
	class Solution
	{
		public int singleNonDuplicate(int[] nums)
		{
			int lo = 0, hi = nums.length - 1;
			int x = nums[0];

			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;

				if (checkPosition(mid, nums) == 1)
				{
					hi = mid - 1;
				}
				else if (checkPosition(mid, nums) == -1)
				{
					lo = mid + 1;
				}
				else
				{
					return nums[mid];
				}
			}

			return x;
		}

		private int checkPosition(int mid, int[] nums)
		{
			int x = nums[mid];

			if ((mid - 1 >= 0 && x == nums[mid - 1] && mid % 2 == 0)
					|| (mid + 1 < nums.length && x == nums[mid + 1] && mid % 2 != 0))
			{
				return 1;
			}
			else if ((mid - 1 >= 0 && x == nums[mid - 1] && mid % 2 != 0)
					|| (mid + 1 < nums.length && x == nums[mid + 1] && mid % 2 == 0))
			{
				return -1;
			}

			return 0;
		}
	}
}