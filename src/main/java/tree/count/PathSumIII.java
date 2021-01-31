package tree.count;

import commons.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437
 */
public class PathSumIII {
	class Solution {
		public int pathSum(TreeNode root, int sum) {
			if (root == null) return 0;
			Map<Integer, Integer> map = new HashMap<>();
			map.put(0, 1);
			return findPathSum(root, 0, sum, map);
		}

		private int findPathSum(TreeNode curr, int sum, int target, Map<Integer, Integer> map) {
			if (curr == null) return 0;
			sum += curr.val;
			int numPathToCurr = map.getOrDefault(sum - target, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
			int res = numPathToCurr + findPathSum(curr.left, sum, target, map) + findPathSum(curr.right, sum, target, map);
			map.put(sum, map.get(sum) - 1);
			return res;
		}
	}

	// Time: O(n^2)
	// O(n)
	public class Solution2 {
		public int pathSum(TreeNode root, int sum) {
			if (root == null) return 0;
			return p(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
		}

		private int p(TreeNode node, int sum) {
			if (node == null) return 0;
			return (node.val == sum ? 1 : 0) + p(node.left, sum - node.val) + p(node.right, sum - node.val);
		}
	}
}
