package tree.count;

import commons.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 671
 */
public class SecondMinimumNodeInABinaryTree {
	// O(n), O(n)
	class Solution {
		public int findSecondMinimumValue(TreeNode root) {
			if (root == null) return -1;
			if (root.left == null && root.right == null) return -1;

			int left = root.left.val;
			int right = root.right.val;
			if (left == root.val) left = findSecondMinimumValue(root.left);
			if (right == root.val) right = findSecondMinimumValue(root.right);

			return (left == -1 || right == -1) ? Math.max(left, right) : Math.min(left, right);
		}
	}

	// DFS
	// O(n), O(n)
	class Solution2 {
		public void dfs(TreeNode root, Set<Integer> uniques) {
			if (root != null) {
				uniques.add(root.val);
				dfs(root.left, uniques);
				dfs(root.right, uniques);
			}
		}

		public int findSecondMinimumValue(TreeNode root) {
			Set<Integer> uniques = new HashSet<Integer>();
			dfs(root, uniques);
			int min1 = root.val;
			long ans = Long.MAX_VALUE;
			for (int v : uniques) if (min1 < v && v < ans) ans = v;
			return ans < Long.MAX_VALUE ? (int) ans : -1;
		}
	}

	// Ad-Hoc
	// O(n), O(n)
	class Solution3 {
		int min1;
		long ans = Long.MAX_VALUE;

		public void dfs(TreeNode root) {
			if (root != null) {
				if (min1 < root.val && root.val < ans) ans = root.val;
				else if (min1 == root.val) {
					dfs(root.left);
					dfs(root.right);
				}
			}
		}

		public int findSecondMinimumValue(TreeNode root) {
			min1 = root.val;
			dfs(root);
			return ans < Long.MAX_VALUE ? (int) ans : -1;
		}
	}
}
