package dp.subset;

import java.util.HashMap;
import java.util.Map;

/**
 * 474
 *
 * ======
 *
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the
 * subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3 Output: 4 Explanation: The largest
 * subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4. Other valid
 * but smaller subsets include {"0001", "1"} and {"10", "1", "0"}. {"111001"} is an invalid subset
 * because it contains 4 1's, greater than the maximum of 3. Example 2:
 *
 * Input: strs = ["10","0","1"], m = 1, n = 1 Output: 2 Explanation: The largest subset is {"0",
 * "1"}, so the answer is 2.
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 600 1 <= strs[i].length <= 100 strs[i] consists only of digits '0' and '1'. 1
 * <= m, n <= 100
 *
 * ======
 *
 * https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {

  /**
   * Recursive + Memo 3D
   */
  public static class Solution1 {

    public int findMaxForm(String[] a, int m, int n) {
      return dfs(m, n, getMap(a), a, 0, new Integer[n + 1][m + 1][a.length + 1]);
    }

    int dfs(int m, int n, Map<String, int[]> map, String[] a, int i, Integer[][][] dp) {
      if (i == a.length) {
        return 0;
      }
      if (dp[n][m][i] != null) {
        return dp[n][m][i];
      }
      int[] counts = map.get(a[i]);
      if (m - counts[0] >= 0 && n - counts[1] >= 0) {
        dp[n][m][i] = Math.max(dfs(m - counts[0], n - counts[1], map, a, i + 1, dp) + 1,
            dfs(m, n, map, a, i + 1, dp));
      } else {
        dp[n][m][i] = dfs(m, n, map, a, i + 1, dp);
      }
      return dp[n][m][i];
    }

    private Map<String, int[]> getMap(String[] a) {
      Map<String, int[]> map = new HashMap<>();
      for (String s : a) {
				if (map.containsKey(s)) {
					continue;
				}
        int z = 0, o = 0;
        for (int j = 0; j < s.length(); j++) {
          if (s.charAt(j) == '0') {
            z++;
          } else {
            o++;
          }
        }
        map.put(s, new int[]{z, o});
      }
      return map;
    }
  }

  /**
   * Iterative 2D
   */
  public static class Solution2 {

    public int findMaxForm(String[] a, int zeros, int ones) {
      int[][] dp = new int[zeros + 1][ones + 1];
      Map<String, int[]> map = new HashMap<>();

      for (String s : a) {
        if (!map.containsKey(s)) {
          map.put(s, getCounts(s));
        }
        int[] counts = map.get(s);

        for (int i = zeros; i >= counts[0]; i--) {
          for (int j = ones; j >= counts[1]; j--) {
            dp[i][j] = Math.max(dp[i - counts[0]][j - counts[1]] + 1, dp[i][j]);
          }
        }
      }

      return dp[zeros][ones];
    }

    private int[] getCounts(String s) {
      int z = 0, o = 0;
      for (int j = 0; j < s.length(); j++) {
        if (s.charAt(j) == '0') {
          z++;
        } else {
          o++;
        }
      }
      return new int[]{z, o};
    }
  }
}