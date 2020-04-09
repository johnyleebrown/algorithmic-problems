package tree.bst;

import util.ds.TreeNode;

/**
 * 783
 */
public class MinimumDistanceBetweenBSTNodes {
	/**
	 * Time complexity: O(n) Space complexity: O(1) + O(n)
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
