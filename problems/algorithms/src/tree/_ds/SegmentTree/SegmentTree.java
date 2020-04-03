package tree._ds.SegmentTree;

import java.util.Random;

import static util.tester.Assert.assertEquals;

/**
 * SegmentTree
 *
 * =====
 *
 * Overview.
 *
 * Segment Tree is represented as an array where root is at index 1 with
 * subtrees starting at 2*i and 2*i+1, like in heap. For min and for increment
 * we do propagate before entering the loop - so we could use the lazy values -
 * the values that we use only when we need them. And we do update after we exit
 * the recursion - we update the min depending on the new values.
 *
 * =====
 *
 * Tasks.
 *
 * https://codeforces.com/problemset/problem/52/C
 */
public class SegmentTree implements SegmentTreeQuery {
	int n;

	int[] lo, hi;
	int[] delta;
	int[] min, max;

	int updateOp = 0; // 0 - incr, 1 - decr
	int aFn; // aggregate function: 0 - min, 1 - max

	public SegmentTree(int n, int u, int a) {
		this.n = n;

		aFn = a;

		lo = new int[4 * n + 1];
		hi = new int[4 * n + 1];
		if (a == 0)
			min = new int[4 * n + 1];
		else if (a == 1)
			max = new int[4 * n + 1];
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

	/**
	 * we search for min, by pushing unpubliched delta to children
	 * then we lift the minimums from the bottom and update parent's mins
	 */
	private int min(int i, int a, int b) {
		// 1 case : no cover
		if (notIntersects(i, a, b)) {
			return Integer.MAX_VALUE; //TODO consider aFn
		}

		// 2 case : ith node covers [a,b]
		if (covers(i, a, b)) {
			return min[i] + delta[i];
		}

		prop(i);

		int minLeft = min(2 * i, a, b);
		int minRight = min(2 * i + 1, a, b);

		//		System.out.println(minLeft+" "+minRight+" _: "+(2*i)+" "+(2*i+1));

		update(i);

		if (aFn == 0)
			return Math.min(minLeft, minRight);
		else
			return Math.max(minLeft, minRight);
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
		if (aFn == 0)
			min[i] = Math.min(min[2 * i] + delta[2 * i],
					min[2 * i + 1] + delta[2 * i + 1]);
		else if (aFn == 1)
			max[i] = Math.max(min[2 * i] + delta[2 * i],
					min[2 * i + 1] + delta[2 * i + 1]);
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

	/**
	 * TODO introduce nulls
	 */
	public static void main(String[] a) {
		int n = 50;

		SegmentTreeSlow rs = new SegmentTreeSlow(n);
		initTest(rs);
		//rs.increment(10,20,10);
		//	rs.print();

		SegmentTree st = new SegmentTree(n,0,0);
		initTest(st);
		//st.increment(10,20,10);
		//st.print(1,0,n);

		//runTest(16, 41, rs, st);

		runSmallTest(n, rs, st);
	}

	/**
	 * TODO
	 */
	private void testDeltas(int n, SegmentTreeQuery stSlow,
			SegmentTreeQuery stRegular) {
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

	private static void runSmallTest(int n, SegmentTreeQuery stSlow,
			SegmentTreeQuery stRegular) {
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

	private static void runTest(int a, int b,
			SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
		System.out.println("a,b: [" + a + " " + b + "]");
		int minSlow = stSlow.min(a, b);
		System.out.println("=======");
		int minRegular = stRegular.min(a, b);
		assertEquals(minSlow, minRegular);
	}

	private static void initTest(SegmentTreeQuery q) {
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
