package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 2850. Minimum Moves to Spread Stones Over Grid
 * <p>
 * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
 * <p>
 * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
 * <p>
 * Return the minimum number of moves required to place one stone in each cell.
 */
public class MinimumMovesToSpreadStonesOverGrid {
    /**
     * Backtracking.
     * Find all combinations of cells to connect, to move stone from-to.
     * Count cells with more than 2 stones more than 1 time.
     * Moving stone to empty cell is the same as shifting stones along the path from-to.
     */
    public static class Solution {
        int res = Integer.MAX_VALUE;

        public int minimumMoves(int[][] g) {
            List<int[]> a = new ArrayList<>();
            List<int[]> b = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // 0 cells are destinations
                    if (g[i][j] == 0) {
                        b.add(new int[]{i, j});
                    }
                    // remove 1 cuz we don't give a crap about those cells
                    if (g[i][j] - 1 != 0) {
                        // Count cells with more than 2 stones more than 1 time.
                        for (int k = 0; k < g[i][j] - 1; k++) {
                            a.add(new int[]{i, j});
                        }
                    }
                }
            }
            if (!a.isEmpty()) {
                fn(new boolean[a.size()], new ArrayList<>(), a, b, 0);
                return res;
            }
            return 0;
        }

        public void fn(boolean[] seen, List<int[][]> l, List<int[]> a, List<int[]> b, int i) {
            if (i == b.size()) {
                // calc result
                int calc = 0;
                for (int[][] ar : l) {
                    // calc distance
                    int[] aa = ar[0];
                    int[] bb = ar[1];
                    int diffX = Math.abs(aa[0] - bb[0]);
                    int diffY = Math.abs(aa[1] - bb[1]);
                    int dist = diffX + diffY;
                    calc += dist;
                }
                // calc min sum of distances
                res = Math.min(res, calc);
                return;
            }
            for (int j = 0; j < b.size(); j++) {
                if (!seen[j]) {
                    seen[j] = true;
                    l.add(new int[][]{a.get(i), b.get(j)});
                    fn(seen, l, a, b, i + 1);
                    l.remove(l.size() - 1);
                    seen[j] = false;
                }
            }
        }
    }
}
