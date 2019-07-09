package medium.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import util.TreeNode;

/**
 * 863
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
 */
public class AllNodesDistanceKInBinaryTree {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution1 {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            if (root == null || target == null) return new LinkedList<>();
            HashMap<Integer, List<Integer>> g = createGraph(root, new HashMap<>());
            if (K > g.size()) return new LinkedList<>();
            if (K == 0) return Arrays.asList(target.val);
            boolean[] marked = new boolean[g.size()];
            return dfs(g, target.val, K, new LinkedList<>(), marked);
        }

        List<Integer> dfs(HashMap<Integer, List<Integer>> g, int target, int K, List<Integer> res, boolean[] marked) {
            marked[target] = true;
            for (int i : g.get(target)) {
                if (!marked[i]) {
                    if (K - 1 == 0) res.add(i);
                    else res = dfs(g, i, K - 1, res, marked);
                }
            }
            return res;
        }

        HashMap<Integer, List<Integer>> createGraph(TreeNode root, HashMap<Integer, List<Integer>> map) {
            map.putIfAbsent(root.val, new LinkedList<>());
            if (root.left != null) {
                map.get(root.val).add(root.left.val);
                map.putIfAbsent(root.left.val, new LinkedList<>());
                map.get(root.left.val).add(root.val);
            }
            if (root.right != null) {
                map.get(root.val).add(root.right.val);
                map.putIfAbsent(root.right.val, new LinkedList<>());
                map.get(root.right.val).add(root.val);
            }
            if (root.left != null) createGraph(root.left, map);
            if (root.right != null) createGraph(root.right, map);
            return map;
        }
    }
}
