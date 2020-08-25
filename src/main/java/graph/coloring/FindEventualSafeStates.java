package graph.coloring;

import java.util.ArrayList;
import java.util.List;

/**
 * 802
 */
public class FindEventualSafeStates {
	public static class Solution {
		private boolean[] seenCycle, globalSeen, memo;
		private List<Integer> ans;

		public List<Integer> eventualSafeNodes(int[][] graph) {
			ans = new ArrayList<>();
			seenCycle = new boolean[graph.length];
			memo = new boolean[graph.length];
			globalSeen = new boolean[graph.length];
			for (int v = 0; v < graph.length; v++) {
				if (dfs(v, graph)) {
					ans.add(v);
				}
			}
			return ans;
		}

		private boolean dfs(int v, int[][] graph) {
			if (globalSeen[v]) return memo[v];
			globalSeen[v] = true;
			if (seenCycle[v]) return false;
			seenCycle[v] = true;

			boolean localans = true;
			for (int w : graph[v]) {
				localans &= dfs(w, graph);
			}

			memo[v] = localans;
			seenCycle[v] = false;

			return localans;
		}
	}

}

