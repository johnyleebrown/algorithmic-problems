package tree.count;

import util.ds.TreeNode;

/**
 * 298
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree, find the length of the longest consecutive sequence
 * path.
 *
 * The path refers to any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The longest consecutive path
 * need to be from parent to child (cannot be the reverse).
 *
 * ======
 *
 * Source: Leetcode
 */
public class BinaryTreeLongestConsecutiveSequence {
    /**
     * Consecutive means child.val == parent.val + 1.
     */
    public static class Solution {
        private int max = 0;

        public int longestConsecutive(TreeNode root) {
            dfs(root, 1);
            return max;
        }

        private void dfs(TreeNode cur, int x) {
            if (cur == null) {
                return;
            }
            max = Math.max(max, x);
            if (cur.left != null) {
                dfs(cur.left, cur.left.val == cur.val + 1 ? x + 1 : 1);
            }
            if (cur.right != null) {
                dfs(cur.right, cur.right.val == cur.val + 1 ? x + 1 : 1);
            }
        }
    }
}