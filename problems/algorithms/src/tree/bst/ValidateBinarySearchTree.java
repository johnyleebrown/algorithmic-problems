package tree.bst;

import util.ds.TreeNode;

/**
 * 98
 */
public class ValidateBinarySearchTree
{
	class Solution
	{
		public boolean isValidBST(TreeNode root)
		{
			return f(root, null, null);
		}

		private boolean f(TreeNode root, Integer min, Integer max)
		{
			if (root == null) return true;
			if ((min != null && root.val <= min) || (max != null && root.val >= max))
				return false;
			return f(root.left, min, root.val) && f(root.right, root.val, max);
		}
	}
}