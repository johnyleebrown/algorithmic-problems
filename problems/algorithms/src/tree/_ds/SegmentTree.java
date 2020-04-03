package tree._ds;

import java.util.Arrays;
import java.util.Random;

import static util.tester.Assert.assertEquals;

/**
 * SegmentTree
 *
 * =====
 *
 * Tasks.
 *
 * https://codeforces.com/problemset/problem/52/C
 */
interface QueryInterface {
	void increment(int i, int j, int val);

	int min(int i, int j);
}

/**
 * Segment Tree is represented as an array where root is at index 1 with
 * subtrees starting at 2*i and 2*i+1, like in heap. For min and for increment
 * we do propagate before entering the loop - so we could use the lazy values -
 * the values that we use only when we need them. And we do update after we exit
 * the recursion - we update the min depending on the new values.
 */
public class SegmentTree implements QueryInterface {
	int n;
	int[] lo, hi, min, delta;

	public SegmentTree(int n) {
		this.n = n;

		lo = new int[4 * n + 1];
		hi = new int[4 * n + 1];
		min = new int[4 * n + 1];
		delta = new int[4 * n + 1];

		// starting from root (1)
		// whole size is from 0 to n - 1
		init(1, 0, n - 1);
	}

	public void increment(int a, int b, int val) {
		increment(1, a, b, val);
	}

	/**
	 * we want to incr in range [a,b]
	 *
	 * 2 cases: - if we are out of the range, left to the ith node or to the
	 * right of the ith node - a,b is inside of the ith node
	 */
	public void increment(int i, int a, int b, int val) {
		// 1 case : no cover
		if (notIntersects(i, a, b)) {
			return;
		}

		// 2 case : a,b is in ith node
		if (covers(i, a, b)) {
			delta[i] += val;
			return;
		}

		// 3 case : partial cover
		// 3.1 at the start of any recursion call propagate first
		// --- so we could push the lazy change to the children
		prop(i);

		// 3.2 increment at the subtrees
		increment(2 * i, a, b, val);
		increment(2 * i + 1, a, b, val);

		// 3.3 we came back from recursive incremention
		// --- we updated the subtree with new values, 
		// --- so now we need to update min of the subtree
		update(i);
	}

	public int min(int a, int b) {
		return min(1, a, b);
	}

	private int min(int i, int a, int b) {
		// 1 case : no cover
		if (notIntersects(i, a, b)) {
			return Integer.MAX_VALUE;
		}

		// 2 case : ith node covers [a,b]
		if (covers(i, a, b)) {
			return min[i] + delta[i];
		}

		prop(i);

		int minLeft = min(2 * i, a, b);
		int minRight = min(2 * i + 1, a, b);

		update(i);

		return Math.min(minLeft, minRight);
	}

	private boolean notIntersects(int i, int a, int b) {
		return a > hi[i] || b < lo[i];
	}

	private boolean covers(int i, int a, int b) {
		return a <= lo[i] && b >= hi[i];
	}

	/**
	 * propagating - modifying children with cur value - the value that we didnt
	 * move further yet until now.
	 */
	private void prop(int i) {
		delta[2 * i] += delta[i];
		delta[2 * i + 1] += delta[i];
		delta[i] = 0;
	}

	/**
	 * for max - change to max.
	 */
	private void update(int i) {
		min[i] = Math.min(min[2 * i] + delta[2 * i], min[2 * i + 1] + delta[2 * i + 1]);
	}

	/**
	 * [a,b] range for init i - current node
	 */
	private void init(int i, int a, int b) {
		lo[i] = a;
		hi[i] = b;

		// we are at a leaf, nowhere to go
		if (a == b) {
			return;
		}

		// split point
		int mid = (a + b) / 2;
		init(2 * i, a, mid);
		init(2 * i + 1, mid + 1, b);
	}

	public void print(int i, int a, int b) {
		System.out.println("[ " + a + "," + b + " ]: delta=" + delta[i]);
		if (a == b) return;
		int mid = (a + b) / 2;
		print(2 * i, a, mid);
		print(2 * i + 1, mid + 1, b);
	}


	public static void main(String[] a) {
		int n = 7;

		RangeSlow rs = new RangeSlow(7);
		initTest(rs);

		SegmentTree st = new SegmentTree(7);
		initTest(st);

		runSmallTest(n, rs, st);
	}

	private static void runSmallTest(int n, QueryInterface stSlow, QueryInterface stRegular) {
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			int a = r.nextInt(n);
			int b = r.nextInt(n);
			while (b < a) b = r.nextInt(n);
			int minSlow = stSlow.min(a, b);
			int minRegular = stRegular.min(a, b);
			assertEquals(minSlow, minRegular);
		}
	}

	private static void initTest(QueryInterface q) {
		q.increment(0, 0, 15);
		q.increment(1, 1, 3);
		q.increment(2, 2, 4);
		q.increment(3, 3, 2);
		q.increment(4, 4, 1);
		q.increment(5, 5, 6);
		q.increment(6, 6, -1);

		q.increment(0, 4, 3);
		q.increment(1, 3, -4);
		q.increment(5, 6, 10);
		q.increment(0, 6, 0);
	}
}

/**
 * Implicit Segment tree , is similar to static segment tree , Here we create
 * nodes only when required, that’s why it is also known as dynamic segment
 * tree.
 */
class ImplicitSegmentTree implements QueryInterface {

	Node root;

	private class Node {
		int lo;
		int hi;

		int delta;

		Node left;
		Node right;

		private Node(int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
		}
	}

	public ImplicitSegmentTree(int n) {
		root = new Node(0, n);
	}

	public void increment(int a, int b, int val) {
		increment(root, a, b, val);
	}

	public void increment(Node root, int a, int b, int val) {
		if (root == null) {
			return;
		}

		if (noIntersection(root, a, b)) {
			return;
		}

		if (covers(root, a, b)) {
			root.val += val;
			root.lazyVal += val;
			return;
		}

		prop(i);

		int mid = (root.hi + root.lo) / 2;

		if (root.left == null) root.left = new Node(root.lo, mid);
		increment(root.left, newInterval, val);

		if (root.right == null) root.right = new Node(mid + 1, root.hi);
		increment(root.right, newInterval, val);

		update(i);
	}

	private boolean noIntersection(Node root, int a, int b) {
		return a > root.hi || b < root.hi;
	}

	private boolean covers(Node root, int a, int b) {
		return a <= root.lo && b >= root.hi;
	}

	public int min(int a, int b) {
		return min(1, a, b);
	}

	private int min(int i, int a, int b) {
		if (notIntersects(i, a, b)) {
			return Integer.MAX_VALUE;
		}

		if (covers(i, a, b)) {
			return min[i] + delta[i];
		}

		prop(i);

		int minLeft = min(2 * i, a, b);
		int minRight = min(2 * i + 1, a, b);

		update(i);

		return Math.min(minLeft, minRight);
	}

	public static void main(String[] a) {
		int n = 7;

		RangeSlow rs = new RangeSlow(7);
		initTest(rs);

		ImplicitSegmentTree st = new ImplicitSegmentTree(7);
		initTest(st);

		runSmallTest(n, rs, st);
	}

	private static void runSmallTest(int n, QueryInterface stSlow, QueryInterface stRegular) {
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			int a = r.nextInt(n);
			int b = r.nextInt(n);
			while (b < a) b = r.nextInt(n);
			int minSlow = stSlow.min(a, b);
			int minRegular = stRegular.min(a, b);
			assertEquals(minSlow, minRegular);
		}
	}

	private static void initTest(QueryInterface q) {
		q.increment(0, 0, 15);
		q.increment(1, 1, 3);
		q.increment(2, 2, 4);
		q.increment(3, 3, 2);
		q.increment(4, 4, 1);
		q.increment(5, 5, 6);
		q.increment(6, 6, -1);

		q.increment(0, 4, 3);
		q.increment(1, 3, -4);
		q.increment(5, 6, 10);
		q.increment(0, 6, 0);
	}
}


/**
 * Slow version of range increment.
 */
class RangeSlow implements QueryInterface {
	private int[] ar;

	public RangeSlow(int n) {
		ar = new int[n];
	}

	public void increment(int i, int j, int val) {
//		System.out.println("[ "+ i + "," + j +" ]: d=" + ar[i] + " v " + val);
		for (int k = i; k <= j; k++) {
			ar[k] += val;
		}
//		System.out.println("[ "+ i + "," + j +" ]: d=" + ar[i] + " v " + val);
	}

	public int min(int i, int j) {
		int res = ar[i];
		for (int k = i + 1; k <= j; k++) {
			res = Math.min(res, ar[k]);
		}
		return res;
	}

	@Override
	public void print(int i, int a, int b) {
		System.out.println(Arrays.toString(ar));
	}
}
