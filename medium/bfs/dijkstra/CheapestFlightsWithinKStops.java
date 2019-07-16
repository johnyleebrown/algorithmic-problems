package medium.bfs.dijkstra;

// 787
public class CheapestFlightsWithinKStops
{
	class Solution {
		public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
			if (src < 0 || src >= n || dst < 0 || dst >= n) return -1;

			// construct directed graph
			Map<Integer, Map<Integer, Integer>> graph = createGraph(flights);
			
			// construct queue for costs {target, cost to get there}
			Queue<int[]> costs = new LinkedList<>(); costs.add(new int[]{src, 0});
			
			// keep the costs (distances) to each target - it will be the minimum
			int[] distances = new int[n];
			for (int i = 0; i < n; i++) distances[i] = Integer.MAX_VALUE;
			distances[src] = 0;
			
			
			// while we haven't traversed all the costs and we haven't used all the stops we are allowed to make
			while (!costs.isEmpty() && K >= 0)
			{
				int verteces = costs.size();
				while (--verteces >= 0)
				{
					int[] cost = costs.poll();
					int source = cost[0], price = cost[1];
	 
					// get all vertices 
					Map<Integer, Integer> map = graph.get(source);

					if (map == null) continue;
					for (int v: map.keySet())
					{
						// price = what it cost us to get to the source
						// map.get(v) = what it will cost us to get to v from source
						// if this price is less then what is in v then we add it to queue
						int newPrice = price + map.get(v);

						if (distances[v] > newPrice)
						{
							distances[v] = newPrice;
							costs.add(new int[]{v, newPrice});
						}
					}
				}
				
				K--;
			}

			return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
		}

		private Map<Integer, Map<Integer, Integer>> createGraph(int[][] flights)
		{
			Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
			for (int[] flight: flights)
			{
				int source = flight[0];
				int destination = flight[1];
				int cost = flight[2];
				
				graph.putIfAbsent(source, new HashMap<>());
				// believe there is no duplicate costs
				graph.get(source).put(destination, cost);
			}
			return graph;
		}
	}
}
/*
3
[[0,1,100],[1,2,100],[0,2,500]]
0
2
1
3
[[0,1,100],[1,2,100],[0,2,500]]
0
2
0
4
[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
0
3
1
7
[[0,3,7],[4,5,3],[6,4,8],[2,0,10],[6,5,6],[1,2,2],[2,5,9],[2,6,8],[3,6,3],[4,0,10],[4,6,8],[5,2,6],[1,4,3],[4,1,6],[0,5,10],[3,1,5],[4,3,1],[5,4,10],[0,1,6]]
2
4
1
4
[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
0
3
1
5
[[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]
0
2
2
*/
