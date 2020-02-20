package tree.construct;

import util.ds.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal
{
	class Solution
	{
		private Map<Integer, Integer> inorderMap = new HashMap<>();
		private int[] inorderArray;
		private int[] postorderArray;

		public TreeNode buildTree(int[] inorder, int[] postorder)
		{
			if (inorder.length != postorder.length || inorder.length == 0)
				return null;
			init(inorder, postorder);
			return createTree(inorder.length - 1, 0, inorder.length - 1);
		}

		private TreeNode createTree(int postorderRootIndex, int lo, int hi)
		{
			if (lo < 0 || lo > hi) return null;

			int rootValue = postorderArray[postorderRootIndex];
			int inorderRootIndex = inorderMap.get(rootValue);

			TreeNode root = new TreeNode(rootValue);

			root.left = createTree(postorderRootIndex - 1 - (hi - inorderRootIndex),
					lo, inorderRootIndex - 1);

			root.right = createTree(postorderRootIndex - 1,
					inorderRootIndex + 1, hi);

			return root;
		}

		private void init(int[] inorder, int[] postorder)
		{
			this.inorderArray = inorder;
			this.postorderArray = postorder;

			fillInorderMap();
		}

		private void fillInorderMap()
		{
			for (int i = 0; i < inorderArray.length; i++)
				inorderMap.put(inorderArray[i], i);
		}
	}
}

