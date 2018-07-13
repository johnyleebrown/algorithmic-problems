package Easy.Tree;

import java.util.LinkedList;
import java.util.Queue;

import Helpers.TreeNode;

/**
 * 101
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 */
public class SymmetricTree {

    // Recursive
    // Total run time is O(n), where n is the total number of nodes in the tree
    // Space complexity: O(n)
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric2(root.left, root.right);
    }

    public boolean isSymmetric2(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        return left != null && right != null
                && left.val == right.val
                && isSymmetric2(left.left, right.right)
                && isSymmetric2(left.right, right.left);
    }

    // Non-Recursive
    // Space complexity: O(n)
    // Time complexity : O(n)
    public boolean isSymmetricNonRecursive(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

}
