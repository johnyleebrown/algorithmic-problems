package array.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * $INSERT_PROBLEM_NUMBER
 */
public class StampingTheGrid {

	/**
	 * $INSERT_EXPLANATION
	 */
	public static class Solution {

		Map<Long, int[]> map = new HashMap<>();

		public boolean possibleToStamp(int[][] grid, int h, int w) {
			// look for rectangles with >= size

			int n = grid.length;
			int m = grid[0].length;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (grid[i][j] == 0) {

					} else {

					}
				}
			}

			return true;
		}

		long getHash(int i, int j, int n) {
			return i + 1 + (n * (j + 1));
		}

		// int getHash(int n, int m) {
		//     if (n > m) {
		//         int t = n;
		//         n = m;
		//         m = t;
		//     }
		//     int x = n/10
		// }
	}
}
