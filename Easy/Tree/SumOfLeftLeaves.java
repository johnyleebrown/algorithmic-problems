package Easy.Tree;

import java.util.Stack;

import Helpers.TreeNode;

/**
 * 404
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if(root == null) return 0;
            int ans = 0;
            if(root.left != null) {
                if(root.left.left == null && root.left.right == null) ans += root.left.val;
                else ans += sumOfLeftLeaves(root.left);
            }
            ans += sumOfLeftLeaves(root.right);
            return ans;
        }
    }

    class Solution2{
        public int sumOfLeftLeaves(TreeNode root) {
            if(root == null) return 0;
            int ans = 0;
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.empty()) {
                TreeNode node = stack.pop();
                if(node.left != null) {
                    if (node.left.left == null && node.left.right == null) ans += node.left.val;
                    else stack.push(node.left);
                }
                if(node.right != null) {
                    if (node.right.left != null || node.right.right != null)
                        stack.push(node.right);
                }
            }
            return ans;
        }
    }
}
