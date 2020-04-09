package tree.count;

import util.ds.TreeNode;

/**
 * 543
 */
public class DiameterOfBinaryTree {
	class Solution {
		int result = 0;

		public int diameterOfBinaryTree(TreeNode root) {
			getHeight(root);
			return result;
		}

		private int getHeight(TreeNode root) {
			if (root == null) return 0;
			int left = getHeight(root.left);
			int right = getHeight(root.right);
			result = Math.max(result, left + right);
			return Math.max(left, right) + 1;
		}
	}
}
