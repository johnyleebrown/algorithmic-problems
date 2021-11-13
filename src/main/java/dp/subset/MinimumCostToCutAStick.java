package dp.subset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1547
 *
 * ======
 *
 * Task.
 *
 * Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick
 * of length 6 is labelled as follows:
 *
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 *
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 *
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of
 * all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their
 * lengths is the length of the stick before the cut). Please refer to the first example for a
 * better explanation.
 *
 * Return the minimum total cost of the cuts.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MinimumCostToCutAStick {

  /**
   * State: dp[i] (dp[lo][hi]) - min cost if we cut subarray [lo,hi] at some position
   *
   * Subproblem: Get cost of cutting if we will cut subarray [lo,hi] at index k.
   *
   * Idea: We try to cut at different indexes, then try to cut subarrays [lo,k] and [k,hi]
   *
   * Recurrence: dp[lo][hi] = Math.min(curLength + minCostLeft + minCostRight, dp[lo][hi]) where
   * curLength = hi-lo
   */
  public static class Solution {

    public int minCost(int n, int[] ar) {
			if (ar.length == 1) {
				return n;
			}
      return dfs(0, n, ar, new HashMap<>());
    }

    private int dfs(int lo, int hi, int[] ar, Map<Integer, Map<Integer, Integer>> dp) {

      // nowhere to cut if length is 1
      if (hi - lo < 2) {
        return 0;
      }

      if (checkInCache(lo, hi, dp)) {
        return dp.get(lo).get(hi);
      }

      int ans = Integer.MAX_VALUE;

      int curLength = hi - lo;
      for (int j = 0; j < ar.length; j++) {
        if (ar[j] > lo && ar[j] < hi) {
          int localAns = dfs(lo, ar[j], ar, dp) + dfs(ar[j], hi, ar, dp);
          ans = Math.min(ans, curLength + localAns);
        }
      }

      // if we haven't found an answer
      if (ans == Integer.MAX_VALUE) {
        ans = 0;
      }

      cache(lo, hi, ans, dp);
      return ans;
    }

    private boolean checkInCache(int k, int v, Map<Integer, Map<Integer, Integer>> dp) {
      return dp.containsKey(k) && dp.get(k).containsKey(v);
    }

    private void cache(int lo, int hi, int ans, Map<Integer, Map<Integer, Integer>> dp) {
      dp.putIfAbsent(lo, new HashMap<>());
      dp.get(lo).put(hi, ans);
    }
  }

  /**
   * Optimized with sort, bs and key hashing
   */
  public static class Solution2 {

    public int minCost(int n, int[] ar) {
			if (ar.length == 1) {
				return n;
			}
      Arrays.sort(ar);
      return dfs(0, n, ar, new HashMap<>(), n);
    }

    private int dfs(int lo, int hi, int[] ar, Map<Long, Integer> m, int n) {

      if (hi - lo < 2) {
        return 0;
      }

      long key = getHash(lo, hi, n);
      if (m.containsKey(key)) {
        return m.get(key);
      }

      int ans = Integer.MAX_VALUE;

      int start = bsGreaterThan(ar, lo);
      int len = hi - lo;
      for (int j = start; j < ar.length && ar[j] < hi; j++) {
        ans = Math.min(ans, len + dfs(lo, ar[j], ar, m, n) + dfs(ar[j], hi, ar, m, n));
      }

      if (ans == Integer.MAX_VALUE) {
        ans = 0;
      }

      m.put(key, ans);
      return ans;
    }

    /**
     * > x: find first index which value is bigger than
     */
    private int bsGreaterThan(int[] ar, int x) {
      int lo = -1;
      int hi = ar.length;
      while (hi - lo > 1) {
        int mid = lo + (hi - lo) / 2;
        if (ar[mid] <= x) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      return hi;
    }

    private long getHash(int lo, int hi, int n) {
      return (lo + 1) * n + hi;
    }
  }
}