package graph.coloring;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 886
 */
public class PossibleBipartition {

	/**
	 * Queue. 6ms. 56Mb.
	 */
	static class Solution {

		public static boolean possibleBipartition(int N, int[][] dislikes) {
			boolean[] seen = new boolean[N + 1], colors = new boolean[N + 1];
			Queue<Integer> q = new LinkedList<>();
			int i = 0, n, prevqsize = 0, size = dislikes.length;

			while (i < dislikes.length || !q.isEmpty()) {
				if (prevqsize == q.size()) seen[dislikes[!q.isEmpty() ? q.peek() : 0][0]] = true;

				prevqsize = q.size();

				while (--size >= 0) {
					if (i < dislikes.length) n = i++;
					else n = q.poll();

					int a = dislikes[n][0], b = dislikes[n][1];

					if (!seen[a] && !seen[b]) q.add(n);
					else if (seen[a] && seen[b] && colors[a] == colors[b]) return false;
					else if (seen[a] && seen[b] && colors[a] != colors[b]) continue;
					else {
						if (!seen[a]) colors[a] = !colors[b];
						else colors[b] = !colors[a];
						seen[a] = seen[b] = true;
					}
				}

				size = q.size();
			}

			return true;
		}

	}
}