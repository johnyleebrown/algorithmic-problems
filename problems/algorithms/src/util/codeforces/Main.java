package util.codeforces;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main
{
	/************************************/
	private static boolean connect(Map<Character, Set<Character>> m,
	                               char c1, char c2, boolean[] seen)
	{
//		System.out.println("1: " + c1 + " " + c2);
		if (c1 == c2 || (m.get(c1).contains(c2) && m.get(c2).contains(c1)))
			return true;

//		System.out.println(c1 + " " + c2);

		if (m.get(c1).size() == 2 || m.get(c2).size() == 2)
			return false;
		if (seen[c1 - 'a'] && seen[c2 - 'a'])
			return false;

//		System.out.println(c1 + " " + c2);
		m.get(c1).add(c2);
		m.get(c2).add(c1);
		seen[c1 - 'a'] = true;
		seen[c2 - 'a'] = true;
		return true;
	}

	private static StringBuilder getVariant(Map<Character, Set<Character>> m, char start)
	{
		StringBuilder sb = new StringBuilder();
		char cur = start;
		Set<Character> s = new HashSet<>();
		while (!s.contains(cur))
		{
			sb.append(cur);
			s.add(cur);
			if (!m.containsKey(cur))
				break;
			for (char child : m.get(cur))
			{
				if (!s.contains(child))
				{
					cur = child;
				}
			}
		}
		return sb;
	}

	private static void s(String s, InputReader in, PrintWriter out)
	{
		Map<Character, Set<Character>> m = new HashMap<>();
		for (int i = 0; i < 26; i++)
			m.put((char) (i + 'a'), new HashSet<>());
		boolean[] seen = new boolean[26];

		int n = s.length();
		char start = s.charAt(0);
		for (int i = 0; i < n - 1; i++)
		{
			char c1 = s.charAt(i);
			char c2 = s.charAt(i + 1);

			if (!connect(m, c1, c2, seen))
			{
				out.println("NO");
				return;
			}

			if (m.get(c1).size() == 1)
				start = c1;
			if (m.get(c2).size() == 1)
				start = c2;
		}

//		 for (Map.Entry<Character, Set<Character>> e : m.entrySet()) if (e.getValue().size()>0)
//		 {
//			 System.out.println();
//		     System.out.println("===");
//		     System.out.println(e.getKey());
//		     for (Character c: e.getValue())
//		     {
//		     	System.out.print(c + "  ");
//		     }
//		 }
//		 System.out.println();

		StringBuilder sb = getVariant(m, start);
		for (int i = 0; i < 26; i++)
		{
			char cur = (char) (i + 'a');
			if (!seen[i])
				sb.append(cur);
		}

		out.println("YES");
		out.println(sb.toString());
	}

	public static void solve(InputReader in, PrintWriter out)
	{
		int t = in.nextInt();
		while (--t >= 0)
		{
			s(in.nextLine(), in, out);
		}
	}

	/************************************/
	public static void main(String[] args)
	{
		PrintWriter out = new PrintWriter(System.out);
		solve(new InputReader(System.in), out);
		out.close();
	}

	public static class InputReader
	{
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream)
		{
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next()
		{
			while (tokenizer == null || !tokenizer.hasMoreTokens())
			{
				try
				{
					tokenizer = new StringTokenizer(reader.readLine());
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt()
		{
			return Integer.parseInt(next());
		}

		public Long nextLong()
		{
			return Long.parseLong(next());
		}

		public String nextLine()
		{
			try
			{
				return reader.readLine();
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
	/************************************/
}