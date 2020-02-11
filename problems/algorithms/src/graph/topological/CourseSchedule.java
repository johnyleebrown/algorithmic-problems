package graph.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 207
 */
public class CourseSchedule
{
	/**
	 * detect if there is a cycle in DAG
	 */
	class Solution
	{
		private boolean[] seen;
		private boolean[] recursionStack;
		private Map<Integer, List<Integer>> g;

		public boolean canFinish(int numCourses, int[][] prerequisites)
		{
			init(numCourses);

			for (int[] p : prerequisites)
			{
				g.putIfAbsent(p[1], new ArrayList<>());
				g.get(p[1]).add(p[0]);
			}

			return !hasCycle();
		}

		private boolean hasCycle()
		{
			for (int v : g.keySet()) if (dfs(v)) return true;
			return false;
		}

		private boolean dfs(int v)
		{
			if (recursionStack[v]) return true;
			if (seen[v]) return false;

			recursionStack[v] = true;
			seen[v] = true;

			for (int w : g.getOrDefault(v, new ArrayList<>()))
				if (dfs(w)) return true;

			recursionStack[v] = false;
			return false;
		}

		private void init(int n)
		{
			seen = new boolean[n];
			recursionStack = new boolean[n];
			g = new HashMap<>();
		}
	}
}