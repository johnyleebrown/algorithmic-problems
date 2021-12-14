package backtracking.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 46
 */
public class Permutations {

  /**
   * Optimization I = keep the list of possible nums to try in a separate
   * list so you don;t check for contains every time
   */
  class Solution {

    List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
      ans = new LinkedList<>();
      PriorityQueue<Integer> p = new PriorityQueue<Integer>();
      gen(new LinkedList<>(), nums, new boolean[nums.length]);
      return ans;
    }

    void gen(List<Integer> cur, int[] nums, boolean[] seen) {
      if (cur.size() == nums.length) {
        ans.add(new ArrayList<>(cur));
      } else {
        for (int i = 0; i < nums.length; i++) {
          if (seen[i]) continue;
          seen[i] = true;
          cur.add(nums[i]);
          gen(cur, nums, seen);
          cur.remove(cur.size() - 1);
          seen[i] = false;
        }
      }
    }
  }

  /**
   * Iterative
   * @todo
   */
  public static class Solution2 {

    public List<List<Integer>> permute(int[] num) {
      List<List<Integer>> ans = new ArrayList<List<Integer>>();
      if (num.length == 0) return ans;
      List<Integer> l0 = new ArrayList<Integer>();
      l0.add(num[0]);
      ans.add(l0);
      for (int i = 1; i < num.length; ++i) {
        List<List<Integer>> new_ans = new ArrayList<List<Integer>>();
        for (int j = 0; j <= i; ++j) {
          for (List<Integer> l : ans) {
            List<Integer> new_l = new ArrayList<Integer>(l);
            new_l.add(j, num[i]);
            new_ans.add(new_l);
          }
        }
        ans = new_ans;
      }
      return ans;
    }
  }
}