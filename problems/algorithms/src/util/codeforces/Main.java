package util.codeforces;

import util.ds.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Main
{
	private static void s(InputReader in, PrintWriter out)
	{
		int n = in.nextInt();
		int m = in.nextInt();
		Map<Integer, Set<Integer>> g = new HashMap<>();
		int[] p = new int[n + 1];
		p[1]=-1;
		for (int i = 0; i < n - 1; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			g.putIfAbsent(v, new HashSet<>());
			g.get(v).add(w);
			g.putIfAbsent(w, new HashSet<>());
			g.get(w).add(v);
			p[w] = v;
		}
		int[] d = d(n,g,p);
		while (--m >= 0) {
			int k = in.nextInt();
			Set<Integer> s = new HashSet<>();
			int maxd = -1;
			int maxind = 0;
			for (int i = 0; i < k; i++) {
				int cur = in.nextInt();
				s.add(cur);
				if (maxd < d[cur]) {
					maxd = d[cur];
					maxind = cur;
				}
			}
			dfs(g,maxind,s,p);
			out.println(s.size()>0?"NO":"YES");
		}
	}

	private static void dfs(Map<Integer, Set<Integer>> g, int cur,
	                        Set<Integer> s, int[] p) {
		if (cur==-1||s.isEmpty())
			return;
		s.removeIf(x -> g.get(cur).contains(x) || cur == x);
		dfs(g, p[cur], s, p);
	}

	private static int[] d(int n,Map<Integer, Set<Integer>> g,int[] p) {
		int[] d = new int[n + 1];
		List<Integer> q = new LinkedList<>();
		q.add(1);
		int curd = 0;
		while (!q.isEmpty()) {
			int s = q.size();
			while (--s >= 0) {
				int v = q.remove(0);
				d[v] = curd;
				for (int w: g.get(v)) {
					if (w!=p[v]) {
						q.add(w);
					}
				}
			}
			curd++;
		}
		return d;
	}

	// @formatter:off
	public static void solve(InputReader in, PrintWriter out)
	{
//		int t = in.nextInt(); while (--t >= 0) { s(in, out); }
		s(in, out);
	}
	public static void main(String[] args){PrintWriter out = new PrintWriter(System.out);InputReader in = new InputReader(System.in);solve(in, out);out.close();}
	// @formatter:on
}