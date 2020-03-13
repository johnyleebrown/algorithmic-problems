package util.codeforces;

//import util.utility.Timer;

import java.io.*;
//import java.lang.reflect.Array;
import java.util.*;

public class Main
{
	/**
	 * ********************************
	 *
	 * ********************************
	 */
//	private static LinearProbingHashST<Integer, Integer>[] memo;

	private static Map<Integer, Integer>[] m;
	private static void s(InputReader in, PrintWriter o)
	{
		int n = in.nextInt();
//		memo = new LinearProbingHashST[n + 1];
		int[] clr = new int[n + 1];
		m = new HashMap[n + 1];
		List<Integer>[] g = new List[n + 1];

		for (int i = 1; i <= n; i++)
		{
			clr[i] = in.nextInt();
			m[i] = new HashMap<>();
			g[i] = new ArrayList<>();
		}

		// fill graph
		for (int i = 0; i < n - 1; i++)
		{
			int w = in.nextInt();
			int v = in.nextInt();
			g[w].add(v);m[w].put(v, Integer.MIN_VALUE);
			g[v].add(w);m[v].put(w, Integer.MIN_VALUE);
		}

//		for (int i = 0; i <= n; i++)
//		{
//			memo[i] = new LinearProbingHashST<>(g[i].size());
//			for (int w : g[i])
//				memo[i].put(w, Integer.MIN_VALUE);
//		}

		int[] res = new int[n];
		for (int i = 1; i <= n; i++)
			res[i - 1] = dfs2(i, clr, g);

		printAr(o, res);
	}

	private static int dfs2(int cur, int[] clr, List<Integer>[] g)
	{
		int curval = clr[cur] == 1 ? 1 : -1;//real node value
		int goodsum = 0;//count only >0
		for (int w: g[cur])
		{
			int x = dfs(cur, w, clr, g);
			if (x > 0) goodsum+=x;
		}
		return goodsum + curval;
	}

	private static int dfs(int prev, int cur, int[] clr, List<Integer>[] g)
	{
		if (m[cur].get(prev) != Integer.MIN_VALUE)
			return m[cur].get(prev);
//		if (memo[cur].get(prev) != Integer.MIN_VALUE)
//			return memo[cur].get(prev);
		int curval = clr[cur] == 1 ? 1 : -1;
		int goodsum = 0;
		for (int w: g[cur])
		{
			if (w == prev)
				continue;
			int x = dfs(cur, w, clr, g);
			if (x > 0)
				goodsum+=x;
		}
		m[cur].put(prev, Math.max(m[cur].get(prev), goodsum + curval));
		return m[cur].get(prev);
//		memo[cur].put(prev, Math.max(memo[cur].get(prev), goodsum + curval));
//		return memo[cur].get(prev);
	}

	public static void solve(InputReader in, PrintWriter o)
	{
//		util.utility.Timer t = new Timer().start();
//		int t = in.nextInt();
//		while (--t >= 0)
//		{
//			s(in, o);
//		}
		s(in, o);
//		System.out.println(String.format("%.2f", t.end().getTotal()));
	}

	/**
	 * ********************************
	 *
	 * ********************************
	 */
	private static void print(PrintWriter out, Object o)
	{
		out.println(o);
	}

	private static void printAr(PrintWriter out, int[] a)
	{
		for (int value : a)
		{
			out.print(value);
			out.print(" ");
		}
		out.println();
	}

	public static void main(String[] args)
	{
		PrintWriter o = new PrintWriter(System.out);
		solve(new InputReader(System.in), o);
		o.close();
	}

	public static class InputReader
	{
		public BufferedReader r;
		public StringTokenizer t;

		public InputReader(InputStream s)
		{
			r = new BufferedReader(new InputStreamReader(s), 32768);
			t = null;
		}

		public String next()
		{
			while (t == null || !t.hasMoreTokens())
			{
				try
				{
					t = new StringTokenizer(r.readLine());
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}
			}
			return t.nextToken();
		}

		public int nextInt()
		{
			return Integer.parseInt(next());
		}

		public Long nextLong()
		{
			return Long.parseLong(next());
		}

		public int[] nextIntAr(int n)
		{
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = nextInt();
			return a;
		}

		public Long[] nextLongAr(int n)
		{
			Long[] a = new Long[n];
			for (int i = 0; i < n; i++) a[i] = nextLong();
			return a;
		}

		public String nextLine()
		{
			try
			{
				return r.readLine();
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}

	private static class LinearProbingHashST<Key, Value>
	{
		private static final int INIT_CAPACITY = 4;

		private int n;
		private int m;
		private Value[] vals;
		private Key[] keys;

		public LinearProbingHashST()
		{
			this(INIT_CAPACITY);
		}

		public LinearProbingHashST(int m)
		{
			this.m = m;
			keys = (Key[]) new Object[m];
			vals = (Value[]) new Object[m];
		}

		public void put(Key key, Value val)
		{
			if (n >= m / 2)
				resize(2*m);

			int i;
			for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
				if (keys[i].equals(key)) {
					vals[i] = val;
					return;
				}
			}

			keys[i] = key;
			vals[i] = val;
			n++;
		}

		private void resize(int newCapacity)
		{
			LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(newCapacity);
			for (int i = 0; i < m; i++) {
				if (keys[i] != null) {
					temp.put(keys[i], vals[i]);
				}
			}
			keys = temp.keys;
			vals = temp.vals;
			m    = temp.m;
		}

		public Value get(Key key)
		{
			return vals[hash(key)];
		}

		private int hash(Key key)
		{
			return (key.hashCode() & 0x7fffffff) % m;
		}

		public void print()
		{
			for (int i = 0; i < m; i++)
			{
				System.out.println(keys[i] + " " + vals[i]);
			}
		}
	}

}
