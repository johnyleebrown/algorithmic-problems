package backtracking;

/**
 * 276 https://leetcode.com/problems/paint-fence
 */
public class PaintFence {
	public static class Solution {
		int ans;

		public int numWays(int n, int k) {
			gen(k, new int[n], 0);
			return ans;
		}

		void gen(int k, int[] ar, int i) {
			if (i == ar.length) {
				ans++;
			} else {
				for (int color = 0; color < k; color++) {
					if (i > 1 && ar[i - 2] == ar[i - 1] && ar[i - 1] == color) {
						continue;
					}
					ar[i] = color;
					gen(k, ar, i + 1);
				}
			}
		}
	}
}
/*
3
2
1
1
7
2
1
7
7
1
11
2
2
46340
2
3
3
3
*/