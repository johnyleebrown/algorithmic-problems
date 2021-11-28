package math.other;

/**
 * 238
 */
public class ProductOfArrayExceptSelf {

  /**
   * @formatter:off 2,   23,  11, 7       -- base 2,   46,  506,3542    -- lr
   * 3542,1771,77, 7       -- rl 1771,154, 322,506     -- ans
   * @formatter:on
   */
  public static class Solution {

    public int[] productExceptSelf(int[] nums) {
      int n = nums.length;
      int[] ans = new int[n];
      ans[0] = 1;
      for (int i = 1; i < n; i++) {
        ans[i] = ans[i - 1] * nums[i - 1];
      }
      int mult = nums[n - 1];
      for (int i = n - 2; i >= 0; i--) {
        ans[i] *= mult;
        mult *= nums[i];
      }
      return ans;
    }
  }
}