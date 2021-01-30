package tree.bst;

import _commons.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 333
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree
 * (BST), where largest means subtree with largest number of nodes in it.
 *
 * Note:
 * A subtree must include all of its descendants.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LargestBSTSubtree {
    /**
     * O(n^2) TODO redo
     */
    public static class Solution {
        int res;
        Map<TreeNode, Boolean> m2;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) return 0;
            m2 = new HashMap<>();
            res = 1;
            isBst2(root);
            return res;
        }

        int isBst2(TreeNode cur) {
            if (cur == null) return 0;
            int l = isBst2(cur.left);
            int r = isBst2(cur.right);
            int x = l + r + 1;
            boolean ans = isBst(cur);
            if (ans) res = Math.max(res, x);
            return x;
        }

        boolean isBst(TreeNode cur) {
            if (m2.containsKey(cur)) return m2.get(cur);
            boolean ans = dfs(cur, null, null);
            m2.put(cur, ans);
            return ans;
        }

        boolean dfs(TreeNode cur, Integer min, Integer max) {
            if (cur == null) return true;
            return (min == null || cur.val > min) && (max == null || cur.val < max) && dfs(cur.left, min, cur.val) && dfs(cur.right, cur.val, max);
        }
    }
}