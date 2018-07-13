package Medium.Tree;

import java.util.Stack;

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

    /**
     * Iterative solution, for similar algorithm:
     * @see KthSmallestElementInABST.Solution4
     * @see BinaryTreeInorderTraversal
     */
    class Solution2 {
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (pre != null && root.val <= pre.val) return false;
                pre = root;
                root = root.right;
            }
            return true;
        }
    }
}
