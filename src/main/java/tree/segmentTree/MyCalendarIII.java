package tree.segmentTree;

/**
 * 732
 *
 * ======
 *
 * Task.
 *
 * Implement a MyCalendarThree class to store your events. A new event can
 * always be added.
 *
 * Your class will have one method, book(int start, int end). Formally, this
 * represents a booking on the half open interval [start, end), the range of
 * real numbers x such that start <= x < end.
 *
 * A K-booking happens when K events have some non-empty intersection (ie.,
 * there is some time that is common to all K events.)
 *
 * For each call to the method MyCalendar.book, return an integer K representing
 * the largest integer such that there exists a K-booking in the calendar.
 * Your class will be called like this: MyCalendarThree cal = new
 * MyCalendarThree(); MyCalendarThree.book(start, end)
 *
 * ======
 *
 * Source: Leetcode
 */
public class MyCalendarIII {
    /**
     * Segment Tree (Increment + Max).
     */
    public static class MyCalendarThree {
        private Tree t;

        public MyCalendarThree() {
            t = new Tree();
        }

        public int book(int l, int r) {
            t.i(l, r - 1);
            return t.root.max;
        }

        private class Tree {
            Node root;

            Tree() {
                root = new Node(0, 1_000_000_000);
            }

            void i(int lo, int hi) {
                increment(root, lo, hi, 1);
            }

            void increment(Node cur, int lo, int hi, int val) {
                if (cur == null || notIntersects(cur.lo, cur.hi, lo, hi))
                    return;
                if (covers(cur, lo, hi)) {
                    cur.delta += val;
                    return;
                }

                int mid = (cur.lo + cur.hi) / 2;

                if (cur.left == null) cur.left = new Node(cur.lo, mid);
                cur.left.delta += cur.delta;

                if (cur.right == null) cur.right = new Node(mid + 1, cur.hi);
                cur.right.delta += cur.delta;

                cur.delta = 0;

                increment(cur.left, lo, hi, val);
                increment(cur.right, lo, hi, val);

                update(cur);
            }

            void update(Node cur) {
                cur.max = Math.max(cur.left.max + cur.left.delta, cur.right.max + cur.right.delta);
            }

            boolean notIntersects(int nodeLo, int nodeHi, int lo, int hi) {
                return lo > nodeHi || hi < nodeLo;
            }

            boolean covers(Node cur, int lo, int hi) {
                return lo <= cur.lo && hi >= cur.hi;
            }

            class Node {
                int lo, hi, delta, max;
                Node left, right;

                private Node(int lo, int hi) {
                    this.lo = lo;
                    this.hi = hi;
                }
            }
        }
    }
}