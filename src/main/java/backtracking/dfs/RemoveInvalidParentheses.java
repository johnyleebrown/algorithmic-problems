package backtracking.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 301. Remove Invalid Parentheses https://leetcode.com/problems/remove-invalid-parentheses/ */
public class RemoveInvalidParentheses {
  /**
   * In this problem I started with basic cases and moved towards harder ones. I started with
   * isValid, isEmpty -> removal of just 1 bracket and so forth. And it really helped to shape the
   * dfs method below.
   */
  /**
   * Backtracking DFS - we are trying to remove 1,2,3..n starting at i, but not 1 starting at i..j
   */
  public static class Solution {
    public List<String> removeInvalidParentheses(String s) {
      List<String> ans = new ArrayList<>();
      StringBuilder base = new StringBuilder(s);
      if (isValid(base, null)) {
        ans.add(base.toString());
        return ans;
      }
      removeLeading(base);
      removeTrailing(base);
      if (isValid(base, null)) {
        ans.add(base.toString());
        return ans;
      }
      boolean[] removed = new boolean[base.length()];
      Status st = new Status();
      /*
      With map:
      Map<Integer, Set<String>> m = new HashMap<>();
      gen(removed, base, m, 0, st);
      return new ArrayList<>(m.get(st.minRemoves));
       */
      Set<String> seen = new HashSet<>();
      dfs(removed, base, seen, 0, st);
      for (String ss : seen) {
        // we have a set of different correct parenthesis
        // leave only those with minimal removals (which have biggest length)
        if (ss.length() == base.length() - st.minRemoves) {
          ans.add(ss);
        }
      }
      return ans;
    }

    public void removeLeading(StringBuilder base) {
      int i = 0;
      while (i < base.length() && base.charAt(i) != '(') {
        if (base.charAt(i) == ')') {
          base.deleteCharAt(i);
        } else {
          i++;
        }
      }
    }

    public void removeTrailing(StringBuilder base) {
      int i = base.length() - 1;
      while (i >= 0 && base.charAt(i) != ')') {
        if (base.charAt(i) == '(') {
          base.deleteCharAt(i);
          i--;
        } else {
          i--;
        }
      }
    }

    public void dfs(boolean[] removed, StringBuilder s, Set<String> ans, int i, Status st) {
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(j) >= 'a' && s.charAt(j) <= 'z') {
          continue;
        }
        if (removed[j]) {
          continue;
        }
        removed[j] = true;
        st.incr();
        if (isValid(s, removed)) {
          st.setMinRemoves();
          /*
          With map
          String res = createResult(removed, s);
          m.putIfAbsent(st.minRemoves, new HashSet<>());
          m.get(st.minRemoves).add(res);
           */
          ans.add(createResult(removed, s));
        } else {
          if (st.removedCount + 1 <= st.minRemoves) {
            dfs(removed, s, ans, j + 1, st);
          }
        }
        st.decr();
        removed[j] = false;
      }
    }

    public String createResult(boolean[] removed, StringBuilder s) {
      StringBuilder result = new StringBuilder();
      for (int k = 0; k < removed.length; k++) {
        if (!removed[k]) {
          result.append(s.charAt(k));
        }
      }
      return result.toString();
    }

    public boolean isValid(StringBuilder s, boolean[] removed) {
      int left = 0, right = 0;
      for (int i = 0; i < s.length(); i++) {
        if (removed != null && removed[i]) {
          continue;
        }
        char c = s.charAt(i);
        if (c >= 'a' && c <= 'z') {
          continue;
        }
        if (c == ')' && left > 0) {
          left--;
        } else if (c == '(') {
          left++;
        } else {
          right++;
        }
      }
      return left + right == 0;
    }

    public static class Status {
      int removedCount = 0;
      int minRemoves = 25;

      public void setMinRemoves() {
        minRemoves = Math.min(minRemoves, removedCount);
      }

      public void incr() {
        removedCount++;
      }

      public void decr() {
        removedCount--;
      }
    }
  }
}
