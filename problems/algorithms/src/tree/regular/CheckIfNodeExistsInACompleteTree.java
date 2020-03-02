package tree.regular;

import util.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Google_Interview_6
 *
 * =====
 *
 * Task.
 *
 * Check if node index exists in complete binary tree.
 */
public class CheckIfNodeExistsInACompleteTree
{
	/**
	 * A complete binary tree is a binary tree in which every level, except
	 * possibly the last, is completely filled, and all nodes are as far left as
	 * possible.
	 */
	class Solution
	{
		public boolean ifExists(TreeNode root, int target)
		{
			if (root == null || target < 0) return false;

			Deque<Integer> pathFromTarget = new ArrayDeque<>();
			while (target > 1)
			{
				pathFromTarget.push(target);
				target /= 2;
			}

			while (!pathFromTarget.isEmpty())
			{
				int val = pathFromTarget.pop();
				if (root.left != null && root.left.val == val)
				{
					root = root.left;
				}
				else if (root.right != null && root.right.val == val)
				{
					root = root.right;
				}
				else
				{
					return false;
				}
			}

			return true;
		}
	}

	class S
	{
		public boolean s(int i)
		{
			return false;
		}
	}
}