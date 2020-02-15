package graph.coloring;

/**
 * 785
 */
public class IsGraphBipartite
{
	class Solution
	{
		private boolean[] seen;
		private boolean[] colors;

		public boolean isBipartite(int[][] graph)
		{
			seen = new boolean[graph.length];
			colors = new boolean[graph.length];
			for (int i = 0; i < graph.length; i++)
				if (!dfs(i, graph)) return false;
			return true;
		}

		private boolean dfs(int v, int[][] graph)
		{
			if (seen[v]) return true;
			seen[v] = true;
			for (int w : graph[v])
			{
				if (seen[w] && colors[w] == colors[v]) return false;
				colors[w] = !colors[v];
				if (!dfs(w, graph)) return false;
			}
			return true;
		}
	}
}