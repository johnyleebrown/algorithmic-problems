package amazon.oa;

import java.util.List;

public class A {
    public static class Solution {
        private final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        /**
         * Number of treasure pop-ups is the number of connected components.
         */
        int numberAmazonTreasureTrucks(int rows, int columns, List<List<Integer>> grid) {
            if (rows == 0 || columns == 0) return 0;
            if (grid.size() != rows || grid.get(0).size() != columns) return 0;

            int numberOfParks = 0;

            // 2d array to store processed areas
            boolean[][] seen = new boolean[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (!seen[i][j] && grid.get(i).get(j) == 1) {
                        traverse(i, j, grid, seen);
                        numberOfParks++;
                    }
                }
            }

            return numberOfParks;
        }

        /**
         * Traverse all connected areas.
         */
        private void traverse(int i, int j, List<List<Integer>> grid, boolean[][] seen) {
            seen[i][j] = true;
            for (int[] direction : directions) {
                int newI = i + direction[0];
                int newJ = j + direction[1];
                if (!isValid(newI, newJ, grid, seen)) continue;
                traverse(newI, newJ, grid, seen);
            }
        }

        /**
         * Check if area coordinates are valid.
         */
        private boolean isValid(int i, int j, List<List<Integer>> grid, boolean[][] seen) {
            return isOnGrid(i, j, grid) && !seen[i][j] && grid.get(i).get(j) == 1;
        }

        /**
         * Check if area coordinates are on the grid.
         */
        private boolean isOnGrid(int i, int j, List<List<Integer>> grid) {
            return i >= 0 && j >= 0 && i < grid.size() && j < grid.get(0).size();
        }
    }
}