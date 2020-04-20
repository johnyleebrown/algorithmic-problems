package a0.sort.regular;

import java.util.Arrays;

/**
 * 280
 */
public class WiggleSort
{
	// straight-forward, sort and rearange
	class Solution 
	{
		public void wiggleSort(int[] nums) 
		{
			Arrays.sort(nums);

			for (int i = 1; i < nums.length - 1; i+=2)
			{
				int temp = nums[i + 1];
				nums[i + 1] = nums[i];
				nums[i] = temp;
			}
		}
	}

	// one pass swapping
	class Solution2
	{
		public void wiggleSort(int[] nums) 
		{
			boolean less = true;
			
			for (int i = 0; i < nums.length - 1; i++) 
			{
				if (less) 
				{
					if (nums[i] > nums[i + 1]) 
					{
						swap(nums, i, i + 1);
					}
				} 
				else 
				{
					if (nums[i] < nums[i + 1]) 
					{
						swap(nums, i, i + 1);
					}
				}

				less = !less;
			}
		}

		private void swap(int[] nums, int i, int i1)
		{
		}
	}
}