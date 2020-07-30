package graph.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 207
 */
public class CourseSchedule {
	public static class Solution {
		public boolean canFinish(int n, int[][] pre) {

			// create graph
			Map<Integer, List<Integer>> g = new HashMap<>();
			for (int[] p : pre) {
				g.putIfAbsent(p[0], new ArrayList<>());
				g.get(p[0]).add(p[1]);
				g.putIfAbsent(p[1], new ArrayList<>());
			}

			// check for a cycle - if can finish all courses
			return !hasCycle(g, n);
		}

		boolean hasCycle(Map<Integer, List<Integer>> g, int n) {
			boolean[] globalSeen = new boolean[n];
			for (int v : g.keySet()) {
				if (hasCycleDfs(v, new boolean[n], globalSeen, g)) {
					return true;
				}
			}
			return false;
		}

		boolean hasCycleDfs(int v, boolean[] cycleSeen, boolean[] globalSeen, Map<Integer, List<Integer>> g) {
			if (cycleSeen[v]) {
				return true;
			}
			if (globalSeen[v]) {
				return false;
			}
			cycleSeen[v] = true;
			globalSeen[v] = true;

			for (int w : g.get(v)) {
				if (hasCycleDfs(w, cycleSeen, globalSeen, g)) {
					return true;
				}
			}

			cycleSeen[v] = false;
			return false;
		}
	}
}