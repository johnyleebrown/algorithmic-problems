package Easy.Tree;

import util.TreeNode;

/**
 * 112
 * Given a binary tree and a sum, determine if the tree
 * has a root-to-leaf path such that adding up all the
 * values along the path equals the given sum.
 */
public class PathSum {

    // Space complexity: O(1)
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.right == null && root.left == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
