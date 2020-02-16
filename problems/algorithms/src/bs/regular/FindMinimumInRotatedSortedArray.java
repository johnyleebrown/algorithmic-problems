package bs.regular;

/**
 * 153
 */
public class FindMinimumInRotatedSortedArray
{
	class Solution
	{
		public int findMin(int[] nums)
		{
			int lo = 0, hi = nums.length - 1, ans = nums[lo];

			while (lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;

				if (nums[lo] > nums[mid])
				{
					if (mid + 1 < nums.length && nums[mid + 1] < nums[mid])
						return nums[mid + 1];

					hi = mid - 1;
				}
				else
				{
					if (mid + 1 < nums.length && nums[mid + 1] < nums[mid])
						return nums[mid + 1];

					lo = mid + 1;
				}
			}

			return ans;
		}
	}
}