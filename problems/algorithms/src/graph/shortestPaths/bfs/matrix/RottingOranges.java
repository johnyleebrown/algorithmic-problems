package graph.shortestPaths.bfs.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 994
 *
 * ======
 *
 * Task.
 *
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten
 * orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange.  If this is impossible, return -1 instead.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RottingOranges {
    /**
     * Add rotten cells to start with, do bfs, then check if there are any good
     * ones untouched.
     */
    public static class Solution {
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        public int orangesRotting(int[][] ar) {
            int n = ar.length;
            int m = ar[0].length;
            Deque<Cell> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ar[i][j] == 2) {
                        q.addLast(new Cell(i, j));
                    }
                }
            }
            int mins = -1;
            boolean[][] seen = new boolean[n][m];
            while (!q.isEmpty()) {
                int size = q.size();
                while (--size >= 0) {
                    Cell c = q.removeFirst();
                    for (int[] d : dirs) {
                        int newi = c.i + d[0];
                        int newj = c.j + d[1];
                        if (!isValid(newi, newj, n, m) || ar[newi][newj] != 1 || seen[newi][newj]) {
                            continue;
                        }
                        seen[newi][newj] = true;
                        ar[newi][newj] = 2;
                        q.addLast(new Cell(newi, newj));
                    }
                }
                mins++;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ar[i][j] == 1) {
                        return -1;
                    }
                }
            }
            return mins == -1 ? 0 : mins;
        }

        boolean isValid(int i, int j, int n, int m) {
            return i >= 0 && j >= 0 && i < n && j < m;
        }

        class Cell {
            int i, j;

            Cell(int ii, int jj) {
                i = ii;
                j = jj;
            }
        }
    }
}