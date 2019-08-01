package Medium.Tree;

import java.util.HashMap;
import java.util.Map;

import util.TreeNode;

/**
 * 105
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * You may assume that duplicates do not exist in the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal 
{
	/*
	preorder = [3,9,20,15,7]
	inorder = [9,3,15,20,7]

	similar idea here like in post and inorder
	the first from the inorder i,j is the root of the i,j subtree
	*/
	class Solution0 
	{
		private Map<Integer, Integer> pre = new HashMap<>();
		private Map<Integer, Integer> in = new HashMap<>();
		
		public TreeNode buildTree(int[] preorder, int[] inorder) 
		{
			// edges
			if (preorder == null || preorder.length == 0 || inorder == null 
				|| inorder.length == 0 || preorder.length != inorder.length) return null;
			
			for (int i = 0; i < preorder.length; i++) pre.put(preorder[i], i);
			for (int i = 0; i < inorder.length; i++) in.put(inorder[i], i);
			
			return buildTreeChildByChildStrategy(0, preorder.length - 1, preorder, inorder);
		}
		
		private TreeNode buildTreeChildByChildStrategy(int i, int j, int[] preorder, int[] inorder)
		{
			if (i > j) return null;
			
			int rootIndPre = findRootIndPre(i, j, inorder);
			int rootVal = preorder[rootIndPre];
			int rootInd = in.get(rootVal);
			
			TreeNode root = new TreeNode(rootVal);
			root.left = buildTreeChildByChildStrategy(i, rootInd - 1, preorder, inorder);
			root.right = buildTreeChildByChildStrategy(rootInd + 1, j, preorder, inorder);
			
			return root;
		}
		
		private int findRootIndPre(int i, int j, int[] inorder)
		{
			int min = pre.get(inorder[i]);
			for (int k = i; k <= j; k++)
			{
            min = Math.min(min, pre.get(inorder[k]));
        }
			
			return min;
		}
	}


	/**
     * Time complexity: O(n)
     * Space complexity: O(n)
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

    /**
     * Optimized beating 96%
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length != inorder.length || preorder.length == 0) return null;
            return helper(0, getMap(inorder), preorder, 0, preorder.length - 1);
        }

        TreeNode helper(int pre_ind, Map<Integer, Integer> map_in, int[] pre, int lo, int hi) {
            int root = pre[pre_ind];
            int root_ind = map_in.get(root);
            TreeNode r = new TreeNode(root);

            if (root_ind - 1 >= lo) {
                int ind = pre_ind + 1;
                r.left = helper(ind, map_in, pre, lo, root_ind - 1);
            }
            if (root_ind + 1 <= hi) {
                int ind = pre_ind + (root_ind - lo) + 1; //plus length of left subtree
                r.right = helper(ind, map_in, pre, root_ind + 1, hi);
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
