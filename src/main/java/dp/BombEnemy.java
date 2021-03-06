package dp;

/**
 * 361. Bomb Enemy
 *
 * <p>======
 *
 * <p>Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
 * return the maximum enemies you can kill using one bomb. The bomb kills all the enemies in the
 * same row and column from the planted point until it hits the wall since the wall is too strong to
 * be destroyed. Note: You can only put the bomb at an empty cell.
 *
 * <p>Example:
 *
 * <p>Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]] Output: 3 Explanation: For the
 * given grid,
 *
 * <p>0 E 0 0 E 0 W E 0 E 0 0
 *
 * <p>Placing a bomb at (1,1) kills 3 enemies.
 *
 * <p>======
 *
 * <p>https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {
  /**
   * DP
   */
  public static class SolutionN {
  }

  /**
   * BF
   */
  public static class Solution1 {
    public int maxKilledEnemies(char[][] a) {
      int n = a.length;
      if (n == 0) return 0;
      int m = a[0].length;
      if (m == 0) return 0;
      int ans = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (a[i][j] != '0') continue;
          int e = 0;
          // go down
          for (int k = i + 1; k < n && a[k][j] != 'W'; k++) {
            if (a[k][j] == 'E') {
              e++;
            }
          }
          // go up
          for (int k = i - 1; k >= 0 && a[k][j] != 'W'; k--) {
            if (a[k][j] == 'E') {
              e++;
            }
          }
          // go right
          for (int k = j + 1; k < m && a[i][k] != 'W'; k++) {
            if (a[i][k] == 'E') {
              e++;
            }
          }
          // go left
          for (int k = j - 1; k >= 0 && a[i][k] != 'W'; k--) {
            if (a[i][k] == 'E') {
              e++;
            }
          }
          if (a[i][j] == 'E') {
            e++;
          }
          ans = Math.max(ans, e);
        }
      }
      return ans == Integer.MIN_VALUE ? 0 : ans;
    }
  }

  /**
   * Pre - accum sum, l to r, u to b and r to l, b to u
   */
  public static class Solution2 {
    public int maxKilledEnemies(char[][] a) {
      int n = a.length;
      if (n == 0) return 0;
      int m = a[0].length;
      if (m == 0) return 0;

      int[][] preIR = new int[n][m], preJR = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (a[i][j] != 'W') {
            int cur = a[i][j] == 'E' ? 1 : 0;
            preJR[i][j] = j > 0 ? preJR[i][j - 1] + cur : cur;
            preIR[i][j] = i > 0 ? preIR[i - 1][j] + cur : cur;
          }
        }
      }

      int[][] preIL = new int[n][m], preJL = new int[n][m];
      for (int i = n - 1; i >= 0; i--) {
        for (int j = m - 1; j >= 0; j--) {
          if (a[i][j] != 'W') {
            int cur = a[i][j] == 'E' ? 1 : 0;
            preIL[i][j] = i < n - 1 ? preIL[i + 1][j] + cur : cur;
            preJL[i][j] = j < m - 1 ? preJL[i][j + 1] + cur : cur;
          }
        }
      }

      int ans = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (a[i][j] == '0') {
            int left = j > 0 ? preJR[i][j - 1] : 0;
            int up = i > 0 ? preIR[i - 1][j] : 0;
            int right = j + 1 < m ? preJL[i][j + 1] : 0;
            int down = i + 1 < n ? preIL[i + 1][j] : 0;
            int local = left + right + up + down;
            ans = Math.max(ans, local);
          }
        }
      }

      return ans == Integer.MIN_VALUE ? 0 : ans;
    }
  }

  /**
   * prev optimized
   */
  public static class Solution3 {
    public int maxKilledEnemies(char[][] a) {
      int n = a.length;
      if (n == 0) return 0;
      int m = a[0].length;
      if (m == 0) return 0;

      int[][] ret = new int[n][m];
      int[][] preIR = new int[n][m], preJR = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (a[i][j] != 'W') {
            int cur = a[i][j] == 'E' ? 1 : 0;
            preJR[i][j] = j > 0 ? preJR[i][j - 1] + cur : cur;
            preIR[i][j] = i > 0 ? preIR[i - 1][j] + cur : cur;
          }
          if (a[i][j] == '0') {
            ret[i][j] = preJR[i][j] + preIR[i][j];
          }
        }
      }

      int ans = Integer.MIN_VALUE;
      int[][] preIL = new int[n][m], preJL = new int[n][m];
      for (int i = n - 1; i >= 0; i--) {
        for (int j = m - 1; j >= 0; j--) {
          if (a[i][j] != 'W') {
            int cur = a[i][j] == 'E' ? 1 : 0;
            preIL[i][j] = i < n - 1 ? preIL[i + 1][j] + cur : cur;
            preJL[i][j] = j < m - 1 ? preJL[i][j + 1] + cur : cur;
          }
          if (a[i][j] == '0') {
            ret[i][j] += preIL[i][j] + preJL[i][j];
            ans = Math.max(ans, ret[i][j]);
          }
        }
      }

      return ans == Integer.MIN_VALUE ? 0 : ans;
    }
  }
}
