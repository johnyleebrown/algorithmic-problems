package Easy.Tree;

import Helpers.TreeNode;

/**
 * 108
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedArrayToBinarySearchTree {

    // O(n) ; O(n)
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return makeTree(nums, 0, nums.length - 1);
        }

        private TreeNode makeTree(int[] a, int lo, int hi) {
            if (hi < lo) return null;
            int mid = lo + (hi - lo) / 2;
            TreeNode node = new TreeNode(a[mid]);
            node.left = makeTree(a, lo, mid - 1);
            node.right = makeTree(a, mid + 1, hi);
            return node;
        }
    }
}
