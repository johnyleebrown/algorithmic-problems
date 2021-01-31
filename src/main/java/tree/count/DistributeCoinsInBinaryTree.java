package tree.count;

import commons.TreeNode;

/**
 * 979
 *
 * ======
 *
 * Task.
 *
 * Given the root of a binary tree with N nodes, each node in the tree has
 * node.val coins, and there are N coins total.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node
 * to another.  (The move may be from parent to child, or from child to
 * parent.)
 *
 * Return the number of moves required to make every node have exactly one
 * coin.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DistributeCoinsInBinaryTree {

    /**
     * Traversal and count. Let's say at each node we are asking a question how
     * many coins we need to send to the children nodes. If i need to send +2
     * coins for example, i need to send a coins with nom (1) 2 times, hence
     * res+=sum. If i the sum is -2 coins, it means that we can get 2 coins from
     * the children, it means to transfer those i still need to go 2 times, so
     * res+=abs(sum).
     */
    private static class Solution {
        private int res;

        public int distributeCoins(TreeNode root) {
            res = 0;
            helper(root);
            return res;
        }

        private int helper(TreeNode cur) {
            if (cur == null)
                return 0;

            int l = helper(cur.left);
            int r = helper(cur.right);

            // either we need to deliver sum of coins to the
            // children nodes, or get form there, the path length is the same
            res += Math.abs(l) + Math.abs(r);

            // these are the same, but clearer to see the reasoning here
            if (cur.val > 1)
                return l + r - (cur.val - 1);
            else
                return l + r + 1;
        }
    }
}
