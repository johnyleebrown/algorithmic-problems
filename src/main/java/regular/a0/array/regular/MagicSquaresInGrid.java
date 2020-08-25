package a0.array.regular;

import java.util.HashSet;
import java.util.Set;

/**
 * 840
 *
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
 * Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
 */
public class MagicSquaresInGrid {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public int numMagicSquaresInside(int[][] grid) {
            if (grid.length < 3 || grid[0].length < 3) return 0;
            int count = 0;
            for (int i = 0; i <= grid.length - 3; i++) {
                for (int  j = 0 ;  j <= grid[0].length - 3; j++)
                    if (has_distinct_numbers(i, j, grid) && equal_sum_diagonals(i, j, grid)) count++;
            }


            return count;
        }

        boolean has_distinct_numbers(int i_init, int j_init, int[][] grid) {
            Set<Integer> set = new HashSet<>();

            for (int i = i_init; i < i_init + 3; i++ )
                for (int j = j_init; j < j_init + 3; j++ )
                    if (!set.add(grid[i][j]) || grid[i][j] < 1 || grid[i][j] > 9)
                        return false;

            return true;
        }

        boolean equal_sum_diagonals(int i , int j , int[][] grid) {
            return grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2] == 15
                    && grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j] == 15
                    && grid[i][j] + grid[i][j + 1] + grid[i][j + 2] == 15
                    && grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2] == 15
                    && grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2] == 15
                    && grid[i][j] + grid[i + 1][j] + grid[i + 2][j] == 15
                    && grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] == 15
                    && grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2] == 15;
        }
    }
}
