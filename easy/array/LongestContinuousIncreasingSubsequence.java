package easy.array;

// 674
public class LongestContinuousIncreasingSubsequence 
{
	class Solution
	{
		public int findLengthOfLCIS(int[] nums)
		{
			if (nums.length == 0) return 0;

			int res = 1, cnt = 1;
			for (int i = 0; i < nums.length - 1; i++)
			{
				if (nums[i] < nums[i + 1])
				{
					cnt++;
				}
				else
				{
					cnt = 1;
				}

				res = Math.max(res, cnt);
			}

			return res;
		}
	}
}

