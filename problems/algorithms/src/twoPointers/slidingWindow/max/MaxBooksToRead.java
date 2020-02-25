package twoPointers.slidingWindow.max;

import util.ds.InputReader;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 256854A
 *
 * ======
 *
 * Source: Codeforces
 *
 * Link: https://codeforces.com/group/pgkaqF4igo/contest/256854
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

	class Solution2 {
		public int[] nextGreaterElement(int[] nums1, int[] nums2) {
			int n = nums1.length;
			int[] res = new int[n];
			Deque<Integer> q = new ArrayDeque<>();
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = nums2.length - 1; i >= 0; i--)
			{
				while (!q.isEmpty() && nums2[i]>=q.peekLast())
				{
					q.removeLast();
				}
				if (!q.isEmpty())
					map.put(nums2[i],-1);
				else
					map.put(nums2[i], q.peekLast());
				q.addLast(nums2[i]);
			}
			for (int i = 0; i < n; i++)
			{
				int x = nums1[i];
				res[i] = map.get(x);
			}
			return res;
		}
	}
}
