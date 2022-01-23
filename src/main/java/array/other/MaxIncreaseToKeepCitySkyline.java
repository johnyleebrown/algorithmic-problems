package array.other;

/**
 * 807
 */
public class MaxIncreaseToKeepCitySkyline {
	public static class Solution {
		public static int solution(int[][] grid) {
			int increase = 0;

			int[] leftRightSkyline = new int[grid.length];
			for (int i = 0; i < grid.length; i++) {
				int max = grid[i][0];
				for (int j = 1; j < grid[0].length; j++) {
					max = Math.max(max, grid[i][j]);
				}
				leftRightSkyline[i] = max;
			}

			int[] topDownSkyline = new int[grid[0].length];
			for (int j = 0; j < grid[0].length; j++) {
				int max = grid[0][j];
				for (int i = 1; i < grid.length; i++) {
					max = Math.max(max, grid[i][j]);
				}
				topDownSkyline[j] = max;
			}

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					increase += Math.min(leftRightSkyline[i], topDownSkyline[j]) - grid[i][j];
				}
			}

			return increase;
		}
	}
}
