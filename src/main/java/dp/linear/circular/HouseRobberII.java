package dp.linear.circular;

import java.util.Arrays;

/**
 * 213
 */
public class HouseRobberII {

  /**
   * We can't take 1st and last at the same time. So we try 2 variants - sub-array with 1st and sub-array without it.
   */
  public static class Solution {

    public int rob(int[] nums) {
      if (nums.length == 1) {
        return nums[0];
      }
      return Math.max(robHelper(nums, 0, nums.length - 2),
          robHelper(nums, 1, nums.length - 1));
    }

    private int robHelper(int[] nums, int i, int j) {
      if (j == i) {
        return nums[i];
      }
      int n = nums.length;
      int[] dp = new int[n + 1];
      // j + 1 is n in this case
      for (int k = i; k <= j + 1; k++) {
        int prev = k > 0 ? dp[k - 1] : 0; // i - 1
        int prevprev = k > 1 ? dp[k - 2] : 0; // i - 2
        int cur = k < j + 1 ? nums[k] : 0;
        dp[k] = Math.max(prev, cur + prevprev);
      }
      return dp[j + 1];
    }
  }
}
