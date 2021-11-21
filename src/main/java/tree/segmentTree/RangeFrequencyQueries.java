package tree.segmentTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 2080. Range Frequency Queries
 *
 * https://leetcode.com/problems/range-frequency-queries/
 */
public class RangeFrequencyQueries {

  /**
   * TLE
   */
  public static class Solution {

    static class RangeFreqQuery {

      SegmentTree st;

      public RangeFreqQuery(int[] arr) {
        st = new SegmentTree(0, arr.length - 1, arr);
        for (int i = 0; i < arr.length; i++) {
          st.increment(i, i, 1);
        }
      }

      public int query(int left, int right, int value) {
        return st.count(left, right, value);
      }

      private static class SegmentTree {

        private static int[] ar;
        private final Node root;

        public SegmentTree(int lo, int hi, int[] ar) {
          SegmentTree.ar = ar;
          root = new Node(lo, hi);
        }

        private void increment(int lo, int hi, int val) {
          increment(root, lo, hi, val);
        }

        private void increment(Node cur, int lo, int hi, int val) {
          if (notIntersects(cur, lo, hi)) {
            return;
          }
          if (covers(cur, lo, hi)) {
            cur.delta.replaceAll((k, v) -> v + val);
            return;
          }

          int mid = cur.lo + (cur.hi - cur.lo) / 2;
          if (cur.left == null) cur.left = new Node(cur.lo, mid);
          if (cur.right == null) cur.right = new Node(mid + 1, cur.hi);

          prop(cur);

          increment(cur.left, lo, hi, val);
          increment(cur.right, lo, hi, val);

          update(cur);
        }

        private void prop(Node cur) {
          for (int k : cur.left.counts.keySet()) {
            int d = cur.delta.get(k);
            cur.left.delta.put(k, cur.left.delta.get(k) + d);
          }
          for (int k : cur.right.counts.keySet()) {
            int d = cur.delta.get(k);
            cur.right.delta.put(k, cur.right.delta.get(k) + d);
          }
          cur.delta.replaceAll((k, v) -> 0);
        }

        private boolean covers(Node cur, int lo, int hi) {
          return lo <= cur.lo && hi >= cur.hi;
        }

        private boolean notIntersects(Node cur, int lo, int hi) {
          return lo > cur.hi || hi < cur.lo;
        }

        public int count(int lo, int hi, int val) {
          return count(root, lo, hi, val);
        }

        private int count(Node cur, int lo, int hi, int val) {
          if (notIntersects(cur, lo, hi)) {
            return 0;
          }

          if (covers(cur, lo, hi)) {
            if (cur.counts.containsKey(val)) {
              return cur.counts.getOrDefault(val, 0) +
                     cur.delta.getOrDefault(val, 0);
            }
            return 0;
          }

          int mid = cur.lo + (cur.hi - cur.lo) / 2;
          if (cur.left == null) cur.left = new Node(cur.lo, mid);
          if (cur.right == null) cur.right = new Node(mid + 1, cur.hi);

          prop(cur);

          int left = count(cur.left, lo, hi, val);
          int right = count(cur.right, lo, hi, val);

          update(cur);

          return left + right;
        }

        private void update(Node cur) {
          for (int key : cur.counts.keySet()) {
            int leftDelta = cur.left.delta.getOrDefault(key, 0);
            int rightDelta = cur.right.delta.getOrDefault(key, 0);
            int leftVal = cur.left.counts.getOrDefault(key, 0);
            int rightVal = cur.right.counts.getOrDefault(key, 0);

            cur.counts.put(key, leftVal + rightVal + leftDelta + rightDelta);
          }
        }

        private static class Node {

          int lo, hi;
          Node left, right;
          Map<Integer, Integer> counts, delta;

          public Node(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            counts = new HashMap<>();
            delta = new HashMap<>();
            for (int i = lo; i <= hi; i++) {
              counts.put(ar[i], 0);
              delta.put(ar[i], 0);
            }
          }
        }
      }
    }
  }
}