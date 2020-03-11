package backtracking;

import util.tester.Tester;

/**
 * 52
 */
public class NQueensII
{
	private static class Solution
	{
		boolean[] js;
		boolean[] d1;
		boolean[] d2;
		long c;
		int n;

		public long totalNQueens(int n)
		{
			this.n = n;
			js = new boolean[n];
			d1 = new boolean[n + n];
			d2 = new boolean[n + n];
			gen(0);
			return c;
		}

		void gen(int i)
		{
			if (i == n)
			{
				c++;
			}
			else
			{
				for (int j = 0; j < n; j++)
				{
					if (js[j]) continue;
					if (d1[i + j]) continue;
					if (d2[n + i - j]) continue;

					js[j] = true;
					d1[i + j] = true;
					d2[n + i - j] = true;

					gen(i + 1);

					js[j] = false;
					d1[i + j] = false;
					d2[n + i - j] = false;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(8).expect(92)
				.add(14).expect(365596)
				.run();
	}
}