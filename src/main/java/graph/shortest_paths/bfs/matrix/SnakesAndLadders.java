package graph.shortest_paths.bfs.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 909
 *
 * ======
 *
 * Task.
 *
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically
 * starting from the bottom left of the board, and alternating direction each
 * row.
 *
 * You start on square 1 of the board (which is always in the last row and first
 * column).  Each move, starting from square x, consists of the following:
 *
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or
 * x+6, provided this number is <= N*N.
 * (This choice simulates the result of a standard 6-sided die roll: ie., there
 * are always at most 6 destinations, regardless of the size of the board.)
 * If S has a snake or ladder, you move to the destination of that snake or
 * ladder.  Otherwise, you move to S.
 *
 * A board square on row r and column c has a "snake or ladder" if board[r][c]
 * != -1.  The destination of that snake or ladder is board[r][c].
 *
 * Note that you only take a snake or ladder at most once per move: if the
 * destination to a snake or ladder is the start of another snake or ladder, you
 * do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and
 * on the first move your destination square is `2`, then you finish your first
 * move at `3`, because you do not continue moving to `4`.)
 *
 * Return the least number of moves required to reach square N*N.  If it is not
 * possible, return -1.
 *
 * ======
 *
 * Source: Leetcode
 */
public class SnakesAndLadders {
    /**
     * Regular, use seen so we could rule out bad cases and return -1. Could
     * optimize with just using numbers and also with precomputing.
     */
    public static class Solution {
        public int snakesAndLadders(int[][] ar) {
            int n = ar.length;
            Deque<Cell> q = new ArrayDeque<>();
            q.addLast(new Cell(n - 1, 0, 1));
            int ans = 0;
            int res = n * n;
            boolean[][] seen = new boolean[n][n];
            seen[n - 1][0] = true;
            while (!q.isEmpty()) {
                int s = q.size();
                while (--s >= 0) {
                    Cell c = q.removeFirst();
                    if (c.num == res) return ans;
                    for (int k = 1; k <= 6; k++) {
                        int newNum = c.num + k;
                        int[] newCoords = numToCoords(newNum, n);
                        if (!isValid(newCoords, n, seen)) continue;
                        int extraNum = ar[newCoords[0]][newCoords[1]];
                        seen[newCoords[0]][newCoords[1]] = true;
                        if (extraNum != -1) {
                            int[] extraCoords = numToCoords(extraNum, n);
                            q.addLast(new Cell(extraCoords[0], extraCoords[1], extraNum));
                        } else {
                            q.addLast(new Cell(newCoords[0], newCoords[1], newNum));
                        }
                    }
                }
                ans++;
            }
            return -1;
        }

        private boolean isValid(int[] cd, int n, boolean[][] seen) {
            return cd[0] < 0 || cd[0] >= n || cd[1] < 0 || cd[1] >= n || seen[cd[0]][cd[1]];
        }

        int[] numToCoords(int i, int n) {
            int x = ((i - 1) / n);
            int row = (n - 1) - x;
            int col = x % 2 == 0 ? (i - 1) % n : n - 1 - ((i - 1) % n);
            return new int[]{row, col};
        }

        class Cell {
            int i, j, num;

            Cell(int ii, int jj, int n) {
                i = ii;
                j = jj;
                num = n;
            }
        }
    }
}