package tree.validate;

import commons.TreeNode;

/**
 * 965
 */
public class UnivaluedBinaryTree {

  /**
   * SF
   */
  public static class Solution {

    public boolean isUnivalTree(TreeNode root) {
      return helper(root.val, root);
    }

    private boolean helper(int val, TreeNode root) {
      if (root == null) return true;
      return root.val == val && helper(val, root.left) && helper(val, root.right);
    }
  }
}
