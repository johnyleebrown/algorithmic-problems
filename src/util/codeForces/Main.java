package util.codeForces;

import java.io.*;
import java.util.StringTokenizer;

public class Main
{
	public static void main(String[] args)
	{
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task
	{
		public void solve(int testNumber, InputReader in, PrintWriter out)
		{
			int n = in.nextInt();
			int t = in.nextInt();
			int[] times = new int[n];
			for (int i = 0; i < n; i++)
			{
				times[i] = in.nextInt();
			}
			int l = 0;
			int count = 0;
			int result = 0;
			for (int r = 0; r < n; r++)
			{
				// use right window boundary
				count += times[r];

				while (count > t)
				{
					// use left window boundary
					count -= times[l];

					l++;
				}

				result = Math.max(result, r - l + 1);
			}
			out.println(result);
		}
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
	}
}
