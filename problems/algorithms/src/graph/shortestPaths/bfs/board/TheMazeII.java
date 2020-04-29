package graph.shortestPaths.bfs.board;

import java.util.LinkedList;
import java.util.List;

/**
 * 505
 *
 * ======
 *
 * Task.
 *
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 *
 * Given the ball's start position, the destination and the maze, find the
 * shortest distance for the ball to stop at the destination. The distance is
 * defined by the number of empty spaces traveled by the ball from the start
 * position (excluded) to the destination (included). If the ball cannot stop at
 * the destination, return -1.
 *
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 *
 * ======
 *
 * Source: Leetcode
 */
public class TheMazeII {
    /**
     * Regular BFS but we use direction and cache only places where we actually
     * stopped - in front of the wall - places that actually matter.
     */
    public static class Solution {
        // directions - up down left right
        static int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int shortestDistance(int[][] ma, int[] s, int[] de) {
            int n = ma.length;
            int ans = 0;
            if (n == 0) return ans;
            int m = ma[0].length;
            List<Cell> q = new LinkedList<>();
            boolean seen[][] = new boolean[n][m];
            seen[s[0]][s[1]] = true;
            for (int i = 0; i < 4; i++) q.add(new Cell(s[0], s[1], i));
            while (!q.isEmpty()) {
                int size = q.size();
                while (--size >= 0) {
                    Cell c = q.remove(0);
                    int ni = d[c.d][0] + c.i, nj = d[c.d][1] + c.j;
                    if (isInvalid(ni, nj, ma)) {//come up to a wall
                        if (de[0] == c.i && de[1] == c.j) return ans;
                        seen[c.i][c.j] = true;
                        for (int k = 0; k < 4; k++) {
                            if (k != c.d) {
                                int ni2 = d[k][0] + c.i, nj2 = d[k][1] + c.j;
                                if (isInvalid(ni2, nj2, ma) || seen[ni2][nj2])
                                    continue;
                                q.add(new Cell(ni2, nj2, k));
                            }
                        }
                    } else {//continue in the direction
                        if (seen[ni][nj]) continue;
                        q.add(new Cell(ni, nj, c.d));
                    }
                }
                ans++;
            }
            return -1;
        }

        private boolean isInvalid(int i, int j, int[][] ma) {
            return i < 0 || j < 0 || i >= ma.length || j >= ma[0].length || ma[i][j] == 1;
        }

        private class Cell {
            int i, j, d;

            Cell(int i, int j, int d) {
                this.i = i;
                this.j = j;
                this.d = d;
            }
        }
    }
}