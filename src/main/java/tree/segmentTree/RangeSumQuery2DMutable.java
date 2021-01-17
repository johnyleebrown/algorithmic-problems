package tree.segmentTree;

/**
 * 308
 *
 * ======
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper
 * left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) =
 * (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * ======
 *
 * https://leetcode.com/problems/range-sum-query-2d-mutable/
 */
public class RangeSumQuery2DMutable {
	/**
	 * A segment tree for each row. Not efficient.
	 */
	public static class Solution1 {
		public static class NumMatrix {

			private final DynamicSegmentTree[] dstar;
			private final int[][] m;

			public NumMatrix(int[][] a) {
				m = a;
				dstar = new DynamicSegmentTree[a.length];
				for (int i = 0; i < dstar.length; i++) {
					dstar[i] = new DynamicSegmentTree(a[i], a[0].length);
				}
			}

			public void update(int row, int col, int val) {
				dstar[row].update(col, col, val - m[row][col]);
				m[row][col] = val;
			}

			public int sumRegion(int row1, int col1, int row2, int col2) {
				int res = 0;
				for (int i = row1; i <= row2; i++) {
					res += dstar[i].sum(col1, col2);
				}
				return res;
			}

			// implicit segment tree
			private class DynamicSegmentTree {

				private final Node root;

				public DynamicSegmentTree(int[] a, int n) {
					root = new Node(0, n);
					for (int i = 0; i < a.length; i++) {
						increment(root, i, i, a[i]);
					}
				}

				public void update(int l, int r, int val) {
					increment(root, l, r, val);
				}

				private void increment(Node node, int l, int r, int val) {

					// not intersects
					if (notIntersects(node, l, r)) {
						return;
					}

					// covers
					if (fullyCovers(node, l, r)) {
						node.delta += val;
						return;
					}

					// create children
					int mid = node.lo + (node.hi - node.lo) / 2;
					if (node.left == null) node.left = new Node(node.lo, mid);
					if (node.right == null) node.right = new Node(mid + 1, node.hi);

					//
					propagate(node);

					// recurse
					increment(node.left, l, r, val);
					increment(node.right, l, r, val);

					//
					update(node);
				}

				public int sum(int l, int r) {
					return sum(root, l, r);
				}

				private int sum(Node node, int l, int r) {

					// not intersects
					if (notIntersects(node, l, r)) {
						return 0;
					}

					// covers
					if (fullyCovers(node, l, r)) {
						return node.delta + node.val;
					}

					// create children
					int mid = node.lo + (node.hi - node.lo) / 2;
					if (node.left == null) node.left = new Node(node.lo, mid);
					if (node.right == null) node.right = new Node(mid + 1, node.hi);

					//
					propagate(node);

					//
					int left = sum(node.left, l, r);
					int right = sum(node.right, l, r);

					//
					update(node);

					return left + right;
				}

				// ====== ====== ====== ====== ====== ======

				private boolean notIntersects(Node node, int l, int r) {
					return node.lo > r || node.hi < l;
				}

				private boolean fullyCovers(Node node, int l, int r) {
					return l <= node.lo && node.hi <= r;
				}

				// prop the delta
				private void propagate(Node node) {
					node.left.delta += node.delta;
					node.right.delta += node.delta;
					node.delta = 0;
				}

				// sum
				private void update(Node node) {
					node.val = node.left.val + node.left.delta + node.right.val + node.right.delta;
				}

				private class Node {
					Node left, right;
					int val, delta, lo, hi;

					Node(int lo, int hi) {
						this.lo = lo;
						this.hi = hi;
					}
				}
			}
		}
	}
}