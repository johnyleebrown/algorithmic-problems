package sort.heap;

/**
 * 973
 */
public class KClosestPointsToOrigin {

  /**
   * Heap O(n*logk)
   */
  public static class Solution {

    public int[][] kClosest(int[][] points, int k) {
      // use max heap
      // [1] the biggest of smallest
      MaxHeap h = new MaxHeap(k);
      for (int[] p : points) {
        if (h.n == k) {
          h.replaceMax(p);
        } else {
          h.add(p);
        }
      }
      return h.getSnapshot();
    }

    static class MaxHeap {

      int cap, n = 0;
      int[][] h;

      MaxHeap(int cap) {
        this.cap = cap;
        h = new int[cap + 1][];
      }

      void add(int[] p) {
        h[++n] = p;
        swim(n);
      }

      void replaceMax(int[] p) {
        if (!less(p, h[1])) return;
        h[1] = p;
        sink(1);
      }

      void swim(int i) {
        while (i > 1 && less(h[i / 2], h[i])) {
          exch(i, i / 2);
          i = i / 2;
        }
      }

      void sink(int i) {
        while (i * 2 <= n) {
          int j = i * 2;
          if (j + 1 <= n && less(h[j], h[j + 1])) j++;
          if (!less(h[i], h[j])) break;
          exch(i, j);
          i = j;
        }
      }

      boolean less(int[] p1, int[] p2) {
        return dist(p1) < dist(p2);
      }

      boolean larger(int[] p1, int[] p2) {
        return dist(p1) > dist(p2);
      }

      int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
      }

      void exch(int i, int j) {
        int[] t = h[i];
        h[i] = h[j];
        h[j] = t;
      }

      int[][] getSnapshot() {
        int[][] ans = new int[cap][];
        for (int i = 0; i < cap; i++) {
          ans[i] = h[i + 1];
        }
        return ans;
      }
    }
  }
}