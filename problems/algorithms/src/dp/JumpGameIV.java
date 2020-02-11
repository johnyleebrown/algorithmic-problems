package dp;

import java.util.*;

/**
 * 5314
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length. i - 1 where: i - 1 >= 0. j where: arr[i] == arr[j] and i != j.
 *
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGameIV
{
	/**
	 *
	 */

	static class Solution
	{
		static int n;
		static int[] dp;
		static Map<Integer, List<Integer>> m;

		public static int minJumps(int[] arr)
		{
			n = arr.length;
			dp = new int[n];
			Arrays.fill(dp, -1);
			int res = Integer.MAX_VALUE;
			m = new HashMap<>();
			for (int i = 0; i < n; i++)
			{
				m.putIfAbsent(arr[i], new ArrayList<>());
				m.get(arr[i]).add(i);
			}
//			for (int i = 0; i < n; i++)
//			{
//				res = Math.min(res, dfs(i, arr, new boolean[n]) );
//			}
//			System.out.println(Arrays.toString(dp));
			return dfs(n-1, arr, new boolean[n]) ;
		}

		private static int dfs(int i, int[] arr, boolean[] s)
		{
			if (dp[i] != -1)
			{
				return dp[i];
			}
			s[i] = true;
//			if (i == 0 || i == n - 1)
//			{
//				return 0;
//			}
			int res = 1;
			for (int ind : m.get(arr[i]))
			{
				if (i != ind && !s[ind])
				{
//					s[ind] = true;
					res = Math.min(res, dfs(ind, arr, s) + 1);
//					s[ind] = false;
				}
			}
			if (i - 1 >= 0 && !s[i - 1])
			{
				res = Math.min(res, dfs(i - 1, arr, s) + 1);
			}
			if (i + 1 < n && !s[i + 1])
			{
				res = Math.min(res, dfs(i + 1, arr, s) + 1);
			}
			s[i] = false;
			dp[i] = res;
			return res;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(JumpGameIV.Solution.minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
	}
}