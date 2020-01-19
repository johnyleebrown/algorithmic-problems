package tree.modify;

import util.ds.TreeNode;

/**
 * 701
 */
class InsertIntoABinarySearchTree
{
	class Solution
	{
		public TreeNode insertIntoBST(TreeNode root, int val)
		{
			if (root == null)
			{
				return new TreeNode(val);
			}

			if (root.val > val)
			{
				root.left = insertIntoBST(root.left, val);
			}
			else
			{
				root.right = insertIntoBST(root.right, val);
			}

			return root;
		}
	}
}
