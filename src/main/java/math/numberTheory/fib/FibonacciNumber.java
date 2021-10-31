package math.numberTheory.fib;

import bs.regular.numbers.ValidPerfectSquare;
import java.util.HashMap;
import java.util.Map;

/**
 * 509. Fibonacci Number
 * https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {

  public static class Solution1 {

    Map<Integer, Integer> map = new HashMap<>();

    public int fib(int n) {
      if (map.containsKey(n)) {
        return map.get(n);
      }
      int k = 0;
      if (n < 0) {
        return 0;
      }
      if (n == 0) {
        k = 0;
      } else if (n == 1) {
        k = 1;
      } else {
        k = fib(n - 2) + fib(n - 1);
      }
      map.put(n, k);
      return k;
    }
  }

  /**
   * Generate fib numbers. 0,1,1,2,3,5,8,13,21..
   */
  public static class Solution3 {

    public boolean solve(int n) {
      return check(n);
    }

    private boolean check(int n) {
      if (n == 0) {
        return true;
      }
      int one = 0;
      int two = 1;
      while (two < n) {
        int t = one + two;
        one = two;
        two = t;
        if (two == n) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * n is a fib number if (5*n^2 + 4) or (5*n^2 – 4) is a perfect square.
   */
  public static class Solution2 {

    public boolean solve(int n) {
      long x = 5 * n * n;
      return isPerfectSquare(x + 4) || isPerfectSquare(x - 4);
    }

    private boolean isPerfectSquare(long n) {
      return bs(n);
    }

    /**
     * Reference: {@link ValidPerfectSquare}.
     */
    private boolean bs(long n) {
      if (n == 1) {
        return true;
      }
      long lo = 1;
      long hi = n / 2;
      while (hi - lo >= 0) {
        long mid = lo + (hi - lo) / 2;
        long x = mid * mid;
        if (x == n) {
          return true;
        }
        if (x < mid) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }
      return false;
    }
  }
}