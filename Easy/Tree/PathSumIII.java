package Easy.Tree;

import java.util.HashMap;
import java.util.Map;

import Helpers.TreeNode;

/**
 * 437
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
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
