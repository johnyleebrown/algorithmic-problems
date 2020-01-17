package tree;

import util.TreeNode;

/**
 * 572
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values
 * with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants.
 * The tree s could also be considered as a subtree of itself.
 */
public class SubtreeOfAnotherTree {

    // O(m*n)
    // O(n)
    public class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == null) return false;
            if (isSame(s, t)) return true;
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }

        private boolean isSame(TreeNode s, TreeNode t) {
            if (s == null && t == null) return true;
            if ((s == null || t == null) || (s.val != t.val)) return false;
            return isSame(s.left, t.left) && isSame(s.right, t.right);
        }
    }
}
