package tree.count.values;

import commons.TreeNode;

/**
 * 124
 *
 * ======
 *
 * Task.
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections. The
 * path must contain at least one node and does not need to go through the
 * root.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BinaryTreeMaximumPathSum {
    public static class Solution {
        int ans;

        public int maxPathSum(TreeNode root) {
            if (root == null) return 0;
            ans = Integer.MIN_VALUE;
            helper(root);
            return ans;
        }

        private int helper(TreeNode cur) {
            if (cur == null) return 0;

            int l = helper(cur.left);
            int r = helper(cur.right);

            // for case when l or r is < 0 or when both
            ans = Math.max(ans, Math.max(l + r + cur.val, Math.max(Math.max(l, r) + cur.val, cur.val)));

            // we can't use l+r+cur globally
            return Math.max(Math.max(l, r) + cur.val, cur.val);
        }
    }
}