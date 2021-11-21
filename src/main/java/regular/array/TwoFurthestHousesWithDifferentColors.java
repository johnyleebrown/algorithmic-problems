package regular.array;

/**
 * 2078. Two Furthest Houses With Different Colors
 *
 * https://leetcode.com/problems/two-furthest-houses-with-different-colors/
 */
public class TwoFurthestHousesWithDifferentColors {

  public static class Solution {

    public int maxDistance(int[] colors) {
      int n = colors.length;
      // move right
      int right = n - 1;
      while (colors[0] == colors[right]) {
        right--;
      }
      // move left
      int left = 0;
      while (colors[left] == colors[n - 1]) {
        left++;
      }
      return Math.abs(right - left);
    }
  }
}