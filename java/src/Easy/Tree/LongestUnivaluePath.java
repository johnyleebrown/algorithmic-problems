package Easy.Tree;

import Helpers.TreeNode;

/**
 * 687
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 */
public class LongestUnivaluePath {
    class Solution {
        int res;

        public int longestUnivaluePath(TreeNode root) {
            res = 0;
            dfs(root);
            return res;
        }

        public int dfs(TreeNode node) {
            if (node == null) return 0;
            int left = dfs(node.left), right = dfs(node.right);
            int arrowLeft = 1, arrowRight = 1;
            if (node.left != null && node.left.val == node.val) arrowLeft += left;
            if (node.right != null && node.right.val == node.val) arrowRight += right;
            res = Math.max(res, arrowLeft + arrowRight - 1);
            return Math.max(arrowLeft, arrowRight);
        }
    }
}
