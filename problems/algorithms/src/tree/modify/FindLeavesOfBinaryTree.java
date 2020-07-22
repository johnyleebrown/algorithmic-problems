package tree.modify;

import util.ds.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all
 * leaves, repeat until the tree is empty.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindLeavesOfBinaryTree {
	/**
	 * Count level from bottom-up to determine at which position we should insert specific node.
	 * To add lists to positions - send depth to recursion.
	 */
	public static class Solution1 {
		public List<List<Integer>> findLeaves(TreeNode root) {

			List<List<Integer>> ans = new ArrayList<>();
			if (root == null) {
				return ans;
			}

			int x = h(root, ans, 1);
			ans.get(x).add(root.val);

			return ans;
		}

		int h(TreeNode cur, List<List<Integer>> ans, int depth) {

			if (ans.size() < depth) {
				ans.add(new ArrayList<>());
			}

			if (cur.left == null && cur.right == null) {
				return 0;
			}

			int l = 0;
			if (cur.left != null) {
				l = h(cur.left, ans, depth + 1);
				ans.get(l).add(cur.left.val);
			}

			int r = 0;
			if (cur.right != null) {
				r = h(cur.right, ans, depth + 1);
				ans.get(r).add(cur.right.val);
			}

			return Math.max(l, r) + 1;
		}
	}
}