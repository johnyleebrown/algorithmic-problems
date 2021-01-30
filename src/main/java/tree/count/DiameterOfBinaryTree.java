package tree.count;

import _commons.TreeNode;

/**
 * 543
 */
public class DiameterOfBinaryTree {
	/**
	 * max could be not at root but also at some other node
	 */
	class Solution {
		int max = Integer.MIN_VALUE;

		public int diameterOfBinaryTree(TreeNode root) {
			if (root == null) return 0;
			int x = h(root);
			return max;
		}

		int h(TreeNode root) {
			if (root == null) return 0;
			int l = h(root.left);
			int r = h(root.right);
			max = Math.max(max, l + r);
			return Math.max(l, r) + 1;
		}
	}
}
