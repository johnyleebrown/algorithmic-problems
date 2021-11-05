package dp.linear.back;

/**
 * 198
 */
public class HouseRobber {

  class Solution {

    public int rob(int[] a) {
      int n = a.length;
      if (n == 0) {
        return 0;
      }
      int[] dp = new int[n + 1];
      dp[1] = a[0];
      for (int i = 2; i <= n; i++) {
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + a[i - 1]);
      }
      return dp[n];
    }
  }

  class Solution2 {

    public int rob(int[] nums) {
      if (nums.length == 1) {
        return nums[0];
      }
      int end = nums.length + 1;
      int[] dp = new int[end];
      dp[0] = nums[0];
      dp[1] = Math.max(nums[0], nums[1]);
      for (int i = 2; i < end; i++) {
        int curValue = i == nums.length ? 0 : nums[i];
        dp[i] = Math.max(curValue + dp[i - 2], dp[i - 1]);
      }
      return dp[end - 1];
    }
  }
}
