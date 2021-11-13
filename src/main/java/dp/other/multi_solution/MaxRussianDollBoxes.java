package dp.other.multi_solution;

import java.util.Arrays;

/**
 * MaxRussianDollBoxes
 *
 * ======
 *
 * Task.
 *
 * Given a collection of boxes. Return the max number of boxes that you can russian doll. Each box
 * has (w, h, l).
 *
 * ======
 *
 * Source: Leetcode Company: Google
 */
public class MaxRussianDollBoxes {

  public interface Solution {

    int solve(int[][] a);
  }

  /**
   * No sort, check all array.
   */
  public static class Solution1 implements Solution {

    Integer[] dp;

    public int solve(int[][] ar) {
      int n = ar.length;
      dp = new Integer[n];
      int ans = 0;
      for (int i = 0; i < n; i++) {
        ans = Math.max(ans, dfs(i, ar) + 1);
      }
      return ans;
    }

    private int dfs(int ind, int[][] ar) {
      if (dp[ind] != null) {
        return dp[ind];
      }
      dp[ind] = 0;
      for (int i = 0; i < ar.length; i++) {
        if (check(ar[ind], ar[i])) {
          dp[ind] = Math.max(dp[ind], dfs(i, ar) + 1);
        }
      }
      return dp[ind];
    }

    private boolean check(int[] a, int[] b) {
      return a[0] < b[0] && a[1] < b[1] && a[2] < b[2];
    }
  }

  public static class Solution2 implements Solution {

    public int solve(int[][] ar) {
      Arrays.sort(ar, (a, b) -> {
        if (a[0] == b[0]) {
          if (a[1] == b[1]) {
            return a[2] - b[2];
          } else {
            return a[1] - b[1];
          }
        } else {
          return a[0] - b[0];
        }
      });
      Integer[] dp = new Integer[ar.length];
      int ans = 0;
      //            for (int i = ar.length - 1; i >= 0; i--) {
      //                ans = Math.max(ans, dfs(i, ar, dp) + 1);
      //            }
      for (int i = 0; i < ar.length; i++) {
        ans = Math.max(ans, dfs(i, ar, dp) + 1);
      }
      return ans;
    }

    private int dfs(int ind, int[][] ar, Integer[] dp) {
      if (dp[ind] != null) {
        return dp[ind];
      }
      dp[ind] = 0;
      for (int i = ind - 1; i >= 0; i--) {
        if (check(ar[ind], ar[i])) {
          dp[ind] = Math.max(dp[ind], dfs(i, ar, dp) + 1);
        }
      }
      return dp[ind];
    }

    private boolean check(int[] a, int[] b) {
      return a[2] > b[2] && a[1] > b[1] && a[0] > b[0];
    }
  }

  /**
   * Solution to compare with.
   */
  public static class SolutionAgainst implements Solution {

    @Override
    public int solve(int[][] a) {
      int res = 1;
      Arrays.sort(a, (e1, e2) -> e1[0] == e2[0] ? e1[1] == e2[1] ? e1[2] - e2[2] : e1[1] - e2[1]
          : e1[0] - e2[0]);
      int[] dp = new int[a.length];
      for (int i = 0; i < a.length; i++) {
        for (int j = i - 1; j >= 0; j--) {
          if (a[i][2] > a[j][2] && a[i][1] > a[j][1] && a[i][0] > a[j][0]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
          }
        }
        res = Math.max(res, dp[i] + 1);
      }
      return res;
    }
  }
}