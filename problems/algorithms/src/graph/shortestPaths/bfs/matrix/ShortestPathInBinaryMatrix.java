package graph.shortestPaths.bfs.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091
 */
public class ShortestPathInBinaryMatrix {
    /**
     * Two-directional BFS.
     */
    class Solution {
        private int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid == null) return 0;
            if (grid.length == 1) return 1;
            int n = grid.length, m = grid[0].length;
            if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;
            int pathLength = 0;

            Queue<int[]> q1 = new LinkedList<>();
            q1.add(new int[]{0, 0});
            int path1Length = 0;
            Queue<int[]> q2 = new LinkedList<>();
            q2.add(new int[]{n - 1, m - 1});
            int path2Length = 0;
            int[][] seen = new int[n][m];
            seen[0][0] = 1;
            seen[n - 1][m - 1] = 2;

            while (!q1.isEmpty() && !q2.isEmpty()) {
                // we want to do a two end graph.shortestPaths.bfs and we want to return a sum of lengths when two graph.shortestPaths.bfs's meet
                path1Length++;
                int size1 = q1.size();
                while (--size1 >= 0) {
                    int[] cell = q1.poll();
                    int i = cell[0];
                    int j = cell[1];

                    for (int[] dir : dirs) {
                        int newI = dir[0] + i, newJ = dir[1] + j;

                        if (!isInBounds(newI, newJ, n, m) || seen[newI][newJ] == 1 || grid[newI][newJ] == 1) {
                            continue;
                        }
                        if (seen[newI][newJ] == 2) {
                            return path1Length + path2Length + 1;
                        }

                        seen[newI][newJ] = 1;
                        q1.add(new int[]{newI, newJ});
                    }
                }

                if (q1.size() == 0) {
                    return -1;
                }

                path2Length++;
                int size2 = q2.size();
                while (--size2 >= 0) {
                    int[] cell = q2.poll();
                    int i = cell[0];
                    int j = cell[1];

                    for (int[] dir : dirs) {
                        int newI = dir[0] + i, newJ = dir[1] + j;

                        if (!isInBounds(newI, newJ, n, m) || seen[newI][newJ] == 2 || grid[newI][newJ] == 1) {
                            continue;
                        }
                        if (seen[newI][newJ] == 1) {
                            return path1Length + path2Length + 1;
                        }

                        seen[newI][newJ] = 2;
                        q2.add(new int[]{newI, newJ});
                    }
                }

                if (q2.size() == 0) {
                    return -1;
                }
            }

            return -1;
        }

        private boolean isInBounds(int i, int j, int n, int m) {
            return i >= 0 && i < n && j >= 0 && j < m;
        }
    }
/*
[[0,0,0],[1,1,0],[1,1,0]]
[[0,1],[1,0]]
[[0,1,0,0]]
[[1,0,0],[1,1,0],[1,1,0]]
*/

}

