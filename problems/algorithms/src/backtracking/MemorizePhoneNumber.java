package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Google_Interview_2
 *
 * ======
 *
 * Task.
 *
 * You are given a phone number as an array of n digits. To help you memorize
 * the number, you want to divide it into groups of contiguous digits. Each
 * group must contain exactly 2 or 3 digits. There are 3 kinds of groups:
 *
 * Excellent: A group that contains only the same digits. For example, 000 or
 * 77. Good: A group of 3 digits, 2 of which are the same. For example, 030, 229
 * or 166. Usual: A group in which all the digits are distinct. For example, 123
 * or 90. The quality of a group assignment is defined as 2 × (number of
 * excellent groups) + (number of good groups). Divide the phone number into
 * groups such that the quality is maximized.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MemorizePhoneNumber
{
	private class Solution
	{
		private List<List<Integer>> ans;
		private int quality = Integer.MIN_VALUE;
		private boolean[] seen;
		private int eg = 0, gg = 0;

		public List<List<Integer>> memorize(int[] number)
		{
			if (number.length == 0)
			{
				return null;
			}

			ans = new ArrayList<>();
			seen = new boolean[number.length];

			generate(new ArrayList<>(), number, 0, new ArrayList<>());

			return ans;
		}

		private void generate(List<Integer> cur, int[] a,
		                      int localc, List<List<Integer>> curans)
		{
			if (localc > 1)
			{
				g(cur, localc, curans);
			}

			if (hasMaxQuality())
			{
				ans = new ArrayList(curans);
			}
			else
			{
				for (int i = 0; i < a.length; i++)
				{
					if (seen[i]) continue;

					seen[i] = true;
					cur.add(a[i]);
					generate(cur, a, localc + 1 == 4 ? 1 : localc + 1, curans);
					generate(cur, a, localc + 1 == 3 ? 1 : localc + 1, curans);
					cur.remove(i);
					seen[i] = false;
				}
			}
		}

		private boolean hasMaxQuality()
		{
			int x = 2 * eg + gg;
			if (quality < x)
			{
				quality = x;
				return true;
			}

			return false;
		}

		private void g(List<Integer> cur, int localc, List<List<Integer>> curans)
		{
			if (localc == 2)
			{
				if (cur.get(0) == cur.get(1))
				{
					eg++;
				}
			}
			else
			{
				if (cur.get(0) == cur.get(1) || cur.get(2) == cur.get(1))
				{
					if (cur.get(0) == cur.get(2))
					{
						eg++;
					}
					else
					{
						gg++;
					}
				}
				else if (cur.get(0) == cur.get(2))
				{
					gg++;
				}
			}

			curans.add(new ArrayList(cur));
		}
	}
}