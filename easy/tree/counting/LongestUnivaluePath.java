package Easy.Tree;

import util.TreeNode;

/**
 * 687
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 */
public class LongestUnivaluePath
{
    class Solution1
    {
        int res;

        public int longestUnivaluePath(TreeNode root)
        {
            res = 0;
            dfs(root);
            return res;
        }

        public int dfs(TreeNode node)
        {
            if (node == null) return 0;
            int left = dfs(node.left), right = dfs(node.right);
            int arrowLeft = 1, arrowRight = 1;
            if (node.left != null && node.left.val == node.val) arrowLeft += left;
            if (node.right != null && node.right.val == node.val) arrowRight += right;
            res = Math.max(res, arrowLeft + arrowRight - 1);
            return Math.max(arrowLeft, arrowRight);
        }
    }

    class Solution2
    {
        private int pathLength;

        public int longestUnivaluePath(TreeNode root)
        {
            pathLength = 0;
            if (root == null) return 0;
            calculatePathLength(root, root.val, 0);
            return pathLength;
        }

        private int calculatePathLength(final TreeNode root, final int prevNodeVal, int currentLength)
        {
            // check if it is not null
            if (root == null) return 0;

            // check if it has the same node value as the prev ones
            // if not we reset the counter
            if (root.val == prevNodeVal) currentLength++;
            else currentLength = 0;

            // calculate length for left and right leaves
            int left = calculatePathLength(root.left, root.val, currentLength);
            int right = calculatePathLength(root.right, root.val, currentLength);

            // calc max
            pathLength = Math.max(pathLength, left + right);

            // if we came from the same valued node then we want to return the longest leaf + route to itself
            if (root.val == prevNodeVal) return Math.max(left, right) + 1;

            // we return zero otherwise
            return 0;
        }
    }
}
