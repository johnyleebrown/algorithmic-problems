/*
 * 540
 * Company: Amazon
 *
 * xor is O(n).
 * bs is O(logn) - correct array is when first item is at even pos, second at odd.
 */
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
/*
[1,1,2,3,3,4,4,8,8]
 0 1 2 3 4 5 6 7 8
[1,1,3,3,4,4,5,8,8]
 0 1 2 3 4 5 6 7 8
*/

