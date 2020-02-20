package tree.regular;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import Helpers.Helper;
import util.TreeNode;

// 222
public class CountCompleteTreeNodes 
{
	class Solution1 
	{
		public int countNodes(TreeNode root) 
		{
			if (root == null) return 0;
			return 1 + countNodes(root.left) + countNodes(root.right);
		}
	}

	/*
	 * The height of the tree is h = log2(n), n is the number of nodes.
	 * Then n = 2^h - 1. x << y == x * 2^y.
	 */
	class Solution2
	{
		public int countNodes(TreeNode root) 
		{
			int leftHeight = calculateHeightOfLeftSubtree(root);
			int rightHeight = calculateHeightOfRightSubtree(root);

			if (leftHeight == rightHeight)
			{
				return (1 << leftHeight) - 1;
			}
			else
			{
				return 1 + countNodes(root.left) + countNodes(root.right);
			}
		}	

		private int calculateHeightOfLeftSubtree(TreeNode root)
		{
			int h = 0;
			while (root != null) 
			{
				root = root.left;
				h++;
			}
			return h;
		}

		private int calculateHeightOfRightSubtree(TreeNode root)
		{
			int h = 0;
			while (root != null) 
			{
				root = root.right;
				h++;
			}
			return h;
		}
	}
}

