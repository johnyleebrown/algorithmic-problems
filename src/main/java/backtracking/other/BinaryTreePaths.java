package backtracking.other;

import _commons.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257
 */
public class BinaryTreePaths {
	class Solution {
		private List<String> ans = new ArrayList<>();

		public List<String> binaryTreePaths(TreeNode root) {
			if (root == null) {
				return ans;
			}

			generateP(root, String.valueOf(root.val));
			return ans;
		}

		private void generateP(TreeNode root, String cur) {
			if (root.left == null && root.right == null) {
				ans.add(cur);
			} else {
				if (root.left != null) {
					generateP(root.left, cur + "->" + root.left.val);
				}
				if (root.right != null) {
					generateP(root.right, cur + "->" + root.right.val);
				}
			}
		}
	}
}

