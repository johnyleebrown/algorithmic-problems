package unionFind.percolation;

/**
 * FlappyBird
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
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        public boolean canReachEnd(int[][] ar) {
            int n = ar.length;
            int m = ar[0].length;
            UnionFind uf = new UnionFind(n, m);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ar[i][j] == 0) continue;
                    int curI = i + 1;
                    uf.add(curI, j);
                    for (int[] dir : dirs) {
                        int ni = dir[0] + curI;
                        int nj = dir[1] + j;
                        if (nj < 0 || nj >= m || uf.parents[ni][nj] == null)
                            continue;
                        uf.union(curI, j, ni, nj);
                    }
                }
            }

            return uf.isConnected(uf.parents[0][0], uf.parents[n + 2 - 1][0]);
        }

        private class UnionFind {
            Cell[][] parents;
            int[][] rank;

            public UnionFind(int arN, int m) {
                int n = arN + 2;
                this.parents = new Cell[n][m];
                rank = new int[n][m];

                for (int j = 1; j < m; j++) {
                    union(0, 0, 0, j);
                }
                for (int j = 1; j < m; j++) {
                    union(n - 1, 0, n - 1, j);
                }
            }

            public boolean isConnected(Cell p, Cell q) {
                return same(find(p.i, p.j), find(q.i, q.j));
            }

            private boolean same(Cell p, Cell q) {
                return p.i == q.i && p.j == q.j;
            }

            private void union(int pi, int pj, int qi, int qj) {
                Cell parentP = find(pi, pj);
                Cell parentQ = find(qi, qj);

                if (same(parentP, parentQ)) return;

                if (getRank(parentP) > getRank(parentQ)) {
                    parentQ.set(parentP);
                } else if (getRank(parentP) < getRank(parentQ)) {
                    parentP.set(parentQ);
                } else {
                    parentQ.set(parentP);
                    rank[parentP.i][parentP.j]++;
                }
            }

            private int getRank(Cell p) {
                return rank[p.i][p.j];
            }

            public void add(int i, int j) {
                if (parents[i][j] == null) parents[i][j] = new Cell(i, j);
            }

            private Cell find(int i, int j) {
                add(i, j);
                while (!s(parents[i][j], i, j)) {
                    // todo add pc
                    int newI = parents[i][j].i;
                    int newJ = parents[i][j].j;
                    i = newI;
                    j = newJ;
                }
                return parents[i][j];
            }

            private boolean s(Cell p, int i, int j) {
                return p.i == i && p.j == j;
            }

            private class Cell {
                int i, j;

                public Cell(int i, int j) {
                    this.i = i;
                    this.j = j;
                }

                public void set(Cell other) {
                    this.i = other.i;
                    this.j = other.j;
                }

                @Override
                public String toString() {
                    return "{" +
                            "i=" + i +
                            ",j=" + j +
                            '}';
                }
            }
        }
    }
}