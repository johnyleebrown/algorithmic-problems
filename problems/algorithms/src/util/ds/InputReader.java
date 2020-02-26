package util.ds;

import java.io.*;
import java.util.StringTokenizer;

public class InputReader
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

	public static void p(PrintWriter out, Object o)
	{
		out.println(o);
	}

	public static void par(PrintWriter out, int[] a)
	{
		for (int value : a)
		{
			out.print(value);
			out.print(" ");
		}
		out.println();
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
		int[] ar = new int[n];
		for (int i = 0; i < n; i++)
			ar[i] = nextInt();
		return ar;
	}

	public Long[] nextLongAr(int n)
	{
		Long[] ar = new Long[n];
		for (int i = 0; i < n; i++)
			ar[i] = nextLong();
		return ar;
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
