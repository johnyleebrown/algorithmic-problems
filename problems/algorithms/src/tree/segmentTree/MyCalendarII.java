package tree.segmentTree;

/**
 * 731
 */
public class MyCalendarII {
    public static class Solution {
        class MyCalendarTwo {
            SegmentTree t;

            public MyCalendarTwo() {
                t = new SegmentTree();
            }

            public boolean book(int start, int end) {
                if (t.max(start, end) > 2) return false;
                t.update(start, end);
                return true;
            }

            /**
             * (+,max) segtree
             */
            private class SegmentTree {
                Node root;

                public SegmentTree() {
                    root = new Node(0, (int) 1e9);
                }

                public void update(int lo, int hi) {
                    update(root, lo, hi, 1);
                }

                private void update(Node cur, int lo, int hi, int val) {
                    if (notIntersects(cur.lo, cur.hi, lo, hi)) {
                        return;
                    }
                    if (overlaps(cur, lo, hi)) {
                        cur.delta += val;
                        return;
                    }
                    int mid = (hi + lo) / 2;
                    //prop
                    if (cur.left == null) cur.left = new Node(cur.lo, mid);
                    cur.left.delta += cur.delta;
                    if (cur.right == null)
                        cur.right = new Node(mid + 1, cur.hi);
                    cur.right.delta += cur.delta;
                    cur.delta = 0;
                    //recursive
                    update(cur.left, lo, hi, val);
                    update(cur.right, lo, hi, val);
                    //update
                    cur.max = Math.max(cur.left.max + cur.left.delta, cur.right.max + cur.right.delta);
                }

                private boolean overlaps(Node cur, int lo, int hi) {
                    return lo <= cur.lo && hi >= cur.hi;
                }

                private boolean notIntersects(int lo1, int hi1, int lo2, int hi2) {
                    return lo2 > hi1 || hi2 < lo1;
                }

                public int max(int lo, int hi) {
                    return max(root, lo, hi, 1);
                }

                public int max(Node cur, int lo, int hi, int delta) {
                    if (notIntersects(cur.lo, cur.hi, lo, hi)) {
                        return Integer.MIN_VALUE;
                    }
                    if (overlaps(cur, lo, hi)) {
                        return cur.max + delta;
                    }
                    //prop
                    //no prop, just send values to children
                    //recurse
                    int l = max(cur.left, lo, hi, delta);
                    int r = max(cur.right, lo, hi, delta);
                    //update
                    //ret
                    return Math.max(cur.max + delta, Math.max(l, r));
                }

                private class Node {
                    int lo, hi;
                    int delta, max;
                    Node left, right;

                    public Node(int lo, int hi) {
                        this.lo = lo;
                        this.hi = hi;
                    }
                }
            }
        }
    }
}