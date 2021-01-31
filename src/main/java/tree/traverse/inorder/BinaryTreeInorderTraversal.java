package tree.traverse.inorder;

import commons.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94
 */
public class BinaryTreeInorderTraversal {
    /**
     * Iterative
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;

            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }

            return res;
        }
    }

    /**
     * Recursive
     */
    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res) {
            if (root != null) {
                if (root.left != null) {
                    helper(root.left, res);
                }

                res.add(root.val);

                if (root.right != null) {
                    helper(root.right, res);
                }
            }
        }
    }
}