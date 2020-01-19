package tree.modify;

import util.ds.TreeNode;

/**
 * 814
 */
public class BinaryTreePruning
{
	public TreeNode pruneTree(TreeNode root)
	{
		if (root == null) return null;
		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);
		if (root.left == null && root.right == null && root.val == 0) return null;
		return root;
	}
}

