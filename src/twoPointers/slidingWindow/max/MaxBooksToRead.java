package twoPointers.slidingWindow.max;

import util.ds.InputReader;

import java.io.PrintWriter;

/**
 * Task A 256854
 *
 * ======
 *
 * Source: Codeforces
 */
public class MaxBooksToRead
{
	static class Solution
	{
		public void solve(int testNumber, InputReader in, PrintWriter out)
		{
			int n = in.nextInt();
			int t = in.nextInt();
			int[] times = new int[n];
			for (int i = 0; i < n; i++)
			{
				times[i] = in.nextInt();
			}
			int l = 0;
			int count = 0;
			int result = 0;
			for (int r = 0; r < n; r++)
			{
				// use right window boundary
				count += times[r];

				// for max type of problems we need while condition to the opposite of good condition
				// good condition is count == K
				// if zerosCount is less than K - means we don't have enough data
				while (count > t)
				{
					// use left window boundary
					count -= times[l];

					l++;
				}

				result = Math.max(result, r - l + 1);
			}
			out.println(result);
		}
	}
}
