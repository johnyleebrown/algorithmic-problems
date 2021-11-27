package array.prefix;

/**
 * 1769
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {

  /**
   * Left to right, right ot left. Number of moves to i is the number of balls in the i-1
   * position.
   */
  public static class Solution {

    public int[] minOperations(String boxes) {
      int n = boxes.length();
      int[] ans = new int[n];
      int sum = 0, count = 0;

      for (int i = 0; i < n; i++) {
        ans[i] += sum;
        int cur = boxes.charAt(i) == '0' ? 0 : 1;
        count += cur;
        sum += count;
      }

      sum = 0; count = 0;
      for (int i = n - 1; i >= 0; i--) {
        ans[i] += sum;
        int cur = boxes.charAt(i) == '0' ? 0 : 1;
        count += cur;
        sum += count;
      }

      return ans;
    }
  }
}