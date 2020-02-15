package graph.cc;

import java.util.*;

/**
 * 1202
 */
public class SmallestStringWithSwaps
{
	class Solution
	{
		private PriorityQueue<Character> chars = new PriorityQueue<>();
		private PriorityQueue<Integer> positions = new PriorityQueue<>();
		private boolean[] seen;
		private Map<Integer, List<Integer>> g = new HashMap<>();

		// collect chars and positions from a connected components
		// sort the chars and fill the resulting string
		public String smallestStringWithSwaps(String s, List<List<Integer>> pairs)
		{
			constructGraph(s, pairs);

			seen = new boolean[s.length()];
			char[] res = new char[s.length()];

			for (int i = 0; i < s.length(); i++)
			{
				if (!searchForConnectedComponents(i, s)) continue;
				while (!chars.isEmpty())
				{
					char character = chars.poll();
					int position = positions.poll();

					res[position] = character;
				}
			}

			return String.valueOf(res);
		}

		// dfs
		private boolean searchForConnectedComponents(int v, String s)
		{
			if (seen[v]) return false;
			seen[v] = true;

			chars.add(s.charAt(v));
			positions.add(v);

			for (int w : g.get(v))
			{
				searchForConnectedComponents(w, s);
			}

			return true;
		}

		private void constructGraph(String s, List<List<Integer>> pairs)
		{
			for (int i = 0; i < s.length(); i++)
			{
				g.put(i, new ArrayList<>());
			}

			for (List<Integer> p : pairs)
			{
				g.get(p.get(0)).add(p.get(1));
				g.get(p.get(1)).add(p.get(0));
			}
		}
	}
}