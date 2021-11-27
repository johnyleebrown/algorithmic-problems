package dp.coin_change;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 983. Minimum Cost For Tickets
 *
 * https://leetcode.com/problems/minimum-cost-for-tickets/
 */
public class MinimumCostForTickets {

  /**
   * Similarly to coin change try different ticket costs for the current day. In the
   * beginning there was a confusion on whether I should do a dp forward. But in coin
   * change we did it backwards if the coin was good - if was not bigger than sum.
   *
   * We track the minimum cost for all calendar days in dp. For non-travel days, the cost
   * stays the same as for the previous day. For travel days, it's a minimum of
   * yesterday's cost plus single-day ticket, or cost for 8 days ago plus 7-day pass, or
   * cost 31 days ago plus 30-day pass.
   */
  public static class Solution {

    public int mincostTickets(int[] days, int[] costs) {
      int[] dp = new int[days[days.length - 1] + 1];
      Set<Integer> set = new HashSet<>();
      for (int dd : days) {
        set.add(dd);
      }
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      int[] d = new int[]{1, 7, 30};
      for (int i = 1; i < dp.length; i++) {
        for (int j = 0; j < costs.length; j++) {
          if (!set.contains(i)) dp[i] = dp[i - 1];
          // if we go out of bounds we take the default which is 0
          // in other words - we could have bought a ticket in day 0
          int prev = i - d[j] >= 0 ? dp[i - d[j]] : 0;
          dp[i] = Math.min(dp[i], prev + costs[j]);
        }
      }
      return dp[dp.length - 1];
    }
  }
}