package tree.lca;

import _commons.TreeNode;

/**
 * 236
 */
public class LowestCommonAncestorOfABinaryTree {
    public static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == p || root == q || root == null) return root;

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left == null) return right;
            else if (right == null) return left;
            else return root;
        }
    }
}