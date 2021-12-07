package array.ksum;

import java.util.Arrays;

/**
 * 16
 */
public class ThreeSumClosest {

  /**
   * Same as {@link ThreeSum} with light mods.
   */
  public static class Solution {

    public int threeSumClosest(int[] nums, int target) {
      Arrays.sort(nums);
      int n = nums.length;
      int diff = Integer.MAX_VALUE;
      int sum = 0;

      for (int i = 0; i < n - 2; i++) {
        int a = target - nums[i];
        int l = i + 1, r = n - 1;

        while (l < r) {
          int b = nums[l] + nums[r];
          int localDiff = Math.abs(b - a);
          if (localDiff < diff) {
            sum = b + nums[i];
            diff = localDiff;
            if (localDiff == 0) return sum;
          }
          if (b < a) {
            l++;
          } else {
            r--;
          }
        }
      }

      return sum;
    }
  }
}