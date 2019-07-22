package Medium.Tree;

import util.TreeNode;

/**
 * 236
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 */
public class LowestCommonAncestorOfABinaryTree {
    /**
     * If the current (sub)tree contains both p and q, then the function result is their LCA.
     * If only one of them is in that subtree, then the result is that one of them. If neither
     * are in that subtree, the result is null
     *
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
