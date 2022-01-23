package array.other;

/**
 * 832
 */
public class FlippingAnImage {
	public static class Solution {
		public int[][] flipAndInvertImage(int[][] A) {
			int n = A.length;
			int[][] ans = new int[n][n];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					ans[i][j] = 1 - A[i][n - 1 - j];

			return ans;
		}
	}
}
