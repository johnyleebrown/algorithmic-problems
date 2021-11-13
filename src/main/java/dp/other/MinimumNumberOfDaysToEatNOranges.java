package dp.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 1553
 *
 * ======
 *
 * Task.
 *
 * There are n oranges in the kitchen and you decided to eat some of these oranges every day as
 * follows:
 *
 * Eat one orange. If the number of remaining oranges (n) is divisible by 2 then you can eat  n/2
 * oranges. If the number of remaining oranges (n) is divisible by 3 then you can eat  2*(n/3)
 * oranges.
 *
 * You can only choose one of the actions per day.
 *
 * Return the minimum number of days to eat n oranges.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumNumberOfDaysToEatNOranges {

  /**
   * SO
   */
  public static class Solution {

    public int minDays(int n) {
      return f(n, new Integer[n + 1]);
    }

    int f(int n, Integer[] dp) {
			if (n == 0) {
				return 0;
			}
			if (dp[n] != null) {
				return dp[n];
			}
      int ans = 1 + f(n - 1, dp);
			if (n % 2 == 0) {
				ans = Math.min(ans, 1 + f(n / 2, dp));
			}
			if (n % 3 == 0) {
				ans = Math.min(ans, 1 + f(n / 3, dp));
			}
      dp[n] = ans;
      return ans;
    }
  }

  /**
   * MLE
   */
  public static class Solution2 {

    public int minDays(int n) {
      int[] dp = new int[n + 1];
      dp[1] = 1;
      for (int i = 2; i <= n; i++) {
        int x = dp[i - 1];
				if (i % 2 == 0) {
					x = Math.min(x, dp[i / 2]);
				}
				if (i % 3 == 0) {
					x = Math.min(x, dp[i / 3]);
				}
        dp[i] = x + 1;
      }
      return dp[n];
    }
  }

  /**
   * Optimized - works. We try to trim the steps we need to take, so we now that eating n/2 is
   * optimal, so we add number of days we need to reach (n%2==0) state = it is just (n % 2), and
   * number of days to reach (n%3==0) state is (n%3). So until we reach those days we will eat only
   * 1 apple/day, so we just add those numbers to trim the steps. So just like prev solutions but
   * with optimization.
   */
  public static class Solution3 {

    public int minDays(int n) {
      return f(n, new HashMap<>());
    }

    int f(int n, Map<Integer, Integer> dp) {
      if (n <= 1) {
        return n;
      }
      if (dp.containsKey(n)) {
        return dp.get(n);
      }
      int x = 1 + Math.min((n % 2) + f(n / 2, dp), (n % 3) + f(n / 3, dp));
      dp.put(n, x);
      return x;
    }
  }
}