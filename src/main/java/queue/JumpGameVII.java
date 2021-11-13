package queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1871. Jump Game VII https://leetcode.com/problems/jump-game-vii/
 */
public class JumpGameVII {

  /**
   * memory limit
   *
   * add jump position candidates to queue and take them form there
   */
  public static class Solution1 {

    public boolean canReach(String s, int minJump, int maxJump) {
      int windowLength = maxJump - minJump;
      if (s.charAt(s.length() - 1) == '1') {
        return false;
      }
      Integer.MAX_VALUE;
      // queue for possible indexes of jump places
      List<Integer> q = new ArrayList<>();
      int n = s.length();
      q.add(n - 1);
      boolean reachedStart = false;
      int delta = maxJump - minJump;
      while (!q.isEmpty() && !reachedStart) {
        int currentIndex = q.remove(0);
        for (int i = currentIndex, target = currentIndex - minJump;
             i >= Math.max(0, currentIndex - delta) && target >= 0; i--, target--) {
          if (s.charAt(target) == '0') {
            q.add(target);
            if (target == 0) {
              reachedStart = true;
              break;
            }
          }
        }
      }
      return reachedStart;
    }
  }

  public static class Solution2 {

    Map<Integer, Boolean> map = new HashMap<>();
    Set<Integer> seen = new HashSet<>();

    public boolean canReach(String s, int minJump, int maxJump) {
      if (s.charAt(s.length() - 1) == '1') {
        return false;
      }

      // greedy with recursion
      // move window from left to right
      // meaning - start with the closest to the start
      // and keep a queue of continue positions and not candidates
      return canReachHelper(s, minJump, maxJump - minJump, s.length() - 1);
    }

    // call this method = jump towards 0
    boolean canReachHelper(String s, int minJump, int delta, int currentIndex) {

      if (seen.contains(currentIndex)) {
        return false;
      }

      int start = currentIndex - delta;
      int end = currentIndex - minJump;

      if (end < 0) {
        map.put(currentIndex, false);
        return false;
      }

      for (int i = start, target = start - minJump; i <= currentIndex; i++, target++) {
        if (seen.add(target)) {
          continue;
        }
        if (target >= 0 && s.charAt(target) == '0') {
          if (target == 0) {
            map.put(currentIndex, true);
            return true;
          }
          boolean ans = canReachHelper(s, minJump, delta, target);
          if (ans) {
            return ans;
          }
        }
      }

      return false;
    }
  }
}