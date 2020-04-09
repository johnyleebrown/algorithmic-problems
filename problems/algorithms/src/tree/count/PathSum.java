package tree.count;

import util.ds.TreeNode;

/**
 * 112
 */
public class PathSum {

    // Space complexity: O(1)
	// top-down approach - sending parameter (curSum) down to each node
	class Solution {
		public boolean hasPathSum(TreeNode root, int sum) {
			if (root == null) return false;
			if (root.left == null && root.right == null && sum - root.val == 0) return true;
			return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
		}
	}
}
