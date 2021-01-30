package tree.traverse.reverse;

import _commons.TreeNode;

/**
 * 114
 */
public class FlattenBinaryTreeToLinkedList {
    class Solution {
        private TreeNode prev = null;

        public void flatten(TreeNode root) {
            if (root == null) return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }
    }
}

