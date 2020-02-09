package twoPointers.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 1330
 *
 * ======
 *
 * Task.
 *
 * You are given an integer array nums. The value of this array is defined as the sum of |nums[i]-nums[i+1]| for all 0
 * <= i < nums.length-1.
 *
 * You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.
 *
 * Find maximum possible value of the final array.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ReverseSubarrayToMaximizeArrayValue
{
	/**
	 * TLE
	 *
	 * When reversing an array, the only changing parts are differences between prevLeft - 1 and prefLeft and prevRight
	 * and prevRight + 1, the part of subarray [prevLeft + 1, prevRight - 1] will stay the same. We pre calculate array
	 * values, so we could count only the differences later while changing the subarray(window) length.
	 */
	class Solution1
	{
		public int maxValueAfterReverse(int[] nums)
		{
			int n = nums.length;

			int totalSum = 0;
			int[] preCalculatedValues = new int[n];
			for (int i = 0; i < n - 1; i++)
			{
				preCalculatedValues[i] = calculateDifference(nums[i], nums[i + 1]);
				totalSum += preCalculatedValues[i];
			}

			int maxValue = totalSum;
			Map<String, Integer> cache = new HashMap<>();
			for (int len = 2; len < n - 1; len++)
			{
				int l = 0;
				int r = l + len - 1;

				while (r < n)
				{
					String s = l + "," + r;
					if (!cache.containsKey(s))
					{
						int x = recalculateSum(totalSum, preCalculatedValues, nums, l, r);
						cache.put(s, x);
						maxValue = Math.max(maxValue, x);
					}

					l++;
					r++;
				}
			}

			return maxValue;
		}

		private int recalculateSum(int base, int[] pre, int[] nums, int l, int r)
		{
			int newSum = base;
			if (l - 1 >= 0)
			{
				newSum -= pre[l - 1];
				newSum += calculateDifference(nums[l - 1], nums[r]);
			}
			if (r + 1 < nums.length)
			{
				newSum -= pre[r];
				newSum += calculateDifference(nums[l], nums[r + 1]);
			}
			return newSum;
		}

		private int calculateDifference(int a, int b)
		{
			return Math.abs(a - b);
		}
	}
}