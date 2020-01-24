package util.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
