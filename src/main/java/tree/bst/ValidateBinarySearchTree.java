package tree.bst;

import _commons.TreeNode;

/**
 * 98
 */
public class ValidateBinarySearchTree
{
	private static class Solution
	{
		public boolean isValidBST(TreeNode cur)
		{
			return f(cur, null, null);
		}

		private boolean f(TreeNode cur, Integer min, Integer max)
		{
			if (cur == null)
				return true;
			if (max != null && cur.val >= max || min != null && cur.val <= min)
				return false;
			return f(cur.left, min, cur.val) && f(cur.right, cur.val, max);
		}
	}
}