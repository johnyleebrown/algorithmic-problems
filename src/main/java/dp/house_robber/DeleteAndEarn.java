package dp.house_robber;

/**
 * 740. Delete and Earn
 *
 * https://leetcode.com/problems/delete-and-earn/
 */
public class DeleteAndEarn {

  /**
   * Convert to house robber. Use the step we use in bucket sort - create count
   * array to count unique values up to 10_000. Recurrence relation is - we
   * either take current + i - 2, or just i - 1. Since we can't take left i - 1
   * and right i + 1 indexes.
   */
  public static class Solution {

    public int deleteAndEarn(int[] nums) {
      int n = nums.length;
      int max = 10_000;

      int[] counts = new int[max + 1];
      for (int i = 0; i < n; i++) {
        counts[nums[i]]++;
      }

      int[] dp = new int[max + 1];
      // # # _ X _ # #
      for (int i = 1; i <= max; i++) {
        if (i == 1) {
          dp[i] = counts[i] * i;
        } else {
          dp[i] = Math.max(counts[i] * i + dp[i - 2], dp[i - 1]);
        }
      }

      return dp[max];
    }
  }
}