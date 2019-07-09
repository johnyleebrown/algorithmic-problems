package Medium.Tree;

import java.util.Stack;

import util.TreeNode;

/**
 * 230
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 */
public class KthSmallestElementInABST {
    // O(n), O(n)
    class Solution {
        public int kthSmallest(TreeNode root, int k) {
            int count = countNodes(root.left);
            if (k <= count) return kthSmallest(root.left, k);
            else if (k > count + 1) return kthSmallest(root.right, k - 1 - count);
            return root.val;
        }

        private int countNodes(TreeNode root) {
            if (root == null) return 0;
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    // DFS in-order recursive
    class Solution2{
        // better keep these two variables in a wrapper class
        private int number = 0;
        private int count = 0;

        public int kthSmallest(TreeNode root, int k) {
            count = k;
            helper(root);
            return number;
        }

        public void helper(TreeNode n) {
            if (n.left != null) helper(n.left);
            count--;
            if (count == 0) {
                number = n.val;
                return;
            }
            if (n.right != null) helper(n.right);
        }
    }
    // DFS in-order iterative:
    class Solution3 {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> st = new Stack<>();

            while (root != null) {
                st.push(root);
                root = root.left;
            }

            while (k != 0) {
                TreeNode n = st.pop();
                k--;
                if (k == 0) return n.val;
                TreeNode right = n.right;
                while (right != null) {
                    st.push(right);
                    right = right.left;
                }
            }

            return -1; // never hit if k is valid
        }
    }

    // iterative
    class Solution4 {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            while(root != null || !stack.isEmpty()) {
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if(--k == 0) break;
                root = root.right;
            }
            return root.val;
        }
    }
}
