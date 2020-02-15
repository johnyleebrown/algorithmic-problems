package graph.coloring;

import java.util.ArrayList;
import java.util.List;

/**
 * 802
 */
public class FindEventualSafeStates
{
	class Solution
	{
		private boolean[] seen, marked, memo;
		private List<Integer> ans;

		public List<Integer> eventualSafeNodes(int[][] graph)
		{
			ans = new ArrayList<>();
			seen = new boolean[graph.length];
			memo = new boolean[graph.length];
			marked = new boolean[graph.length];
			for (int v = 0; v < graph.length; v++)
				if (dfs(v, graph)) ans.add(v);
			return ans;
		}

		private boolean dfs(int v, int[][] graph)
		{
			if (marked[v]) return memo[v];
			marked[v] = true;
			if (seen[v]) return false;
			seen[v] = true;

			boolean localans = true;
			for (int w : graph[v]) localans &= dfs(w, graph);

			memo[v] = localans;
			seen[v] = false;

			return localans;
		}
	}

}

