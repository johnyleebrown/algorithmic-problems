package tree.modify;

import util.ds.TreeNode;

/**
 * 1325
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 *
 * Note that once you delete a leaf node with value target, if it's parent node becomes a leaf node and has the value
 * target, it should also be deleted (you need to continue doing that until you can't).
 */
public class DeleteLeavesWithAGivenValue
{
	/**
	 * Post-order traversal.
	 */
	class Solution
	{
		public TreeNode removeLeafNodes(TreeNode root, int target)
		{
			if (root == null)
			{
				return null;
			}

			root.left = removeLeafNodes(root.left, target);
			root.right = removeLeafNodes(root.right, target);

			if (root.left == null && root.right == null && root.val == target)
			{
				return null;
			}

			return root;
		}
	}

	/**
	 * Pre-order traversal.
	 * Count how many needs to be removed.
	 */
	class Solution2
	{
		Integer leftOver = null;

		public TreeNode removeLeafNodes(TreeNode root, int target)
		{
			while (leftOver == null || leftOver != 0)
			{
				leftOver = 0;
				root = removeLeafNodesHelper(root, target);
			}

			return root;
		}

		public TreeNode removeLeafNodesHelper(TreeNode root, int target)
		{
			if (root == null)
			{
				return null;
			}

			if (root.left == null && root.right == null && root.val == target)
			{
				leftOver++;
				return null;
			}

			root.left = removeLeafNodesHelper(root.left, target);
			root.right = removeLeafNodesHelper(root.right, target);

			return root;
		}
	}
}
