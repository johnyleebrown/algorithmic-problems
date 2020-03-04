package tree.regular;

import util.ds.TreeNode;
import util.test.Tester;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Google_Interview_6
 *
 * =====
 *
 * Task.
 *
 * Check if node index exists in complete binary tree. Given a complete
 * (virtual) binary tree, return true/false if the given target node exists in
 * the tree or not. Here, the virtual means the tree nodes are numbered assuming
 * the tree is a complete binary tree.
 *
 * https://leetcode.com/discuss/interview-question/236898/
 */
public class CheckIfNodeExistsInACompleteTree
{
	/**
	 * A complete binary tree is a binary tree in which every level, except
	 * possibly the last, is completely filled, and all nodes are as far left as
	 * possible.
	 */
	private static class Solution
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

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new TreeNode("1,2,3,4,null,6,null"), 4).expect(true)
				.add(new TreeNode("1,2,3,4,null,6,null"), 7).expect(false)
				.run();
	}
}