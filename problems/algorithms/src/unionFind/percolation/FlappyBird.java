package unionFind.percolation;

import java.util.Arrays;

/**
 * $INSERT_PROBLEM_NUMBER
 *
 * ======
 *
 * Task.
 *
 * Say in a 2d space. You are a flippy bird and you are facing some obstacles.
 * How do you know if you can cross all the obstacles and reach the other end.
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class FlappyBird {
    /**
     * Percolation problem, we will have a 2d array with first and last extra
     * rows:
     * --row -1--
     * --2d arr--
     * --row n --
     *
     * And we will fill first and last rows with -1 and n for example, at the
     * end we will check if -1,0 is connected with n,0 - if ceiling is connected
     * to floor, if yes, then flappy bird can't reach end.
     */
    public static class Solution {
        public boolean canReachEnd(int[][] ar) {
            int n = ar.length;
            UnionFind uf = new UnionFind();
            return uf.isConnected(0, 1);
        }

        private class UnionFind {
            Cell[][] parents;

            public UnionFind(int[][] ar) {
                int n = ar.length + 2;
                int m = ar[0].length;
                this.parents = new Cell[n][m];

                Cell first = new Cell(0, 0);
                Arrays.fill(parents[0], first);
                Cell last = new Cell(n - 1, 0);
                Arrays.fill(parents[n - 1], last);

                for (int i = 1; i < n - 1; i++) {
                    for (int j = 0; j < m; j++) {
                        parents[i][j] = new Cell(i, j);
                    }
                }
            }

            public boolean isConnected(Cell p, Cell q) {
                return same(find(p), find(q));
            }

            private boolean same(Cell p, Cell q) {
                return p.i == q.i && p.j == q.j && p.rank == q.rank;
            }

            private void union(Cell p, Cell q) {
                Cell parentP = find(p);
                Cell parentQ = find(q);

                if (parentP == parentQ) return;

                if (parentP.rank > parentQ.rank) {
                    parentQ.set(parentP);
                } else if (parentP.rank < parentQ.rank) {
                    parentP.set(parentQ);
                } else {
                    parentQ.set(parentP);
                    parentQ.rank++;
                }
            }

            private Cell find(Cell p) {
                while (!(p.i == parents[p.i][p.j].i && p.j == parents[p.i][p.j].j)) {
                    // todo - add path compression
                    p = parents[p.i][p.j];
                }
                return p;
            }

            private class Cell {
                int i, j, rank;

                public Cell(int i, int j) {
                    this.i = i;
                    this.j = j;
                }

                public void set(Cell other) {
                    this.i = other.i;
                    this.j = other.j;
                }
            }
        }
    }
}