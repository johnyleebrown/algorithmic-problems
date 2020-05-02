package backtracking.tree;

import util.ds.TreeNode;

/**
 * 1430
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree where each path going from the root to any leaf form a
 * valid sequence, check if a given string is a valid sequence in such binary
 * tree.
 *
 * We get the given string from the concatenation of an array of integers arr
 * and the concatenation of all values of the nodes along a path results in a
 * sequence in the given binary tree.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {
    /**
     * Backtrack if arr is a path from root to leaf.
     */
    public static class Solution {
        public boolean isValidSequence(TreeNode root, int[] arr) {
            return gen(root, arr, 0);
        }

        private boolean gen(TreeNode cur, int[] a, int i) {
            if (cur == null || i >= a.length || cur.val != a[i]) {
                return false;
            }
            if (cur.left == null && cur.right == null && i == a.length - 1) {
                return true;
            }
            if (gen(cur.left, a, i + 1) || gen(cur.right, a, i + 1)) {
                return true;
            }
            return false;
        }
    }
}