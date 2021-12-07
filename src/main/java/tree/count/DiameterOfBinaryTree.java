package tree.count;

import commons.TreeNode;

/**
 * 543
 */
public class DiameterOfBinaryTree {

  /**
   * max could be not at root but also at some other node
   */
  public static class Solution {

    int ans = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
      helper(root);
      return ans;
    }

    private int helper(TreeNode root) {
      if (root == null) return 0;
      int left = helper(root.left);
      int right = helper(root.right);
      ans = Math.max(left + right, ans);
      return Math.max(left, right) + 1;
    }
  }
}