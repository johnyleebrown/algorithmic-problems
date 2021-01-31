package sort.other;

import reader.InputReader;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 624Div3B
 *
 * ======
 *
 * Task.
 *
 * You are given an array 𝑎 of length 𝑛. You are also given a set of distinct
 * positions 𝑝1,𝑝2,…,𝑝𝑚 , where 1≤𝑝𝑖< 𝑛. The position 𝑝𝑖 means that you
 * can swap elements 𝑎[𝑝𝑖] and 𝑎[𝑝𝑖+1]. You can apply this operation any
 * number of times for each of the given positions.
 *
 * Your task is to determine if it is possible to sort the initial array in
 * non-decreasing order (𝑎1≤𝑎2≤⋯≤𝑎𝑛) using only allowed swaps.
 *
 * For example, if 𝑎=[3,2,1] and 𝑝=[1,2], then we can first swap elements
 * 𝑎[2] and 𝑎[3] (because position 2 is contained in the given set 𝑝). We get
 * the array 𝑎=[3,1,2]. Then we swap 𝑎[1] and 𝑎[2] (position 1 is also
 * contained in 𝑝). We get the array 𝑎=[1,3,2]. Finally, we swap 𝑎[2] and
 * 𝑎[3] again and get the array 𝑎=[1,2,3] , sorted in non-decreasing order.
 *
 * You can see that if 𝑎=[4,1,2,3] and 𝑝=[3,2] then you cannot sort the
 * array.
 *
 * You have to answer 𝑡 independent test cases.
 *
 * ======
 *
 * Source: Codeforces
 */
public class WeirdSort {
	/**
	 * Swap if a[i + 1] < a[i], if it can't be done, return no, otherwise yes.
	 *
	 * Other solution: sort ranges of swap indexes, then check if the array is
	 * sorted.
	 */
	private static class Solution {
		private static void s(InputReader in, PrintWriter out) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}

			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < m; i++) {
				set.add(in.nextInt() - 1);
			}

			while (true) {
				int e = -1;
				boolean bad = false;

				for (int i = 0; i < n - 1; i++) {
					if (a[i] > a[i + 1]) {
						bad = true;

						if (set.contains(i)) {
							int t = a[i + 1];
							a[i + 1] = a[i];
							a[i] = t;

							if (e == -1)
								e = 1;
							else
								e++;
						}
					}
				}

				if (e == -1) {
					// if found out that there are 2 consecutive elements that we consider bad
					// and we didn't swap them
					if (bad) {
						out.println("NO");
					} else {
						out.println("YES");
					}
					return;
				}
			}
		}
	}
}
