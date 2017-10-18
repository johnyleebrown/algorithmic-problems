package Medium.Tree;

import Helpers.TreeNode;

/**
 * 98
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 */
public class ValidateBinarySearchTree {

    // O(n), O(n)
    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isBST(root, null, null);
        }

        private boolean isBST(TreeNode root, Integer min, Integer max) {
            if (root == null) return true;
            if (min != null && root.val <= min) return false;
            if (max != null && root.val >= max) return false;
            return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
        }
    }
}
