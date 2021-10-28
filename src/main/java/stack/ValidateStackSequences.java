package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/** 946 */
public class ValidateStackSequences {
  /** Simulate addition to stack, check every time if we can pop at the point. */
  public static class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
      Deque<Integer> q = new ArrayDeque<>();
      int j = 0;
      for (int i = 0; i < pushed.length; i++) {
        if (pushed[i] == popped[j]) {
          j++;
          while (!q.isEmpty() && q.peekLast() == popped[j]) {
            j++;
            q.removeLast();
          }
        } else {
          q.add(pushed[i]);
        }
      }
      return q.isEmpty();
    }
  }

  public static class Solution2 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
      Set<Integer> seen = new HashSet<>();
      Stack<Integer> st = new Stack<>();
      int j = 0;
      for (int i = 0; i < popped.length; i++) {
        int cur = popped[i];
        if (seen.contains(cur)) {
          if (st.peek() != cur) {
            return false;
          } else {
            st.pop();
          }
        } else {
          int x = -1;
          while (j < pushed.length && x != cur) {
            if (cur != pushed[j]) st.push(pushed[j]);
            x = pushed[j];
            seen.add(pushed[j]);
            j++;
          }
        }
      }
      return true;
    }
  }

  /**
   * We go through the popped and try to find it in pushed. While going through pushed we collect
   * items in the list. Our cur item can be either last in the collected list or can be somewhere
   * ahead in the pushed array - so we go look for it. If none of those - we return false.
   */
  public static class Solution3 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
      List<Integer> list = new ArrayList<>();
      int n = pushed.length;
      int j = 0;
      for (int i = 0; i < n; i++) {
        int cur = popped[i];
        // 2 cases - 1) take peek 2) look for in array
        if (!list.isEmpty() && list.get(list.size() - 1) == cur) {
          list.remove(list.size() - 1);
          continue;
        }
        j = findNext(j, pushed, list, cur);
        if (j >= pushed.length) return false;
        j++;
      }
      return true;
    }

    public int findNext(int j, int[] pushed, List<Integer> list, int cur) {
      while (j < pushed.length && pushed[j] != cur) {
        list.add(pushed[j]);
        j++;
      }
      return j;
    }
  }
}
