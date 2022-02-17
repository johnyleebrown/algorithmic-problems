package graph.topological_sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1857
 */
public class LargestColorValueInADirectedGraph {

	/**
	 * $INSERT_EXPLANATION
	 */
	public static class Solution {

		private static final List<Integer> em = Collections.emptyList();
		private static Map<Integer, Integer> cmap;
		private static Map<Integer, List<Integer>> g;
		private static int[] dp;
		private int max = 0, curMax = 0;

		public int largestPathValue(String colors, int[][] edges) {

			if (edges == null || edges.length == 0) {
				if (colors.length() > 0) {
					return 1;
				}
				return -1;
			}

			g = new HashMap<>();

			for (int[] e : edges) {
				g.putIfAbsent(e[0], new ArrayList<>());
				if (e[1] == e[0]) return -1;
				g.get(e[0]).add(e[1]);
			}

			if (hasCycle(g)) {
				return -1;
			}

			int n = colors.length();

			cmap = new HashMap<>();
			for (int i = 0; i < n; i++) {
				cmap.put(i, colors.charAt(i) - 'a');
			}

			dp = new int[n];
			int[] cc = new int[26];
			for (int i = 0; i < n; i++) {
				if (g.containsKey(i)) {
					dfs(i, cc);
				}
			}

			return max;
		}

		private boolean hasCycle(Map<Integer, List<Integer>> g) {
			Set<Integer> globalSeen = new HashSet<>();

			for (int v : g.keySet()) {
				Set<Integer> localSeen = new HashSet<>();
				if (hasCycleHelper(g, globalSeen, localSeen, v)) {
					return true;
				}
			}
			return false;
		}

		private boolean hasCycleHelper(Map<Integer, List<Integer>> g,
				Set<Integer> globalSeen,
				Set<Integer> localSeen,
				int v) {

			if (localSeen.contains(v)) {
				return true;
			}
			if (globalSeen.contains(v)) {
				return false;
			}
			localSeen.add(v);
			globalSeen.add(v);
			for (int w : g.getOrDefault(v, em)) {
				if (hasCycleHelper(g, globalSeen, localSeen, w)) {
					return true;
				}
			}
			localSeen.remove(v);
			return false;
		}

		private void dfs(int v, int[] cc) {
			int ind = cmap.get(v);
			cc[ind]++;
			dp[v] = Math.max(dp[v], getMax(cc, ind));
			max = Math.max(max, dp[v]);
			for (int w : g.getOrDefault(v, em)) {
				dfs(w, cc);
			}
			cc[cmap.get(v)]--;
		}

		private int getMax(int[] cc, int i) {
			curMax = Math.max(cc[i], curMax);
			return curMax;
		}
	}

	/**
	 * DFS2 - combine hasCycle with actual dfs
	 */
	public static class Solution2 {

		private static final List<Integer> em = Collections.emptyList();
		private static Map<Integer, Integer> cmap;
		private static Map<Integer, List<Integer>> g;
		private static int[] dp;
		int[] cc = new int[26];
		private int max = 0, curMax = 0;

		public int largestPathValue(String colors, int[][] edges) {

			if (edges == null || edges.length == 0) {
				if (colors.length() > 0) {
					return 1;
				}
				return -1;
			}

			g = new HashMap<>();

			for (int[] e : edges) {
				g.putIfAbsent(e[0], new ArrayList<>());
				if (e[1] == e[0]) return -1;
				g.get(e[0]).add(e[1]);
			}

			int n = colors.length();

			dp = new int[n];
			cmap = new HashMap<>();
			for (int i = 0; i < n; i++) {
				cmap.put(i, colors.charAt(i) - 'a');
			}

			if (hasCycle(g)) {
				return -1;
			}

			// for (int i = 0; i < n; i++) {
			//     dfs(i, cc);
			// }

			return max;
		}

		private boolean hasCycle(Map<Integer, List<Integer>> g) {
			for (int v : g.keySet()) {
				Set<Integer> localSeen = new HashSet<>();
				if (hasCycleHelper(g, localSeen, v)) {
					return true;
				}
			}
			return false;
		}

		private boolean hasCycleHelper(Map<Integer, List<Integer>> g,
				Set<Integer> localSeen,
				int v) {

			if (localSeen.contains(v)) {
				return true;
			}

			localSeen.add(v);

			int ind = cmap.get(v);
			cc[ind]++;
			dp[v] = Math.max(dp[v], getMax(cc, ind));
			max = Math.max(max, dp[v]);

			for (int w : g.getOrDefault(v, em)) {
				if (hasCycleHelper(g, localSeen, w)) {
					return true;
				}
			}

			cc[cmap.get(v)]--;

			localSeen.remove(v);
			return false;
		}

		private int getMax(int[] cc, int i) {
			curMax = Math.max(cc[i], curMax);
			return curMax;
		}
	}
}
