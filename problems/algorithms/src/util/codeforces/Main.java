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
	private static void s(String s, InputReader in, PrintWriter out)
	{

	}

	public static void solve(InputReader in, PrintWriter out)
	{
		int t = in.nextInt();
		while (--t >= 0)
		{
			s(in.nextLine(), in, out);
		}
	}
	/**
	 * ********************************
	 *
	 * ********************************
	 */
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
}