package hard.graph.topological;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

import static test.Out.sout;
import static test.Out.sou;

// 269
public class AlienDictionary
{
	private static Map<Character, Set<Character>> g;

	private static boolean[] seenCycle;
	private static boolean[] seen;
	private static boolean[] seenInRecStack;

	private static char[] charr;
	private static int c;

	public static void main(String[] args)
	{
		String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
		sout(alienOrder(words));
	}

	public static String alienOrder(String[] words) 
	{
		if (words.length == 0) return "";
		init(words);
		if (graphHasCycle()) return "";
		return getTopologicalOrder();	
	}

	private static boolean graphHasCycle()
	{
		for (char v: g.keySet()) if (searchForCycle(v)) return true;
		return false;
	}

	private static int getInt(char x)
	{
		return (int) x - 97;
	}

	private static boolean searchForCycle(char v0)
	{
		int v = getInt(v0);
		if (seenInRecStack[v]) return true;
		if (seen[v]) return false;
		seenInRecStack[v] = seen[v] = true;
		for (char w: g.getOrDefault(v, new HashSet<>())) 
			if (searchForCycle(w)) return true;
		seenInRecStack[v] = false;
		return false;
	}

	private static String getTopologicalOrder()
	{
		for (char v: g.keySet()) postOrderTraverse(v);
		return String.valueOf(charr);
	}
	
	private static void postOrderTraverse(char v0)
	{
		int v = getInt(v0);
		//sout(v0);
		if (seen[v]) return;
		seen[v] = true;
		for (char w: g.getOrDefault(v, new HashSet<>())) postOrderTraverse(w);
		//sout(v0);
		charr[c--] = v0; 
	}

	private static void init(String[] words)
	{
		initGraph(words);
/*
		for (char c: g.keySet())
		{
			sout(c+" : ");
			for (char x: g.get(c))
			{
				sou(x+" ");
			}
		}		
*/
		seenCycle = new boolean[25];
		seen = new boolean[25];
		seenInRecStack = new boolean[25];
		charr = new char[g.size()];
		c = g.size() - 1;
	}

	private static void initGraph(String[] words)
	{
		g = new HashMap<>();
		// comparing two conseq words
		for (int i = 0; i < words.length - 1; i++)
		{
			String s1 = words[i], s2 = words[i + 1];
			int len = Math.min(s1.length(), s2.length());
			for (int j = 0; j < len; j++)
			{
				char c1 = s1.charAt(j), c2 = s2.charAt(j);
				g.putIfAbsent(c1, new HashSet<>());
				g.putIfAbsent(c2, new HashSet<>());
				//sout(c1);sout(c2);
				if (c1 == c2) continue;
				g.get(c1).add(c2);
			}
			for (int j = len; j < Math.max(s1.length(), s2.length());j++)
			{
				if (j < s1.length()) g.putIfAbsent(s1.charAt(j), new HashSet<>());
				if (j < s2.length()) g.putIfAbsent(s2.charAt(j), new HashSet<>());
			}
		}
	}

}

