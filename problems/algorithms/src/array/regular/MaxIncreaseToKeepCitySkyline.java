package array.regular;

/**
 * 807
 *
 * In a 2 dimensional array grid, each value grid[i][j] represents the height of a building located there.
 * We are allowed to increase the height of any number of buildings, by any amount (the amounts can be different
 * for different buildings). Height 0 is considered to be a building as well. At the end, the "skyline" when
 * viewed from all four directions of the grid, i.e. top, bottom, left, and right, must be the same as the
 * skyline of the original grid. A city's skyline is the outer contour of the rectangles formed by all the
 * buildings when viewed from a distance. See the following example. What is the maximum total sum that the
 * height of the buildings can be increased?
 */
public class MaxIncreaseToKeepCitySkyline {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     */
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
    /*
           9  4  8  7
        8 [3, 0, 8, 4], 8
        7 [2, 4, 5, 7], 7
        9 [9, 2, 6, 3], 9
        3 [0, 3, 1, 0]  3
           9  4  8  7
     */
    /*
           9  4  8  7
        8 [8, 4, 8, 7], 8
        7 [7, 4, 7, 7], 7
        9 [9, 4, 8, 7], 9
        3 [3, 3, 3, 3]  3
           9  4  8  7
     */
}
