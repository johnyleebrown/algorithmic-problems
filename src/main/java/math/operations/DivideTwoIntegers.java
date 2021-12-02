package math.operations;

/**
 * 29
 */
public class DivideTwoIntegers {

  /**
   * Overflows, but start here - easier - then modify to S2.
   *
   * General idea - each time we are trying to find out how many 'b' we can fit in 'a'. We
   * could just be decreasing b from a, but faster would be to *2 b at every iteration.
   *
   * a = INT_MAX, b = 3 at some point summing up b will overflow: 1610612736 -1073741824
   */
  public static class Solution1 {

    public int divide(int a, int b) {
      if (a == 0) return 0;
      boolean negative = (a < 0) ^ (b < 0);
      a = Math.abs(a);
      b = Math.abs(b);
      if (b == 1) return negative ? -a : a;
      int ans = 0;
      while (a >= b) {
        int sum = b;
        int count = 1;

        while (sum + sum <= a) {
          sum += sum;
          count += count;
        }

        ans += count;
        a -= sum;
      }
      return negative ? -ans : ans;
    }
  }

  /**
   * To overcome overflows the easy way is to deal with negatives because they have more
   * room for addition.
   */
  public static class Solution2 {

    public int divide(int a, int b) {
      if (a == 0) return 0;

      // edge case
      if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;

      boolean negative = (a < 0) ^ (b < 0);
      // make em negative
      a = a > 0 ? -a : a;
      b = b > 0 ? -b : b;

      int ans = 0;

      // we record half of min max because we jump to sum*2 every step
      final int minMaxHalf = Integer.MIN_VALUE >> 2;

      while (a <= b) {
        int sum = b;
        int count = 1;

        // edge case : sum >= minMaxHalf
        // b can be too big
        // edge case :  sum + sum >= minMaxHalf
        // we don't want to overflow sum
        while (sum >= minMaxHalf && sum + sum >= a && sum + sum >= minMaxHalf) {
          sum += sum;
          count += count;
        }

        ans += count;
        a -= sum;
      }

      return negative ? -ans : ans;
    }
  }
}