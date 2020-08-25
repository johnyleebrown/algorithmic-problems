package tree.construct;

import util.ds.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal
{
	/**
	 * Optimized beating 96% Time complexity: O(n) Space complexity: O(n)
	 */
	class Solution2
	{
		public TreeNode buildTree(int[] preorder, int[] inorder)
		{
			if (preorder.length != inorder.length || preorder.length == 0)
				return null;
			return helper(0, getMap(inorder), preorder, 0, preorder.length - 1);
		}

		private TreeNode helper(int pre_ind, Map<Integer, Integer> map_in, int[] pre, int lo, int hi)
		{
			int root = pre[pre_ind];
			int root_ind = map_in.get(root);
			TreeNode r = new TreeNode(root);

			if (root_ind - 1 >= lo)
			{
				int ind = pre_ind + 1;
				r.left = helper(ind, map_in, pre, lo, root_ind - 1);
			}

			if (root_ind + 1 <= hi)
			{
				int ind = pre_ind + (root_ind - lo) + 1; //plus length of left subtree
				r.right = helper(ind, map_in, pre, root_ind + 1, hi);
			}

			return r;
		}

		private Map<Integer, Integer> getMap(int[] a)
		{
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < a.length; i++) map.put(a[i], i);
			return map;
		}
	}
}