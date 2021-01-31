package tree.validate;

import commons.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101
 */
public class SymmetricTree {

	// Recursive
	// Total run time is O(n), where n is the total number of nodes in the tree
	// Space complexity: O(n)
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		return helper(root.left, root.right);
	}

	public boolean helper(TreeNode left, TreeNode right) {
		return (left == null && right == null)
				|| (left != null && right != null
				&& left.val == right.val
				&& helper(left.left, right.right)
				&& helper(left.right, right.left));
	}

	// Non-Recursive
	// Space complexity: O(n)
	// Time complexity : O(n)
	public boolean isSymmetricNonRecursive(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if (t1 == null && t2 == null) continue;
			if (t1 == null || t2 == null) return false;
			if (t1.val != t2.val) return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}

}
