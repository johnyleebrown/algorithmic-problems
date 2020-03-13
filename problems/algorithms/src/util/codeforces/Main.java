package util.codeforces;

import util.ds.InputReader;

import java.io.PrintWriter;

import static util.utility.Out.printAr;

public class Main
{
	/**
	 * ********************************
	 */
	private static void s(InputReader in, PrintWriter out)
	{
		int n = in.nextInt();

		int[] res = new int[n];
		printAr(out, res);
	}

	public static void solve(InputReader in, PrintWriter out)
	{
//		int t = in.nextInt();
//		while (--t >= 0)
//		{
//			s(in, o);
//		}
		s(in, o);
	}

	/**
	 * ********************************
	 */
	// @formatter:off
	public static void main(String[] args){PrintWriter out = new PrintWriter(System.out);InputReader in = new InputReader(System.in);solve(in, out);out.close();}
	// @formatter:on
}
