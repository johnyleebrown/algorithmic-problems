package tree.construct;

import util.ds.TreeNode;

/**
 * 1008
 *
 * ======
 *
 * Task.
 *
 * Return the root node of a binary search tree that matches the given preorder
 * traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any
 * descendant of node.left has a value < node.val, and any descendant of
 * node.right has a value > node.val.  Also recall that a preorder traversal
 * displays the value of the node first, then traverses node.left, then
 * traverses node.right.)
 *
 * ======
 *
 * Source: Leetcode
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    /**
     * Send self and parent for left and right child references.
     */
    public static class Solution {
        private int last;

        public TreeNode bstFromPreorder(int[] a) {
            return traverse(a, 0, Integer.MAX_VALUE);
        }

        private TreeNode traverse(int[] a, int i, int p) {
            TreeNode root = new TreeNode(a[i]);
            last = i;
            if (i + 1 < a.length && a[i + 1] < a[i]) {
                root.left = traverse(a, i + 1, a[i]);
            } else {
                root.left = null;
                last = i;
            }
            if (last + 1 < a.length && a[last + 1] < p) {
                root.right = traverse(a, last + 1, p);
            } else {
                root.right = null;
            }
            return root;
        }
    }
}