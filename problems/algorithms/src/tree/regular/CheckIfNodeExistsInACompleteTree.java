package tree.regular;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Check if node index exists in complete binary tree.
 *
 * google phone interview.
 */
public class CheckIfNodeExistsInACompleteTree
{
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
}

