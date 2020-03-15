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
	class Solution {
		public List<Integer> findMinHeightTrees(int n, int[][] e) {
			List<Integer>[] g= new List[n];
			for (int i = 0; i < n; i++)
				g[i] = new LinkedList<>();
			for (int[] ee : e) {
				g[ee[0]].add(ee[1]);
				g[ee[1]].add(ee[0]);
			}
			List<Integer> q = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				if (g[i].size() <= 1) {
					q.add(i);
				}
			}
			while (n > 2) {
				int size = q.size();
				n -= size;
				while (--size >= 0) {
					int v = q.remove(0);
					for (int w : g[v]) {
						g[w].remove(new Integer(v));
						if (g[w].size() == 1) {
							q.add(w);
						}
					}
				}
			}
			return q;
		}
	}
}