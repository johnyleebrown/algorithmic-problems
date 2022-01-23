package array.other;

// 189
// rotate left, rotate right, rotate whole
public class RotateArray 
{
	public void rotate(int[] nums, int k) 
	{
		k = k % nums.length;
		reverse(nums, 0, nums.length - k - 1);
		reverse(nums, nums.length - k, nums.length - 1);
		reverse(nums, 0, nums.length - 1);
	}

	private void reverse(int[] nums, int i, int j)
	{
		int tmp = 0;       
		while (i < j)
		{
			tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
			i++;
			j--;
		}
	}

}

