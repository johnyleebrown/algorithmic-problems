package tree._ds.SegmentTree;

import java.util.*;

import static util.tester.Assert.assertEquals;

/**
 * ImplicitSegmentTree
 *
 * Similar to static segment tree, we create nodes only when required, that’s
 * why it is also known as dynamic segment tree.
 */
public class ImplicitSegmentTree implements SegmentTreeQuery {

	Node root;
	int max;

	private class Node {
		int lo;
		int hi;

		int delta;
		int min;

		Node left;
		Node right;

		private Node(int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
		}
	}

	public ImplicitSegmentTree(int n) {
		root = new Node(0, n);
		max = n;
	}

	public void increment(int a, int b, int val) {
		increment(root, a, b, val);
	}

	public void increment(Node root, int a, int b, int val) {
		if (root == null || noIntersection(root, a, b)) {
			return;
		}

		if (covers(root, a, b)) {
			root.delta += val;
			return;
		}

		int mid = (root.hi + root.lo) / 2;

		// make sure that [a,b] intersects with the half
		if (!noInter(root.lo, mid, a, b)) {
			if (root.left == null) root.left = new Node(root.lo, mid);
			root.left.delta += root.delta; // delta leftover
			increment(root.left, a, b, val);
		}

		// make sure that [a,b] intersects with the half
		if (!noInter(mid + 1, root.hi, a, b)) {
			if (root.right == null) root.right = new Node(mid + 1, root.hi);
			root.right.delta += root.delta; // delta leftover
			increment(root.right, a, b, val);
		}

		root.delta = 0;

		// after children have been updated - we can use up-to-date data from them
		update(root);
	}

	private boolean noInter(int lo, int hi, int a, int b) {
		return a > hi || b < lo;
	}

	/**
	 * TODO root - curNode
	 */
	private void update(Node root) {
		int m = Integer.MAX_VALUE;
		int l = root.left == null ? m : root.left.min + root.left.delta;
		int r = root.right == null ? m : root.right.min + root.right.delta;
		root.min = Math.min(l, r);
	}

	private boolean noIntersection(Node root, int a, int b) {
		return a > root.hi || b < root.lo;
	}

	private boolean covers(Node root, int a, int b) {
		return a <= root.lo && b >= root.hi;
	}

	public int min(int a, int b) {
		return min(root, a, b);
	}

	private int min(Node root, int a, int b) {
		if (root == null || noIntersection(root, a, b)) {
			return Integer.MAX_VALUE;
		}

		if (covers(root, a, b)) {
			return root.delta + root.min;
		}

		prop(root);

		int left = min(root.left, a, b);
		int right = min(root.right, a, b);

		update(root);

		return Math.min(left, right);
	}

	private void prop(Node root) {
		if (root.left != null) {
			root.left.delta += root.delta;
		}
		if (root.right != null) {
			root.right.delta += root.delta;
		}
		root.delta = 0;
	}

	public void print() {
		print(0, max);
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

				if (cur.left != null)
					q.addLast(cur.left);
				if (cur.right != null)
					q.addLast(cur.right);
			}
			System.out.println();
		}
	}

	public static void main(String[] a) {
		int n = 50;

		SegmentTreeSlow rs = new SegmentTreeSlow(n);
		initTest(rs);
		//rs.print();

		ImplicitSegmentTree st = new ImplicitSegmentTree(n);
		//st.increment(10,20,10);
		//st.print();
		initTest(st);

		//runTest(3, 49, rs, st);
		//runTest(16, 41, rs, st);

		runSmallTest(n, rs, st);
	}

	private static void runSmallTest(int n, SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
		Random r = new Random();
		String testType = "Random choices test.";
		String step = "step";
		int st = n/2;
		System.out.println(testType);
		while (--st >= 0) {
			System.out.println(step + " " + (n/2-st));
			List<String> ret = new LinkedList<>();
			boolean allOk = true;
			for (int i = 0; i < n; i++) {
				int a = r.nextInt(n);
				int b = r.nextInt(n);
				while (b < a) b = r.nextInt(n);
				ret.add("> [" + a + " " + b + "]");

				int minSlow = stSlow.min(a, b);
				int minRegular = stRegular.min(a, b);
				boolean ans = assertEquals(minSlow, minRegular, false);
				if (ans) {
					ret.add("[++++] " + minSlow + " == " + minRegular);
				} else {
					ret.add("[FAIL] " + minSlow + " != " + minRegular);
				}
				if (!ans) {
					allOk = false;
				}
			}
			if (allOk) {
				System.out.println("[ == ACCEPTED == ]");
			} else {
				for (String s: ret) {
					System.out.println(s);
				}
			}
		}
	}

	private static void runTest(int a, int b,
			SegmentTreeQuery stSlow, SegmentTreeQuery stRegular) {
		//		System.out.println("a,b: [" + a + " " + b + "]");
		int minSlow = stSlow.min(a, b);
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

