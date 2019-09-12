package medium.tree;

import util.TreeNode;

// 236
public class LowestCommonAncestorOfABinaryTree 
{
	class Solution 
	{
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
		{
			if (root == p || root == q || root == null) return root;

			TreeNode left = lowestCommonAncestor(root.left, p, q);
			TreeNode right = lowestCommonAncestor(root.right, p, q);

			if (left == null && right == null) return root;
			else if (left == null) return right;
			else return left;
		}
	}
}

