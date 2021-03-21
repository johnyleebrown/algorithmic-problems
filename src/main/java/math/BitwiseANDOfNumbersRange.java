package math;

/**
 * 1131. Maximum of Absolute Value Expression
 *
 * <p>======
 *
 * <p>Task.
 *
 * <p>Given two arrays of integers with equal lengths, return the maximum value of:
 *
 * <p>|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * <p>where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 * <p>Example 1:
 *
 * <p>Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6] Output: 13 Example 2:
 *
 * <p>Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4] Output: 20
 *
 * <p>Constraints:
 *
 * <p>2 <= arr1.length == arr2.length <= 40000 -10^6 <= arr1[i], arr2[i] <= 10^6
 *
 * <p>======
 *
 * <p>https://leetcode.com/problems/maximum-of-absolute-value-expression/
 */
public class BitwiseANDOfNumbersRange {
  /**
   * https://www.notion.so/1131-Maximum-of-Absolute-Value-Expression-6a5adc2a85584ba689b3bcfe70a0792e
   */
  public static class Solution {
    public int maxAbsValExpr(int[] a, int[] b) {
      Ans ans1 = new Ans(), ans2 = new Ans(), ans3 = new Ans(), ans4 = new Ans();
      for (int i = 0; i < a.length; i++) {
        ans1.update(a[i] + b[i] - i, a[i] + b[i] - i);
        ans2.update(a[i] - b[i] - i, a[i] - b[i] - i);
        ans3.update(a[i] - b[i] + i, a[i] - b[i] + i);
        ans4.update(a[i] + b[i] + i, a[i] + b[i] + i);
      }
      return Math.max(
      Math.max(ans1.getDiff(), ans2.getDiff()), Math.max(ans3.getDiff(), ans4.getDiff()));
    }

    static class Ans {
      int min, max;

      Ans() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
      }

      void update(int candidateMax, int candidateMin) {
        updateMax(candidateMax);
        updateMin(candidateMin);
      }

      void updateMax(int candidate) {
        max = Math.max(max, candidate);
      }

      void updateMin(int candidate) {
        min = Math.min(min, candidate);
      }

      int getDiff() {
        return max - min;
      }
    }
  }
}
