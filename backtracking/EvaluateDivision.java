// 399
class Solution
{
	private Map<String, Map<String, Double>> g;
	public double[] calcEquation(List<List<String>> equations, double[] values, 
			List<List<String>> queries) 
	{	
		double[] ans = new double[queries.size()];
		g = new HashMap<>();
		for (int i = 0; i < equations.size(); i++)
		{
			List<String> l = equations.get(i);
			String s1 = l.get(0), s2 = l.get(1);
			g.putIfAbsent(s1, new HashMap<>());
			g.putIfAbsent(s2, new HashMap<>());
			g.get(s1).put(s2, values[i]);
			g.get(s2).put(s1, 1/values[i]);
		}
		for (int i = 0; i < queries.size(); i++)
		{
			List<String> l = queries.get(i);
			String s1 = l.get(0), s2 = l.get(1);
			if (!g.containsKey(s1) || !g.containsKey(s2)) ans[i] = -1.0;
			else if (s1.equals(s2)) ans[i] = 1.0;
			else
			{
				Double x = search(s1, s2, 1.0, new HashSet<>());
				if (x == null) ans[i] = -1.0;
				else ans[i] = x;
			}
		}
		return ans;
	}

	private Double search(String s1, String s2, double ans, Set<String> seen)
	{
		if (s1.equals(s2)) return ans;
		Map<String, Double> m = g.get(s1);
		for (String s: m.keySet())
		{
			if (!seen.add(s)) continue;
			Double x = search(s, s2, ans * m.get(s), seen);
			if (x == null) continue;
			else return x;
		}
		return null;
	}
}
/*
   [["a","b"],["b","c"]]
   [2.0,3.0]
   [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
   [["a","b"],["c","d"]]
   [1.0,1.0]
   [["a","c"],["b","d"],["b","a"],["d","c"]]
   */
