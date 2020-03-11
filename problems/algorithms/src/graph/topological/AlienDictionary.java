package graph.topological;

import util.tester.Tester;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 269
 */
public class AlienDictionary
{
	public static class Solution
	{
		private Map<Character, Set<Character>> g;

		private boolean[] seenCycle;
		private boolean[] seen;
		private boolean[] seenInRecStack;

		private char[] charr;
		private int c;

		public String alienOrder(String[] words)
		{
			if (words.length == 0) return "";
			init(words);
			if (graphHasCycle()) return "";
			return getTopologicalOrder();
		}

		private boolean graphHasCycle()
		{
			for (char v : g.keySet()) if (searchForCycle(v)) return true;
			return false;
		}

		private int getInt(char x)
		{
			return (int) x - 97;
		}

		private boolean searchForCycle(char v0)
		{
			int v = getInt(v0);
			if (seenInRecStack[v]) return true;
			if (seenCycle[v]) return false;
			seenInRecStack[v] = seenCycle[v] = true;
			for (char w : g.getOrDefault(v0, new HashSet<>()))
				if (searchForCycle(w)) return true;
			seenInRecStack[v] = false;
			return false;
		}

		private String getTopologicalOrder()
		{
			for (char v : g.keySet()) postOrderTraverse(v);
			return String.valueOf(charr);
		}

		private void postOrderTraverse(char v0)
		{
			int v = getInt(v0);
			if (seen[v]) return;
			seen[v] = true;
			for (char w : g.getOrDefault(v0, new HashSet<>()))
				postOrderTraverse(w);
			charr[c--] = v0;
		}

		private void init(String[] words)
		{
			initGraph(words);
			seenCycle = new boolean[26];
			seen = new boolean[26];
			seenInRecStack = new boolean[26];
			charr = new char[g.size()];
			c = g.size() - 1;
		}

		private void initGraph(String[] words)
		{
			g = new HashMap<>();
			for (int i = 0; i < words.length - 1; i++)
			{
				String s1 = words[i], s2 = words[i + 1];
				int len = Math.min(s1.length(), s2.length());
				for (int j = 0; j < len; j++)
				{
					char c1 = s1.charAt(j), c2 = s2.charAt(j);
					g.putIfAbsent(c1, new HashSet<>());
					g.putIfAbsent(c2, new HashSet<>());
					if (c1 == c2) continue;
					g.get(c1).add(c2);
				}
				for (int j = len; j < Math.max(s1.length(), s2.length()); j++)
				{
					if (j < s1.length())
						g.putIfAbsent(s1.charAt(j), new HashSet<>());
					if (j < s2.length())
						g.putIfAbsent(s2.charAt(j), new HashSet<>());
				}
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new String[]{"wrt", "wrf", "er", "ett", "rftt"}).expect("wertf")
				.add(new String[]{"za", "zb", "ca", "cb"}).expect("abzc")
				.add(new String[]{"z", "x"}).expect("zx")
				.add(new String[]{"z", "x", "z"}).expect("")
				.add(new String[]{"caa", "aaa", "aab"}).expect("cab")
				.add(new String[]{"z", "z"}).expect("z")
				.add(new String[]{"ab", "adc"}).expect("cbda")
				.add(new String[]{"ri", "xz", "qxf", "jhsguaw", "dztqrbwbm", "dhdqfb", "jdv", "fcgfsilnb", "ooby"}).expect("")
				.add(new String[]{"bsusz", "rhn", "gfbrwec", "kuw", "qvpxbexnhx", "gnp", "laxutz", "qzxccww"}).expect("")
				.run();
	}
}