package dp.pther;

import java.util.ArrayList;
import java.util.List;

/**
 * 279
 */
class PerfectSquares {

	public class Solution {
		// yo, static calls are faster then virtual and non virtual ones.
		List<Integer> result = new ArrayList<>();

		public int numSquares(int n) {
			if (result.size() == 0) {
				result.add(0);
			}

			while (result.size() <= n) {
				int m = result.size();
				int tempMin = Integer.MAX_VALUE;

				for (int j = 1; j * j <= m; j++) {
					tempMin = Math.min(tempMin, result.get(m - j * j) + 1);
				}

				result.add(tempMin);
			}

			return result.get(n);
		}
	}
}