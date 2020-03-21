package math.combinatorics.factorial;

import java.util.ArrayList;
import java.util.List;

/**
 * 60
 */
public class PermutationSequence
{
	/**
	 * Some math. In total we have n! combinations, so we have (n-1)!
	 * combinations starting at some kind of digit. The ith digit will be
	 * k/(n-i-1)! out of leftovers.
	 */
	private static class Solution1
	{
		public String getPermutation(int n, int k)
		{
			List<Integer> nums = new ArrayList<>();
			for (int i = 1; i <= n; i++)
				nums.add(i);

			int[] fact = new int[n + 1];
			fact[0] = 1;
			for (int i = 1; i <= n; i++)
				fact[i] = fact[i - 1] * i;

			k = k - 1;

			StringBuilder sb = new StringBuilder();
			for (int j = n - 1; j >= 0; j--)
			{
				int i = k / fact[j];
				k %= fact[j];
				sb.append(nums.get(i));
				nums.remove(i);
			}

			return sb.toString();
		}
	}

	/**
	 * Backtracking - not efficient. O(n!).
	 */
	private static class Solution2
	{
		private int n, k;
		private boolean[] seen;
		private int c;

		public String getPermutation(int n, int k)
		{
			this.n = n;
			this.k = k;
			seen = new boolean[n + 1];
			return g(new char[n], 0);
		}

		private String g(char[] ca, int pos)
		{
			if (pos == n && ++c == k)
			{
				for (int i = 0; i < n; i++)
					ca[i] += '0';
				return String.valueOf(ca);
			}
			else
			{
				for (int i = 1; i <= n; i++)
				{
					if (seen[i])
						continue;
					seen[i] = true;
					ca[pos] = (char) i;
					String res = g(ca, pos + 1);
					if (res != null)
						return res;
					seen[i] = false;
				}
			}
			return null;
		}
	}
}