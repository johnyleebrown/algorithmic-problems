package twoPointers.regular.other;

import reader.InputReader;

import java.io.PrintWriter;

/**
 * 60 A
 *
 * ======
 *
 * Task.
 *
 * One morning the Cereal Guy found out that all his cereal flakes were gone. He found a note
 * instead of them. It turned
 * out that his smart roommate hid the flakes in one of n boxes. The boxes stand in one row, they
 * are numbered from 1 to
 * n from the left to the right. The roommate left hints like "Hidden to the left of the i-th box"
 * ("To the left of i"),
 * "Hidden to the right of the i-th box" ("To the right of i"). Such hints mean that there are no
 * flakes in the i-th box
 * as well. The Cereal Guy wants to know the minimal number of boxes he necessarily needs to check
 * to find the flakes
 * considering all the hints. Or he wants to find out that the hints are contradictory and the
 * roommate lied to him,
 * that is, no box has the flakes.
 *
 * ======
 *
 * Source: Codeforces
 */
public class WhereAreMyFlakes {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.nextInt();
		int m = in.nextInt();

		int l = 1;
		int r = n;
		while (--m >= 0) {
			String[] str = in.nextLine().split(" ");
			if (str[2].length() == 5) {
				l = Math.max(Integer.parseInt(str[4]) + 1, l);
			} else {
				r = Math.min(Integer.parseInt(str[4]) - 1, r);
			}

			if (l > r) {
				out.println(-1);
				return;
			}
		}

		out.println(r - l + 1);
	}
}
