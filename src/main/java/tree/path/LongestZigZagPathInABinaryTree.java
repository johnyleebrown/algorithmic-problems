package tree.path;

import _commons.TreeNode;
import util.tester.Tester;

/**
 * 1372
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree root, a ZigZag path for a binary tree is defined as
 * follow:
 *
 * Choose any node in the binary tree and a direction (right or left). If the
 * current direction is right then move to the right child of the current node
 * otherwise move to the left child. Change the direction from right to left or
 * right to left. Repeat the second and third step until you can't move in the
 * tree.
 *
 * Zigzag length is defined as the number of nodes visited - 1. (A single node
 * has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestZigZagPathInABinaryTree
{
	/**
	 * SF.
	 */
	public static class Solution
	{
		private int max = 0;

		public int longestZigZag(TreeNode root)
		{
			traverse(root.left, 0, true);
			traverse(root.right, 0, false);
			return max;
		}

		private void traverse(TreeNode cur, int x, boolean left)
		{
			if (cur == null)
			{
				max = Math.max(x, max);
				return;
			}

			if (left) // left node for root
			{
				traverse(cur.right, x + 1, !left);
				traverse(cur.left, 0, left);
			}
			else // right node for root
			{
				traverse(cur.left, x + 1, !left);
				traverse(cur.right, 0, left);
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new TreeNode("1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1")).expect(3)
				.add(new TreeNode("1")).expect(0)
				.add(new TreeNode("1,null,1")).expect(1)
				.add(new TreeNode("1,1,1,null,1,null,null,1,1,null,1")).expect(4)
				.run();
	}
}
