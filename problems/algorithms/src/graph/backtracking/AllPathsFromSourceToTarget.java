package graph.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 797
 */
public class AllPathsFromSourceToTarget
{
	/**
	 * Time complexity: O(V) Space complexity: O(1)
	 */
	public class Solution
	{
		List<List<Integer>> l = new ArrayList<>();

		public List<List<Integer>> allPathsSourceTarget(int[][] graph)
		{
			fill(graph, 0, new ArrayList<>());
			return l;
		}

		void fill(int[][] graph, int v, ArrayList<Integer> cur)
		{
			if (v == graph.length - 1) l.add(cur);
			cur.add(v);
			for (int w : graph[v]) fill(graph, w, new ArrayList<>(cur));
		}
	}
}