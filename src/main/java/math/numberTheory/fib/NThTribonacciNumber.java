package math.numberTheory.fib;

import java.util.HashMap;
import java.util.Map;

/**
 * 1137. N-th Tribonacci Number
 * https://leetcode.com/problems/n-th-tribonacci-number/
 */
public class NThTribonacciNumber {

  public static class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public int tribonacci(int n) {
      if (map.containsKey(n)) {
        return map.get(n);
      }
      int x = 0;
      if (n == 0) {
      } else if (n == 1) {
        x = 1;
      } else if (n == 2) {
        x = 1;
      } else {
        x = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
      }
      map.put(n, x);
      return x;
    }
  }
}