package tree.modify;

import _commons.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226
 */
public class InvertBinaryTree {
	class Solution {
		public TreeNode invertTree(TreeNode root) {
			if (root == null) return null;
			TreeNode x = root.left;
			root.left = invertTree(root.right);
			root.right = invertTree(x);
			return root;
		}
	}

	public class Solution2 {
		public TreeNode invertTree(TreeNode root) {

			if (root == null) {
				return null;
			}

			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);

			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				TreeNode left = node.left;
				node.left = node.right;
				node.right = left;

				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			return root;
		}
	}
}
