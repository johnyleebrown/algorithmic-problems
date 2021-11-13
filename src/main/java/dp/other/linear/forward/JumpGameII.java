package dp.other.linear.forward;

import java.util.Arrays;

/**
 * 45
 */
public class JumpGameII {

  /**
   * DP forward precalc.
   */
  public static class Solution {

    public int jump(int[] a) {
      int n = a.length;
      int[] dp = new int[n];
      for (int i = 0; i < n; i++) {
        int newCount = dp[i] + 1;
        for (int j = i + 1; j <= Math.min(i + a[i], n - 1); j++) {
          if (dp[j] != 0) {
            dp[j] = Math.min(dp[j], newCount);
          } else {
            dp[j] = newCount;
          }
        }
      }
      return dp[n - 1];
    }
  }

  /**
   * Similar to above.
   */
  public static class Solution2 {

    public int jump(int[] nums) {
      int n = nums.length;
      int[] dp = new int[n];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;
      for (int i = 0; i < n; i++) {
        for (int j = i; j <= Math.min(i + nums[i], n - 1); j++) {
          dp[j] = Math.min(dp[j], dp[i] + 1);
        }
        System.out.println(Arrays.toString(dp));
      }
      return dp[n - 1];
    }
  }
}
