package tree.traverse.other;

import _commons.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * BinaryTreeConstantSpaceTraversal
 *
 * ======
 *
 * Task.
 *
 * Given a TreeNode defined with an additional parent pointer, print all nodes of a given tree in
 * any order using constant space. You are not allowed to modify the tree structure itself.
 *
 * https://leetcode.com/discuss/interview-question/514834/Databricks-or-Phone-or-Iterating-Over-a-Binary-Tree-With-Constant-Space
 *
 * ======
 *
 * Source: Leetcode
 * Company: DataBricks
 */
public class BinaryTreeConstantSpaceTraversal {
	/**
	 * Try to move right every time, first move to parent with the right child that is not null
	 * and check if this right child is not the current one.
	 */
	public static class Solution {
		// for testing
		Map<Integer, Integer> debugMap;
		int countOfValues;

		public Solution() {
			this.debugMap = new HashMap<>();
		}

		public void traverse(TreeNode root) {
			while (root != null) {
				System.out.println(root.val);

				// for testing
				debugMap.put(root.val, debugMap.getOrDefault(root.val, 0) + 1);
				countOfValues++;
				// for testing

				if (root.left != null) {
					root = root.left;
				} else if (root.right != null) {
					root = root.right;
				} else {
					while (root.parent != null && (root.parent.right == null || root.parent.right == root)) {
						root = root.parent;
					}

					if (root.parent == null) {
						return;
					}

					root = root.parent.right;
				}
			}
		}
	}
}