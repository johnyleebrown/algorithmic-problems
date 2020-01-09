//
class Solution 
{
	private enum Dir
	{
		R, L;
	}

	// looking for left and right separetely, updating vars if found
    public int[] searchRange(int[] nums, int target) 
	{
		int l = bs(target, nums, Dir.L);
		int r = bs(target, nums, Dir.R);
		return new int[]{l, r};		
    }

	private int bs(int target, int[] nums, Dir dir)
	{
		int ans = -1;
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi)
		{
			int mid = lo + (hi - lo)/2;
			if (target < nums[mid])
			{
				hi = mid - 1;
			}
			else if (target > nums[mid])
			{
				lo = mid + 1;
			}
			else
			{
				ans = mid;
				if (dir == Dir.R) lo = mid + 1;
				else hi = mid - 1;
			}
		}
		return ans;
	}

}

