package tree.regular;

import util.TreeNode;

import java.util.TreeSet;

/**
 * 530
 * Given a binary search tree with non-negative values,
 * find the minimum absolute difference between values of any two nodes.
 */
public class MinimumAbsoluteDifferenceInBST {

    // O(n)
    public class Solution {
        int minDiff = Integer.MAX_VALUE;
        TreeNode prev;

        public int getMinimumDifference(TreeNode root) {
            inorder(root);
            return minDiff;
        }

        public void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            if (prev != null) minDiff = Math.min(minDiff, root.val - prev.val);
            prev = root;
            inorder(root.right);
        }
    }

    // What if it is not a BST
    // time complexity O(NlgN), space complexity O(N)
    class Solution2 {
        TreeSet<Integer> set = new TreeSet<>();
        int min = Integer.MAX_VALUE;

        public int getMinimumDifference(TreeNode root) {
            if (root == null) return min;
            if (!set.isEmpty()) {
                if (set.floor(root.val) != null)
                    min = Math.min(min, root.val - set.floor(root.val));
                if (set.ceiling(root.val) != null)
                    min = Math.min(min, set.ceiling(root.val) - root.val);
            }
            set.add(root.val);
            getMinimumDifference(root.left);
            getMinimumDifference(root.right);
            return min;
        }
    }
}
