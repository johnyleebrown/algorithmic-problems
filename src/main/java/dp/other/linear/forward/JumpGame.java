package dp.other.linear.forward;

/**
 * 55
 */
public class JumpGame {

  /**
   * Drag max reach with you, if we cross, the false.
   */
  public static class Solution {

    public boolean canJump(int[] a) {
      int max = 0;
      for (int i = 0; i < a.length; i++) {
        if (i > max) {
          return false;
        }
        max = Math.max(max, i + a[i]);
      }
      return true;
    }
  }

  /**
   * DP solution.
   */
  public static class Solution2 {

    public boolean canJump(int[] a) {
      return dfs(0, new Boolean[a.length], a);
    }

    private boolean dfs(int i, Boolean[] dp, int[] a) {
      if (i >= a.length) {
        return false;
      }
      if (i == a.length - 1) {
        return true;
      }
      if (dp[i] != null) {
        return dp[i];
      }
      boolean ans = false;
      for (int j = i + a[i]; j >= i + 1; j--) {
        ans = ans || dfs(j, dp, a);
      }
      dp[i] = ans;
      return ans;
    }
  }

  /**
   * 2d dp - draw the picture. dp[i][j] - if we can get to this index
   */
  public static class Solution3 {

    public boolean canJump(int[] nums) {
      int n = nums.length;
      boolean[][] dp = new boolean[n][n];

      // at the start we can get only to the index = 0
      dp[0][0] = true;

      for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {

          // check if we
          if (j <= i + nums[i]) {
            dp[i][j] |= (j > 0 && dp[i][j - 1]);
          }

          // transfer prev values because even if we can't get to this index j from i
          // we can still get there from somewhere else
          dp[i][j] |= (i > 0 && dp[i - 1][j]);
        }
      }

      return dp[n - 1][n - 1];
    }
  }

  /**
   * For each index we create intervals for jump [min, max]. For each index we are computing if new
   * interval intersects with the biggest interval so far (main interval). If not then we can't get
   * to the new index from any of the prev positions. Draw the picture with intervals to
   * understand.
   */
  public static class Solution4 {

    public boolean canJump(int[] nums) {
      int max = 0;
      for (int i = 0; i < nums.length; i++) {
        if (i > max) {
          return false;
        }
        max = Math.max(max, nums[i] + i);
      }
      return true;
    }
  }
}