package tree.compare;

import _commons.TreeNode;

/**
 * 100
 */
public class SameTree {
	public static class Solution {
		public boolean isSameTree(TreeNode p, TreeNode q) {
			return (p == null && q == null)
			       || (p != null && q != null && p.val == q.val
			           && isSameTree(p.left, q.left)
			           && isSameTree(p.right, q.right));
		}
	}
}