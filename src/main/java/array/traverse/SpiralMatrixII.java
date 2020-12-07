package array.traverse;

/**
 * 59
 *
 * ======
 *
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral
 * order.
 *
 * ======
 *
 * https://leetcode.com/problems/spiral-matrix-ii/
 */
public class SpiralMatrixII {
	public static class Solution {
		public int[][] generateMatrix(int n) {
			int i = 0, j = 0, cur = 1, offset = 0;
			int[][] ans = new int[n][n];
			while (cur <= n * n) {
				while (j != n - offset) {
					ans[i][j] = cur++;
					j++;
				}
				if (cur > n * n) {
					break;
				}
				i++;
				j--;
				while (i != n - offset) {
					ans[i][j] = cur++;
					i++;
				}
				if (cur > n * n) {
					break;
				}
				i--;
				j--;
				while (j != -1 + offset) {
					ans[i][j] = cur++;
					j--;
				}
				if (cur > n * n) {
					break;
				}
				j++;
				i--;
				offset++;
				while (i != -1 + offset) {
					ans[i][j] = cur++;
					i--;
				}
				if (cur > n * n) {
					break;
				}
				i++;
				j++;
			}
			return ans;
		}
	}
}