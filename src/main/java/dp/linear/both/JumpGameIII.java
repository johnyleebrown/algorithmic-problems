package dp.linear.both;

/**
 * 1306. Jump Game III
 */
public class JumpGameIII {

  /**
   * DP - dfs - like tree problems.
   */
  public static class Solution {

    public boolean canReach(int[] arr, int start) {
      return canReachHelper(arr, new Boolean[arr.length], start);
    }

    private boolean canReachHelper(int[] arr, Boolean[] dp, int i) {
      if (i < 0 || i >= arr.length) {
        return false;
      }
      if (dp[i] != null) {
        return dp[i];
      }
      if (arr[i] == 0) {
        return true;
      }
      dp[i] = false;
      dp[i] = canReachHelper(arr, dp, i - arr[i]) || canReachHelper(arr, dp,
          i + arr[i]);
      return dp[i];
    }
  }
}