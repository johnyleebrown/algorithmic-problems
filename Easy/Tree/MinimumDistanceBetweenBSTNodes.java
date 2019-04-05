package Easy.Tree;

import util.TreeNode;

/**
 * 783
 * Given a Binary Search Tree (BST) with the root node root,
 * return the minimum difference between the values of
 * any two different nodes in the tree.
 */
public class MinimumDistanceBetweenBSTNodes {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1) + O(n)
     */
    Integer prev, ans;

    public int minDiffInBST(TreeNode root) {
        prev = null;
        ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }

    // bottom up
    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null)
            ans = Math.min(ans, node.val - prev);
        prev = node.val;
        dfs(node.right);
    }
}
