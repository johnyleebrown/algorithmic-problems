package tree.path;

import util.ds.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1379
 *
 * ======
 *
 * Task.
 *
 * Given two binary trees original and cloned and given a reference to a node
 * target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target
 * node and the answer must be a reference to a node in the cloned tree.
 *
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 *
 * ======
 *
 * Source: Leetcode
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree
{
	/**
	 * Record path, then follow the path traversing the copy tree.
	 */
	public static class Solution
	{
		private List<Integer> path = new ArrayList<>();

		public final TreeNode getTargetCopy(final TreeNode original, final TreeNode copy, final TreeNode target)
		{
			recordPath(original, target);
			return findNodeInCopy(copy);
		}

		private TreeNode findNodeInCopy(final TreeNode copy)
		{
			TreeNode cur = copy;
			for (int step : path)
			{
				if (step == 1)
					cur = cur.left;
				else
					cur = cur.right;
			}
			return cur;
		}

		private boolean recordPath(TreeNode cur, TreeNode target)
		{
			if (cur == null)
				return false;

			if (cur == target)
				return true;

			boolean l = recordPath(cur.left, target);
			boolean r = recordPath(cur.right, target);

			if (l)
				path.add(0, 1);
			else if (r)
				path.add(0, 2);

			return l || r;
		}

		public Solution()
		{
		}
	}
}
