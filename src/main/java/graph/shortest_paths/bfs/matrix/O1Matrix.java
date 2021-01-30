package graph.shortest_paths.bfs.matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 542
 */
public class O1Matrix {
    /**
     * Add zeros, for them add their neighbors, for them dist+=1 and so forth.
     */
    private static class Solution1 {
        private int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        public int[][] updateMatrix(int[][] a) {
            List<P> q = new ArrayList<>();
            int n = a.length;
            int m = a[0].length;
            boolean[][] ss = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i][j] == 0)
                        q.add(new P(i, j));
                    if (a[i][j] == 0)
                        ss[i][j] = true;
                }
            }

            while (!q.isEmpty()) {
                int s = q.size();
                while (--s >= 0) {
                    P p = q.remove(0);
                    int i = p.i;
                    int j = p.j;
                    for (int[] d : dirs) {
                        int newi = i + d[0];
                        int newj = j + d[1];
                        if (newi < 0 || newj < 0 || newi >= n || newj >= m || ss[newi][newj])
                            continue;
                        ss[newi][newj] = true;
                        a[newi][newj] = a[i][j] + 1;
                        q.add(new P(newi, newj));
                    }
                }
            }
            return a;
        }

        private class P {
            int i, j;

            P(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
    }

    private static class Solution2 {
        public int[][] updateMatrix(int[][] a) {
            int n = a.length, m = a[0].length;
            int[][] resultArray = new int[n][m];
            int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    if (a[i][j] == 1)
                        resultArray[i][j] = bfs(dirs, i, j, a, n, m);
                }

            return resultArray;
        }

        private int bfs(int[][] dirs, int baseI, int baseJ, int[][] matrix, int n, int m) {
            int length = 0;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{baseI, baseJ});
            while (!q.isEmpty()) {
                int size = q.size();
                length++;
                for (int k = 0; k < size; k++) {
                    int[] cell = q.poll();
                    // we can go in 4 dirs minding the seen cells
                    int i = cell[0], j = cell[1];
                    for (int[] dir : dirs) {
                        int newI = i + dir[0], newJ = j + dir[1];
                        if (newI >= 0 && newI < n && newJ >= 0 && newJ < m) {
                            if (matrix[newI][newJ] == 0)
                                return length;
                            q.add(new int[]{newI, newJ});
                        }
                    }
                }
            }

            return length;
        }
    }
}