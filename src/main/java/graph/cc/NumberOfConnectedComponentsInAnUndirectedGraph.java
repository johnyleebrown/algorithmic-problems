package graph.cc;

import java.util.ArrayList;
import java.util.List;

/**
 * 323
 *
 * ======
 *
 * Task.
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to find the number of connected components in an undirected graph.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
	public static class Solution {
		public int countComponents(int n, int[][] ed) {
			if (n <= 0) return 0;
			if (n == 1) return 1;
			boolean[] visited = new boolean[n];
			List<Integer>[] g = new List[n];
			for (int i = 0; i < n; i++) {
				g[i] = new ArrayList<>();
			}
			for (int[] e : ed) {
				g[e[0]].add(e[1]);
				g[e[1]].add(e[0]);
			}
			int ans = 0;
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					dfs(i, g, visited);
					ans++;
				}
			}
			return ans;
		}

		private void dfs(int v, List<Integer>[] g, boolean[] visited) {
			visited[v] = true;
			for (int w : g[v]) {
				if (!visited[w]) {
					dfs(w, g, visited);
				}
			}
		}
	}
}