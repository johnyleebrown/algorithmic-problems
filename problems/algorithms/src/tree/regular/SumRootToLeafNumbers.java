package tree.regular;

import util.TreeNode;

/**
 * 129
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 */
public class SumRootToLeafNumbers {
    /**
     * Time complexity: O(nlogn)
     * Space complexity: O(1)
     */
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s) {
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s * 10 + n.val;
        return sum(n.left, s * 10 + n.val) + sum(n.right, s * 10 + n.val);
    }
}
