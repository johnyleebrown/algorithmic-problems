// 1129
public class ShortestPathWithAlternatingColors
{

	class Solution 
	{
		public int[] shortestAlternatingPaths(int n, 
				int[][] red_edges, int[][] blue_edges) 
		{
			// 2 maps for diff colors
			Map<Integer, List<Integer>> rm = new HashMap<>(); 
			fillMap(rm, red_edges);
			Map<Integer, List<Integer>> bm = new HashMap<>(); 
			fillMap(bm, blue_edges);

			// fill answer with -1 because we might not get to those nodes
			int[] ans = new int[n]; Arrays.fill(ans, Integer.MAX_VALUE);

			// classique
			List<int[]> q = new ArrayList<>(); 
			// we add two variants of 0, for red and for blue
			q.add(new int[]{0, 0}); q.add(new int[]{0, 1});

			// temp map with base value
			Map<Integer, List<Integer>> m = rm;

			// count
			int c = 0;

			// set to keep track of seen els
			Set<String> seen = new HashSet<>();

			while (!q.isEmpty())
			{
				int size = q.size();

				while (--size >= 0)
				{
					int[] x = q.remove(0);
					int a = x[0], b = x[1];

					// if we ve seen a vertex of some color
					if (!seen.add(a + " " + b)) continue;

					ans[a] = Math.min(ans[a], c);

					// if its blue take red map, if red - take blue
					m = b == 1 ? rm : bm;

					for (int w: m.getOrDefault(a, new ArrayList<>()))
					{
						q.add(new int[]{w, 1 - b});
					}
				}

				c++;
			}

			for (int i = 0; i < n; i++) if (ans[i] == Integer.MAX_VALUE) 
				ans[i] = -1;

			return ans;
		}

		private void fillMap(Map<Integer, List<Integer>> g, int[][] ars)
		{
			for (int[] ar: ars) 
			{
				g.putIfAbsent(ar[0], new ArrayList<>());
				g.get(ar[0]).add(ar[1]);
			}
		}
	}




}

