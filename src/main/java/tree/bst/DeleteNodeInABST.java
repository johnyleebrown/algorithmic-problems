package tree.bst;

import _commons.TreeNode;

/**
 * 450
 */
public class DeleteNodeInABST {
	// stand binary tree deletion
	class Solution {
		public TreeNode deleteNode(TreeNode root, int key) {
			// bst = unique
			// we v found a node
			// check left and find max, check right and find min
			if (root == null) return null;
			if (key < root.val) root.left = deleteNode(root.left, key);
			else if (key > root.val) root.right = deleteNode(root.right, key);
			else {
				if (root.right == null) return root.left;
				if (root.left == null) return root.right;

				TreeNode min = findMin(root.right);
				min.right = removeMin(root.right);
				min.left = root.left;
				root = min;
			}

			return root;
		}

		private TreeNode findMin(TreeNode root) {
			if (root.left == null) return root;
			return findMin(root.left);
		}

		private TreeNode removeMin(TreeNode root) {
			if (root.left == null) return root.right;
			root.left = removeMin(root.left);
			return root;
		}
	}
}