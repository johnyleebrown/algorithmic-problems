package util.codeforces;

import tree.ds_.ExpressionTree;
import util.ds.InputReader;
import util.ds.TreeNode;

import java.io.PrintWriter;

import static util.utility.Out.print;

public class Main
{
	/**
	 * ********************************
	 */
	private static void s(InputReader in, PrintWriter out)
	{
		String exp = in.nextLine();
		int p = in.nextInt();
		int m = in.nextInt();
		ExpressionTree e = new ExpressionTree(exp);
		print(out, dfs(e, e.getRoot(), p, m, true));
	}

	private static int dfs(ExpressionTree e, TreeNode cur, int p, int m, boolean max)
	{
		if (cur == null)
			return 0;
		if (max)
		{
			int res = 0;
			if (p > 0)
			{
				res = dfs(e, cur.left, p - 1, m, true) + dfs(e, cur.right, p - 1, m, true);
			}
			if (m > 0)
			{
				res = Math.max(res, dfs(e, cur.left, p, m - 1, true) - dfs(e, cur.right, p, m - 1, false));
			}
			return res;
		}
		else // if min
		{
			int res = 0;
			if (p > 0)
			{
				res = dfs(e, cur.left, p - 1, m, false) - dfs(e, cur.right, p - 1, m, true);
			}
			if (m > 0)
			{
				res = Math.min(res, dfs(e, cur.left, p, m - 1, false) + dfs(e, cur.right, p, m - 1, false));
			}
			return res;
		}
	}

	// @formatter:off
	public static void solve(InputReader in, PrintWriter out)
	{
		//int t = in.nextInt(); while (--t >= 0) { s(in, out); }
		s(in, out);
	}
	/**
	 * ********************************
	 */
	public static void main(String[] args){PrintWriter out = new PrintWriter(System.out);InputReader in = new InputReader(System.in);solve(in, out);out.close();}
	// @formatter:on
}
