package Medium.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.TreeNode;

/**
 * 94
 * Given a binary tree, return the inorder traversal of its nodes' values.
 */
public class BinaryTreeInorderTraversal {

    /**
     * Iterative solution, for similar algorithm:
     * @see KthSmallestElementInABST.Solution4
     * @see ValidateBinarySearchTree.Solution2
     */
    // O(n), O(n)
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
}
