package graph.shortestPaths.bfs.tree;

import java.util.*;

/**
 * 310
 */
public class MinimumHeightTrees
{
	/**
	 * We start from every vertex of degree 1. We let the pointers move the same
	 * speed. When two pointers meet, we keep only one of them, until the last
	 * two pointers meet or one step away we then find the roots. It is easy to
	 * see that the last two pointers are from the two ends of the longest path
	 * in the graph. Remove the leaves, update the degrees of inner vertexes.
	 * Then remove the new leaves. Doing so level by level until there are 2 or
	 * 1 nodes left. Basically, the idea is to eat up all the leaves at the same
	 * time, until one/two leaves are left.
	 */
	class Solution
	{
		public List<Integer> findMinHeightTrees(int n, int[][] edges)
		{
			Map<Integer, List<Integer>> g = new HashMap<>();
			for (int i = 0; i < n; i++)
				g.put(i, new ArrayList<>());

			for (int[] e : edges)
			{
				g.get(e[0]).add(e[1]);
				g.get(e[1]).add(e[0]);
			}

			List<Integer> q = new ArrayList<>();
			for (int i = 0; i < n; i++)
				if (g.get(i).size() <= 1)
					q.add(i);

			while (n > 2)
			{
				int size = q.size();
				while (--size >= 0)
				{
					int v = q.remove(0);
					for (int w : g.get(v))
					{
						g.get(w).remove(new Integer(v));
						if (g.get(w).size() == 1)
						{
							q.add(w);
						}
					}
					n--;
				}
			}
			return q;
		}
	}
}