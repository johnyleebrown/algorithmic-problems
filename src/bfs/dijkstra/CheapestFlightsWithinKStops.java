package bfs.dijkstra;

import java.util.*;

// 787
public class CheapestFlightsWithinKStops 
{
	// Dijkstras
	class Solution 
	{
		public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) 
		{
			Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
			for (int[] f: flights) 
			{ 
				g.putIfAbsent(f[0], new HashMap<>()); 
				g.get(f[0]).put(f[1], f[2]); 
			}
			int[] ans = new int[n]; 
			Arrays.fill(ans, Integer.MAX_VALUE); 
			ans[src] = 0;
			List<int[]> q = new ArrayList<>();
			q.add(new int[]{0, src});
			Map<Integer, Integer> m;

			while (!q.isEmpty() && K >= 0)
			{
				int size = q.size();
				while (--size >= 0)
				{
					int[] vertex = q.remove(0);
					int costToSource = vertex[0];
					m = g.getOrDefault(vertex[1], new HashMap<>());

					for (int w: m.keySet())
					{
						int costSourceToTarget = m.get(w);
						if (ans[w] < costToSource + costSourceToTarget) continue;
						ans[w] = costToSource + costSourceToTarget;
						q.add(new int[]{costToSource + costSourceToTarget, w});
					}
				}

				K--;
			}

			return ans[dst] == Integer.MAX_VALUE ? -1 : ans[dst];    
		}
	}
}

