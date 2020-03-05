package graph.traverse;

import java.util.*;

/**
 * 851
 *
 * =====
 *
 * Task.
 *
 * In a group of N people (labelled 0, 1, 2, ..., N-1), each person has
 * different amounts of money, and different levels of quietness. For
 * convenience, we'll call the person with label x, simply "person x". We'll say
 * that richer[i] = [x, y] if person x definitely has more money than person y.
 * Note that richer may only be a subset of valid observations. Also, we'll say
 * quiet[x] = q if person x has quietness q. Now, return answer, where answer[x]
 * = y if y is the least quiet person (that is, the person y with the smallest
 * value of quiet[y]), among all people who definitely have equal to or more
 * money than person x.
 */
public class LoudAndRich
{
    /**
     * SF.
     */
	private static class Solution
	{
        private int[] ans;
        private Map<Integer, List<Integer>> m = new HashMap<>();

		public int[] loudAndRich(int[][] r, int[] q)
		{
			int n = q.length;
			ans = new int[n];
			for (int i = 0; i < n; i++)
				m.putIfAbsent(i, new ArrayList<>());
			for (int[] rr : r)
				m.get(rr[1]).add(rr[0]);
			Arrays.fill(ans, -1);
			for (int i = 0; i < n; i++)
				ans[i] = dfs(i, i, q);
			return ans;
		}

		private int dfs(int curInd, int minInd, int[] q)
		{
			if (ans[curInd] != -1)
				return ans[curInd];
			if (q[minInd] > q[curInd])
				minInd = curInd;
			for (int w : m.get(curInd))
			{
				int x = dfs(w, minInd, q);
				if (q[minInd] > q[x])
					minInd = x;
			}
			return minInd;
		}
	}
}
