package tree.modify;

import util.ds.TreeNode;

/**
 * 156
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node
 * that shares the same parent node) or empty, flip it upside down and turn it into a tree where the
 * original right nodes turned into left leaf nodes. Return the new root.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BinaryTreeUpsideDown {
	/**
	 * Get clear understanding of the description first:
	 * - we just want the most left node because at his parent we will transfer the children to
	 * him, so if we have a subtree that is not the most left node and it is deeper, - it doesnt
	 * count.
	 * - on exit from recursion re-assign children of nodes
	 * - clear old children too
	 */
	public static class Solution {
		public TreeNode upsideDownBinaryTree(TreeNode root) {
			if (root == null) return null;
			if (root.left == null && root.right == null) {
				return root;
			}
			TreeNode res = upsideDownBinaryTree(root.left);
			root.left.left = root.right;
			root.left.right = root;
			root.left = null;
			root.right = null;
			return res;
		}
	}
}