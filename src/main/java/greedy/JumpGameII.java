package greedy;

/**
 * 45. Jump Game II
 */
public class JumpGameII {

  public static class Solution {

    public int jump(int[] nums) {
      int i = 0;
      int n = nums.length;
      int end = 0; // end of current reach
      int count = 0; // min count of jumps
      int max = 0; // max reach

      // go till n - 2 because the last possible interval would start here
      while (i < n - 1) {
        max = Math.max(max, nums[i] + i);
        if (i == end) {
          count++;
          end = max;
        }
        i++;
      }

      return count;
    }
  }
}