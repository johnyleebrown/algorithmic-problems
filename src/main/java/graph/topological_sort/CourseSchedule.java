package graph.topological_sort;

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
				if (hasCycleHelper(v, new boolean[n], globalSeen, g)) {
					return true;
				}
			}
			return false;
		}

		boolean hasCycleHelper(int v,
				boolean[] localSeen,
				boolean[] globalSeen,
				Map<Integer, List<Integer>> g) {
			if (localSeen[v]) {
				return true;
			}
			if (globalSeen[v]) {
				return false;
			}
			localSeen[v] = true;
			globalSeen[v] = true;

			for (int w : g.get(v)) {
				if (hasCycleHelper(w, localSeen, globalSeen, g)) {
					return true;
				}
			}

			localSeen[v] = false;
			return false;
		}
	}
}