package Medium.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Helpers.TreeNode;

/**
 * 144
 * Given a binary tree, return the preorder traversal of its nodes' values.
 */
public class BinaryTreePreorderTraversal {

    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            Stack<TreeNode> st = new Stack<>();
            TreeNode x = root;
            while (x != null || !st.isEmpty()) {
                while (x != null) {
                    st.push(x.right);
                    list.add(x.val);
                    x = x.left;
                }
                x = st.pop();
            }
            return list;
        }
    }

    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            if (root == null) return list;
            Stack<TreeNode> st = new Stack<>();
            st.push(root);
            while (!st.isEmpty()) {
                TreeNode x = st.pop();
                list.add(x.val);
                if (x.right != null) st.push(x.right);
                if (x.left != null) st.push(x.left);
            }
            return list;
        }
    }
}
