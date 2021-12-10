package graph.shortest_paths.bfs.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 317
 */
public class ShortestDistanceFromAllBuildings {

  /**
   * We calculate SP to all cells from all houses. And since we need total
   * dist, we just sum distances to diff houses at each cell, then iterate
   * through all and record min.
   *
   * Let's say that we can't reach a house if at dist 1 it doesnt have empty
   * places. And we can't reach if the houses count at each cell != total
   * houses count.
   */
  public static class Solution {

    private static final int[][] dd = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int n, m;

    public int shortestDistance(int[][] ar) {
      n = ar.length;
      m = ar[0].length;
      Res[][] distAr = new Res[n][m];
      int housesCount = 0;

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (ar[i][j] == 1) {
            if (!calcShortestDistances(i, j, ar, distAr)) {
              return -1;
            }
            housesCount++;
          }
        }
      }
      int ans = Integer.MAX_VALUE;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (ar[i][j] == 0 && distAr[i][j] != null &&
              distAr[i][j].housesCount == housesCount) {
            ans = Math.min(ans, distAr[i][j].totalDist);
          }
        }
      }
      return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * Regular BFS. Distances to Empty Lands, return true if can reach at
     * least one, false otherwise.
     */
    boolean calcShortestDistances(int i, int j, int[][] ar, Res[][] dist) {
      Deque<Cell> q = new ArrayDeque<>();
      q.add(new Cell(i, j));
      boolean[][] seen = new boolean[n][m];
      seen[i][j] = true;
      int count = 0;

      while (!q.isEmpty()) {
        int size = q.size();
        while (--size >= 0) {
          Cell c = q.removeFirst();

          if (dist[c.i][c.j] == null) dist[c.i][c.j] = new Res(0, 0);
          dist[c.i][c.j].totalDist += count;
          dist[c.i][c.j].housesCount++;

          for (int[] d : dd) {
            int ni = c.i + d[0];
            int nj = c.j + d[1];
            if (!isValid(ni, nj) || ar[ni][nj] != 0 || seen[ni][nj]) {
              continue;
            }
            seen[ni][nj] = true;
            q.addLast(new Cell(ni, nj));
          }
          if (count == 0 && q.isEmpty()) { // opt
            return false;
          }
        }
        count++;
      }
      return true;
    }

    private boolean isValid(int i, int j) {
      return i >= 0 && j >= 0 && i < n && j < m;
    }

    private static class Cell {

      int i, j;

      Cell(int i, int j) {
        this.i = i;
        this.j = j;
      }
    }

    private static class Res {

      // total dist to all houses,count of houses that access to cell
      int totalDist, housesCount;

      Res(int t, int c) {
        totalDist = t;
        housesCount = c;
      }
    }
  }
}