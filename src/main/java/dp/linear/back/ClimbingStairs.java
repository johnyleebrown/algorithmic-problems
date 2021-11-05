package dp.linear.back;

import java.util.HashMap;
import java.util.Map;

/**
 * 70
 */
public class ClimbingStairs {

  public static class Solution {

    public int climbStairs(int n) {
      int[] dp = new int[n + 1];
      dp[0] = 1; // there is 1 way to climb 0 steps 0->0
      dp[1] = 1; // there is 1 way to climb 1 steps 0->1
      // we can jump to ith step from i-1th and from i-2th
      for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
      }
      return dp[n];
    }
  }

  public static class Solution2 {

    Map<Integer, Integer> m = new HashMap<>();

    public int climbStairs(int n) {
      if (m.containsKey(n)) {
        return m.get(n);
      }
      int ans = 1;
      if (n == 1) {
        ans = 1;
      } else if (n == 2) {
        ans = 2;
      } else if (n == 3) {
        ans = 3;
      } else {
        ans = climbStairs(n - 1) + climbStairs(n - 2);
      }
      m.put(n, ans);
      return ans;
    }
  }
}