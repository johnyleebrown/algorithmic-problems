package medium.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands
{
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) return 0;

            boolean[][] seen = new boolean[grid.length][grid[0].length];
            int numOfIslands = 0;

            for (int i = 0; i < grid.length; i++)
            {
                for (int j = 0; j < grid[i].length; j++)
                {
                    if (canBeSeen(i, j, seen, grid))
                    {
                        Queue<int[]> q = new LinkedList<>();
                        q.add(new int[]{i, j});
                        seen[i][j] = true;

                        while (!q.isEmpty())
                        {
                            populateQueue(q, seen, grid);
                        }

                        numOfIslands++;
                    }
                }
            }

            return numOfIslands;
        }

        private void populateQueue(Queue<int[]> q, boolean[][] seen, char[][] grid)
        {
            int[] cell = q.poll();
            int base_i = cell[0], base_j = cell[1];
            int[][] directions = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

            for (int[] direction: directions)
            {
                int i = direction[0] + base_i, j = direction[1] + base_j;
                if (canAddToQueue(i, j, seen, grid))
                {
                    seen[i][j] = true;
                    q.add(new int[]{i, j});
                }
            }
        }

        private boolean canAddToQueue(int i, int j, boolean[][] seen, char[][] grid)
        {
            return isInArrayBounds(i, j, seen[0].length, seen.length) && canBeSeen(i, j, seen, grid);
        }

        private boolean canBeSeen(int i, int j, boolean[][] seen, char[][] grid)
        {
            return !seen[i][j] && grid[i][j] != '0';
        }

        private boolean isInArrayBounds(int i, int j, int width, int height)
        {
            return i >= 0 && i < height && j >= 0 && j < width;
        }

    }
}
