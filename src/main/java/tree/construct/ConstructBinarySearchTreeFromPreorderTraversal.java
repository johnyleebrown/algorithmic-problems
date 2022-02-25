package tree.construct;

import commons.TreeNode;

/**
 * 1008
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

	/**
	 * Send self and parent for left and right child references.
	 */
	public static class Solution {

		private int last;

		public TreeNode bstFromPreorder(int[] a) {
			return traverse(a, 0, Integer.MAX_VALUE);
		}

		private TreeNode traverse(int[] a, int i, int p) {
			TreeNode root = new TreeNode(a[i]);
			last = i;
			if (i + 1 < a.length && a[i + 1] < a[i]) {
				root.left = traverse(a, i + 1, a[i]);
			} else {
				root.left = null;
				last = i;
			}
			if (last + 1 < a.length && a[last + 1] < p) {
				root.right = traverse(a, last + 1, p);
			} else {
				root.right = null;
			}
			return root;
		}
	}
}