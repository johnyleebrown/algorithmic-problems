package tree.traverse.other;

import commons.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 545
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree, return the values of its boundary in anti-clockwise
 * direction starting from root. Boundary includes left boundary, leaves, and
 * right boundary in order without duplicate nodes.  (The values of the nodes
 * may still be duplicates.)
 *
 * Left boundary is defined as the path from root to the left-most node. Right
 * boundary is defined as the path from root to the right-most node. If the root
 * doesn't have left subtree or right subtree, then the root itself is left
 * boundary or right boundary. Note this definition only applies to the input
 * binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always
 * firstly travel to the left subtree if exists. If not, travel to the right
 * subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right
 * exchanged.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BoundaryOfBinaryTree {
    /**
     * Followed instructions from the task.
     */
    public static class Solution {
        List<Integer> ans;
        Set<TreeNode> set;

        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            ans = new LinkedList<>();
            if (root == null) return ans;
            set = new HashSet<>();
            ans.add(root.val);
            set.add(root);
            if (root.left != null) {
                h1(root.left);
            }
            h2(root);
            if (root.right != null) {
                h3(root.right);
            }
            return ans;
        }

        // left boundary
        void h1(TreeNode cur) {
            if (cur == null) return;
            if (set.add(cur)) ans.add(cur.val);
            h1(cur.left);
            if (cur.left == null) h1(cur.right);
        }

        // leaves
        void h2(TreeNode cur) {
            if (cur == null) return;
            if (cur.left == null && cur.right == null && set.add(cur))
                ans.add(cur.val);
            h2(cur.left);
            h2(cur.right);
        }

        // right boundary
        void h3(TreeNode cur) {
            if (cur == null) return;
            h3(cur.right);
            if (cur.right == null) h3(cur.left);
            if (set.add(cur)) ans.add(cur.val);
        }
    }
}