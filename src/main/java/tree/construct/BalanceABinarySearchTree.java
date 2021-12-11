package tree.construct;

import commons.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382
 */
public class BalanceABinarySearchTree {

  /**
   * Collect values in inorder and then construct like you would construct a balanced
   * tree.
   */
  public static class Solution {

    List<Integer> values = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
      collectValues(root);
      return constructBST(0, values.size() - 1);
    }

    private TreeNode constructBST(int l, int r) {
      if (l > r) return null;
      int ind = l + (r - l) / 2;
      int val = values.get(ind);
      TreeNode newRoot = new TreeNode(val);
      newRoot.left = constructBST(l, ind - 1);
      newRoot.right = constructBST(ind + 1, r);
      return newRoot;
    }

    private void collectValues(TreeNode root) {
      if (root == null) return;
      collectValues(root.left);
      values.add(root.val);
      collectValues(root.right);
    }
  }
}
