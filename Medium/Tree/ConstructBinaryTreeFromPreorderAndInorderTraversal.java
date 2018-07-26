package Medium.Tree;

import java.util.HashMap;
import java.util.Map;

import Helpers.TreeNode;

/**
 * 105
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length != inorder.length || preorder.length == 0) return null;
            return helper(preorder[0], getMap(preorder), getMap(inorder), preorder, 0, preorder.length - 1);
        }

        TreeNode helper(int root, Map<Integer, Integer> map_pre, Map<Integer, Integer> map_in, int[] pre, int lo, int hi) {
            int root_ind = map_in.get(root);
            TreeNode r = new TreeNode(root);
            if (root_ind - 1 >= lo) {
                int next_root_left = pre[map_pre.get(root) + 1];
                r.left = helper(next_root_left, map_pre, map_in, pre, lo, root_ind - 1);
            }
            if (root_ind + 1 <= hi) {
                int next_root_right = pre[map_pre.get(root) + (root_ind - lo) + 1]; //plus length of left subtree
                r.right = helper(next_root_right, map_pre, map_in, pre, root_ind + 1, hi);
            }
            return r;
        }

        Map<Integer, Integer> getMap(int[] ar) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < ar.length; i++) {
                map.put(ar[i], i);
            }
            return map;
        }
    }
/*
[1,2]
[1,2]
[3,9,20,15,7]
[9,3,15,20,7]
*/
}
