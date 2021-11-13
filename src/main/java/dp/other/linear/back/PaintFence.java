package dp.other.linear.back;

/**
 * 276 https://leetcode.com/problems/paint-fence
 */
public class PaintFence {

  public static class Solution {

    public int numWays(int n, int k) {
      return f(new int[n + 1], n, k);
    }

    private int f(int[] dp, int i, int k) {
      if (i == 0) {
        return 0;
      }
      if (i == 1) {
        return k;
      }
      if (i == 2) {
        return k * k;
      }
      if (dp[i] != 0) {
        return dp[i];
      }
      dp[i] = (f(dp, i - 1, k) + f(dp, i - 2, k)) * (k - 1);
      return dp[i];
    }
  }
}
/*
3
2
1
1
7
2
1
7
7
1
11
2
2
46340
2
3
3
3
*/