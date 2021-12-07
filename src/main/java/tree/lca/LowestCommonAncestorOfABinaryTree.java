package tree.lca;

import commons.TreeNode;

/**
 * 236
 */
public class LowestCommonAncestorOfABinaryTree {

  public static class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if (root == null) return null;
      TreeNode left = lowestCommonAncestor(root.left, p, q);
      TreeNode right = lowestCommonAncestor(root.right, p, q);
      if (root == p || root == q) return root;
      if (left != null && right != null) return root;
      if (left == null) return right;
      else return left;
    }
  }
}