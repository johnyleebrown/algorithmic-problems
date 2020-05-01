package tree.segmentTree;

import java.util.LinkedList;
import java.util.List;

/**
 * 315
 *
 * ======
 *
 * Task.
 *
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountOfSmallerNumbersAfterSelf {
    /**
     * Sum of increments of the range - how many numbers are less then current.
     */
    public static class Solution {
        public List<Integer> countSmaller(int[] nums) {
            List<Integer> ans = new LinkedList<>();
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i : nums) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
            SegmentTree st = new SegmentTree(min, max);
            for (int i = nums.length - 1; i >= 0; i--) {
                ans.add(0, st.sum(min, nums[i] - 1));
                st.increment(nums[i], nums[i], 1);
            }
            return ans;
        }

        private static class SegmentTree {
            private final Node root;

            public SegmentTree(int lo, int hi) {
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
                    cur.delta += val;
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
                cur.left.delta += cur.delta;
                cur.right.delta += cur.delta;
                cur.delta = 0;
            }

            private boolean covers(Node cur, int lo, int hi) {
                return lo <= cur.lo && hi >= cur.hi;
            }

            private boolean notIntersects(Node cur, int lo, int hi) {
                return lo > cur.hi || hi < cur.lo;
            }

            public int sum(int lo, int hi) {
                return sum(root, lo, hi);
            }

            private int sum(Node cur, int lo, int hi) {
                if (notIntersects(cur, lo, hi)) {
                    return 0;
                }

                if (covers(cur, lo, hi)) {
                    return cur.val + cur.delta;
                }

                int mid = cur.lo + (cur.hi - cur.lo) / 2;
                if (cur.left == null) cur.left = new Node(cur.lo, mid);
                if (cur.right == null) cur.right = new Node(mid + 1, cur.hi);

                prop(cur);

                int left = sum(cur.left, lo, hi);
                int right = sum(cur.right, lo, hi);

                update(cur);

                return left + right;
            }

            private void update(Node cur) {
                cur.val = cur.left.val + cur.left.delta + cur.right.val + cur.right.delta;
            }

            private static class Node {
                int lo, hi, val, delta;
                Node left, right;

                public Node(int lo, int hi) {
                    this.lo = lo;
                    this.hi = hi;
                }
            }
        }
    }
}