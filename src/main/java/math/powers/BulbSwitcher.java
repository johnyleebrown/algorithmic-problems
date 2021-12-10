package math.powers;

/**
 * 319
 */
public class BulbSwitcher {

  /**
   * The final state 1 if odd number of divisors, 0 otherwise. Only sqrt roots have odd
   * number of divisors. Number of sqrt roots in a number is sqrt of this number.
   */
  public static class Solution {

    public int bulbSwitch(int n) {
      return (int) Math.sqrt(n);
    }
  }
}
