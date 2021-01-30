package tree.modify;

import _commons.TreeNode;

/**
 * 1080
 */
class InsufficientNodesInRootToLeafPaths
{
	class Solution
	{
		public TreeNode sufficientSubset(TreeNode root, int limit)
		{
			if (root == null)
			{
				return null;
			}

			if (root.left == null && root.right == null)
			{
				// if limit covers the value
				return root.val >= limit ? root : null;
			}

			int x = limit - root.val;
			root.left = sufficientSubset(root.left, x);
			root.right = sufficientSubset(root.right, x);

			return root.left == null && root.right == null ? null : root;
		}
	}
}
