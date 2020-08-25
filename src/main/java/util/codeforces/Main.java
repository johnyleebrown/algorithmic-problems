package util.codeforces;

import util.utils.reader.InputReader;

import java.io.PrintWriter;
import java.util.*;

public class Main {
	private static void s(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		int m = in.nextInt();
		Map<Integer, Set<Integer>> g = new HashMap<>();
		int[] p = new int[n + 1];
		p[1] = -1;
		for (int i = 0; i < n - 1; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			g.putIfAbsent(v, new HashSet<>());
			g.get(v).add(w);
			g.putIfAbsent(w, new HashSet<>());
			g.get(w).add(v);
			p[w] = v;
		}
		int[] d = d(n, g, p);
		while (--m >= 0) {
			int k = in.nextInt();
			List<Integer> l = new LinkedList<>();
			int maxd = -1;
			int maxind = 0;
			for (int i = 0; i < k; i++) {
				int cur = in.nextInt();
				l.add(cur);
				if (maxd < d[cur]) {
					maxd = d[cur];
					maxind = cur;
				}
			}
			Set<Integer> s = new HashSet<>();
			dfs(g, maxind, s, p);
//			for (Object o : s) {
//				System.out.println(o);
//			}
			boolean f = false;
			for (int v : l) {
				if (!s.contains(v)) {
					f = true;
					break;
				}
			}
			if (f) {
				out.println("NO");
			} else {
				out.println("YES");
			}
		}
	}

	private static void dfs(Map<Integer, Set<Integer>> g, int cur, Set<Integer> s, int[] p) {
		if (cur == -1)
			return;
//		s.removeIf(x -> g.get(cur).contains(x) || cur == x);
		s.add(cur);
		s.addAll(g.get(cur));
		dfs(g, p[cur], s, p);
	}

	private static int[] d(int n, Map<Integer, Set<Integer>> g, int[] p) {
		int[] d = new int[n + 1];
		List<Integer> q = new LinkedList<>();
		q.add(1);
		int curd = 0;
		while (!q.isEmpty()) {
			int s = q.size();
			while (--s >= 0) {
				int v = q.remove(0);
				d[v] = curd;
				for (int w : g.get(v)) {
					if (w != p[v]) {
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