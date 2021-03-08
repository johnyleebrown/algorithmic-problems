package stack;

import java.util.Stack;

/**
 * 277
 *
 * <p>======
 *
 * <p>Task.
 *
 * <p>Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may
 * exist one celebrity. The definition of a celebrity is that all the other n - 1 people know
 * him/her but he/she does not know any of them.
 *
 * <p>Now you want to find out who the celebrity is or verify that there is not one. The only thing
 * you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of
 * whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as
 * few questions as possible (in the asymptotic sense).
 *
 * <p>You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement
 * a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party.
 * Return the celebrity's label if there is a celebrity in the party. If there is no celebrity,
 * return -1.
 *
 * <p>======
 *
 * <p>Source: Leetcode
 */
public class FindTheCelebrity {
  /**
   * Check every two, add candidates to stack.
   */
  public static class Solution {
    public int findCelebrity(int n) {
      if (n <= 0) return -1;
      if (n == 1) return 0;

      Stack<Integer> stack = new Stack<>();
      for (int i = 0; i < n; i++) stack.push(i);

      while (stack.size() > 1) {
        int a = stack.pop();
        int b = stack.pop();

        if (knows(a, b)) stack.push(b);
        else stack.push(a);
      }

      int c = stack.pop();
      for (int i = 0; i < n; i++) if (i != c && (knows(c, i) || !knows(i, c))) return -1;

      return c;
    }

    private boolean knows(int a, int b) {
      return false;
    }
  }

  /**
   * The first loop will stop to an candidate i who doesn't know anyone from i+1 to n-1. For any
   * previous x < i either know sb else or is not known by sb else.
   *
   * <p>The second loop will check whether i knows anyone from 0 to i-1.
   *
   * <p>The third loop is gonna check whether all party participants know x or not.
   */
  public static class Solution2 {
    public int findCelebrity(int n) {
      int x = 0;
      for (int i = 0; i < n; ++i) if (knows(x, i)) x = i;
      for (int i = 0; i < x; ++i) if (knows(x, i)) return -1;
      for (int i = 0; i < n; ++i) if (!knows(i, x)) return -1;
      return x;
    }

    private boolean knows(int a, int b) {
      return false;
    }
  }
}
