package backtracking.other;

/**
 * 526
 */
public class BeautifulArrangement {
	class Solution {
		private int count = 0;

		public int countArrangement(int N) {
			generate(N, new boolean[N + 1], 1);
			return count;
		}

		private void generate(int N, boolean[] seen, int j) {
			if (j == N + 1) {
				count++;
			} else {
				for (int i = 1; i <= N; i++) {
					if (seen[i]) {
						continue;
					}
					if (!isB(i, j)) {
						continue;
					}

					seen[i] = true;
					generate(N, seen, j + 1);
					seen[i] = false;
				}
			}
		}

		private boolean isB(int val, int i) {
			return i % val == 0 || val % i == 0;
		}
	}
}

