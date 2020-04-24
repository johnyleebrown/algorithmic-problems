package tree._ds.SegmentTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ImplicitSegmentTree
 *
 * Similar to static segment tree, we create nodes only when required, that’s
 * why it is also known as dynamic segment tree.
 *
 * increment - creates interval nodes for the interval and increments the delta.
 * if intersects hi+lo / 2 intervals then we create.
 *
 * TODO impl w/o lazy prop
 */
public class ImplicitSegmentTree implements SegmentTreeQuery {
    private Node root;
    private AggregateFunction af;

    public ImplicitSegmentTree(int n) {
        root = new Node(0, n);
        af = AggregateFunction.MIN;
    }

    public ImplicitSegmentTree(int n, AggregateFunction aggregateFunction) {
        root = new Node(0, n);
        af = aggregateFunction;
    }

    public void increment(int a, int b, int val) {
        increment(root, a, b, val);
    }

    public void increment(Node cur, int a, int b, int val) {
        if (cur == null || noIntersection(cur.lo, cur.hi, a, b)) {
            return;
        }

        if (covers(cur, a, b)) {
            cur.delta += val;
            return;
        }

        int mid = (cur.hi + cur.lo) / 2;

        if (cur.left == null) cur.left = new Node(cur.lo, mid);
        cur.left.delta += cur.delta; // delta leftover
        if (cur.right == null) cur.right = new Node(mid + 1, cur.hi);
        cur.right.delta += cur.delta; // delta leftover
        cur.delta = 0;

        increment(cur.left, a, b, val);
        increment(cur.right, a, b, val);

        // after children have been updated - we can use up-to-date data from them
        update(cur);
    }

    private void update(Node cur) {
        if (af == AggregateFunction.MIN) {
            cur.min = Math.min(cur.left.min + cur.left.delta, cur.right.min + cur.right.delta);
        } else {
            cur.max = Math.max(cur.left.max + cur.left.delta, cur.right.max + cur.right.delta);
        }
    }

    public int min(int a, int b) {
        return min(root, a, b);
    }

    public int max(int a, int b) {
        return min(root, a, b);
    }

    private int min(Node cur, int a, int b) {
        if (cur == null || noIntersection(cur.lo, cur.hi, a, b)) {
            if (af == AggregateFunction.MIN) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }

        if (covers(cur, a, b)) {
            if (af == AggregateFunction.MIN) return cur.delta + cur.min;
            else return cur.delta + cur.max;
        }

        prop(cur);

        int left = min(cur.left, a, b);
        int right = min(cur.right, a, b);

        update(cur);

        if (af == AggregateFunction.MIN) return Math.min(left, right);
        else return Math.max(left, right);
    }

    private void prop(Node cur) {
        if (cur.left != null) {
            cur.left.delta += cur.delta;
        }
        if (cur.right != null) {
            cur.right.delta += cur.delta;
        }
        cur.delta = 0;
    }

    private boolean covers(Node root, int a, int b) {
        return a <= root.lo && b >= root.hi;
    }

    private boolean noIntersection(int nodeLo, int nodeHi, int lo, int hi) {
        return lo > nodeHi || hi < nodeLo;
    }

    public void print() {
        print(root.lo, root.hi);
    }

    public void print(int a, int b) {
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (--size >= 0) {
                Node cur = q.removeFirst();
                System.out.print("i: [" + cur.lo + ", " + cur.hi + "] ");
                System.out.print("d: [" + cur.delta + "], ");
                System.out.print("m: [" + cur.min + "] | ");
                if (cur.left != null) q.addLast(cur.left);
                if (cur.right != null) q.addLast(cur.right);
            }
            System.out.println();
        }
    }

    private class Node {
        int lo, hi;
        int delta, min, max;
        Node left, right;

        private Node(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }
}

