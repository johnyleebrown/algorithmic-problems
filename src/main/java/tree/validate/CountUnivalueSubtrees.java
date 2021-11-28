package tree.validate;

import commons.TreeNode;

/**
 * 250
 */
public class CountUnivalueSubtrees {

  /**
   * Increase count each time current is same as children values and subtrees are also
   * ok.
   */
  public static class Solution {

    int ans = 0;

    public int countUnivalSubtrees(TreeNode root) {
      helper(root);
      return ans;
    }

    private boolean helper(TreeNode root) {
      if (root == null) return true;

      boolean left = helper(root.left);
      boolean right = helper(root.right);
      boolean children_ok = left && right;

      int left_val = root.left == null ? root.val : root.left.val;
      int right_val = root.right == null ? root.val : root.right.val;
      boolean cur_ok = root.val == left_val && root.val == right_val;

      boolean ok = children_ok && cur_ok;
      ans += (ok ? 1 : 0);

      return ok;
    }
  }
}
