package sort.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503
 *
 * <p>======
 *
 * <p>Task.
 *
 * <p>Given a circular array (the next element of the last element is the first element of the
 * array), print the Next Greater Number for every element. The Next Greater Number of a number x is
 * the first greater number to its traversing-order next in the array, which means you could search
 * circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * <p>======
 *
 * <p>Source: Leetcode
 */
public class NextGreaterElementII {
  /**
   * Circularly using Decreasing MQ to input all elements twice. So if we didn't get any results
   * after first iteration, do it again.
   */
  public static class Solution {
    public int[] nextGreaterElements(int[] ar) {
      MQImpl mq = new MQImpl(ar.length);
      for (int i = 0; i < ar.length; i++) {
        mq.push(new Pair(ar[i], i));
      }
      for (int i = 0; i < ar.length; i++) {
        mq.push(new Pair(ar[i], i));
      }
      return mq.ans;
    }

    interface MQ {
      void push(Pair e);
    }

    private class MQImpl implements MQ {

      Deque<Pair> q = new ArrayDeque<>();
      int[] ans;

      MQImpl(int n) {
        ans = new int[n];
        Arrays.fill(ans, -1);
      }

      public void push(Pair e) {
        // rem
        while (!q.isEmpty() && q.peekLast().val < e.val) {
          Pair p = q.peekLast();
          ans[p.ind] = e.val;
          q.removeLast();
        }
        // add
        q.addLast(e);
      }
    }

    private class Pair {
      int val, ind;

      Pair(int v, int i) {
        val = v;
        ind = i;
      }
    }
  }
}
