package dp.other.linear.back;

/**
 * 746
 */
public class MinCostClimbingStairs {

  class Solution {

    public int minCostClimbingStairs(int[] cost) {
      int n = cost.length + 1;
      int[] dp = new int[n];
      dp[0] = cost[0];
      dp[1] = cost[1];
      for (int i = 2; i < n; i++) {
        dp[i] =
            Math.min(dp[i - 1], dp[i - 2]) + (i == cost.length ? 0 : cost[i]);
      }
      return dp[n - 1];
    }
  }
}
