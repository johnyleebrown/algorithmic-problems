package Medium.Tree;

import Helpers.TreeNode;

/**
 * 654
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 */
public class MaximumBinaryTree {

    // O(n^2)
    // O(n)
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return helper(nums, 0, nums.length);
        }

        private TreeNode helper(int[] nums, int l, int r) {
            if (l == r) return null;
            int max = l;
            for (int i = l + 1; i < r; i++) if (nums[max] < nums[i]) max = i;
            TreeNode root = new TreeNode(nums[max]);
            root.left = helper(nums, l, max);
            root.right = helper(nums, max + 1, r);
            return root;
        }
    }
}
