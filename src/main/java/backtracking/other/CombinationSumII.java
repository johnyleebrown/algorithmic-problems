package backtracking.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40
 */
public class CombinationSumII {

  /**
   * Unique combinations - sort + don't step on same number.
   *
   * When we start a new combination we shouldn't start with seen number.
   */
  public static class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> ans = new ArrayList<>();
      gen(candidates, target, ans, new ArrayList<>(), 0, 0);
      return ans;
    }

    private void gen(int[] ar, int target, List<List<Integer>> ans, List<Integer> cur,
        int sum, int j) {
      if (sum == target) {
        ans.add(new ArrayList<>(cur));
      } else {
        for (int i = j; i < ar.length; i++) {
          // overflow
          if (ar[i] + sum > target) continue;
          // don't step on same number
          if (i != j && ar[i] == ar[i - 1]) continue;

          cur.add(ar[i]);
          gen(ar, target, ans, cur, sum + ar[i], i + 1);
          cur.remove(cur.size() - 1);
        }
      }
    }
  }
}

