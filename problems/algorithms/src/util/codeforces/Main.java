package util.codeforces;

import java.io.*;
import java.util.*;

public class Main
{
	/**
	 * ********************************
	 *
	 * ********************************
	 */
	private static void s(InputReader in, PrintWriter o)
	{

	}

	public static void solve(InputReader in, PrintWriter o)
	{
		s(in, o);
	}

	/**
	 * ********************************
	 *
	 * ********************************
	 */
	private static void p(PrintWriter out, Object o)
	{
		out.println(o);
	}

	private static void par(PrintWriter out, Integer[] a)
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
}
