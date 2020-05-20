package hashtable.other;

/**
 * 645
 *
 *
 * The set S originally contains numbers from 1 to n. But unfortunately, due to
 * the data error, one of the numbers in the set got duplicated to another
 * number in the set, which results in repetition of one number and loss of
 * another number.
 *
 * Given an array nums representing the data status of this set after the error.
 * Your task is to firstly find the number occurs twice and then find the number
 * that is missing. Return them in the form of an array.
 *
 * Note: The given array size will in the range [2, 10000]. The given array's
 * numbers won't have any order.
 */

public class SetMismatch
{

	// Time complexity : O(n). Two traversals over the nums array of size n are done.
	// Space complexity : O(1). Constant extra space is used.
	public int[] findErrorNums(int[] nums)
	{
		int dup = -1, missing = 1;
		for (int n : nums)
		{
			if (nums[Math.abs(n) - 1] < 0)
				dup = Math.abs(n);
			else
				nums[Math.abs(n) - 1] *= -1;
		}
		for (int i = 1; i < nums.length; i++)
		{
			if (nums[i] > 0)
				missing = i + 1;
		}
		return new int[]{dup, missing};
	}

	// Time complexity : O(n). We iterate over nn elements five times.
	// Space complexity : O(1). Constant extra space is used.
	//
	// This works because XORing a number with itself results in a 0 result.
	// Thus, only the number which is missing can't get cancelled with this XORing.
	public int[] findErrorNums2(int[] nums)
	{
		int xor = 0, xor0 = 0, xor1 = 0;
		for (int n : nums)
			xor ^= n;
		for (int i = 1; i <= nums.length; i++)
			xor ^= i;
		int rightmostbit = xor & ~(xor - 1);
		for (int n : nums)
		{
			if ((n & rightmostbit) != 0)
				xor1 ^= n;
			else
				xor0 ^= n;
		}
		for (int i = 1; i <= nums.length; i++)
		{
			if ((i & rightmostbit) != 0)
				xor1 ^= i;
			else
				xor0 ^= i;
		}
		for (int i = 0; i < nums.length; i++)
		{
			if (nums[i] == xor0)
				return new int[]{xor0, xor1};
		}
		return new int[]{xor1, xor0};
	}
}
