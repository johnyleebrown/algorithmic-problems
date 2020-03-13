package util.utility;

import java.io.PrintWriter;

public class Out
{
	public static void print(PrintWriter out, Object o)
	{
		out.println(o);
	}

	public static void printAr(PrintWriter out, int[] a)
	{
		for (int value : a)
		{
			out.print(value);
			out.print(" ");
		}
		out.println();
	}
}
