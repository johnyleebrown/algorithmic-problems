/*
 * 300
 * Google
 */
public class LongestIncreasingSubsequence 
{
	class Solution 
	{
		public int lengthOfLIS(int[] nums) 
		{
			int n = nums.length;
			if (n == 0)
			{
				return 0;
			}

			int[] dp = new int[n + 1];
			Arrays.fill(dp, 1);
			int max = -1, ind = 0;

			for (int i = 1; i <= n; i++)
			{
				for (int j = i - 1; j >= 1; j--)
				{
					if (nums[j - 1] < nums[i - 1])
					{
						dp[i] = Math.max(dp[i], dp[j] + 1);
						if (max < dp[i])
						{
							max = dp[i];
							ind = i;
						}
					}
				}
			}

			return dp[ind];
		}
	}
}

