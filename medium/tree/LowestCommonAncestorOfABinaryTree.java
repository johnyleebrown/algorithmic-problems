package Medium.Tree;

import util.TreeNode;

// 236
public class LowestCommonAncestorOfABinaryTree 
{
    /*
	 * firstly, if p and q are in the same subtree, we need to return the first that we meet
	 * secondly, if they are in the diff subtrees, then we will return the root
	 *
     * O(log n) / O(1)
     */
	class Solution {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
		{
			if (root == p || root == q || root == null) return root;

			TreeNode left = lowestCommonAncestor(root.left, p, q);
			TreeNode right = lowestCommonAncestor(root.right, p, q);

			if (left == null) return right;
			else if (right == null) return left;
			else return root;
		}
	}
}
