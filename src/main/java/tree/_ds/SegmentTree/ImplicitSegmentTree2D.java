package tree._ds.SegmentTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2D Implicit Segment Tree
 * Is for storing information about a matrix
 * Each node represents an interval on y-axis, contains a segment tree that represents intervals
 * on x-axis.
 * It means that in 2DST interval [0,0] will correspond to matrix[i] row represented as
 * regular segment tree.
 *
 * TODO
 */
public class ImplicitSegmentTree2D {

	private final AggregateFunction af;
	private final Node root;
	private final int cols;

	public ImplicitSegmentTree2D(AggregateFunction aggregateFunction, int[][] matrix) {
		af = aggregateFunction;
		int n = matrix.length;
		cols = matrix[0].length;
		root = new Node(new ImplicitSegmentTree(cols - 1, aggregateFunction), 0, n - 1);
		for (int i = 0; i < n; i++) {
			increment(root, i, i, matrix[i]);
		}
	}

	/**************************************************************************/

	public static void main(String[] args) {
		int[][] m = new int[][]{{1, 2, 3}, {4, 5, 6}};
		ImplicitSegmentTree2D implicitSegmentTree2D =
		new ImplicitSegmentTree2D(AggregateFunction.SUM, m);
		implicitSegmentTree2D.print();
	}

	/**************************************************************************/

	public void increment(int x1, int y1, int x2, int y2, int val) {
		// TODO
	}

	/**
	 * Wrapped in ifs so that update function would work only once, when both subtrees are ready
	 * Because all node will have either 2 children or none in any segment tree
	 */
	private void increment(Node node, int il, int ir, int[] a) {

		if (notIntersects(node, il, ir)) {
			return;
		}

		if (covers(node, il, ir)) {
			// fill the segment tree in the node
			// cur.delta += val
			node.tree.fillFromAr(a);
			return;
		}

		// left, right
		int mid = node.lo + (node.hi - node.lo) / 2;
		if (intersects(node.lo, mid, il, ir)) {
			if (node.left == null)
				node.left = new Node(new ImplicitSegmentTree(node.tree.getN(), af), node.lo, mid);

			increment(node.left, il, ir, a);
		}
		if (intersects(mid + 1, node.hi, il, ir)) {
			if (node.right == null)
				node.right = new Node(new ImplicitSegmentTree(node.tree.getN(), af), mid + 1, node.hi);

			increment(node.right, il, ir, a);
		}

		if (node.left != null && node.right != null) {
			update(node);
		}
	}

	/**************************************************************************/

	/**************************************************************************/

	public int sum(int x1, int y1, int x2, int y2) {
		return 0;
	}

	/**************************************************************************/

	private void update(Node node) {
		if (af == AggregateFunction.SUM) {
			// sum all the shit in children
			updateTheNodeTree(node);
		}
	}

	private void updateTheNodeTree(Node node) {
		for (int i = 0; i < cols; i++) {
			int sum = node.left.tree.sum(i, i) + node.right.tree.sum(i, i);
			node.tree.increment(i, i, sum);
		}
	}

	private boolean notIntersects(Node node, int l, int r) {
		return r < node.lo || l > node.hi;
	}

	private boolean notIntersects(int l1, int r1, int l2, int r2) {
		return l2 > r1 || r2 < l1;
	}

	private boolean intersects(int l1, int r1, int l2, int r2) {
		return !(notIntersects(l1, r1, l2, r2));
	}

	private boolean covers(Node node, int l, int r) {
		return l <= node.lo && node.hi <= r;
	}

	public Node getRoot() {
		return root;
	}

	private void print() {

		Deque<Node> q = new ArrayDeque<>();
		q.add(root);
		int level = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			System.out.println("=== Level " + level);
			while (--size >= 0) {
				Node cur = q.removeFirst();
				System.out.println(cur.lo + ", " + cur.hi);
				cur.tree.print();
				if (cur.left != null) q.add(cur.left);
				if (cur.right != null) q.add(cur.right);
			}

			level++;
		}

		System.out.println("=======");
	}

	public static class Node {
		// left and right boundaries of the interval
		int lo, hi;
		// containing tree
		ImplicitSegmentTree tree;
		// left and right trees
		Node left, right;

		public Node(ImplicitSegmentTree segmentTree, int lo, int hi) {
			tree = segmentTree;
			this.lo = lo;
			this.hi = hi;
		}
	}
}
