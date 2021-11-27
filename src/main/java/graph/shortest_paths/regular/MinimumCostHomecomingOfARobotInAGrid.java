package graph.shortest_paths.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2087
 */
public class MinimumCostHomecomingOfARobotInAGrid {

  /**
   * All shortest paths from start to end have the same length due to the fact that all
   * costs are positive. So however you go you will have to cross the same rows and
   * columns to get to target, thus travelling in the opposite direction from the target
   * is not optimal.
   *
   * So just count the sum of costs of all rows and columns between start and end.
   */
  public static class Solution1 {

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
      if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) return 0;

      int minRow = Math.min(startPos[0], homePos[0]);
      int maxRow = Math.max(startPos[0], homePos[0]);

      int minCol = Math.min(startPos[1], homePos[1]);
      int maxCol = Math.max(startPos[1], homePos[1]);

      int res = 0;

      for (int i = minRow; i <= maxRow; i++) {
        res += rowCosts[i];
      }
      for (int i = minCol; i <= maxCol; i++) {
        res += colCosts[i];
      }

      res = res - rowCosts[startPos[0]] - colCosts[startPos[1]];

      return res;
    }
  }

  /**
   * BFS. TLE.
   */
  public static class Solution2 {

    int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    Map<Integer, Integer> em = new HashMap<>();

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {

      int startI = startPos[0], startJ = startPos[1], endI = homePos[0], endJ = homePos[1];
      if (startI == endI && startJ == endJ) return 0;
      int n = rowCosts.length, m = colCosts.length;

      List<int[]> q = new ArrayList<>();
      q.add(new int[]{startI, startJ, 0});

      Map<Integer, Map<Integer, Integer>> costs = new HashMap<>();
      add(costs, startI, startJ, 0);

      while (!q.isEmpty()) {

        int size = q.size();

        while (--size >= 0) {

          int[] pos = q.remove(0);
          int i = pos[0], j = pos[1], cost = pos[2];

          for (int[] d : dirs) {
            int newI = d[0] + i, newJ = d[1] + j;
            if (!(newI >= 0 && newJ >= 0 && newI < n && newJ < m)) continue;
            int newCost = d[0] == 0 ? colCosts[newJ] : rowCosts[newI];
            int finalCost = cost + newCost;
            if (finalCost < get(costs, newI, newJ)) {
              add(costs, newI, newJ, finalCost);
              q.add(new int[]{newI, newJ, finalCost});
            }
          }
        }
      }

      return get(costs, endI, endJ);
    }

    int get(Map<Integer, Map<Integer, Integer>> costs, int i, int j) {
      return costs.getOrDefault(i, em).getOrDefault(j, Integer.MAX_VALUE);
    }

    void add(Map<Integer, Map<Integer, Integer>> costs, int i, int j, int val) {
      costs.putIfAbsent(i, new HashMap<>());
      costs.get(i).put(j, val);
    }
  }
}
