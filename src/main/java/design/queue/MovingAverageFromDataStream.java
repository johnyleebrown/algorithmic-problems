package design.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 346
 */
public class MovingAverageFromDataStream {

  public static class Solution {

    class MovingAverage {

      int d;
      Deque<Integer> l;
      double sum;

      public MovingAverage(int size) {
        d = size;
        l = new ArrayDeque<>();
      }

      public double next(int val) {
        sum += val;
        l.addLast(val);
        if (l.size() > d) sum -= l.removeFirst();
        return sum / Math.min(d, l.size());
      }
    }
  }
}