package graph.traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * 261
 *
 * ======
 *
 * Task.
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to check whether these edges make up a valid tree.
 *
 * ======
 *
 * Source: Leetcode
 */
public class GraphValidTree {

	/**
	 * Graph is a tree iff
	 * - it has no cycles
	 * - it has 1 cc
	 */
	public static class Solution1 {
		public boolean validTree(int n, int[][] edges) {

			if (n <= 1) return true;

			List<Integer>[] g = new List[n];
			createGraph(g, edges);

			boolean[] seen = new boolean[n];

			// check if has cycle
			if (!dfs(0, seen, new boolean[n], g, -1)) {
				return false;
			}

			// check if 1 cc - if we have visited n vertices in dfs
			for (int i = 0; i < n; i++) {
				if (!seen[i]) {
					return false;
				}
			}

			return true;
		}

		private boolean dfs(int v, boolean[] seen, boolean[] cycleSeen, List<Integer>[] g, int parent) {

			// if on a cycle
			if (cycleSeen[v]) {
				return false;
			}
			cycleSeen[v] = true;

			// if seen before
			if (seen[v]) {
				return true;
			}
			seen[v] = true;

			if (g[v] != null) {
				for (int w : g[v]) {
					if (w != parent) {
						if (!dfs(w, seen, cycleSeen, g, v)) {
							return false;
						}
					}
				}
			}

			cycleSeen[v] = false;
			return true;
		}

		private void createGraph(List<Integer>[] g, int[][] edges) {
			for (int[] e : edges) {
				if (g[e[0]] == null) {
					g[e[0]] = new ArrayList<>();
				}
				g[e[0]].add(e[1]);
				if (g[e[1]] == null) {
					g[e[1]] = new ArrayList<>();
				}
				g[e[1]].add(e[0]);
			}
		}
	}

	/**
	 * Same idea, but.
	 * Count in dfs, without visited array.
	 */
	public static class Solution2 {
		public boolean validTree(int n, int[][] edges) {

			if (n <= 1) return true;

			// create graph
			List<Integer>[] g = new List[n];
			createGraph(g, edges);

			int verticesCount = dfs(0, new boolean[n], g, -1);
			return verticesCount != -1 && verticesCount + 1 == n;
		}

		private int dfs(int v, boolean[] cycleSeen, List<Integer>[] g, int parent) {
			if (cycleSeen[v]) {
				return -1;
			}
			cycleSeen[v] = true;

			int count = 0;
			if (g[v] != null) {
				for (int w : g[v]) {
					if (w != parent) {
						int innerCount = dfs(w, cycleSeen, g, v);
						if (innerCount == -1) {
							return -1;
						}

						// + 1 for w itself
						count += innerCount + 1;
					}
				}
			}

			cycleSeen[v] = false;
			return count;
		}

		private void createGraph(List<Integer>[] g, int[][] ed) {
			for (int[] e : ed) {
				if (g[e[0]] == null) {
					g[e[0]] = new ArrayList<>();
				}
				g[e[0]].add(e[1]);
				if (g[e[1]] == null) {
					g[e[1]] = new ArrayList<>();
				}
				g[e[1]].add(e[0]);
			}
		}
	}
}