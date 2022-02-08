package tree.bst;

import commons.TreeNode;

/**
 * 98
 */
public class ValidateBinarySearchTree {

	/**
	 * Top down
	 */
	private static class Solution {

		public boolean isValidBST(TreeNode cur) {
			return dfs(cur, null, null);
		}

		private boolean dfs(TreeNode cur, Integer min, Integer max) {
			if (cur == null) return true;
			if (max != null && cur.val >= max || min != null && cur.val <= min) return false;
			return dfs(cur.left, min, cur.val) && dfs(cur.right, cur.val, max);
		}
	}

	/**
	 * Bottom up
	 */
	public static class Solution2 {

		public boolean isValidBST(TreeNode root) {
			Long[] ans = isValidBSTHelper(root);
			return ans[0] != null;
		}

		Long[] isValidBSTHelper(TreeNode root) {
			if (root == null) {
				return new Long[]{Long.MAX_VALUE, Long.MIN_VALUE};
			}
			Long[] left = isValidBSTHelper(root.left);
			Long[] right = isValidBSTHelper(root.right);
			// in subtree is not valid
			if (left[0] == null || right[0] == null) {
				return new Long[]{null, null};
			}
			// root is not valid
			if (root.val <= left[1] || root.val >= right[0]) {
				return new Long[]{null, null};
			}
			return new Long[]{Math.min(left[0], root.val),
					Math.max(right[1], root.val)};
		}
	}
}